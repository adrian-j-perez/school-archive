<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>Book-O-Rama Book Entry Results</title>


</head>

<body>
    <main role="main" class="container-fluid">
	<h1> Book-O-Rama Book Entry Results </h1>
<?php
    $isbn=$_POST["isbn"];
    $author=$_POST["author"];
    $title=$_POST["title"];
    $price=$_POST["price"];

    if (!$isbn || !$author || !$title || !$price) {
        echo "You have not entered all required details.  Please go back and try again.";
        exit;
    }

    //format input
    $isbn = addslashes($isbn);
    $author = addslashes($author);
    $title = addslashes($title);
    $price = doubleval($price);

    //connect to the database
    @$db = new mysqli('localhost', 'bookorama', 'cpsc33000', 'books');


    if ($db->connect_error) {
        die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
    }


    $query = "insert into books values (?, ?, ?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param("sssd", $isbn, $author, $title, $price);
    $stmt->execute();
    echo $stmt->affected_rows." book inserted into database";

    $db->close();
?>
    <br/
    ><a href="show_books.php">Show Books</a>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>
