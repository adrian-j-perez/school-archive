# PHP and  Databases

## Web Database Architecture
![arch](images/web-arch.png)

## Databases
- Very important to the popularity of PHP
- We'll use MySQL
  - Open source
  - Powerful
  - Free

## Using MySQL with PHP

### Connecting to a database using PHP
- Create a mysqli object
```
@ $db= new mysqli('localhost', 'bookorama', 'cs440', 'books');
```
- Arguments
  - Host
  - Username
  - Password
  - Database

### Checking for Errors
- `connect_error` returns a string description of the last connect error
```
if ($db->connect_error) {    	
  die('Connect Error ' .$db->connect_error);
}
```

### Querying the database
- Use query method
```
$query="SELECT * FROM books";
$result = $db->query($query)
```
- Returns a result object

### Processing Query Results
- Result object consists of rows and columns
  - Number of rows: `$num_results = $result->num_rows;`
  - Number of columns: `$num_fields = $result->field_count;`
- Each row is retrieved as an array
  - indexed use `fetch_row()`
  - associative use `fetch_assoc()`
- Example: [show_books.php](../examples/show_books.php)

### Formatting user input
You should filter user input to handle any control characters
Use the `addslashes()` function to escape data
Example:
```
$name = addslashes($_POST['name']);
```
To get rid of the slashes uses the function stripslashes()
Example:
```
$title= stripslashes($searchterm);
```
To encode special HTML characters use the htmlspecialchars() function

### Handling newlines
- A user can enter text over multiple lines in a form element such as a text area
- To create the equivalent of these newlines in a webpage use the `nl2br()` function
```
$comments = nl2br($_POST['comments']);
```

### Closing  a database connection
- Release the result
```
$result->free();
```

- Close the database using the `close()` method
```
$db->close();
```

### Adding new information
- Can use an INSERT query
- The result is a Boolean value representing the success of the operation

```php
$query="INSERT INTO customer VALUES (44, 'Barak Obama', '1600 Pennsylvania Avenue','Washington DC')";

if ($mysqli->query($query) === TRUE){
    echo '<p> Record added </p>';
}
else{
	echo '<p>Unable to insert record</p>';
}
```

### Prepared Statements
- Preferred over INSERT queries
- Faster execution
- Protects against SQL injection attacks
```
$query = "insert into books values (?, ?, ?, ?)";
$stmt = $db->prepare($query);
$stmt->bind_param("sssd", $isbn, $author, $title, $price);
$stmt->execute();
echo $stmt->affected_rows." book inserted into database";
```
- Example: [newbooks.html](../examples/newbook.html) and [insert_book.php](../examples/insert_book.php)


## [Lab Exercise 5](../exercises/exercise-5.md)
