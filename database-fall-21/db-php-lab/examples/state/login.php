<?php // 
/* This page lets people log into the site (almost!). */

ob_start();

// Print some introductory text:
echo '<!DOCTYPE html><html><head> <meta charset="UTF-8"><title>Login</title></head>';
echo '<body><h1>Login Form</h1><p>Users who are logged in can take advantage of certain features like this, that, and the other thing.</p>';

// Check if the form has been submitted:
if ( isset($_POST['submitted']) ) {

 	// Handle the form:
	if ( (!empty($_POST['email'])) && (!empty($_POST['password'])) ) {
	
		if ( (strtolower($_POST['email']) == 'howardcy@lewisu.edu') && ($_POST['password'] == 'testpass') ) { // Correct!
	
			// Do session stuff:
			session_start();
			$_SESSION['email'] = $_POST['email'];
			$_SESSION['loggedin'] = time();
			
			// Redirect the user to the welcome page!
			ob_end_clean(); // Destroy the buffer!
			header ('Location: welcome.php');
			exit();
		
		} else { // Incorrect!
			echo '<p>The submitted email address and password do not match those on file!<br />Go back and try again.</p>';
		
		}
	
	} else { // Forgot a field.
	    
		echo '<p>Please make sure you enter both an email address and a password!<br />Go back and try again.</p>';
		
	}

} else { // Display the form. ?>

	<form action="login.php" method="post">
		<p>Email Address: <input type="text" name="email" size="20" /></p>
		<p>Password: <input type="password" name="password" size="20" /></p>
		<p><input type="submit" name="submit" value="Log In!" /></p>
		<input type="hidden" name="submitted" value="true" />

	</form>
<?php } ?>	
</body>
</html>