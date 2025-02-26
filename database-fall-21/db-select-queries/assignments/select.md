# Assignment - Basic SQL Queries

1. Copy the URL for this repository and clone it to your laptop: `git clone url`  where `url` is the URL for this repository
2. Change directories to the new folder and create the buildings database: `mysql -u root < movies.sql`
3.  Open MySQL at the command line and select the buildings database using `USE movies`
3. Paste the solutions to the following exercises in the `select-assign.sql` file
4.  When you've completed the exercises, save the `select-assign.sql` file.  Commit and push your solutions to GitHub.
```
git add .
git commit -m "completed SQL select assignment"
```


## Exercise 1
List all films with a G rating.  Include title, description and length in the result set and sort by title.

## Exercise 2
Find the title,  rating and length for all films shorter than 1 hour (60 minutes).

## Exercise 3
Find the title and length for films that are longer than 3 hours and rated PG-13.  Order by length in descending order.

## Exercise 4
List the title, rating and length for all films order by rating and within each rating by length.

## Exercise 5
Find all rental rates for films. Include each rental rate in ascending order and be sure that each rate appears only a single time.
