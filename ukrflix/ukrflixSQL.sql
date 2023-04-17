DROP DATABASE IF EXISTS ukrflix;
CREATE SCHEMA ukrflix DEFAULT CHARACTER SET utf8;

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
	phone_num VARCHAR(12),
	PRIMARY KEY (id),
	UNIQUE (login)
);

CREATE TABLE film(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
  	price INT DEFAULT 0,
      img_src VARCHAR(255) NOT NULL,
      yt_src VARCHAR(255) NOT NULL,
      email VARCHAR(30),
	release_date DATE,
	PRIMARY KEY (id)	
);

CREATE TABLE Purchase (
	id int NOT NULL AUTO_INCREMENT,
  	film_id INT,
  	user_id INT,
  	PRIMARY KEY (id),
      FOREIGN KEY (user_id) REFERENCES user(id),
      FOREIGN KEY (film_id) REFERENCES film(id)
);

INSERT INTO ukrflix.user (login, password, firstname, lastname, email, birthday, phone_num)
VALUES ('user', '1234', 'a', 'b', null, '2023-03-10', null);

INSERT INTO film (name, release_date, price, img_src, yt_src)
VALUES ('Avatar', '2021-03-04', '22',
'https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg', 'https://www.youtube.com/watch?v=d9MyW72ELq0');
INSERT INTO film (name, release_date, price, img_src, yt_src)
VALUES ('Avatar', '2021-03-04', '22', 
'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71qtDoM-rcL._SL1200_.jpg',
'https://www.youtube.com/watch?v=2TAOizOnNPo');
INSERT INTO film (name, release_date, price, img_src, yt_src)
VALUES ('Avatar', '2021-03-04', '22', 
'https://de.web.img2.acsta.net/pictures/22/09/22/09/26/2379387.jpg', 
'https://www.youtube.com/watch?v=Kx55Rkynhtk');
