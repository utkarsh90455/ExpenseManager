package com.example.root.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    public void onclick(View v){
        switch (v.getId()){
            case R.id.button1 :
                Intent i = new Intent(testActivity.this,AddActivity.class);
                startActivity(i);
                break;
            case R.id.button2 :
                Intent i2 = new Intent(testActivity.this,DeleteActivity.class);
                startActivity(i2);
                break;
            case R.id.button3 :
                Intent i3 = new Intent(testActivity.this,ViewAcivity.class);
                startActivity(i3);
                break;
            case R.id.button4 :
                Intent i4 = new Intent(testActivity.this,UpdateActivity.class);
                startActivity(i4);
                break;
            case R.id.button5 :
                Intent i5 = new Intent(testActivity.this,ExpenseActivity.class);
                startActivity(i5);
                break;
            case R.id.button6 :
                Intent i6 = new Intent(testActivity.this,ExpenseViewActivity.class);
                startActivity(i6);
                break;
            case R.id.button8 :
                Intent i7 = new Intent(testActivity.this,ReportActivity.class);
                startActivity(i7);
                break;
            case R.id.button9 :
                Intent i8 = new Intent(testActivity.this,TabShowActivity.class);
                startActivity(i8);
                break;

        }
    }
}
