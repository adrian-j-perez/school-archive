<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>Show Books</title>
</head>
<body>
    <main role="main" class="container-fluid">
        <h1>Book Catalog</h1>
<?php
    @ $db = new mysqli('localhost', 'bookorama', 'cpsc33000', 'books');


    if ($db->connect_error) {
        die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
    }

    $query="SELECT * FROM books";
    //$result = $db->query($query);

    if ($result = $db->query($query)) {

        //find size of result set
        $num_results = $result->num_rows;
        $num_fields = $result->field_count;

        echo "<table class='table table-responsive'>";
        echo "<tr>";

        //get and display field names
        $dbinfo = $result->fetch_fields();


        foreach ($dbinfo as $val) {
            echo "<th>".ucwords($val->name)."</th>";
        }

        echo "</tr>";

        while ($row = $result->fetch_row()) {
            echo "<tr>";
            // to remove the slash from the database also the user input has to be the same
            for ($i=0; $i<$num_fields; $i++) {
                echo "<td>". stripslashes($row[$i])."</td>";
            }
            echo "</tr>";
        }

        // just like java to it is go
        $result->close();
        echo "</table>";
    }

    $db->close();

?>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
