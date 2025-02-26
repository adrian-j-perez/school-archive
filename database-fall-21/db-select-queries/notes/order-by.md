## `ORDER BY` Clause

### Retrieving data in a particular order
- Use the `ORDER BY` clause
- Sorts the rows on one or more columns
- Default order is ascending
  - Can specify using the keyword `ASC`
  - Example: `ORDER BY last_name` is the same as `ORDER BY last_name ASC`
- To sort in descending order use the keyword `DESC`
  - Example: `ORDER BY last_name DESC`

### Example: Single Column Ordering
List national day for all countries, arranged in descending order of national day.

```sql
SELECT name, national_day
FROM countries ORDER BY national_day DESC;
```
### Example: Multiple Column Ordering
Produce a list of countries in each region ordered by region and country name.

```sql
SELECT name, region_id
FROM countries
ORDER by region_id, name;
```

### Sort by other criteria
- A column that uses an alias
- A calculation

### `LIMIT `Clause
- Specifies the maximum number of rows that are returned in the result set
- When combining `LIMIT` and `ORDER BY` you can select the n largest or n smallest values
- Example
```sql
SELECT name, area
FROM countries
ORDER BY area DESC
LIMIT 3;
```

### :question: Sorting Exercises
- Select regions in ascending order by alphabetically by region name.
- Repeat the above query but display in descending order.
- Select countries and area by region id and within each region order by area with the largest area first.

