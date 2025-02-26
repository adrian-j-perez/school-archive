<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search Results</title>
</head>

<body>
    <button onclick="window.location.href='./index.html'">Home Page</button>
    <button onclick="window.location.href='./search.html'">Search</button>
    <h1>Results...</h1>

    <?php

    // function we check what table user pick
    function whatType($type){
        if($type == "users"){
            return "SELECT * FROM users WHERE first_name LIKE ";
        }
        if($type == "devices"){
            return "SELECT * FROM devices WHERE device_name LIKE ";
        }
        if($type == "uses"){
            //this query is dealing with the many to many table
            //so it can show user and device name instead of number id
            return "SELECT b.first_name, b.last_name, c.device_name
            FROM uses a
            JOIN users b ON a.user = b.users_id
            JOIN devices c ON a.device = c.device_id
            WHERE b.first_name LIKE ";
        }
    }

    $searchType = $_POST["searchtype"];
    $searchTerm = trim($_POST["searchterm"]);
    //no user input will should just output all results

    //connecting to the database, the `@` is error handleing
    @$db = new mysqli("localhost", "appuser1", "password1", "devicedb");

    if ($db->connect_error) {
        die("Connect error" . $db->connect_errorno . ": " . $db->connect_error);
    }

    //if statement to see what table to make query of
    $query = whatType($searchType) . " '%$searchTerm%' "; 

    if ($result = $db->query($query)) {

        //find size of result set
        $num_results = $result->num_rows;
        $num_fields = $result->field_count;
        $counter=0; //var use to help remove ids col

        echo "<table>";
        echo "<tr>";
        //get and display field names
        $dbinfo = $result->fetch_fields();
        foreach ($dbinfo as $val) {
            //this if statement is here to find the colm. that have ids 
            if (strpos($val->name, "_id") !== false ){ 
                //we found the word
                $counter=1;
            }else{
                echo "<th>" . ucwords($val->name) . "</th>";
            }            
        }
        echo "</tr>";

        while ($row = $result->fetch_row()) {
            echo "<tr>";
            // if becomes true when foreach loop found a '%_id' col
            //so we set forloop to start one ahead
            if($counter == 1){$i=1;}else{$i=0;}

            for ($i; $i < $num_fields; $i++) {
                echo "<td>" . stripslashes($row[$i]) . "</td>";
            }
            echo "</tr>";
        }
        echo "</table>";
    }//end of if statment


    $db->close();
    ?>

</body>

</html>