package proiect.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.java.entity.Film;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    Optional<Film> findById(String idFilm);

    void deleteById(String idFilm);

    boolean existsById(String idFilm);

    List<Film> findByFilters(String titluParam, String genParam, String regizorParam);
}
