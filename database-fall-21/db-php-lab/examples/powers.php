<!doctype html>
<html lang="en">
<!-- powers.php
     An example to illustrate loops and arithmetic
     -->

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>powers.php</title>
</head>
<body>
    <main role="main" class="container-fluid">
        <h1> Powers table </h1>
        <table class="table table-bordered">
          <tr>
    	<th> Number </th>
    	<th> Square Root </th>
    	<th> Square </th>
    	<th> Cube </th>
    	<th> Quad </th>
          </tr>
          <?php
        for ($number = 1; $number <=10; $number++) {
            $root = sqrt($number);
            $square = pow($number, 2);
            $cube = pow($number, 3);
            $quad = pow($number, 4);
            echo("<tr> <td> $number </td>");
            echo("<td> $root </td> <td> $square </td>");
            echo("<td> $cube </td> <td> $quad </td> </tr>");
        }
          ?>
        </table>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>
