package com.example.root.expensemanager;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        //e7 = (EditText) findViewById(R.id.editText7);
        try {
            SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
            db.execSQL("create table if not exists Trip(tid varchar PRIMARY KEY,fromc varchar,toc varchar,sdate varchar" +
                    ",fdate varchar,abudget varchar,bbudget varchar) ");
            db.close();
           // Toast.makeText(this, "Database Created...!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "" +e, Toast.LENGTH_SHORT).show();
        }
    }
public void insert(View v){
    try {
        String id = e1.getText().toString();
        String from = e2.getText().toString();
        String to = e3.getText().toString();
        String sdate = e4.getText().toString();
        String fdate = e5.getText().toString();
        String abudget = e6.getText().toString();
        String bbudget = abudget;
        SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
        String q = "insert into Trip(tid,fromc,toc,sdate,fdate,abudget,bbudget) values ('" + id + "','" + from + "','" + to + "','" + sdate + "','" + fdate + "','" + abudget + "','"+bbudget+"')";
        db.execSQL(q);
        if (id.isEmpty() || from.isEmpty() || to.isEmpty() || to.isEmpty() || sdate.isEmpty() || fdate.isEmpty() || abudget.isEmpty()) {
            Toast.makeText(this, "Enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Successfully Added a Trip!", Toast.LENGTH_SHORT).show();
        }
    }
    catch(Exception e){
        Toast.makeText(this, "This trip_id already exists", Toast.LENGTH_SHORT).show();
    }
}
    public void opencalendar(View v){
        switch (v.getId())
        {
            case R.id.editText4:
              calendar1 cal1 = new calendar1();
              Date d = new Date();
              GregorianCalendar gc = new GregorianCalendar();
              gc.setTime(d);
              int y = gc.get(Calendar.YEAR);
              int m = gc.get(Calendar.MONTH);
              int d1 = gc.get(Calendar.DATE);
              DatePickerDialog dialog = new DatePickerDialog(this, cal1, y, m, d1);
                //dialog.getDatePicker().setMinDate(d.getTime());
                dialog.show();
                break;

            case R.id.editText5:
                calendar cal = new calendar();
                Date d2 = new Date();
                GregorianCalendar gc1 = new GregorianCalendar();
                gc1.setTime(d2);
                int y1 = gc1.get(Calendar.YEAR);
                int m2 = gc1.get(Calendar.MONTH);
                int d3 = gc1.get(Calendar.DATE);
                DatePickerDialog dialog1 = new DatePickerDialog(this, cal, y1, m2, d3);
                //dialog1.getDatePicker().setMinDate(d2.getTime());
                dialog1.show();
                break;
        }
    }
    class calendar1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            String str = i2 + "-" + (i1+1)+"-"+ i;

            e4.setText(str);
        }
    }//for first date column
    class calendar implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
        {

            String str = i2 + "-" + (i1+1)+"-"+ i;

            e5.setText(str);
        }
    }//for second date column

}
