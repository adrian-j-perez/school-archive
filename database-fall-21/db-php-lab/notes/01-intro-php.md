# Introduction to PHP
- PHP is a scripting language that generates HTML
- If you aren't familiar with HTML, review [HTML Basics](notes/html-basics.md) before continuing

# What is PHP?
- PHP is one of the most popular server-side scripting languages for creating dynamic web pages.
- PHP is open source.
- PHP can be used on all major operating systems.
- PHP has support for most of the web servers today.
- PHP is purely interpreted.


# General Syntactic Characteristics
PHP code can be specified in an HTML document internally or externally:

 Internally:  
 ```
<?php
…
?>
```

Externally:  
```HTML
include ("myScript.inc")
```

**A file can contain both PHP and HTML but the file must have the extension `php`**


# Statements and semi-colons
- A statement is a collection of PHP code that does something.
- PHP uses semicolons to separate statements.
- Compound statements use curly brackets to mark blocks of code.


# Whitespace and line breaks
- In general whitespace and line breaks don't matter in PHP programs



# Case Sensitivity
- Names of user-defined classes and function, built-in constructs and keywords are case insensitive.
- Variables are case-sensitive.


# Comments

- Shell-style comments
```
  ############
  #  functions
  ############
```

- C++ style comments

```
////////////
// functions
///////////
```

- C style comments

```
/*
  functions
*/
```


# Literals

Literals are data values that appear directly in a program

Examples:

  - `1999`
  - `0xAF`
  - `3.1415`
  - `"hello world"`
  - `true`
  - `null`


# Identifiers
- In PHP identifiers are used to name variables, functions, constants and classes
- General rules
  - Identifiers can include letters, digits and the underscore
  - First character must be either a letter or an underscore
- **Variable names**
  - **Begin with a dollar sign** `$`
  - Case-sensitive
- Function names are not case-sensitive
- Class names are not case-sensitive


# Constants
- Identifiers for a simple value only
- Boolean, integer, double and string
- Once set cannot be changed
- Set using the `define` statement
  ```php
  define('NAME', "Cindy");
  define ('PI', 3.1415);
  ```

# Keywords
A list of keywords can be found at [here](http://php.net/manual/en/reserved.keywords.php)

# Data Types
- Scalar types
  - Integers, floating-point numbers, strings and Booleans
- Compound types
  - Arrays and objects
- Special types
  - Resource and NULL

# Numbers
- Integers
  - Can be written in decimal, octal or hexadecimal
  - Use `is_int()` function to test whether a value is an integer
- Floating-point numbers
  - Standard notation
  - Floating point notation
  - Use `is_float()` function to test whether a value is a floating-point number

# Strings
- Delimited either by single quotes or double quotes
- - Variables are expanded within double quotes but not within single quotes
Escape characters are supported within double quotes
- Use `is_string()` to test whether a value is  a string

# Booleans
- The following values will evaluate to false
  - Keyword `false`
  - Integer `0`
  - Floating-point value `0.0`
  - Empty string
  - String `"0"`
  - Array with zero elements
  - Object with no values or functions
  - Null
- Everything else will evaluate to true
- Use `is_bool()` to test whether a value is a Boolean

# Resources
- Anything that is not PHP data
- Holds a handle to the actual data
- Example:  database, image

# NULL
- Represents that a variable has no value

# Variables
- **Variables in PHP are identifiers prefaced with a `$`**
- Variables may hold any type of data
  - No compile-time or run-time checking
  - Loosely typed
- There is no explicit syntax for declaring variables
  - The first time the value of a variable is set the variable is declared
- An unassigned (unbound) variable has the value NULL

# Output
- Output from a PHP script is HTML that is sent to the browser
- Use `echo` for basic output
  ```php
  echo "This is too <br /> much fun <br />";
  echo 72;   
  ```

# Formatted Output
- For formatted output can use printf
  - Works like in  C++ or Java
- Can also format numbers using `number_format`
  - Formats number with commas and specified number of decimal places

  ```php
  echo number_format($number, 2)
  ```
- To round a number use the round() function

# PHP code is placed in the body of an HTML document
```php
   <html>   
    <head>
      <title> Trivial php example </title>
    </head>
    <body>
     <?php
      echo"Welcome to my Web site!";
     ?>
   </body>
  </html>
```

Example:  [today.php](../today.php)

## [Lab Exercise 1](../exercises/exercise-1.md)
