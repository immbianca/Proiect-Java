package proiect.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proiect.java.entity.Film;
import proiect.java.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    // Găsește toate filmele
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    // Găsește un film după ID
    public Optional<Film> findById(String idFilm) {
        return filmRepository.findById(idFilm);
    }

    // Salvează un film nou sau actualizează unul existent
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    // Șterge un film după ID
    public void deleteById(String idFilm) {
        filmRepository.deleteById(idFilm);
    }

    // Verifică dacă un film există
    public boolean existsById(String idFilm) {
        return filmRepository.existsById(idFilm);
    }

    // Filtrare dinamică bazată pe parametrii dați
    public List<Film> findByFilters(String titlu, String gen, String regizor) {
        // Convertește stringurile goale în null pentru interogarea JPA
        String titluParam = (titlu != null && titlu.trim().isEmpty()) ? null : titlu;
        String genParam = (gen != null && gen.trim().isEmpty()) ? null : gen;
        String regizorParam = (regizor != null && regizor.trim().isEmpty()) ? null : regizor;

        return filmRepository.findByFilters(titluParam, genParam, regizorParam);
    }

    // Găsește filme după gen
    public List<Film> findByGen(String gen) {
        return filmRepository.findByGenContainingIgnoreCase(gen);
    }

    // Găsește filme după regizor
    public List<Film> findByRegizor(String regizor) {
        return filmRepository.findByRegizorContainingIgnoreCase(regizor);
    }

    // Găsește filme după titlu
    public List<Film> findByTitlu(String titlu) {
        return filmRepository.findByTitluContainingIgnoreCase(titlu);
    }

    // Găsește filme după anul lansării
    public List<Film> findByAn(Integer anul) {
        return filmRepository.findByAnulLansarii(anul);
    }

    // Găsește filme dintr-un interval de ani
    public List<Film> findByIntervalAni(Integer anInceput, Integer anSfarsit) {
        return filmRepository.findByAnulLansariiBetween(anInceput, anSfarsit);
    }

    // Găsește filme cu rating IMDB mai mare sau egal cu valoarea dată
    public List<Film> findByRatingMinim(Double rating) {
        return filmRepository.findByRatingImdbGreaterThanEqual(rating);
    }

    // Găsește filme după țara de origine
    public List<Film> findByTara(String tara) {
        return filmRepository.findByTaraOrigineContainingIgnoreCase(tara);
    }

    // Găsește filmele adăugate de un utilizator specific
    public List<Film> findByUtilizator(Long idUtilizator) {
        return filmRepository.findByIdUtilizator(idUtilizator);
    }

    // Actualizează un film existent
    public Film updateFilm(String idFilm, Film filmActualizat) {
        Optional<Film> filmExistent = filmRepository.findById(idFilm);

        if (filmExistent.isPresent()) {
            Film film = filmExistent.get();

            // Actualizează doar câmpurile care nu sunt null sau goale
            if (filmActualizat.getTitlu() != null && !filmActualizat.getTitlu().trim().isEmpty()) {
                film.setTitlu(filmActualizat.getTitlu());
            }
            if (filmActualizat.getRegizor() != null && !filmActualizat.getRegizor().trim().isEmpty()) {
                film.setRegizor(filmActualizat.getRegizor());
            }
            if (filmActualizat.getGen() != null && !filmActualizat.getGen().trim().isEmpty()) {
                film.setGen(filmActualizat.getGen());
            }
            if (filmActualizat.getAnulLansarii() != null) {
                film.setAnulLansarii(filmActualizat.getAnulLansarii());
            }
            if (filmActualizat.getDurataMinute() != null) {
                film.setDurataMinute(filmActualizat.getDurataMinute());
            }
            if (filmActualizat.getTaraOrigine() != null && !filmActualizat.getTaraOrigine().trim().isEmpty()) {
                film.setTaraOrigine(filmActualizat.getTaraOrigine());
            }
            if (filmActualizat.getRating() != null && !filmActualizat.getRating().trim().isEmpty()) {
                film.setRating(filmActualizat.getRating());
            }
            if (filmActualizat.getRatingImdb() != null) {
                film.setRatingImdb(filmActualizat.getRatingImdb());
            }
            if (filmActualizat.getPret() != null) {
                film.setPret(filmActualizat.getPret());
            }

            return filmRepository.save(film);
        }

        return null;
    }
}