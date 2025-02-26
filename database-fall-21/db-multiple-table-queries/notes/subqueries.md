# Subqueries

Some SQL statements can have a `SELECT` embedded within them.  A subselect can be used in `WHERE` and `HAVING` clauses of an outer `SELECT`, where it is called a subquery or nested query.  Subselects may also appear in INSERT, UPDATE, and DELETE statements.

### Example: subquery using equality

```sql
SELECT name	FROM countries
WHERE region_id =  
    (SELECT region_id FROM regions     
    WHERE name = 'Middle East');
```

### Example:  subquery with aggregate

```SQL
SELECT name, 
   area - (SELECT AVG(area) FROM countries) AS area_diff 
FROM countries 
WHERE area > (SELECT AVG(area) FROM countries);
```
### Example:  Nested subquery and use of IN

```sql
SELECT name 
FROM countries 
WHERE region_id in    
(SELECT region_id from regions       
WHERE name  LIKE "%Africa%");
```

### Example: Use of ANY/SOME
```sql
SELECT name, area  
FROM countries
WHERE area > ANY 
(SELECT area FROM countries      
WHERE region_id = 5);
```

### Example: Use of ALL
```SQL
SELECT name, area  
FROM countries
WHERE area > ALL 
(SELECT area FROM countries     
WHERE region_id = 5);
```

###  :question: Subquery Exercises
1. List all countries in the Caribbean region.
1. List the name  of all countries who have English (language id 2) as an official language.
1. Find the countries  and their national day for those countries whose national day is after the national day of all countries in region 5 that have a national day.
