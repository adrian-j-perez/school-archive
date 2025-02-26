# Summary Queries


## Aggregate Operations
- These are proposed additions to relational Algebra
- These functions aggregate data, like totals at the bottom of a report
- They also group, like subtotals


## Aggregate Functions
-  Operate on a series of values and return a single value
    -  Sometimes called column functions
-  Query that contains one or more aggregate functions is called a summary query

## SELECT statement Aggregates
-  Aggregate functions can be used only in SELECT list and in HAVING clause.
-  If SELECT list includes an aggregate function and there is no GROUP BY clause, SELECT list cannot reference a column out with an aggregate function.

## COUNT
-  To count all of the selected rows usually use `COUNT(*)`
-  Can also use the name of any column that does not contain null

## DISTINCT
-  Use `DISTINCT` to omit duplicate values

## :question: Aggregate Functions Exercises
1.  Write a query to find the number of languages in the languages table
1.  Write a query to find the number of different regions in the countries table
1.  Find the earliest national day of all countries using an aggregate function (Don’t sort and limit). 
1.  Find the number of countries without a national day.

## GROUP BY
-  Divide rows in a table into smaller groups by using the GROUP BY clause
    -  groups the rows a result set based on one or more columns or expressions
-  If you use aggregate functions the aggregate is calculated for each group specified by the GROUP BY clause
-  The GROUP BY clause follows the WHERE clause and precedes the ORDER BY clause
-  The default sort order is ascending
    -  Use DESC after the column name in the GROUP BY clause to change the sort order

## :question: GROUP BY Exercises
1. Find the number of countries in each region.
1. Find the average population for each year



## :question: Having Exercises
1. Find regions that have more than 10 or more countries and the total area of the region.
1. Find the number of countries with and the average area for each region.  Include only those regions with an average area greater than 200,000 square km.


## More Summary Queries 
1. Find the number of countries with an area less than 1000 square km.
1. Find the lowest GDP in the statistics table.
1. Find the average area in each region for countries with an area of greater than 5000 square km
1. Find those regions where the average area is more than 100,000 square km.
