package proiect.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proiect.java.entity.Utilizator;
import proiect.java.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SecurityConfig implements UserDetailsService {

    @Autowired
    private UserRepository utilizatorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Implementarea pentru Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilizator> utilizator = utilizatorRepository.findByUtilizator(username);

        if (utilizator.isEmpty()) {
            throw new UsernameNotFoundException("Utilizatorul nu a fost găsit: " + username);
        }

        Utilizator user = utilizator.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRol()));

        return new User(user.getUtilizator(), user.getParola(), authorities);
    }

    // Găsește toate utilizatorii
    public List<Utilizator> findAll() {
        return utilizatorRepository.findAll();
    }

    // Găsește un utilizator după ID
    public Optional<Utilizator> findById(Long id) {
        return utilizatorRepository.findById(id);
    }

    // Găsește un utilizator după numele de utilizator
    public Optional<Utilizator> findByUtilizator(String utilizator) {
        return utilizatorRepository.findByUtilizator(utilizator);
    }

    // Salvează un utilizator nou
    public Utilizator save(Utilizator utilizator) {
        // Criptează parola înainte de salvare
        utilizator.setParola(passwordEncoder.encode(utilizator.getParola()));
        return utilizatorRepository.save(utilizator);
    }

    // Șterge un utilizator după ID
    public void deleteById(Long id) {
        utilizatorRepository.deleteById(id);
    }

    // Verifică dacă un utilizator există
    public boolean existsByUtilizator(String utilizator) {
        return utilizatorRepository.existsByUtilizator(utilizator);
    }

    // Găsește utilizatori după rol
    public List<Utilizator> findByRol(String rol) {
        return utilizatorRepository.findByRol(rol);
    }

    // Găsește utilizatori după nume
    public List<Utilizator> findByNume(String nume) {
        return utilizatorRepository.findByNumeContainingIgnoreCase(nume);
    }

    // Actualizează un utilizator existent
    public Utilizator updateUtilizator(Long id, Utilizator utilizatorActualizat) {
        Optional<Utilizator> utilizatorExistent = utilizatorRepository.findById(id);

        if (utilizatorExistent.isPresent()) {
            Utilizator utilizator = utilizatorExistent.get();

            if (utilizatorActualizat.getNume() != null && !utilizatorActualizat.getNume().trim().isEmpty()) {
                utilizator.setNume(utilizatorActualizat.getNume());
            }
            if (utilizatorActualizat.getUtilizator() != null && !utilizatorActualizat.getUtilizator().trim().isEmpty()) {
                utilizator.setUtilizator(utilizatorActualizat.getUtilizator());
            }
            if (utilizatorActualizat.getParola() != null && !utilizatorActualizat.getParola().trim().isEmpty()) {
                utilizator.setParola(passwordEncoder.encode(utilizatorActualizat.getParola()));
            }
            if (utilizatorActualizat.getRol() != null && !utilizatorActualizat.getRol().trim().isEmpty()) {
                utilizator.setRol(utilizatorActualizat.getRol());
            }

            return utilizatorRepository.save(utilizator);
        }

        return null;
    }
}
