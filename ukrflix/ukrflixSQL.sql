DROP DATABASE IF EXISTS ukrflix;
CREATE SCHEMA ukrflix DEFAULT CHARACTER SET utf8;
SET NAMES UTF8;
USE ukrflix;
CREATE TABLE User(
	id INT NOT NULL  AUTO_INCREMENT,
	login varchar(20) NOT NULL,
	password varchar(30) NOT NULL,
  	account int DEFAULT 0,
      firstname VARCHAR(20) NOT NULL,
      lastname VARCHAR(20) NOT NULL,
      email VARCHAR(30),
	birthday DATE,
	phone VARCHAR(12),
	PRIMARY KEY (id),
	UNIQUE (login)
);

CREATE TABLE Film(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
  	price INT DEFAULT 0,
      img_src VARCHAR(255) NOT NULL,
      yt_src VARCHAR(255) NOT NULL,
      email VARCHAR(30),
	release_date DATE,
	description VARCHAR(255),
	PRIMARY KEY (id)	
);

CREATE TABLE Purchase (
	id int NOT NULL AUTO_INCREMENT,
  	film_id INT,
  	user_id INT,
  	PRIMARY KEY (id),
      FOREIGN KEY (user_id) REFERENCES user(id),
      FOREIGN KEY (film_id) REFERENCES film(id),
	UNIQUE (user_id, film_id)
);

CREATE TABLE actor (
  id int NOT NULL AUTO_INCREMENT,
  birthday date NOT NULL,
  firstname varchar(255) NOT NULL,
  lastname varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE actorassociation (
  id int NOT NULL AUTO_INCREMENT,
  actor_id int DEFAULT NULL,
  film_id int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (actor_id) REFERENCES actor(id),
  FOREIGN KEY (film_id) REFERENCES film(id),
  UNIQUE (actor_id, film_id)
);




INSERT INTO ukrflix.user (login, password, firstname, lastname, email, birthday, phone)
VALUES ('user', '1234', 'a', 'b', null, '2023-03-10', null);

INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('Avatar', '2021-03-04', '22',
'https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg', 'https://www.youtube.com/watch?v=d9MyW72ELq0', '{"en": "Description in English", "uk": "опис українською"}');
INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('Fast & Furious', '2001-10-18', '22', 
'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71qtDoM-rcL._SL1200_.jpg',
'https://www.youtube.com/watch?v=2TAOizOnNPo', '{"en": "Description in English", "uk": "опис українською"}');
INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('Menu', '2022-11-17', '22', 
'https://de.web.img2.acsta.net/pictures/22/09/22/09/26/2379387.jpg', 
'https://www.youtube.com/watch?v=Kx55Rkynhtk', '{"en": "Description in English", "uk": "опис українською"}');
INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('Men in black', '1997-07-02', '22', 
'https://m.media-amazon.com/images/I/511GMS2V4GL.jpg', 
'https://www.youtube.com/watch?v=1Q4mhYF9aQQ', '{"en": "Description in English", "uk": "опис українською"}');
INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('Pulp fiction', '1994-11-03', '22', 
'https://m.media-amazon.com/images/I/51LFlLl0ZOL._SX300_SY300_QL70_ML2_.jpg', 
'https://www.youtube.com/watch?v=s7EdQ4FqbhY', '{"en": "Description in English", "uk": "опис українською"}');
INSERT INTO film (name, release_date, price, img_src, yt_src, description)
VALUES ('The Lord of the Rings', '2001-12-19', '22', 
'https://m.media-amazon.com/images/I/513N2WS7ENL._AC_UF894,1000_QL80_.jpg', 
'https://www.youtube.com/watch?v=V75dMMIW2B4', '{"en": "Description in English", "uk": "опис українською"}');

INSERT INTO actor (firstname, lastname, birthday) VALUES ('Tom', 'Hanks', '1956-07-09');
INSERT INTO actor (firstname, lastname, birthday) VALUES ('Brad', 'Pitt', '1963-12-18');
INSERT INTO actor (firstname, lastname, birthday) VALUES ('Angelina', 'jolie', '1975-7-4');
INSERT INTO actor (firstname, lastname, birthday) VALUES ('Jennifer', 'Aniston', '1969-02-11');

INSERT INTO actorassociation (actor_id, film_id) VALUES ('1', 1);
INSERT INTO actorassociation (actor_id, film_id) VALUES ('1', 2);
INSERT INTO actorassociation (actor_id, film_id) VALUES ('1', 3);
