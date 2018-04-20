package com.example.root.expensemanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseViewActivity extends AppCompatActivity {
    EditText et1;
    TextView tv;
    int d1,d2,d3,d4;
    String c1,c2,c3;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_view);
        tv=(TextView)findViewById(R.id.textView1);
        et1=(EditText)findViewById(R.id.editText1);

    }
    public void show(View v) {
        try {
            String s1 = et1.getText().toString();
            if(s1.isEmpty()){
                Toast.makeText(this, "Enter id First !", Toast.LENGTH_SHORT).show();
            }else {
                SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                Cursor cursor = db.rawQuery("select SUM(amount) from Expense where tid='" + s1 + "'", null);
                Cursor cur = db.rawQuery("select abudget from Trip where tid='" + s1 + "'", null);
                if (cursor.moveToNext()) {
                    c1 = cursor.getString(0);
                    d1 = Integer.parseInt(c1);//jitna expense ho gaya hai

                }
                if (cur.moveToNext()) {
                    c2 = cur.getString(0);
                    d2 = Integer.parseInt(c2); //jitna bugdet diya gaya initially

                }
                d3 = d2 - d1;
                String c3 = Integer.toString(d3);
                tv.setText("Your Current Balance : " + "\u20B9" + c3);
                d4 = (d2 / 10);
                if (d3 < d4) {
                    NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
                    nBuilder.setSmallIcon(R.drawable.alert);
                    nBuilder.setDefaults(Notification.DEFAULT_ALL);
                    nBuilder.setContentTitle("Alert");
                    nBuilder.setContentText("Your balance is low");
                    Notification n = nBuilder.build();
                    NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    nManager.notify(++count, n);
                }

            }

        } catch (Exception e)
        {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }

    }
}
