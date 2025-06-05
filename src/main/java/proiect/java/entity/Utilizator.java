package proiect.java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="utilizatori")
public class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_utilizator")
    private Long idUtilizator;

    @NotBlank(message="Numele este obligatoriu")
    @Size(max=100, message = "Numele nu poate depăși 100 de caractere")
    @Column(name="nume", nullable=false, length = 100)
    private String nume;

    @NotBlank(message = "Numele de utilizator este obligatoriu")
    @Size(max = 50, message = "Numele de utilizator nu poate depăși 50 de caractere")
    @Column(name = "utilizator", nullable = false, unique = true, length = 50)
    private String utilizator;

    @NotBlank(message = "Parola este obligatorie")
    @Column(name = "parola", nullable = false)
    private String parola; // va fi criptată cu BCryptPasswordEncoder

    @NotBlank(message = "Rolul este obligatoriu")
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;// ROLE_USER sau ROLE_EDITOR

    public Utilizator() {
    }

    public Utilizator(Long idUtilizator, String nume, String utilizator, String parola, String rol) {
        this.idUtilizator = idUtilizator;
        this.nume = nume;
        this.utilizator = utilizator;
        this.parola = parola;
        this.rol = rol;
    }

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "idUtilizator=" + idUtilizator +
                ", nume='" + nume + '\'' +
                ", utilizator='" + utilizator + '\'' +
                ", parola='" + parola + '\'' +
                ", rol=" + rol +
                '}';
    }
}
