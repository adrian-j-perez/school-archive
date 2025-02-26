# Arrays

## PHP Arrays
- A PHP array is a generalization of the arrays of  other languages
- Two types
  - Indexed arrays
  - Associative arrays
- PHP arrays have an internal order, usually the order the elements were inserted

## Storing data in arrays
- Storing a value in an array will create the array if it didn't already exist
- Can use simple assignment to initialize an array
  - Indexed array
    ```php
    $addresses[0]="howardcy@lewisu.edu";
    $addresses[2]="cjhoward106@gmail.com";
    ```
  - Associative array
    ```php
    $addresses['lewis']="howardcy@lewisu.edu";
    $addresses['personal']="cjhoward106@gmail.com";
    ```

## Storing data in arrays
- Can also use the `array()` construct
    - takes one or  more key => value pairs
    ```php  
    $list = array(0 => "apples", 1 => "oranges", 2 => "grapes")
    $days=array(1=> "Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday")
    ```
- To construct an empty array, pass no arguments to array()
  ```php
  $addresses = array();
  ```

## Accessing Array Elements
- Access specific elements by using the array variable's name followed by the key in square brackets
  ```php
  $age["Fred"];
  $age[2];
  $list[4] = 7;
  $list["day"] = "Tuesday";
  $list[] = 17;
  ```
- If an element with the specified key does not exist, it is created
- If the array does not exist, the array is created

## Traversing arrays
- There are several different ways to traverse arrays in PHP
  - Using a for loop
  - The foreach construct
  - Iterator functions

- Example: [array-test.php](../array-test.php)

* for loop example:
  ```php
  $colors = array("red", "yellow", "green",  "purple", "blue");
  for($i=0; $i < count($colors); $i++){
     echo $colors[$i] . "<br>";
  }
  ```
* foreach example:
  ```php
  foreach($addresses as $addr)
         echo $addr . "<br>";
  ```
* An alternative form gives you access to the current key
  ```php
  foreach($addresses as $key => $value)
     echo "$key  address is $value <br>";
  ```
