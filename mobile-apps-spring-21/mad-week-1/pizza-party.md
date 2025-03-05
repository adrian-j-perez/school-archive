# Code for Pizza Party App

### activity_main.xml
xml layout for the pizza party app

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:tools="http://schemas.android.com/tools"
   android:id="@+id/activity_main"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   android:paddingBottom="16dp"
   android:paddingLeft="16dp"
   android:paddingRight="16dp"
   android:paddingTop="16dp"
   tools:context="edu.lewisu.example.pizzaparty.MainActivity">

   <TextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:text="Number of people?"
       android:textSize="24sp"
       android:labelFor="@id/attendEditText" />

   <EditText
       android:id="@+id/attendEditText"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:inputType="number"
       android:ems="5"
       android:importantForAutofill="no"
       android:hint="10" />

   <TextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:layout_marginTop="20dp"
       android:text="How hungry?"
       android:textSize="24sp"
       android:labelFor="@id/hungryRadioGroup" />

   <RadioGroup
       android:id="@+id/hungryRadioGroup"
       android:layout_width="fill_parent"
       android:layout_height="wrap_content"
       android:orientation="horizontal">
       <RadioButton
           android:id="@+id/lightRadioButton"
           android:text="Light"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content" />
       <RadioButton
           android:id="@+id/mediumRadioButton"
           android:text="Medium"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:checked="true" />
       <RadioButton
           android:id="@+id/ravenousRadioButton"
           android:text="Ravenous"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content" />
   </RadioGroup>

   <TextView
       android:id="@+id/answerTextView"
       android:text="Total pizzas: ?"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:layout_marginTop="20dp"
       android:textSize="24sp"/>

   <Button
       android:id="@+id/calcButton"
       android:text="Calculate"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:layout_marginTop="20dp"
       android:onClick="calculateClick" />
</LinearLayout>
```

### MainActivity.java
Java Code

```java
public class MainActivity extends AppCompatActivity {

    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumAttendEditText = findViewById(R.id.attendEditText);
        mNumPizzasTextView = findViewById(R.id.answerTextView);
    }

    public void calculateClick(View view) {
      String numAttendStr = mNumAttendEditText.getText().toString();
      int numAttend = Integer.parseInt(numAttendStr);           

      int slicesPerPerson = 4;
      int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson /
         (double) SLICES_PER_PIZZA);
      mNumPizzasTextView.setText("Total pizzas: " + totalPizzas);
  }
}
```


### MainActivity.java (updated)
Declare a reference to the radio button group at the top of MainActivity

```java
private RadioGroup mHowHungryRadioGroup;
```
Then get a reference to the Radio group in `onCreate`
```java
mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);
```

Update `calculateClick` by replacing `int slicesPerPerson = 4;` with the code below

```java
   // Determine how many slices on average each person will eat
   int slicesPerPerson = 0;
   int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
   if (checkedId == R.id.lightRadioButton) {
       slicesPerPerson = 2;
   }
   else if (checkedId == R.id.mediumRadioButton) {
       slicesPerPerson = 3;
   }
   else if (checkedId == R.id.ravenousRadioButton) {
       slicesPerPerson = 4;
   }

   }
   ```
