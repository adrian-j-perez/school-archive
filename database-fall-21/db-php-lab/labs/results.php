<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Results</title>
</head>

<body>
    <h1>Search Results</h1>

    <main>
        <!-- this php code is more OOP  -->
        <?php
        $searchtype = $_POST["searchtype"];
        // trim will remove whitespace
        $searchterm = trim($_POST["searchterm"]); 

        if(!$searchtype || !$searchterm){
            echo "<p>You have not entered anything...</p>";
            exit;
        }

        //connecting to the database, the `@` is error handleing
        @$db = new mysqli("localhost", "bookorama", "cpsc33000", "books");

        if($db->connect_error){
            die("Connect error".$db->connect_errorno.": ".$db->connect_error);
        }

        //this can be used to attacks
        $query = "SELECT * FROM books WHERE $searchtype LIKE '%$searchterm%'";
        //                                                   ^^^^^^- attack point
        //echo $query // for testing

        $result = $db->query($query);
        $num_results = $result->num_rows;
        echo "<p>Number of books foind: $num_results</p>";

        for($i=0; $i<$num_results; $i++){
            $row = $result->fetch_assoc();
            echo "<p><strong>Title: ";
            echo htmlspecialchars(stripslashes($row["title"]))."</strong><br/>";
            echo "Author: ".$row["author"]."<br/>";
            echo "ISBN: ".$row["isbn"]."<br/>";
            echo "Price: ".$row["price"]."<br/>";
            echo "</p>";
        }


        //closing objects 
        $result->free();
        $db->close();

        ?>
    </main>
</body>

</html>