# Multiple Table Queries

Using the `movies` database, write and test queries to complete the exercises below.  Copy and paste the sql statement for each of the exercises into `multi-table.sql`. Commit and push your changes to GitHub.  

Submit the assignment in Blackboard.
## Exercise 1
Use a subquery to list all actor ids for actors in the film "MULAN MOON".  You should **not** use a constant for film_id.

## Exercise 2
Find the first and last name of all actors in film 604 **using a subquery**.

## Exercise 3
List the title of the movie for each movie in which the actor  44 appears.  Use a join and sort by movie title.

## Exercise 4
Find the film ids for movies categorized as comedy. Display the film id and the category name.

## Exercise 5
Find languages that have no films in the database.  *Hint: use an outer join and return only those rows where the film_id is null*


## Exercise 6
List the title and category for all films that are rated PG. Sort by title.   *Hint:  This requires 3 tables.*


## Exercise 7
Find the number of actors in each film.  Your query result should display the title of the movie and the number of actors in that movie.
