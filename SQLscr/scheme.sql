/*
DROP TABLE IF EXISTS orders_items;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
*/

CREATE TABLE IF NOT EXISTS books(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 title               CHARACTER VARYING (80) NOT NULL,
 name_Author         CHARACTER VARYING (60),
 date_Release_Book   DATE NOT null,
 price               DECIMAL(10,2),
 isbn                char(17) unique,
 cover	             CHARACTER VARYING (60),
 deleted			 BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 name               CHARACTER VARYING (100) NOT NULL,
 last_name          CHARACTER VARYING (100) NOT NULL,
 email              CHARACTER VARYING (100) NOT NULL,
 password           CHARACTER VARYING (50) NOT null,
 role	            CHARACTER VARYING (60),
 is_active			BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS orders(
id                 BIGSERIAL PRIMARY KEY NOT NULL,
user_id			   BIGINT REFERENCES users(id),
total_cost  	   DECIMAL(8,2),
timestamp		   DATE NOT NULL,
status	           CHARACTER VARYING (60)
);

CREATE TABLE IF NOT EXISTS orders_items(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 orders_id 			BIGINT REFERENCES orders(id),
 book_id			BIGINT REFERENCES books(id),
 quantity			INT2 NOT NULL,
 price 				DECIMAL (6,2)
);

