package com.example.root.expensemanager;

/**
 * Created by root on 4/7/17.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;


public class Tab3 extends Fragment {
TextView tv1;
   Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);
        b1= (Button) rootView.findViewById(R.id.button13);
        tv1= (TextView) rootView.findViewById(R.id.textView1);
        SharedPreferences sp = this.getActivity().getSharedPreferences("DemoFile", 0);
        String s=sp.getString("STATUS1","");
        tv1.append(s);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),ReportActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }

}