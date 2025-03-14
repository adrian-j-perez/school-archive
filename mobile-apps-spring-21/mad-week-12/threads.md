# Threads
- Computers can perform operations concurrently.
- Operating systems on single-processor computers create the illusion of concurrent execution by rapidly switching between activities.
  - Time slicing

## Concurrent programming
In concurrent programming, there are two basic units of execution:
- Process: Generally has a complete, private set of basic run-time and  resources and its own memory space
- Threads: Exist within a process — every process has at least one.
  - Sometimes called lightweight processes


- Multithreaded execution is an essential feature of the Java platform.
  - Makes the UI more responsive
  - Allows for asynchronous or background processing


## Using Threads in Android
- One Approach:
  - Create a `Handler` object in your UI thread
  - Use worker threads to perform any required expensive operations
  - Post results either through a `Runnable` or a `Message`
  - Update the views on the UI thread as needed


- Another approach - use `AsyncTask`
  - Uses [Java Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html) which allow a type or method to operate on objects of various types
  - Must specify
    - the type of the parameters
    - the progress values
    - the final value of the task


## Volley
Volley is a library that can be used to simplify the use of additional threads.  It uses a number of objects to interact with a web API:
- A `RequestQueue` manages worker threads that make HTTP requests and parse HTTP responses.
- A `JsonObjectRequest` object with a URL, Response.Listener, and Response.ErrorListener are added to the RequestQueue to make an HTTP request for a JSON response.
- A `Response`.Listener defines an `onResponse()` callback that is passed a JSONObject when the HTTP response is received.
- A `JSONObject` object is a set of name-value pairs created from JSON data. The values may be JSONObjects, JSONArrays, strings, booleans, and other number data types.
- A `Response`.ErrorListener defines an `onErrorResponse()` callback that is called if the HTTP response has a 4xx or 5xx error status code.
