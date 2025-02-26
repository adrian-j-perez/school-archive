<?php 
/* This is the logout page. It destroys the session information. */

// Need the session:
session_start();

// Delete the session variable:
unset($_SESSION);

// Destroy the session data:
session_destroy();
?>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">    
        <title><Logout></title>
</head>
<body>
	 <h1>You are now logged out.</h1>
	 <p>Thank you for using this site. We hope that you liked it.<br />
	 Blah, blah, blah...
	 Blah, blah, blah...</p>
</body>
</html>