# Web Development Basics

## HTML
### HTML 5 Document Structure
```html
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Title of the document</title>
</head>
<body>
Content of the document......
</body>
</html>
```

### Standard HTML Document Structure
- HTML element encloses the head section and the body section
- Head section contains information about the HTML document
- `<html>`, `<head>`, `<title>`, and `<body> `are required  in every document
- You can only have one of each of these

### Important HTML Tags
- Headings
  - Six sizes, 1 - 6, specified with `<h1>` to `<h6>`
- Paragraphs
  - The `<p>` tag breaks the current line and inserts a blank line - the new line gets the beginning of the content of the paragraph.
- Line break
  - `<br />`
- Hypertext Link
  - A link is specified with the `href` (hypertext reference) attribute of `<a>` (the anchor tag)
    - The text displayed as a link is placed between `<a>` and `</a>`
- Images
  - To insert an image use:
`<img src="image.jpg" />`
  - `src` is an attribute
  - specify image using a relative file path
  - self-closing tag
- Line
  - `<hr/>`

## CSS

### CSS
CSS styles look like this
```CSS
selector {
  property: value;
}
```
- The selector points to the element that you want to style
- Properties are things like colors and fonts
- Be careful with the curly braces.
  - They mark the start and finish of a style.

### Text Color
- The color property is used to set the color of the text.
- With CSS, a color is most often specified by:
  - a HEX value - like "#ff0000"
  - an RGB value - like "rgb(255,0,0)"
  - a color name - like "red"
- [Some HTML colors](http://www.w3.org/TR/css3-color/#svg-color)
- Example:

```CSS
h1 {
    color: purple;
}
```

### Fonts
- You can specify font families, font styles and font sizes in CSS
- Font families are specified using the font-family property
  - [Some font families](http://www.w3.org/TR/CSS21/fonts.html#ge)
- The font-style property has three  possible values
  - normal
  - italic
  - oblique
- Font size is specified using the font-size property
  - Use pixels (px) for a fixed size
  - Use em for relative size
    - 1 em = current font size
- Example

```CSS
p{
    font-family: Arial, serif;
    font-style: italic;
    font-size: 1.2em;
}
```

### Class Selector
- You can use class selectors are used to specify elements on a page
- Use the class attribute of  the HTML  tag to set the attribute
- Example:

```HTML
<p class="myclass">Some text</p>
```
- With CSS, to specify formatting for an element with a specific class, use a dot character, followed by the class of the element.
- Example:

```CSS
myclass{
  color:red;
}
```

## Bootstrap
- Bootstrap is a free front-end framework for faster and easier web development
- Bootstrap gives you the ability to easily create responsive designs
- Bootstrap includes HTML and CSS based design templates for typography, forms, buttons, tables, navigation, images and many other

### Using Bootstrap

- This [link](https://getbootstrap.com/docs/5.0/getting-started/introduction/) has Bootstrap setup information and links for CDNs for JavaScript and Bootstrap CSS.
