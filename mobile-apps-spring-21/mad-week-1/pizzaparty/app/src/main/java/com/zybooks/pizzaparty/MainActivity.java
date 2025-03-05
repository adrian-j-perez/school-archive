package com.zybooks.pizzaparty;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.os.Bundle;
// set it where to auto import in ide

public class MainActivity extends AppCompatActivity {

    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumAttendEditText = findViewById(R.id.attendEditText); // calling the xml part
        mNumPizzasTextView = findViewById(R.id.answerTextView);
        mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);

    }

    public void calculateClick(View view) {
        String numAttendStr = mNumAttendEditText.getText().toString();
        int numAttend = Integer.parseInt(numAttendStr);

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

        int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson /
                (double) SLICES_PER_PIZZA);
        mNumPizzasTextView.setText("Total pizzas: " + totalPizzas);

    }
}