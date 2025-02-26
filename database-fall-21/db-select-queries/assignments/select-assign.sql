--NAME: Adrian Perez

USE movies;
-- add sql queries under the appropriate comment

/*exercise 1: List all films with a G rating. Include title, description and length in the result
set and sort by title. */
SELECT title AS 'G-rated', description, length 
FROM film 
WHERE rating='G' 
ORDER BY title ASC; 


/*exercise 2: Find the title, rating and length for all films shorter than 1 hour (60 minutes). */
SELECT title, rating, length 
FROM film 
WHERE length < 60 ;

/*exercise 3: Find the title and length for films that are longer than 3 hours (180 min) and 
rated PG-13. Order by length in descending order.*/
SELECT title, length, rating 
FROM film 
WHERE length > 180 AND rating = 'PG-13' 
ORDER BY length DESC;

/*exercise 4: List the title, rating and length for all films order by rating and within each rating by length*/
SELECT title, rating, length 
FROM film 
ORDER BY rating, length;


/*exercise 5: Find all rental rates for films. Include each rental rate in ascending order and be 
sure that each rate appears only a single time.*/
SELECT DISTINCT rental_rate 
FROM film 
ORDER BY rental_rate ASC;

