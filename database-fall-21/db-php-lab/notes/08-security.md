# Web Application Security

## Strategies
- Security is not a feature
- Balance security and usability
- Monitor Security

## Threats
- Access to data
- Data loss
- Denial of service
- Malicious code injection
- Compromised server

## Filter Input
- Double check expected values
- Filter all data
- Make strings safe for SQL
  - Use [`mysqli.real-escape-string`](http://php.net/manual/en/mysqli.real-escape-string.php)
- Validate input

## Escaping Output
- Use [`stripslashes`](http://php.net/manual/en/function.stripslashes.php)
- Use [`htmlentitites`](http://php.net/manual/en/function.htmlentities.php)

## Authentication

### Implementing Access Control
- User table
  - Stores login information
- Use [`crypt`](http://php.net/manual/en/function.crypt.php) function
- Hash passwords using [`password_hash`](http://php.net/manual/en/function.password-hash.php) if PHP 5.5 or greater
- Example:  [pass_hash.php](../examples/pass-hash.php)


### Validating User
- PHP script will query the database for user information
  - Verify password
- Use cookies or sessions to keep user logged in
- Example:  [book_login.php](../examples/authentication/book_login.php) and [home.php](../examples/authentication/home.php)
