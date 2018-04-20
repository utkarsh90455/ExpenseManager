package com.example.root.expensemanager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UpdateActivity extends AppCompatActivity {
    Spinner sp1;
    String str;
    String arr[]={"Update","Origin City","Destination City","Starting Date","Ending Date","Approved Budget","Balance Budget"};
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str = arr[i];
                if (str.equals("UPDATE"))
                {
                    e2.setText(null);
                }
                else if (str.equalsIgnoreCase("Starting Date") || str.equalsIgnoreCase("Ending Date"))
                {
                    e2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Toast.makeText(UpdateActivity.this, "Starting date", Toast.LENGTH_SHORT).show();
                            Mydatechooser ref = new Mydatechooser();
                            Date d = new Date();
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(d);
                            int dd = gc.get(Calendar.DATE);
                            int mm = gc.get(Calendar.MONTH);
                            int yy = gc.get(Calendar.YEAR);
                            DatePickerDialog dialog =new DatePickerDialog(UpdateActivity.this,ref,yy,mm,dd);
                            dialog.show();
                        }
                    });
                }
                else if(str.equals("Origin City")||str.equals("Destination City")||str.equals("Approved Budget")
                        ||str.equals("Balance Budget"))
                {
                    e2.setText(null);
                    e2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            } //end of onItemSelected
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }); //end of onItemSelectedListener
    }
    private class Mydatechooser implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String str=i2+ " / " +(i1+1)+ " / "+i;
            // Toast.makeText(UpdateActivity.this,str, Toast.LENGTH_SHORT).show();
            e2.setText(str);
        }
    }//end of Mydatechooser
    public void search(View v)
    {
        try {
            SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
            String trip_id = (e1.getText().toString());
            Cursor cursor = db.rawQuery("Select * from Trip where tid='" + trip_id + "'", null);
            if (cursor.moveToNext()) {
                if (str.equals("Origin City")) {
                    String name = cursor.getString(cursor.getColumnIndex("fromc"));
                    e2.setText(name);
                } else if (str.equals("Destination City")) {
                    String name = cursor.getString(cursor.getColumnIndex("toc"));
                    e2.setText(name);
                } else if (str.equals("Approved Budget")) {
                    String name = cursor.getString(cursor.getColumnIndex("abudget"));
                    e2.setText(name);
                } else if (str.equals("Balance Budget")) {
                    String name = cursor.getString(cursor.getColumnIndex("bbudget"));
                    e2.setText(name);
                } else if (str.equals("Starting Date")) {
                    String name = cursor.getString(cursor.getColumnIndex("sdate"));
                    e2.setText(name);
                } else if (str.equals("Ending Date")) {
                    String name = cursor.getString(cursor.getColumnIndex("fdate"));
                    e2.setText(name);
                }
            }
        }catch (Exception e){
            Toast.makeText(this, " "+e, Toast.LENGTH_SHORT).show();
        }
    } //end of search()
    public void update(View v)
    {
        SQLiteDatabase db= openOrCreateDatabase("EXPENSE_M",MODE_APPEND,null);
        String trip_id=e1.getText().toString();
        if(trip_id.isEmpty())
            Toast.makeText(this, "Enter trip id first", Toast.LENGTH_SHORT).show();
        else {
            String details = (e2.getText().toString());
            if (str.equals("Origin City")) {
                db.execSQL("update Trip set fromc='" + details + "'where tid='" + trip_id + "'");
            } else if (str.equals("Destination City")) {
                db.execSQL("update Trip set toc='" + details + "'where tid='" + trip_id + "'");
            } else if (str.equals("Approved Budget")) {
                db.execSQL("update Trip set abudget='" + details + "'where tid='" + trip_id + "'");
            } else if (str.equals("Balance Budget")) {
                db.execSQL("update Trip set bbudget='" + details + "'where tid='" + trip_id + "'");
            } else if (str.equals("Starting Date")) {
                db.execSQL("update Trip set sdate='" + details + "'where tid='" + trip_id + "'");
            } else if (str.equals("Ending Date")) {
                db.execSQL("update Trip set fdate='" + details + "'where tid='" + trip_id + "'");
            }
            Toast.makeText(this, " RECORD UPDATED", Toast.LENGTH_SHORT).show();
        }
    } //end of update()
}
