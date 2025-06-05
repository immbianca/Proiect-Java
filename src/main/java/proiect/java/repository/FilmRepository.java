package proiect.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.java.entity.Film;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    void deleteById(String idFilm);

    boolean existsById(String idFilm);

    List<Film> findByFilters(String titluParam, String genParam, String regizorParam);

    List<Film> findByGenContainingIgnoreCase(String gen);

    List<Film> findByRegizorContainingIgnoreCase(String regizor);

    List<Film> findByTitluContainingIgnoreCase(String titlu);

    List<Film> findByAnulLansarii(Integer anul);

    List<Film> findByAnulLansariiBetween(Integer anInceput, Integer anSfarsit);

    List<Film> findByRatingImdbGreaterThanEqual(Double rating);

    List<Film> findByTaraOrigineContainingIgnoreCase(String tara);

    List<Film> findByIdUtilizatorContainingIgnoreCase(String utilizator);

    Optional<Film> findById(String idFilm);
}
