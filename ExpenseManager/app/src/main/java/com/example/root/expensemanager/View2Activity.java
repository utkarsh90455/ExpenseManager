package com.example.root.expensemanager;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class View2Activity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5,tv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6= (TextView) findViewById(R.id.textView6);
        Intent i = getIntent();
        Bundle bundle=i.getExtras();
        tv1.append(bundle.getString("FROM"));
        tv2.append(bundle.getString("TO"));
        tv3.append(bundle.getString("SDATE"));
        tv4.append(bundle.getString("FDATE"));
        tv5.append(bundle.getString("ABUDGET"));
        tv6.append(bundle.getString("BBUDGET"));
    }
}
