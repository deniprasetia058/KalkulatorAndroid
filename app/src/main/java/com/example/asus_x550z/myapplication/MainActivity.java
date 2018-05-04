package com.example.asus_x550z.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends Activity  implements TextWatcher{

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;
    EditText showResult;
    TextView txtBinary,txtOctal,txtHexadecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        showResult = (EditText)findViewById(R.id.decimal);
        txtBinary=(TextView)findViewById(R.id.binary);
        txtOctal=(TextView)findViewById(R.id.octa);
        txtHexadecimal=(TextView)findViewById(R.id.heksa);
        showResult.addTextChangedListener( this );
    }
    public void btn1Clicked(View v){
        insert(1);
    }

    public void btn2Clicked(View v){
        insert(2);
    }

    public void btn3Clicked(View v){
        insert(3);
    }

    public void btn4Clicked(View v){
        insert(4);
    }

    public void btn5Clicked(View v){
        insert(5);
    }

    public void btn6Clicked(View v){
        insert(6);
    }

    public void btn7Clicked(View v){
        insert(7);
    }

    public void btn8Clicked(View v){
        insert(8);
    }

    public void btn9Clicked(View v){
        insert(9);
    }

    public void btn0Clicked(View v){
        insert(0);
    }

    private void insert(int j) {
        str = str+Integer.toString(j);
        num = Integer.valueOf(str).intValue();
        showResult.setText(str);
    }
    public void btnclearClicked(View v){
        reset();
    }
//    public void samadenganClicked(View v){
//        hasil();
//    }
    private void reset() {
        str ="";
        op ='q';
        num = 0;
        numtemp = 0;
        showResult.setText("");
    }
//    public void hasil(){
//    }
    public void beforeTextChanged(CharSequence sequence,int start,int count,int after) {
    }
    public void afterTextChanged(Editable editable)
    {
    }

    public void onTextChanged(CharSequence sequence,int start,int before,int count)
    {
        calculate(2,txtBinary);        // for base 2 (binary)
        calculate(8,txtOctal);        // for base 8 (octal)
        calculate(16,txtHexadecimal);    // for base 16 (hexadecimal)
    }
    public void calculate(int base,TextView txtView)
    {
        if(showResult.getText().toString().trim().length()==0)
        {
            txtView.setText("");
            return;
        }
        try
        {
            Stack<Object> stack=new Stack<Object>();
            int number=Integer.parseInt(showResult.getText().toString());
            while (number>0)
            {
                int remainder=number%base; // find remainder
                if(remainder<10)
                // for remainder smaller than 10
                {
                    stack.push(remainder);
                    // push remainder in stack
                }
                else
                {
                    switch (remainder)
                    // for remainder larger than 9 (for hexadecimal values)
                    {
                        case 10:
                            stack.push("A");
                            break;
                        case 11:
                            stack.push("B");
                            break;
                        case 12:
                            stack.push("C");
                            break;
                        case 13:
                            stack.push("D");
                            break;
                        case 14:
                            stack.push("E");
                            break;
                        case 15:
                            stack.push("F");
                            break;
                    }
                }
                number/=base;
            }
            StringBuffer buffer=new StringBuffer();
            while (!stack.isEmpty())
            {
                buffer.append(stack.pop().toString());
            }
            txtView.setText(buffer.toString());
        }
        catch (Exception e)
        {
            txtView.setText(e.getMessage());
        }
    }
}