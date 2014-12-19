package com.JZHeadley.Calculations;

import java.text.DecimalFormat;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import 	android.view.inputmethod.InputMethodManager;

public class QuadraticCalc extends ActionBarActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_calc);

        //Creating Button variable
        Button button = (Button) findViewById(R.id.calculateButton);

        //Adding Listener to button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Creating TextView Variable
                TextView answer = (TextView) findViewById(R.id.quadAnswer);

                DecimalFormat DecimalFormat = new DecimalFormat("#,###.#####");
                EditText editTextA = (EditText) findViewById(R.id.AVariable);
                EditText editTextB = (EditText) findViewById(R.id.BVariable);
                EditText editTextC = (EditText) findViewById(R.id.CVariable);
                String AVariable = editTextA.getText().toString();
                String BVariable = editTextB.getText().toString();
                String CVariable = editTextC.getText().toString();
                // end getting variables

                if (AVariable == "" || BVariable == "" || CVariable == "") {
                    String Answer = "You did not input one of your values.";
                    answer.setText(Answer);
                } else {
                    double a = HandleException(AVariable);
                    double b = HandleException(BVariable);
                    double c = HandleException(CVariable);

                    if (a == Double.POSITIVE_INFINITY || b == Double.POSITIVE_INFINITY || c == Double.POSITIVE_INFINITY) {
                        String Answer = "You did not enter a number.  Please Try Again.";
                        answer.setText(Answer);
                    }
                    if (a == 0) {
                        String Answer = "Your A value can not be 0";
                        answer.setText(Answer);
                    } else {
                        double NewB = b * -1;
                        double bInRoot = Math.pow(b, 2);
                        double rightRoot = 4 * a * c;
                        double inRoot = bInRoot - rightRoot;
                        double Root = Math.pow(inRoot, (1.0 / 2));

                        if (inRoot <= -1) {
                            String Answer = "The equation entered does not have any roots.  There is a negative under the square root.";
                            answer.setText(Answer);
                        } else {
                            double plusNumerator = NewB + Root;
                            double minusNumerator = NewB - Root;
                            double denominator = 2 * a;
                            double plusRoot = plusNumerator / denominator;
                            double minusRoot = minusNumerator / denominator;

                            if (plusRoot == Double.NaN || plusRoot == Double.POSITIVE_INFINITY || plusRoot == Double.NEGATIVE_INFINITY || minusRoot == Double.NaN || minusRoot == Double.POSITIVE_INFINITY || minusRoot == Double.NEGATIVE_INFINITY) {
                                String Answer = "There were problems with the numbers you entered";
                                answer.setText(Answer);
                            } else {
                                String Answer = "The two roots of the equation you entered are " + DecimalFormat.format(plusRoot) + " and " + DecimalFormat.format(minusRoot) + ".";
                                answer.setText(Answer);

                            }

                        }

                    }

                }
            }

        });
    }
	public double HandleException (String Variable) {
		try {
			double Value = Double.parseDouble(Variable);
			return Value;
		}catch(NumberFormatException nfe) {
			double Answer = Double.POSITIVE_INFINITY;
			return Answer;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quadratic_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void hideKeyboard(View view){
        EditText A = (EditText) findViewById(R.id.AVariable);
        EditText B = (EditText) findViewById(R.id.BVariable);
        EditText C = (EditText) findViewById(R.id.CVariable);
        // hides keyboard if first EditText is selected
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(A.getWindowToken(), 0);
        // hides keyboard if second EditText is selected
        imm.hideSoftInputFromWindow(B.getWindowToken(), 0);
        // hides keyboard if third EditText is selected
        imm.hideSoftInputFromWindow(C.getWindowToken(), 0);
    }
}
