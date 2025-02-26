## WHERE Clause

### Retrieving data with specific criteria
- Use a `WHERE` clause
- Example:
```sql
SELECT name
FROM countries 
WHERE region_id = 15;
```

### Comparison Operators
- Equality: `=`
- Less than: `<`
- Greater than: `>`
- Less than or equal to: `<=`
- Greater than or equal to: `>=`


### Example:  Comparison Search Condition
List all countries with an area less than 200 square kilometers.

```sql
SELECT name, area 
FROM countries 
WHERE area < 200;;
```

### Comparing Data
- You can compare data of unlike types but it may produce unexpected results
- When comparing to a string literal or a date literal, you must enclose the literal in quotes
- Character comparisons are not case sensitive

### :question: Comparison Exercises
- Find all countries with a region id of 5.
- Find all regions with a continent id of 5.
- Find all countries with a national day later than January 1, 1990.

### Compound Search Conditions
- Use logical operators
  - AND
  - OR
  - NOT


### Example: Compound Comparison Search Condition
Find all country ids for countries with an official language of language id 2.
```sql
SELECT country_id, official 
FROM country_languages 
WHERE language_id = 2 and official = 1;
```

### :question: Compound Search Conditions Exercises
- Find all countries in region 5 with an area of more than 500,000 square km 
- Find all country_ids for countries with a population greater 50,000,000 in 1970
- Find all country_ids for countries with an unofficial language of French (language id 23) 

### Range Search
- Use the `BETWEEN` operator
- Compares the value of a test expression to see if it falls within the range of the given values
- `BETWEEN` test includes the endpoints of range.
- Also a negated version `NOT BETWEEN`.
- `BETWEEN` does not add much to SQL’s expressive power.


### Example: Range Search Condition
Find all countries founded in 1990
```SQL
SELECT name
FROM countries 
WHERE national_day BETWEEN "1990-01-01" AND "1990-12-31";

### Set Membership
- `IN` operator
- Used to test whether a value is in a specified list of items
- There is a negated version (`NOT IN`).
- `IN` does not add much to SQL’s expressive power
- Use OR operator instead
-'IN' is more efficient when set contains many values.

### Example:  Set Membership
List all countries in Europe

```sql
SELECT name, area 
FROM countries 
WHERE region_id IN (4, 10, 13, 19, 21, 24);
```

### Pattern Matching
- Uses `LIKE` and `NOT LIKE` and regular expressions
  - `%`: sequence of zero or more characters;
  - `_` (underscore): any single character.
- Can degrade performance so use sparingly
- Example: `LIKE '%Staff%;` means a sequence of characters of any length containing Staff.

### Example: Pattern Matching
Find all countries with the word island in their name.

```sql
Select name 
FROM countries 
WHERE name LIKE "%islands%";
```

### Null Values
- Columns can contain a null value
- Value is empty or unknown
- Can test for null values using
    - IS NOT NULL
    - IS NULL

### Example:  NULL Search Condition
```sql  
SELECT name 
FROM countries 
WHERE national_day is NULL;
```
