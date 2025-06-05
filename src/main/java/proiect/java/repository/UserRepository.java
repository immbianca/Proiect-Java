package proiect.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.java.entity.Utilizator;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Utilizator, Long> {

    // Găsește utilizatorul după numele de utilizator (pentru autentificare)
    Optional<Utilizator> findByUtilizator(String utilizator);

    // Verifică dacă există un utilizator cu numele dat
    boolean existsByUtilizator(String utilizator);

    // Găsește utilizatori după rol
    List<Utilizator> findByRol(String rol);

    // Găsește utilizatori după nume (parțial)
    List<Utilizator> findByNumeContainingIgnoreCase(String nume);
}