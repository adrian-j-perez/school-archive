<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title> Order Summary </title>

</head>

  <body>
    <?php

// Get form data values

      $unpop = $_POST["unpop"];
      $caramel = $_POST["caramel"];
      $caramelnut = $_POST["caramelnut"];
      $toffeynut = $_POST["toffeynut"];
      $name = $_POST["name"];
      $email = $_POST["email"];
      $payment = $_POST["payment"];

// If any of the quantities are blank, set them to zero

      if ($unpop == "") $unpop = 0;
      if ($caramel == "") $caramel = 0;
      if ($caramelnut == "") $caramelnut = 0;
      if ($toffeynut == "") $toffeynut = 0;

// Compute the item costs and total cost

      $unpop_cost = 3.0 * $unpop;
      $caramel_cost = 3.5 * $caramel;
      $caramelnut_cost = 4.5 * $caramelnut;
      $toffeynut_cost = 5.0 * $toffeynut;
      $total_price = $unpop_cost + $caramel_cost +
                     $caramelnut_cost + $toffeynut_cost;
      $total_items = $unpop + $caramel + $caramelnut + $toffeynut;

// Return the results to the browser in a table

    ?>
    <main role="main" class="container-fluid">
        <h1> Popcorn Order Summary</h1>

    <p><strong> Customer: </strong>
    <?php
      print ("$name");
    ?>
    <br/>

    <strong> Email Address: </strong>
    <?php
      print ("$email");
    ?>
    </p>

    <?php if($total_items > 0){ ?>


        <table class="table table-bordered">
          <tr>
            <th> Product </th>
            <th> Unit Price </th>
            <th> Quantity Ordered </th>
            <th> Item Cost </th>
          </tr>

          <?php if($unpop > 0){ ?>
              <tr>
                <td> Unpopped Popcorn </td>
                <td> $3.00 </td>
                <td> <?php print ("$unpop"); ?> </td>
                <td> <?php printf ("$ %4.2f", $unpop_cost); ?>
                </td>
              </tr>
         <?php } ?>

         <?php if($caramel > 0){ ?>
          <tr >
            <td> Caramel Popcorn </td>
            <td> $3.50 </td>
            <td> <?php print ("$caramel"); ?> </td>
            <td> <?php printf ("$ %4.2f", $caramel_cost); ?>
            </td>
            </tr>
        <?php } ?>

        <?php if($caramelnut > 0){ ?>
          <tr >
            <td> Caramel Nut Popcorn </td>
            <td> $4.50 </td>
            <td> <?php print ("$caramelnut"); ?> </td>
            <td> <?php printf ("$ %4.2f", $caramelnut_cost); ?>
            </td>
          </tr>
        <?php } ?>
        <?php if($toffeynut > 0){ ?>
          <tr >
            <td> Toffey Nut Popcorn </td>
            <td> $5.00 </td>
            <td> <?php print ("$toffeynut"); ?> </td>
            <td> <?php printf ("$ %4.2f", $toffeynut_cost); ?>
            </td>
          </tr>
         <?php } ?>
    </table>
    <br/>
    <br/>


    <?php
    }
      print ("You ordered $total_items  items <br />");
      printf ("Your total bill is: $ %5.2f <br />", $total_price);
      print ("Your chosen method of payment is: $payment <br />");
    ?>
</main>
  </body>
</html>
