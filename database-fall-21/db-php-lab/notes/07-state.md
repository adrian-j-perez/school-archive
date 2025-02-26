# Redirection and State

## Headers and Redirection

### Setting Response Headers
- PHP and Apache normally take care of the headers
- To send information to the client that's not HTTP, you need to use the response header
- Use the `header()` or `setcookie()` function

### Header function
- Headers must be set before any of the body is generated
- `header()`must be called at the very top of the file
  - Before the `<html>` tag

### Different content types
- The `Content-Type` header identifies the type of document being returned
  - Usually `text/html`
- There are other useful document types
  - `text/plain` : forces the browser to treat the page as plain text  (useful for debugging)
  - images: `image/jpeg`, `image/gif/`, `image/png`
  - `text/xml`

### Redirection
- To send a browser to a new URL, set the Location header
- Examples: [redirect.php](../examples/state/redirect.php) and [redirect1.php](../examples/state/redirect1.php)

### Output buffering
- With output buffering the HTML and data will be put into a buffer
- Turn on output buffering with the `ob_start()` function
- To discard the data in the buffer use the `ob_end_clean()` function
- To send the buffer to the browser use the `ob_end_flush()` function


## Maintaining State

### Why?
- HTTP is a stateless protocol
- Why would we want to maintain state?
- PHP has two main methods for tracking data
  - Cookies
  - Sessions

### Cookies
- A cookie is a name/value pair that is passed between a browser and a server in the HTTP  header
- In PHP, cookies are created with `setcookie(cookie_name, cookie_value, lifetime)`
```
setcookie("voted", "true", time() + 86400);
```
- Cookies are implicitly deleted when their lifetimes are over

### Cookie Notes
- Cookies are stored in a Web browser but only the site that originally sent a cookie can read it
- Cookies are read by a site when the page on that site is requested by the web browser
- Cookies are generally limited to about 4kb of total data
- Cookies must be created before any other HTML is created by the script
- Cookies are obtained in a script the same way form values are gotten, using the `$_COOKIES` array

### Accessing cookies
- When the browser sends a cookie back to the server, you can access the cookie through `$_COOKIE` array.
- A cookie is never accessible immediately after it's been set

### Deleting cookies
- You can use the `setcookie()` function to delete a cookie
  - name parameter is required
  - set the remaining parameters to blank or for expiration, a time in the past
```
setcookie('user', '', time()-3600);
```
- Example:  [customize.php](../examples/state/customize.php),  [view_settings.php](../examples/state/view_settings.php) and [reset.php](../examples/state/reset.php)

### Sessions
- Provide a way to track data for a user over a series of pages
- When you start a session, PHP generates a random session ID, a reference to that particular session and its stored data
- Subsequent PHP pages will use this cookie to retrieve the session ID and access the session information

### Creating a session
- Use the `session_start()` function
- This function sets a cookie so it must be called prior to any HTML or white space
- The first time a session is started a random session ID is generated and a cookie is sent to the Web browser with the name `PHPSESSID` and a value

### Recording session data
- Record data by assigning values to the $_SESSION array
- Example: `$_SESSION['name']='Cindy';`
- Each time you do this, PHP writes the data to a temporary file stored on the server

### Accessing session variables
- Begin the session by calling the `session_start()` function
- Access values by retrieving them from the `$_SESSION` array

### Deleting a session
- Delete the session variables by unsetting the `$_SESSION` array
```
unset($_SESSION);
```

- Remove the session data from the server
```
session_destroy();
```

### Session life
- Sessions don't persist after the browser ceases to exist
- You can use cookies to allow some session information to persist after the browser is closed
- Example:  [login.php](../examples/state/login.php), [welcome.php](../examples/state/welcome.php) and [logout.php](../examples/state/logout.php)

### Benefits of sessions over cookies
- Session are generally more secure because the data isn't repeatedly transmitted back and forth between the client and the server
- Sessions let you store more information than you can in a cookie
- Session can be made to work even if the user doesn't accept cookies in their browser

### Benefits of cookies over sessions
- Marginally easier to create and retrieve
- Require slightly less work from the server
- Normally persist over a longer period of time

### How to choose
- Use cookies in situations where
  - security is less of an issue
  - a minimum amount of data is being stored
- Otherwise use sessions
  - May require more effort when writing scripts
