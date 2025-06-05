package proiect.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proiect.java.entity.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {

    // Filtrare dinamică bazată pe titlu, gen și regizor
    @Query("SELECT f FROM Film f WHERE " +
            "(:titlu IS NULL OR :titlu = '' OR LOWER(f.titlu) LIKE LOWER(CONCAT('%', :titlu, '%'))) AND " +
            "(:gen IS NULL OR :gen = '' OR LOWER(f.gen) LIKE LOWER(CONCAT('%', :gen, '%'))) AND " +
            "(:regizor IS NULL OR :regizor = '' OR LOWER(f.regizor) LIKE LOWER(CONCAT('%', :regizor, '%')))")
    List<Film> findByFilters(@Param("titlu") String titlu,
                             @Param("gen") String gen,
                             @Param("regizor") String regizor);

    // Găsește filme după gen
    List<Film> findByGenContainingIgnoreCase(String gen);

    // Găsește filme după regizor
    List<Film> findByRegizorContainingIgnoreCase(String regizor);

    // Găsește filme după titlu
    List<Film> findByTitluContainingIgnoreCase(String titlu);

    // Găsește filme după anul lansării
    List<Film> findByAnulLansarii(Integer anul);

    // Găsește filme dintr-un interval de ani
    List<Film> findByAnulLansariiBetween(Integer anInceput, Integer anSfarsit);

    // Găsește filme cu rating IMDB mai mare decât o valoare
    List<Film> findByRatingImdbGreaterThanEqual(Double rating);

    // Găsește filme după țara de origine
    List<Film> findByTaraOrigineContainingIgnoreCase(String tara);

    // Găsește filmele adăugate de un utilizator specific
    List<Film> findByIdUtilizator(Long idUtilizator);

    // Verifică dacă există un film cu ID-ul specificat
    boolean existsByIdFilm(String idFilm);
}