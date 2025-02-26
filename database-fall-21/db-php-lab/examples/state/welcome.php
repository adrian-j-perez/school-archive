<?php 
/* This is the welcome page. The user is redirected here
after they successfully log in. */

// Need the session:
session_start();
?>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <title>welcome</title>
        
</head>        
   <h2>Welcome</h2>
	<?php // Print a greeting:
		echo '<h1>Welcome, ' . $_SESSION['email'] . '!</h1>';
	
		// Print how long they've been logged in:
		date_default_timezone_set('America/Chicago');
		echo '<p>You have been logged in since: ' . date('g:i a', $_SESSION['loggedin']) . '</p>';

		// Make a logout link:
		echo  '<p><a href="logout.php">Click here to logout.</a></p>';
		?>
</body>
</html>