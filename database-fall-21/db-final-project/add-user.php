<!DOCTYPE html>
<html lang="en-US">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Added User</title>

</head>

<body>
    <button onclick="window.location.href='./index.html'">Home Page</button>
    <button onclick="window.location.href='./add-user.html'">Add User</button>
    <h1>User has been added</h1>

    <?php
    $first = trim($_POST["fname"]);
    $last = trim($_POST["lname"]);
    // echo $first . " " . $last  

    if (!$first || !$last) {
        echo "<p>You have missed a input field... </p>";
        exit;
    }
    //connecting to the database
    @$db = new mysqli("localhost", "appuser1", "password1", "devicedb");

    if ($db->connect_error) {
        die("Connect error" . $db->connect_errorno . ": " . $db->connect_error);
    }

    //prepared statement
    $query = "INSERT INTO users(first_name, last_name) VALUES(?,?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param('ss', $first, $last);
    $stmt->execute();
    echo $stmt->affected_rows . " new user has been added<br/>";

    echo "first name: " . $first . " <br/>last name: " . $last;

    $db->close();
    ?>

</body>

</html>