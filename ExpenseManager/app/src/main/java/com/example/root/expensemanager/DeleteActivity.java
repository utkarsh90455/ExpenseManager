package com.example.root.expensemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
     EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        e= (EditText) findViewById(R.id.editText1);
        SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);

    }
   public void delete(View v){
           String cid = e.getText().toString();
           if (cid.isEmpty()) {
               Toast.makeText(this, "Enter id First", Toast.LENGTH_SHORT).show();
           }
           else
               {
               SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
               Cursor cursor = db.rawQuery("select * from Trip where tid='" + cid + "' ", null);
                   if (cursor.moveToNext())
                   {
                   String q1 = "Delete from Trip where tid='" + cid + "'";
                   db.execSQL(q1);
                   Cursor cur2 = db.rawQuery("select * from Expense where tid='" + cid + "'", null);
                   if(cur2.moveToNext()) {
                       String q2 = "Delete from Expense where tid='" + cid + "'";
                       db.execSQL(q2);
                   }
                   else
                   {
                     //  Toast.makeText(this, "No expenses till now", Toast.LENGTH_SHORT).show();
                   }
                       Toast.makeText(this, "Trip Details Deleted Successfully!", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(this, "Error Occured in Deletion!", Toast.LENGTH_SHORT).show();
               }
       }
    }
}
