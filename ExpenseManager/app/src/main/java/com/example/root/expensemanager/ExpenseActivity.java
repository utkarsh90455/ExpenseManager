package com.example.root.expensemanager;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExpenseActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4;
    String cate[] = {"Travelling", "Meal", "Lodging", "Misc"};
    Spinner sp1;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);

        try {
            SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
            db.execSQL("Create table if not exists Expense(_id INTEGER PRIMARY KEY AUTOINCREMENT,cate varchar," +
                    "particular varchar,amount int,date varchar,tid varchar,FOREIGN KEY (tid) REFERENCES Trip(tid)) ");
            db.close();
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
        //  Toast.makeText(this, "Database available", Toast.LENGTH_SHORT).show();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = cate[position];
                // Toast.makeText(ExpenseActivity.this, str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void date(View v)

    {
        Myclass obj = new Myclass();
        Date d = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        int dd = gc.get(Calendar.DATE);
        int mm = gc.get(Calendar.MONTH);
        int yy = gc.get(Calendar.YEAR);
        DatePickerDialog dialog =new DatePickerDialog(ExpenseActivity.this,obj,yy,mm,dd);
        dialog.show();
    }

    class Myclass implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month = month + 1;
            String str = dayOfMonth + "/" + month + "/" + year ;
            et4.setText(str);
        }
    }

    public void submit(View v) {
        String TripId = et1.getText().toString();
        String Cate = str;
        String Parti = et2.getText().toString();
        String Amount = et3.getText().toString();
        String Date = et4.getText().toString();
        if (TripId.isEmpty() || Cate.isEmpty()  || Amount.isEmpty() || Date.isEmpty())
        {
            Toast.makeText(this, "Enter Your Details First !", Toast.LENGTH_SHORT).show();
        } else {

            try {
                SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                String q1= "select * from Trip where tid='"+TripId+"'";
                Cursor cursor = db.rawQuery(q1,null);
                if(cursor.moveToNext()) {
                    String q = "insert into Expense(cate,particular,amount,date,tid)values ('" + Cate + "','" + Parti + "','" + Amount + "','" + Date + "','" + TripId + "')";
                    db.execSQL(q);
                    Toast.makeText(this, "Successfully...Added Expense!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error Occured...!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}