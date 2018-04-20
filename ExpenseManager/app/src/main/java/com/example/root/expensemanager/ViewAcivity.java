package com.example.root.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ViewAcivity extends AppCompatActivity {
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acivity);
        e1= (EditText) findViewById(R.id.editText1);
    }
    public void details(View v) {
        String trip_id = e1.getText().toString();

        if (trip_id.isEmpty())
        {
            Toast.makeText(this, "Enter id first", Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    Cursor cursor = db.rawQuery("select * from Trip where tid='" + trip_id + "'", null);
                    if (cursor.moveToNext()) {
                        Intent box = new Intent(ViewAcivity.this, View2Activity.class);
                        String a = cursor.getString(cursor.getColumnIndex("fromc"));
                        String b = cursor.getString(cursor.getColumnIndex("toc"));
                        String c = cursor.getString(cursor.getColumnIndex("sdate"));
                        String d = cursor.getString(cursor.getColumnIndex("fdate"));
                        String e = cursor.getString(cursor.getColumnIndex("abudget"));
                        String f = cursor.getString(cursor.getColumnIndex("bbudget"));
                        box.putExtra("FROM", a);
                        box.putExtra("TO", b);
                        box.putExtra("SDATE", c);
                        box.putExtra("FDATE", d);
                        box.putExtra("ABUDGET", e);
                        box.putExtra("BBUDGET", f);
                        startActivity(box);
                    }
                }catch (Exception e)
            {
                Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
            }
            }
    }
}