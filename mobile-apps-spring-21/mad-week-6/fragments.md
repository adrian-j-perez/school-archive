# Fragments
- App components that represent a self-contained portion of user interface in an activity
	- Mini-activities
- Allow us to modularize our activities
- Can dynamically add and remove fragments while app is running
- Fragments are designed to be placed, alone or in groups, in a single activity

## Advantages of using fragments
- Easy to reuse components in different layouts
- Can use fragments to support different devices and orientations

## Important points about Fragments
- A fragment has its own layout
- A fragment has its own behavior with its own lifecycle callbacks.
- You can add or remove fragments in an activity while the activity is running.
- You can combine multiple fragments in a single activity to build a multi-pane UI.
- A fragment can be used in multiple activities.
- A fragment can implement a behavior that has no user interface component.
- Fragments may only be used as part of an activity and cannot be instantiated as standalone application elements

## Fragment Lifecycle
- Fragments must be embedded in an activity
- Fragments have their own lifecycle which is affected by the host lifecycle
	- As the host activity moves through its callbacks the fragment does too
	- Can put code in either the host or the fragment
- A fragment has some additional lifecycle callbacks
	- `onCreateView`  instead of `onCreate`
	-  onDestroyView`

## Fragment Naming Convention
- The class name should be in CamelCase.
	- Ex: `SignInFragment`
- The corresponding XML file should follow this naming convention `fragment_<FRAGMENT_NAME>.xml`
	- Ex: `fragment_sign_in.xml`
- For a full list of Android naming conventions, checkout [android-guidelines/project_and_code_guidelines.md at master · ribot/android-guidelines · GitHub](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md).

## Adding a fragment
- Create a Layout
- Create a Java class that extends the `Fragment` class
	- Use the support library
	- Include default constructor
	- Also include `onCreateView` method
		- returns the inflated layout
- Add a frame layout to the host activity layout
- In the host activity add the fragment using the fragment manager and fragment transaction

## Fragment Manager and Transaction
- Fragment Manager is used to add, remove and replace fragments
- You’ll need a container view to to reference and hold each location
	- Usually an empty `FrameLayout`
- If the fragment is static, you do not need a container


## Fragment Arguments
- Avoid retrieving values from host activity
- Fragments have arguments which are passed in a Bundle object that contains key-value pairs


## Support Library
- Can update your app by updating the version of the support library
  - New features could be added to the fragment support  library
  - Bug fixes
- No significant downsides to using support library
  - May increase the size of your application
  - Size of library is small < 1MB
