# Arithmetic Operators and Expressions

## Usual operators:
-  `+` `-` `*` `/` `%`
- [PHP operators and operator precedence](http://php.net/manual/en/language.operators.php)
- Arithmetic functions
  - `floor`, `ceil`, `round`, `abs`, `min`, `max`, `rand`, etc.

## Automatic type conversion
- PHP will automatically convert one type of variable to another whenever possible
- Implicit casting rules:
  - Float and integer:  integer is converted to a floating-point number
  - Integer and string: String is converted to a number

## String operators and functions
- The only operator is period, for catenation
  ```php
  "good " . "morning"
  ```

- Indexing
  - `$str{3}` is the fourth character
- String Functions
  - `chop` – remove whitespace from the right end
  - `trim` – remove whitespace from both ends
  - `ltrim` – remove whitespace from the left end
  - `strtolower` and `strtoupper`

## Comparison operators
- Compares numbers numerically and strings lexicographically
- Equality vs. identity
  - Equality `==`  compares if two values are equal
  - Identity `===` compares if two values are the same type and are equal
- Not-equal vs. Not identical
  - Not-equal `!=` or `<>`
  - Not identical `!==`

## Logical operators
- And:  `&&` , `and`
- Or:  `||`, `or`
- Exclusive or:  `xor`
- Not: `!`

## Casting operators
- `(int)`
- `(float)`
- `(string)`
- `(bool)`
- `(array)`
- `(object)`

## [Lab Exercise 2](../exercises/exercise-2.md)
