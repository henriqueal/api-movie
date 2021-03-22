DROP TABLE IF EXISTS movie;


CREATE TABLE movie(id IDENTITY, year, title, studios, producers, winner) AS
SELECT NULL, * FROM CSVREAD('./movielist.csv', null, 'fieldSeparator=;');

