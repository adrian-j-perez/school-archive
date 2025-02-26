<?php
if (isset($_GET['logout'])) {
    session_start();
    // remove all session variables
    session_unset();

    // destroy the session
    session_destroy();
}


ob_start();

// Print some introductory text:
echo '<!DOCTYPE html><html lang="en"><head> <meta charset="UTF-8">';
echo '<meta name="viewport" content="width=device-width, initial-scale=1">';
echo '<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">';
echo '<title>Login</title></head>';
echo '<body><main role="main" class="container-fluid"><h1>Login Form</h1>';

// Check if the form has been submitted:
if (isset($_POST['submitted'])) {
    $username = trim(stripslashes($_POST['username']));
    $password = trim(stripslashes($_POST['password']));
    if (($password == '') || ($username == '')) { // Forgot a field.
        echo '<p>Please make sure you enter both an email address and a password!</p>';
    } else {


            //open database connection
        @ $db = new mysqli('localhost', 'bookorama', 'cpsc33000', 'books');


        if ($db->connect_error) {
            die('Connect Error ' . $db->connect_errno . ': ' . $db->connect_error);
        }

        $query = "SELECT password FROM users WHERE  name = '".$username."'";
        $result = $db->query($query);
        $num_results = $result->num_rows;
        if ($num_results == 1) {
            $row = $result->fetch_assoc();
            $hash_stored = $row['password'];
            $parts = explode('$', $hash_stored);
            $method = $parts[1];
            $rounds = $parts[2];
            $salt = $parts[3];
            $test_hash = crypt($password, '$'.$method.'$'.$rounds.'$'.$salt.'$');
            if ($test_hash == $hash_stored) {
                session_start();
                $_SESSION['username'] = $username;

                //redirect
                header('Location: home.php?sessionid='.session_id());
            } else {
                echo '<h2>Invalid password!<br />Try again.</h2>';
            }
        } else { // Incorrect!
            echo '<h2>User name not found!<br />Try again.</h2>';
        }
    }
} ?>



	<form action="book_login.php" method="post">
        <div id="order-data" class="form-div">
            <div class="form-group">
                <label for="username" >Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" />
            </div>
            <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>

        </div>
        <button type="submit" class="btn btn-primary">Log in</button>

		<input type="hidden" name="submitted" value="true" />

	</form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
