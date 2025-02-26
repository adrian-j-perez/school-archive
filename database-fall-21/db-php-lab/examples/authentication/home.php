
<?php

//get the session id
session_start();
if ($_SESSION['username'] == null) {
    header('Location: book_login.php?logout');
}
?>
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
        <?php
        // Print a greeting:
        echo  '<h1>Welcome, ' . $_SESSION['username'] . '!</h1>';

        echo '<a href="book_login.php?logout">Logout</a>';


        ?>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
