<!doctype html>
<html lang="en">
<!-- pass-hash.php
     An example to passwords and hashing
     -->
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>PHP and HTML</title>
</head>
<body>
    <main role="main" class="container-fluid">
        <h1> Password Test</h1>

<?php
$password = 'password123';
$encrypted = crypt('flyers2016','1234');

echo "<p>Encrypted: ".$encrypted."</p>";


// generate a 16-character salt string
$salt = substr(str_replace('+','.',base64_encode(md5(mt_rand(), true))),0,16);

// how many times the string will be hashed
$rounds = 10000;

// pass in the password, the number of rounds, and the salt
// $5$ specifies SHA256-CRYPT, use $6$ if you want SHA512
$hash_stored = crypt($password, '$5$rounds='.$rounds.'$'. $salt.'$');


echo "<p>Hash stored: " .$hash_stored."<br/>";

// the hash  to stored for the user
$test_pw = 'password123';

// extract the hashing method, number of rounds, and salt from the stored hash
// and hash the password string accordingly
$parts = explode('$', $hash_stored);
$method = $parts[1];
$rounds = $parts[2];
$salt = $parts[3];
$test_hash = crypt($test_pw, '$'.$method.'$'.$rounds.'$'.$salt.'$');
echo "Entered hash:". $test_hash . "<p/>" ;
// compare

if($hash_stored === $test_hash)
	echo "<h4>The passwords match</h4>";
else
	echo "<h4>The passwords do not match</h4>";


?>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
