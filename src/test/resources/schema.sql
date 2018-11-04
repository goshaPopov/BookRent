DROP TABLE IF EXISTS "USER";
DROP TABLE IF EXISTS "BOOK";
DROP TABLE IF EXISTS "BOOK_RENT";

CREATE TABLE "USER"(
  id BIGINT AUTO_INCREMENT,
  login VARCHAR(50),
  password VARCHAR(50),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  balance DECIMAL(20,2),
  PRIMARY KEY (id)
);

CREATE TABLE "BOOK"(
  id BIGINT AUTO_INCREMENT,
  author VARCHAR(50),
  name VARCHAR(50),
  price DECIMAL(20,2),
  PRIMARY KEY (id)
);

CREATE TABLE "BOOK_RENT"(
  id BIGINT AUTO_INCREMENT,
  user_id BIGINT,
  book_id BIGINT,
  start_rent TIMESTAMP,
  end_rent TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id)
    REFERENCES "USER"(id),
  FOREIGN KEY (book_id)
    REFERENCES "BOOK"(id)
);

INSERT INTO "USER"(login, password, first_name, last_name, balance) VALUES('gorge39@gmail.com', 'testtest','Goga','P', 52.65);
INSERT INTO "USER"(login, password, first_name, last_name, balance) VALUES('g.popov@surfstudio.ru', 'testtest','Georgy','Popov', 150.65);

INSERT INTO "BOOK"(author, name, price) VALUES('Gluhovskiy', 'Metro 2033', 4.00);
INSERT INTO "BOOK"(author, name, price) VALUES('Shildt', 'Java 7', 5.00);
INSERT INTO "BOOK"(author, name, price) VALUES('Sergeev', 'Chernovik', 3.50);
INSERT INTO "BOOK"(author, name, price) VALUES('Craig Walls', 'Spring in action', 5.50);
INSERT INTO "BOOK"(author, name, price) VALUES('Bob martin', 'Clean code', 4.75);

INSERT INTO "BOOK_RENT"(user_id, book_id, start_rent, end_rent) VALUES(1, 1, PARSEDATETIME('Sat, 3 Feb 2001 03:05:06 GMT',
        'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'), PARSEDATETIME('Sat, 3 Feb 2020 03:05:06 GMT',
        'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'));

INSERT INTO "BOOK_RENT"(user_id, book_id, start_rent, end_rent) VALUES(1, 4, PARSEDATETIME('Sat, 3 Feb 2001 03:05:06 GMT',
        'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'), PARSEDATETIME('Sat, 3 Feb 2020 03:05:06 GMT',
        'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'));

INSERT INTO "BOOK_RENT"(user_id, book_id, start_rent, end_rent) VALUES(1, 5, PARSEDATETIME('Sat, 3 Feb 2001 03:05:06 GMT',
         'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'), PARSEDATETIME('Sat, 3 Feb 2020 03:05:06 GMT',
         'EEE, d MMM yyyy HH:mm:ss z', 'en', 'GMT'));