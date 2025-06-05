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
    public List<Film> findAll() {
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

    //Gaseste filme dupa gen
    public List<Film> findByGen(String gen) {
        return filmRepository.findByGenContainingIgnoreCase(gen);
    }

    //Gaseste filme dupa regizor
    public List<Film> findByRegizor(String regizor) {
        return filmRepository.findByRegizorContainingIgnoreCase(regizor);
    }

    //Gaseste film dupa titlu
    public List<Film> findByTitlu(String titlu) {
        return filmRepository.findByTitluContainingIgnoreCase(titlu);
    }

    //Gaseste film dupa anul lansarii
    public List<Film> findByAn(Integer anul){
        return  filmRepository.findByAnulLansarii(anul);
    }

    //Gaseste filme dintr-un interval de ani
    public List<Film> findByIntervalAni(Integer anInceput, Integer anSfarsit){
        return filmRepository.findByAnulLansariiBetween(anInceput, anSfarsit);
    }

    //Gaseste filme cu rating IMDB mai mare sau egal cu valoarea data
    public List<Film> findByRatingMinim(Double rating){
        return filmRepository.findByRatingImdbGreaterThanEqual(rating);
    }

    //Gaseste filme dupa tara de origine
    public List<Film> findByTara(String tara){
        return filmRepository.findByTaraOrigineContainingIgnoreCase(tara);
    }

    //Gaseste filme adaugate de un utilizator specific
    public List<Film> findByUtilizator(String utilizator){
        return filmRepository.findByIdUtilizatorContainingIgnoreCase(utilizator);
    }

    //Actializeaza un film Existent
    public Film updateFilm(String idFilm, Film filmActualizat) {
        Optional<Film> filmExistent = filmRepository.findById(idFilm);

        if (filmExistent.isPresent()) {
            Film film = filmExistent.get();

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
