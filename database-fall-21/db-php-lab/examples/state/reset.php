<?php 

// Delete the cookies:
setcookie('font_size', '', time() - 3600);
setcookie('font_color', '', time() - 3600 );

?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<title>Reset Your Settings</title>
</head>
<body>

<p>Your settings have been reset! Click <a href="view_settings.php">here</a> to go back to the main page.</p>

</body>
</html>
