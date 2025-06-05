INSERT IGNORE INTO utilizatori (nume, utilizator, parola, rol) VALUES
('Administrator', 'admin', '$2a$10$8qvqI6aZqE8h8DnJ9F9ZMO7.zKxE.KG5R5F.WxX3K3vF9qA5pF5TG', 'ROLE_EDITOR'),
('Editor Filme', 'editor', '$2a$10$8qvqI6aZqE8h8DnJ9F9ZMO7.zKxE.KG5R5F.WxX3K3vF9qA5pF5TG', 'ROLE_EDITOR'),
('Utilizator Simplu', 'user', '$2a$10$dX1J2KJ9F8E7h8DnJ9F9ZMO7.zKxE.KG5R5F.WxX3K3vF9qA5pF5TG', 'ROLE_USER');

INSERT IGNORE INTO filme (id_film, id_utilizator, titlu, regizor, gen, anul_lansarii, durata_minute, tara_origine, rating, rating_imdb, pret) VALUES
('TT0111161', 1, 'The Shawshank Redemption', 'Frank Darabont', 'Dramă', 1994, 142, 'SUA', 'R', 9.3, 15.99),
('TT0068646', 1, 'The Godfather', 'Francis Ford Coppola', 'Crimă', 1972, 175, 'SUA', 'R', 9.2, 19.99),
('TT0071562', 1, 'The Godfather Part II', 'Francis Ford Coppola', 'Crimă', 1974, 202, 'SUA', 'R', 9.0, 19.99),
('TT0468569', 2, 'The Dark Knight', 'Christopher Nolan', 'Acțiune', 2008, 152, 'SUA', 'PG-13', 9.0, 12.99),
('TT0050083', 2, '12 Angry Men', 'Sidney Lumet', 'Dramă', 1957, 96, 'SUA', 'NR', 9.0, 9.99),
('TT0108052', 1, 'Schindler\'s List', 'Steven Spielberg', 'Biografic', 1993, 195, 'SUA', 'R', 8.9, 14.99),
('TT0167260', 2, 'The Lord of the Rings: The Return of the King', 'Peter Jackson', 'Fantasy', 2003, 201, 'Noua Zeelandă', 'PG-13', 8.9, 16.99),
('TT0110912', 1, 'Pulp Fiction', 'Quentin Tarantino', 'Crimă', 1994, 154, 'SUA', 'R', 8.9, 13.99),
('TT0060196', 2, 'The Good, the Bad and the Ugly', 'Sergio Leone', 'Western', 1966, 178, 'Italia', 'R', 8.8, 11.99),
('TT0137523', 1, 'Fight Club', 'David Fincher', 'Dramă', 1999, 139, 'SUA', 'R', 8.8, 12.99),
('TT0120737', 2, 'The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 'Fantasy', 2001, 178, 'Noua Zeelandă', 'PG-13', 8.8, 16.99),
('TT0109830', 1, 'Forrest Gump', 'Robert Zemeckis', 'Dramă', 1994, 142, 'SUA', 'PG-13', 8.8, 13.99),
('TT0080684', 2, 'Star Wars: Episode V - The Empire Strikes Back', 'Irvin Kershner', 'Sci-Fi', 1980, 124, 'SUA', 'PG', 8.7, 14.99),
('TT0073486', 1, 'One Flew Over the Cuckoo\'s Nest', 'Milos Forman', 'Dramă', 1975, 133, 'SUA', 'R', 8.7, 12.99),
('TT0099685', 2, 'Goodfellas', 'Martin Scorsese', 'Biografic', 1990, 146, 'SUA', 'R', 8.7, 13.99),
('TT0167261', 1, 'The Lord of the Rings: The Two Towers', 'Peter Jackson', 'Fantasy', 2002, 179, 'Noua Zeelandă', 'PG-13', 8.7, 16.99),
('TT0047478', 2, 'Singin\' in the Rain', 'Stanley Donen', 'Musical', 1952, 103, 'SUA', 'G', 8.3, 9.99),
('TT0076759', 1, 'Star Wars: Episode IV - A New Hope', 'George Lucas', 'Sci-Fi', 1977, 121, 'SUA', 'PG', 8.6, 14.99),
('TT0118799', 2, 'Life Is Beautiful', 'Roberto Benigni', 'Comedie-Dramă', 1997, 116, 'Italia', 'PG-13', 8.6, 11.99),
('TT0317248', 1, 'City of God', 'Fernando Meirelles', 'Crimă', 2002, 130, 'Brazilia', 'R', 8.6, 12.99);

-- Inserarea unor filme românești
INSERT IGNORE INTO filme (id_film, id_utilizator, titlu, regizor, gen, anul_lansarii, durata_minute, tara_origine, rating, rating_imdb, pret) VALUES
('RO001', 1, 'Moromeții', 'Stere Gulea', 'Dramă', 1987, 142, 'România', 'G', 8.5, 8.99),
('RO002', 2, 'Reconstituirea', 'Lucian Pintilie', 'Dramă', 1968, 100, 'România', 'G', 8.2, 7.99),
('RO003', 1, 'Duminică la ora 6', 'Lucian Pintilie', 'Dramă', 1965, 76, 'România', 'G', 7.8, 6.99),
('RO004', 2, 'Pădurea spânzuraților', 'Liviu Ciulei', 'Război', 1965, 158, 'România', 'G', 8.0, 7.99),
('RO005', 1, 'Balanta', 'Lucian Pintilie', 'Dramă', 1992, 99, 'România', 'G', 7.5, 6.99);