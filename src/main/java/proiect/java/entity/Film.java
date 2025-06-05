package proiect.java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "filme")
public class Film {

    @Id
    @Column(name = "id_film", length = 20)
    private String idFilm; // ID unic pentru film (ex: IMDB ID sau cod intern)

    @Column(name = "id_utilizator")
    private Long idUtilizator; // ID-ul utilizatorului care a adăugat filmul

    @NotBlank(message = "Titlul este obligatoriu")
    @Size(max = 200, message = "Titlul nu poate depăși 200 de caractere")
    @Column(name = "titlu", nullable = false, length = 200)
    private String titlu;

    @NotBlank(message = "Regizorul este obligatoriu")
    @Size(max = 100, message = "Numele regizorului nu poate depăși 100 de caractere")
    @Column(name = "regizor", nullable = false, length = 100)
    private String regizor;

    @NotBlank(message = "Genul este obligatoriu")
    @Size(max = 50, message = "Genul nu poate depăși 50 de caractere")
    @Column(name = "gen", nullable = false, length = 50)
    private String gen;

    @NotNull(message = "Anul lansării este obligatoriu")
    @Min(value = 1888, message = "Anul nu poate fi mai mic decât 1888") // primul film din istorie
    @Max(value = 2030, message = "Anul nu poate fi în viitorul îndepărtat")
    @Column(name = "anul_lansarii", nullable = false)
    private Integer anulLansarii;

    @NotNull(message = "Durata este obligatorie")
    @Min(value = 1, message = "Durata trebuie să fie cel puțin 1 minut")
    @Max(value = 600, message = "Durata nu poate depăși 600 de minute")
    @Column(name = "durata_minute", nullable = false)
    private Integer durataMinute;

    @Size(max = 50, message = "Țara nu poate depăși 50 de caractere")
    @Column(name = "tara_origine", length = 50)
    private String taraOrigine;

    @Size(max = 20, message = "Rating-ul nu poate depăși 20 de caractere")
    @Column(name = "rating", length = 20)
    private String rating; // G, PG, PG-13, R, NC-17, etc.

    @DecimalMin(value = "0.0", message = "Rating-ul IMDB nu poate fi negativ")
    @DecimalMax(value = "10.0", message = "Rating-ul IMDB nu poate depăși 10.0")
    @Column(name = "rating_imdb", precision = 3, scale = 1)
    private Double ratingImdb;

    @DecimalMin(value = "0.0", message = "Prețul nu poate fi negativ")
    @Column(name = "pret", precision = 10, scale = 2)
    private Double pret; // pentru achiziționare/închiriere

    // Constructors
    public Film() {}

    public Film(String idFilm, String titlu, String regizor, String gen,
                Integer anulLansarii, Integer durataMinute) {
        this.idFilm = idFilm;
        this.titlu = titlu;
        this.regizor = regizor;
        this.gen = gen;
        this.anulLansarii = anulLansarii;
        this.durataMinute = durataMinute;
    }

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public Long getIdUtilizator() { return idUtilizator; }
    public void setIdUtilizator(Long idUtilizator) { this.idUtilizator = idUtilizator; }

    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getRegizor() { return regizor; }
    public void setRegizor(String regizor) { this.regizor = regizor; }

    public String getGen() { return gen; }
    public void setGen(String gen) { this.gen = gen; }

    public Integer getAnulLansarii() { return anulLansarii; }
    public void setAnulLansarii(Integer anulLansarii) { this.anulLansarii = anulLansarii; }

    public Integer getDurataMinute() { return durataMinute; }
    public void setDurataMinute(Integer durataMinute) { this.durataMinute = durataMinute; }

    public String getTaraOrigine() { return taraOrigine; }
    public void setTaraOrigine(String taraOrigine) { this.taraOrigine = taraOrigine; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public Double getRatingImdb() { return ratingImdb; }
    public void setRatingImdb(Double ratingImdb) { this.ratingImdb = ratingImdb; }

    public Double getPret() { return pret; }
    public void setPret(Double pret) { this.pret = pret; }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm='" + idFilm + '\'' +
                ", titlu='" + titlu + '\'' +
                ", regizor='" + regizor + '\'' +
                ", gen='" + gen + '\'' +
                ", anulLansarii=" + anulLansarii +
                ", durataMinute=" + durataMinute +
                '}';
    }
}