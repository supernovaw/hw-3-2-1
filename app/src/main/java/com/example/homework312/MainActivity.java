package com.example.homework312;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final char POINT = '.';

	private static final int DOT_BUTTON = R.id.buttonDot;
	private static final int C_BUTTON = R.id.buttonClear;
	private static final int SWITCHSIGN_BUTTON = R.id.buttonSwitchSign;

	private TextView textView;
	private Button[] numberButtons;

	private static final String POINT_STRING = Character.toString(POINT);
	private String inputDigits = "0";
	private boolean sign = true; // true-positive; false-negative

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		textView.setText(inputDigits);
		View.OnClickListener padListener = this::handlePress;
		for (Button b : numberButtons)
			b.setOnClickListener(padListener);
	}

	private void initViews() {
		textView = findViewById(R.id.textView);

		numberButtons = new Button[13];

		numberButtons[0] = findViewById(R.id.button0);
		numberButtons[1] = findViewById(R.id.button1);
		numberButtons[2] = findViewById(R.id.button2);
		numberButtons[3] = findViewById(R.id.button3);
		numberButtons[4] = findViewById(R.id.button4);
		numberButtons[5] = findViewById(R.id.button5);
		numberButtons[6] = findViewById(R.id.button6);
		numberButtons[7] = findViewById(R.id.button7);
		numberButtons[8] = findViewById(R.id.button8);
		numberButtons[9] = findViewById(R.id.button9);

		numberButtons[10] = findViewById(DOT_BUTTON);
		numberButtons[11] = findViewById(C_BUTTON);
		numberButtons[12] = findViewById(SWITCHSIGN_BUTTON);
	}

	private void handlePress(View v) {
		switch (v.getId()) {
			case DOT_BUTTON:
				dotPress();
				break;
			case C_BUTTON:
				clear();
				break;
			case SWITCHSIGN_BUTTON:
				switchSign();
			default:
				numPress(v.getId());
				break;
		}

		textView.setText(getInputString());
	}

	private void numPress(int id) { // proceeds number press
		for (int i = 0; i < 10; i++)
			if (id == numberButtons[i].getId()) {
				inputDigits += i;

				if (inputDigits.charAt(0) == '0') { // cut leading zero
					boolean isPointAfterZero = false;
					if (inputDigits.length() >= 2) // to prevent IndexOutOfBoundsException
						isPointAfterZero = inputDigits.charAt(1) == POINT;

					if (!isPointAfterZero) // in case it's i.e. "0.1", don't cut
						inputDigits = inputDigits.substring(1);
				}

				return;
			}
	}

	private void dotPress() { // proceeds dot press
		if (inputDigits.contains(POINT_STRING))
			return;

		inputDigits += POINT;
	}

	private void clear() {
		inputDigits = "0";
		sign = true;
	}

	private void switchSign() {
		sign = !sign;
	}

	private String getInputString() {
		String sign = this.sign ? "" : "-";
		return sign + inputDigits;
	}
}
