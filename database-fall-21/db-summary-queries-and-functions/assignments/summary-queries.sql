--NAME: Adrian Perez
--Date: 2021/10/08

USE movies;
-- add sql queries under the appropriate comment

-- exercise 1: Find the number of distinct rental rates using a summary query.
SELECT COUNT(DISTINCT rental_rate)
FROM film;

-- exercise 2: Find the number of actors whose last name begins with Z.
SELECT COUNT(last_name) AS zLastName
FROM actor
WHERE SUBSTRING(last_name,1,1)='Z';

-- exercise 3: Find the length of the shortest movie.
SELECT title, MIN(length)
FROM film ;

-- exercise 4: Find the length of the longest R rated movie.
SELECT title, MAX(length) AS MaxLengthRatedR
FROM film
WHERE rating='R';

-- exercise 5: List each replacement cost and the number of movies with that replacement cost.
SELECT replacement_cost, COUNT(replacement_cost)
FROM film
GROUP BY replacement_cost;

-- exercise 6: List all replacement costs with more than 50 movies with that replacement cost.
SELECT replacement_cost, COUNT(replacement_cost) AS num
FROM film
GROUP BY replacement_cost
HAVING COUNT(replacement_cost)>50;

