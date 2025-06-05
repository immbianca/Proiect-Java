package proiect.java.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proiect.java.entity.Film;
import proiect.java.entity.Utilizator;
import proiect.java.service.FilmService;
import proiect.java.service.UtilizatorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/filme")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private UtilizatorService utilizatorService;

    // Pagina principală cu listarea filmelor
    @GetMapping
    public String listeazaFilme(Model model, Authentication authentication) {
        // Adaugă numele utilizatorului conectat
        String numeUtilizator = authentication.getName();
        Optional<Utilizator> utilizator = utilizatorService.findByUtilizator(numeUtilizator);
        if (utilizator.isPresent()) {
            model.addAttribute("numeUtilizator", utilizator.get().getNume());
        }

        // Verifică rolul utilizatorului
        boolean esteEditor = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EDITOR"));
        model.addAttribute("esteEditor", esteEditor);

        // Listează toate filmele
        List<Film> filme = filmService.findAll();
        model.addAttribute("filme", filme);

        // Adaugă un obiect Film gol pentru formulare
        model.addAttribute("filmNou", new Film());
        model.addAttribute("filmFiltru", new Film());

        return "filme/lista";
    }

    // Filtrarea filmelor
    @PostMapping("/filtreaza")
    public String filtreazaFilme(@RequestParam(required = false) String titlu,
                                 @RequestParam(required = false) String gen,
                                 @RequestParam(required = false) String regizor,
                                 Model model, Authentication authentication) {

        // Adaugă numele utilizatorului conectat
        String numeUtilizator = authentication.getName();
        Optional<Utilizator> utilizator = utilizatorService.findByUtilizator(numeUtilizator);
        if (utilizator.isPresent()) {
            model.addAttribute("numeUtilizator", utilizator.get().getNume());
        }

        // Verifică rolul utilizatorului
        boolean esteEditor = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EDITOR"));
        model.addAttribute("esteEditor", esteEditor);

        // Filtrează filmele
        List<Film> filme = filmService.findByFilters(titlu, gen, regizor);
        model.addAttribute("filme", filme);

        // Păstrează valorile filtrelor în formulare
        Film filmFiltru = new Film();
        filmFiltru.setTitlu(titlu);
        filmFiltru.setGen(gen);
        filmFiltru.setRegizor(regizor);
        model.addAttribute("filmFiltru", filmFiltru);

        // Adaugă un obiect Film gol pentru formularul de adăugare
        model.addAttribute("filmNou", new Film());

        return "filme/lista";
    }

    // Adăugarea unui film nou (doar pentru ROLE_EDITOR)
    @PostMapping("/adauga")
    public String adaugaFilm(@Valid @ModelAttribute("filmNou") Film film,
                             BindingResult bindingResult,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        if (bindingResult.hasErrors()) {
            // Reîncarcă pagina cu erorile de validare
            String numeUtilizator = authentication.getName();
            Optional<Utilizator> utilizator = utilizatorService.findByUtilizator(numeUtilizator);
            if (utilizator.isPresent()) {
                model.addAttribute("numeUtilizator", utilizator.get().getNume());
            }

            boolean esteEditor = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EDITOR"));
            model.addAttribute("esteEditor", esteEditor);

            List<Film> filme = filmService.findAll();
            model.addAttribute("filme", filme);
            model.addAttribute("filmFiltru", new Film());

            return "filme/lista";
        }

        // Verifică dacă filmul există deja
        if (filmService.existsById(film.getIdFilm())) {
            redirectAttributes.addFlashAttribute("error", "Un film cu acest ID există deja!");
            return "redirect:/filme";
        }

        // Setează ID-ul utilizatorului care adaugă filmul
        String numeUtilizator = authentication.getName();
        Optional<Utilizator> utilizator = utilizatorService.findByUtilizator(numeUtilizator);
        if (utilizator.isPresent()) {
            film.setIdUtilizator(utilizator.get().getIdUtilizator());
        }

        filmService.save(film);
        redirectAttributes.addFlashAttribute("success", "Filmul a fost adăugat cu succes!");

        return "redirect:/filme";
    }

    // Modificarea unui film (doar pentru ROLE_EDITOR)
    @PostMapping("/modifica")
    public String modificaFilm(@RequestParam String idFilm,
                               @RequestParam(required = false) String titlu,
                               @RequestParam(required = false) String regizor,
                               @RequestParam(required = false) String gen,
                               @RequestParam(required = false) Integer anulLansarii,
                               @RequestParam(required = false) Integer durataMinute,
                               @RequestParam(required = false) String taraOrigine,
                               @RequestParam(required = false) String rating,
                               @RequestParam(required = false) Double ratingImdb,
                               @RequestParam(required = false) Double pret,
                               RedirectAttributes redirectAttributes) {

        if (!filmService.existsById(idFilm)) {
            redirectAttributes.addFlashAttribute("error", "Filmul cu ID-ul specificat nu există!");
            return "redirect:/filme";
        }

        // Creează un obiect Film cu valorile de actualizat
        Film filmActualizat = new Film();
        filmActualizat.setTitlu(titlu);
        filmActualizat.setRegizor(regizor);
        filmActualizat.setGen(gen);
        filmActualizat.setAnulLansarii(anulLansarii);
        filmActualizat.setDurataMinute(durataMinute);
        filmActualizat.setTaraOrigine(taraOrigine);
        filmActualizat.setRating(rating);
        filmActualizat.setRatingImdb(ratingImdb);
        filmActualizat.setPret(pret);

        Film filmSalvat = filmService.updateFilm(idFilm, filmActualizat);

        if (filmSalvat != null) {
            redirectAttributes.addFlashAttribute("success", "Filmul a fost modificat cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Eroare la modificarea filmului!");
        }

        return "redirect:/filme";
    }

    // Ștergerea unui film (doar pentru ROLE_EDITOR)
    @PostMapping("/sterge")
    public String stergeFilm(@RequestParam String idFilm,
                             RedirectAttributes redirectAttributes) {

        if (!filmService.existsById(idFilm)) {
            redirectAttributes.addFlashAttribute("error", "Filmul cu ID-ul specificat nu există!");
            return "redirect:/filme";
        }

        filmService.deleteById(idFilm);
        redirectAttributes.addFlashAttribute("success", "Filmul a fost șters cu succes!");

        return "redirect:/filme";
    }
}