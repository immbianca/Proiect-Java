CREATE TABLE IF NOT EXISTS utilizatori (
                                           id_utilizator BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           nume VARCHAR(100) NOT NULL,
    utilizator VARCHAR(50) NOT NULL UNIQUE,
    parola VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
    INDEX idx_utilizator (utilizator),
    INDEX idx_rol (rol)
    );

CREATE TABLE IF NOT EXISTS filme (
                                     id_film VARCHAR(20) PRIMARY KEY,
    id_utilizator BIGINT,
    titlu VARCHAR(200) NOT NULL,
    regizor VARCHAR(100) NOT NULL,
    gen VARCHAR(50) NOT NULL,
    anul_lansarii INT NOT NULL,
    durata_minute INT NOT NULL,
    tara_origine VARCHAR(50),
    rating VARCHAR(20),
    rating_imdb DECIMAL(3,1),
    pret DECIMAL(10,2),
    FOREIGN KEY (id_utilizator) REFERENCES utilizatori(id_utilizator) ON DELETE SET NULL,
    INDEX idx_titlu (titlu),
    INDEX idx_gen (gen),
    INDEX idx_regizor (regizor),
    INDEX idx_an (anul_lansarii),
    INDEX idx_utilizator (id_utilizator)
    );