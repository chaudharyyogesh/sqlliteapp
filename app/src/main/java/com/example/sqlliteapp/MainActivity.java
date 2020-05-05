package com.example.sqlliteapp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;



import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText myinput;
    TextView mytext;
    Mydbhandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myinput=(EditText) findViewById(R.id.myinput);
        mytext=(TextView) findViewById(R.id.mytext);
        mytext.setBackgroundColor(Color.GREEN);
        dbhandler=new Mydbhandler(this,null,null,1);
        printdatabase();
    }
    public void printdatabase()
    {
        String dbstring=dbhandler.databaseToString();
        mytext.setText( dbstring);

        myinput.setText("");
    }
    public void addbuttonclick(View view)
    {
        String input=myinput.getText().toString();
            Products products = new Products(input);
            dbhandler.addProduct(products);
            printdatabase();

    }
    public void deletebuttonclick(View view){
        String inputtext=myinput.getText().toString();
        dbhandler.deleteProduct(inputtext);
        printdatabase();
    }


}
