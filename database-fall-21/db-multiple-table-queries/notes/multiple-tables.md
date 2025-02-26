# Multi-Table Queries in SQL

One way to retrieve data from two tables is to use subqueries but this requires that the  result columns come from same table.  If result columns come from more than one table must use a join instead.  To perform join in the `FROM` clause, use the keyword `JOIN` between the tables and specify the condition using the `ON` clause.

### Aliases
When working with multiple tables, we often use aliases for for tables named in the `FROM` clause.  The alias is separated from table name with a space. We can use the alias to qualify column names when there is ambiguity.

### Simple Join Example
Find all customer orders

```SQL
SELECT c.name, r.name, area
FROM countries c 
   JOIN regions r ON c.region_id = r.region_id;
```

### :question: JOIN Exercises
- List region name and the continent name for all regions.
- List the country name and population in 2005.  Sort by country name. *Note:  this requires a where clause*

### Alternate join syntax

```sql
SELECT countries.name, regions.name, area 
FROM countries JOIN regions USING (region_id); 
```

Multiple Table Joins
-  Think of it as a series of two-table joins proceeding from right to left


```SQL
SELECT c.name, r.name, d.name
FROM countries c JOIN  regions r ON c.region_id = r.region_id
JOIN continents d ON d.continent_id =  r.continent_id
ORDER BY c.name;
```

## Computing a Join
The procedure for generating results of a join are:
- Form a Cartesian product of the tables named in `FROM` clause.
- Restrict the results by specifying a predicate using `JOIN ON`
- If there is a `WHERE` clause, apply the search condition to each row of the product table, retaining those rows that satisfy the condition.
- For each remaining row, determine value of each item in `SELECT` list to produce a single row in result table.
- If `DISTINCT` has been specified, eliminate any duplicate rows from the result table.
- If there is an `ORDER BY` clause, sort result table as required.


### :question:  Multiple Table Join Exercise

- List the name of the official languages for each country. Order the results by country name.


## Multiple Table Groupings
Find number of countries in each region

```SQL
SELECT region.name, count(country_id) as num_countries 
FROM countries 
JOIN regions USING (region_id)
GROUP by regions.region_id 
ORDER BY regions.name;
```


## Outer Joins
In an inner join, if one row of a joined table is unmatched the row is omitted from result table. Outer join operations retain rows that do not satisfy the join condition.  Use keywords `LEFT` or `RIGHT` to specify the type of outer join
The `OUTER` keyword is optional and is usually omitted.



###  Left Outer Join
A left outer join includes those rows of first (left) table unmatched with rows from second (right) table. Unmatched columns from second table are filled with nulls.

Example: Find countries and their GDP.

```sql
SELECT name, year,  gdp
FROM countries LEFT JOIN country_stats 
  USING (country_id) 
ORDER BY name;
```



## Unions
A union combines the result sets of two or more SELECT statements into a single result set.  Unions eliminate duplicate rows by default.  Column names are taken from the first `SELECT` clause and sorting is done by the last `SELECT` statement

Example: List all contact names (employees and customers).  Assume two tables, one for employees and one for customers.

```SQL
(SELECT firstName, lastName  FROM employees)
UNION 
(SELECT contactFirstName, contactLastName FROM customers);
```

