<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <title>lab-3</title>
</head>

<body>

    <?php
    $hours = 20;

    echo "<p>Currect hour is $hours</p>";

    if ($hours < 12) {
        echo "good morning<br/>";
    } elseif ($hours < 18) {
        echo "good afternoon<br/>";
    } else {
        echo "good evening<br/>";
    }

    echo "-------for loop------------<br/>";
    for ($i = 1; $i < 11; $i++) {
        echo "$i<br/>";
    }

    echo "----------while loop---------<br/>";
    $j = 1;
    while ($j < 11) {
        echo "$j<br/>";
        $j = $j + 1;
    }
    ?>

</body>

</html>