# Search Form Lab

## Create Form
Create a form that looks like this.  The form method should be `post` and the action should be `results.php`.
![form](images/search.png)

**Be sure to give names to your select and input so you can retrieve values from the `$_POST` array.**

## Process Search Form
- Retrieve search type and search term from the $_POST array
- Make a connection to the database
- Assemble the query
- Retrieve the results


## Display results
- Loop through the results to display the title, ISBN and price.
- Use functions to handle special characters
Close Resources
Free the results and close the database
