<!DOCTYPE html>
<html lang="en-US">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Confirmation</title>
</head>

<body>
    <?php
    //when getting the data from post will need to store the data in a different var

    $email = $_POST["email"];
    $bday = $_POST["bday"];
    $password = $_POST["password"];
    $confirm = $_POST["confirm"];

    echo "<p>Your email address is $email</p>";
    echo "<p>Your birthday is $bday</p>";

    if (empty($password))
        echo "<p>Password is blank</p>";
    elseif ($password == $confirm)
        echo "<p>Password match</p>";
    else
        echo "<p>Passwords don't match</p>";

    ?>


</body>

</html>