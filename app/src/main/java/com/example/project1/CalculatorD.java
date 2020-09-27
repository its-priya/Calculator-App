package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import  org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculatorD extends AppCompatActivity implements View.OnClickListener {
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button bAdd, bSub, bMul, bDiv, bClear, bDelete, bDec, bExponent, bEqual;
    TextView inputs, result;
    boolean decimal = true;
    String inputExp, resultStr;

    public void updateText(String c){
        inputExp= result.getText().toString();
        result.setText(inputExp+c);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_d);
        // Number Buttons
        b0 = (Button) findViewById(R.id.zero);
        b1 = (Button) findViewById(R.id.one);
        b2 = (Button) findViewById(R.id.two);
        b3 = (Button) findViewById(R.id.three);
        b4 = (Button) findViewById(R.id.four);
        b5 = (Button) findViewById(R.id.five);
        b6 = (Button) findViewById(R.id.six);
        b7 = (Button) findViewById(R.id.seven);
        b8 = (Button) findViewById(R.id.eight);
        b9 = (Button) findViewById(R.id.nine);
        // Operator Buttons
        bAdd = (Button) findViewById(R.id.add);
        bSub = (Button) findViewById(R.id.subtract);
        bMul = (Button) findViewById(R.id.multiply);
        bDiv = (Button) findViewById(R.id.divide);
        bDec = (Button) findViewById(R.id.decimal);
        bClear = (Button) findViewById(R.id.clear);
        bEqual = (Button) findViewById(R.id.equal);
        bExponent= (Button)findViewById(R.id.expo);
        bDelete= (Button)findViewById(R.id.del);
        // TextViews
        inputs = (TextView) findViewById(R.id.input);
        result = (TextView) findViewById(R.id.result);

        //On number Button's click
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        //On operator Button's click
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().toString().length() != 0) {
                    updateText("+");
                    decimal = false;
                }
            }
        });
        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().length() != 0) {
                    updateText("-");
                    decimal = false;
                }
            }
        });
        bMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().length() != 0) {
                    updateText("×");
                    decimal = false;
                }
            }
        });
        bDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().length() != 0) {
                    updateText("÷");
                    decimal = false;
                }
            }
        });
        bExponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().length() != 0) {
                    updateText("^");
                    decimal = false;
                }
            }
        });
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs.setText("");
                result.setText("0");
            }
        });
        bEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputExp= result.getText().toString();
                inputs.setText(inputExp);
                inputExp= inputExp.replaceAll("×","*");      // × : Alt+0215
                inputExp= inputExp.replaceAll("÷","/");      // ÷ : Alt+0247

                Expression exp = new Expression(inputExp);
                resultStr = String.valueOf(exp.calculate());
                if(resultStr.equals("NaN"))
                    result.setText("Error");
                else
                    result.setText(resultStr);
            }
        });

        bDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (decimal)
                    result.setText(result.getText() + ".");
                else if (inputs.getText().length() != 0) {
                    inputs.setText("");
                    result.setText("0.");
                    decimal = true;
                } else {
                    inputs.setText("");
                    result.setText(result.getText() + "0.");
                    decimal = true;
                }
            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().toString().equals("0"))
                    result.setText("");
                else if (inputs.getText().length() != 0) {
                    inputs.setText("");
                    result.setText("0");
                }
                else {
                    inputExp = result.getText().toString();
                    int len = inputExp.length();
                    result.setText(inputExp.substring(0, len - 1));
                }
            }
        });
    }
    @Override
    public void onClick(View view){
        inputExp= result.getText().toString();
        if(inputExp.equals("0") || inputs.getText().length()!=0) {
            result.setText(((Button) view).getText().toString());
        }
        else {
            result.setText(inputExp + ((Button) view).getText().toString());
        }
        inputs.setText("");
    }
}


