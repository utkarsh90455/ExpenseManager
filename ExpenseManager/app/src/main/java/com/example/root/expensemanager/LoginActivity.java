package com.example.root.expensemanager;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1= (EditText) findViewById(R.id.editText1);
        e2= (EditText) findViewById(R.id.editText2);

    }
    public void continue1(View v)
    {
        Intent box = new Intent(LoginActivity.this,TabShowActivity.class);
        String str1 = e1.getText().toString();
        String str2 = e2.getText().toString();
        if (str1.isEmpty() || str2.isEmpty()) {
            Toast.makeText(LoginActivity.this, "ENTER DETAILS FIRST", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences sp = getSharedPreferences("DemoFile", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("STATUS1", e1.getText().toString());
            editor.putString("STATUS2", e1.getText().toString());
            editor.commit();
            startActivity(box);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("DemoFile", 0);
        String str1=sp.getString("STATUS1"," ");
        String str2=sp.getString("STATUS2"," ");
        if(str1.equalsIgnoreCase(" ")||str2.equalsIgnoreCase(""))
        {

        }
        else{
            Intent i=new Intent(LoginActivity.this,TabShowActivity.class);
            startActivity(i);
        }
    }
}