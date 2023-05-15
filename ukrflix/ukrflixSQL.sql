DROP DATABASE IF EXISTS ukrflix;
CREATE SCHEMA ukrflix DEFAULT CHARACTER SET utf8;
SET NAMES UTF8;
USE ukrflix;
CREATE TABLE user(
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

CREATE TABLE film(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
  	price INT DEFAULT 0,
      img_src VARCHAR(255) NOT NULL,
      yt_src VARCHAR(255) NOT NULL,
	release_date DATE,
	description TEXT,
	PRIMARY KEY (id)	
);

CREATE TABLE purchase (
	id int NOT NULL AUTO_INCREMENT,
  	film_id INT,
  	user_id INT,
  	PRIMARY KEY (id),
      FOREIGN KEY (user_id) REFERENCES user(id),
      FOREIGN KEY (film_id) REFERENCES film(id),
	UNIQUE (user_id, film_id)
);

CREATE TABLE actor (
  id INT NOT NULL AUTO_INCREMENT,
  birthday DATE NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE actor_images
(
    actor_id INT,
    actorImages VARCHAR(255) null,
    FOREIGN KEY (actor_id) REFERENCES actor(id)
);

CREATE TABLE actorassociation (
  id int NOT NULL AUTO_INCREMENT,
  role VARCHAR(255),
  actor_id int DEFAULT NULL,
  film_id int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (actor_id) REFERENCES actor(id),
  FOREIGN KEY (film_id) REFERENCES film(id),
  UNIQUE (actor_id, film_id)
);




INSERT INTO ukrflix.user (login, password, firstname, lastname, email, birthday, phone)
VALUES ('user', '1234', 'Artem', 'Novikov', 'email@email', '2023-03-10', '+1234567');

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

INSERT INTO actorassociation (role, actor_id, film_id) VALUES ('Martin', '1', 1);
INSERT INTO actorassociation (role, actor_id, film_id) VALUES ('Sully', '1', 2);
INSERT INTO actorassociation (role, actor_id, film_id) VALUES ('Mike', '1', 3);

INSERT INTO actor_images (actor_id, actorImages) VALUES ('1', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413838550_e148a808b5.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('1', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413838555_2a42722a84.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('1', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413838559_a440fc0400.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('1', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413838564_3d95d33ef3.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('2', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413713183_7b67886d4a.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('2', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413713167_2f0fdbfa87.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('2', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413713173_360594d873.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('2', 'https://ex-fs.net/uploads/posts/2014-10/thumbs/1413713194_7c1f4f907c.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('3', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410012046_75850fa883.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('3', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410012052_16c589349a.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('3', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410012041_3b82d8f219.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('3', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410012025_76325c9994.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('4', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410005773_219a3268e2.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('4', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410005768_c922be14f8.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('4', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410005779_929fe62b50.jpg');
INSERT INTO actor_images (actor_id, actorImages) VALUES ('4', 'https://ex-fs.net/uploads/posts/2014-09/thumbs/1410005773_219a3268e2.jpg');
