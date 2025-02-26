--source D:\0School\sem7\Database Systems\week-8\db-multiple-table-queries-7pereza\assignments\multi-table.sql

USE movies;

-- exercise 1: Use a subquery to list all actor ids for actors in the film "MULAN MOON". You should not use a constant for film_id.

SELECT actor_id 
FROM film_actor
WHERE film_id = 
(SELECT film_id 
FROM film 
WHERE title = "MULAN MOON");

-- exercise 2: Find the first and last name of all actors in film 604 using a subquery

SELECT first_name, last_name
FROM actor
WHERE actor_id IN
(SELECT actor_id
FROM film_actor
WHERE film_id="604"
);

-- exercise 3: List the title of the movie for each movie in which the actor 44 appears. Use a join and sort by movie title

SELECT title
FROM film a
JOIN film_actor b ON a.film_id = b.film_id
WHERE actor_id="44"
ORDER BY title ASC ;

-- exercise 4: Find the film ids for movies categorized as comedy. Display the film id and the category name

SELECT film_id, name
FROM film_category
JOIN category USING(category_id)
WHERE category.name="comedy";

-- exercise 5: Find languages that have no films in the database. Hint: use an outer join and return only those rows where the film_id is null

SELECT name, film_id
FROM language 
LEFT JOIN film USING(language_id)
WHERE film_id IS NULL;

-- exercise 6: List the title and category for all films that are rated PG. Sort by title. Hint: This requires 3 tables

SELECT title, name AS category
FROM film
JOIN film_category USING(film_id)
JOIN category USING(category_id)
WHERE rating="PG";

-- exercise 7: Find the number of actors in each film. Your query result should display the title of the movie and the number of actors in that movie.

SELECT title, count(actor_id)
FROM film
JOIN film_actor USING(film_id)
GROUP BY title;
