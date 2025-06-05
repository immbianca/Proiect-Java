package proiect.java.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.java.entity.Film;
import proiect.java.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    //Gasire toate filmele
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    //Gaseste un film dupa ID
    public Optional<Film> findById(String idFilm) {
        return filmRepository.findById(idFilm);
    }

    //Salveaza un film nou sau actualizeaza unul existent
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    //Sterge un film dupa ID
    public void deleteFilm(String idFilm) {
        filmRepository.deleteById(idFilm);
    }

    //Verifica daca un film exista
    public boolean existByID(String idFilm) {
        return filmRepository.existsById(idFilm);
    }

    //Filtrare dimamica bazata pe parametrii dati
    public List<Film> findByFilters(String titlu, String gen, String regizor) {
        //Convertire stringuri goale in null pentru interogare Jpa
        String titluParam= (titlu!=null && titlu.trim().isEmpty())?null:titlu;
        String genParam= (gen!=null && gen.trim().isEmpty())?null:gen;
        String regizorParam = (regizor!=null && regizor.trim().isEmpty())?null:regizor;

        return filmRepository.findByFilters(titluParam,genParam,regizorParam);
    }

}
