# Intents
* Three of the four core components of an application are activated through intents.
* Allow messaging between components in the same or different application
* Typically used to launch activities

## Intent Object
* An Intent object is a bundle of information.
* Contains information of interest to the component that receives the intent and information of interest to the Android system
* Contents of an Intent Object
  * Component name
  * Action
  * Data
  * Category
  * Extras
  * Flags


## Delivering Intents
* For an Activity, pass an Intent object to  `Context.startActivity()` or `Activity.startActivityForResult() `
* For a Service, pass an Intent object to `Context.startService()`
* For Broadcasts, Intent objects are delivered to all interested broadcast receivers.

## Intent Resolution
* Explicit intents designate the target component by its name
* Implicit intents do not name an activity

## Back Stack
* When an Activity starts another Activity or task, the current activity is put
on the "back stack" and the new activity comes to the foreground
* When the second activity finishes, it is popped of the stack and the user returns
to the first activity
* See Android Developer for more information on [Tasks and Back Stack](https://developer.android.com/guide/components/activities/tasks-and-back-stack.html)

---
## Explicit Intents
* Delivered to an instance of the designated target class
* Typically used for application-internal messages

### Launcher activity
* Defined in the manifest
  * All activities must be declared in the manifest
* Declaration requires name attribute
* Launcher activity includes intent filter

```xml
<intent-filter>
     <action android:name="android.intent.action.MAIN" />
     <category android:name="android.intent.category.LAUNCHER" />
 </intent-filter>
 ```

### Launching another activity
* Create an intent.
* Start the activity.
* When the activity is done and should be closed, call `finish()`
  * Your application will return to calling Activity

### Sending data to another Activity
* Before starting the Activity, store extras in the Intent
  * Use method `putExtra() `
  * Extras are stored as key-value pairs
* Retrieve extras from an Intent in `onCreate`
  * Get the intent from the send using `getIntent()`
  * Get extras using the `getXXXExtra()` method where `XXX` is a data type

### Returning values from other activities
* Use the method `startActivityForResult()`to launch new the new Activity
* Takes two arguments – Activity and request code
  * Request code is often set as a constant
* In the Activity that was launched by the intent, create an intent and add values before calling `finish()`
* Override `onActivityResult()` in launching Activity
---
## Implicit Intents
* Do not specify the component to handle the intent
* Instead allow the system to launch the most appropriate component based on the type of data

### Intent Filters
* Intent filters are structures associated with components that can potentially receive intents.
* Filters advertise the capabilities of a component and the intents it can handle.
* Android finds the most appropriate component  by comparing the contents of the Intent object to intent filters.

## Matching Intent Filters
* Action test:  To pass this test, the action specified in the Intent object must match one of the actions listed in the filter.
* Data test: must match MIME type and potential a URL
* Category test:  For an intent to pass the category test, every category in the Intent object must match a category in the filter

## Launching Implicit Intents
* Create an Intent with an Action and URI
  * A URI consists of a scheme, a path and an optional query
* Use the `startActivity()` method
* You may need to add extra data
* Check for an Activity to handle the intent using `intent.resolveActivity(getPackageManager()) != null`

## Standard Activity Actions
* `ACTION_MAIN`:  launch an Activity
* `ACTION_VIEW`:  render a web page
* `ACTION_EDIT`: display data for a user to edit
* `ACTION_DIAL`: dial a phone number and allow the user to initiate the call  
* `ACTION_WEB_SEARCH`: perform a web search
* More at http://developer.android.com/reference/android/content/Intent.html

## Common Intents
* There are many more intents other than the ones discussed in class. Here is a list of [Common Intents](https://developer.android.com/guide/components/intents-common.html)
