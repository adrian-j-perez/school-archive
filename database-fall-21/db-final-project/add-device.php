<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Added Device</title>

</head>

<body>
    <button onclick="window.location.href='./index.html'">Home Page</button>
    <button onclick="window.location.href='./add-device.html'">Add Device</button>
    <h1>New device added </h1>

    <?php
    //getting user data
    $name = trim($_POST["dname"]);
    $year = trim($_POST["year"]);
    $model = trim($_POST["model"]);
    $category = trim($_POST["category"]);
    $descrip = trim($_POST["desc"]);

    if (!$name || !$year || !$model || !$category || !$descrip) {
        echo "<p>You have missed a input field... </p>";
        exit;
    }
    //connecting to the database
    @$db = new mysqli("localhost", "appuser1", "password1", "devicedb");

    if ($db->connect_error) {
        die("Connect error" . $db->connect_errorno . ": " . $db->connect_error);
    }

    //prepared statement
    $query = "INSERT INTO devices(device_name, year_made, model, category, descrip) 
    VALUES (?, ?, ?, ?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param('sssss', $name, $year, $model, $category, $descrip);
    $stmt->execute();
    echo $stmt->affected_rows . " new device has been added<br/>";

    echo "device name: ".$name."<br/>" 
    ."year: ".$year."<br/>" 
    ."model: ".$model."<br/>" 
    ."category: ".$category."<br/>" 
    ."description: ".$descrip."<br/>";

    $db->close();
    ?>


</body>


</html>