package com.example.root.expensemanager;

/**
 * Created by root on 4/7/17.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;


public class Tab2 extends Fragment {

    Button b1,b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        b1= (Button) rootView.findViewById(R.id.button10);
        b2= (Button) rootView.findViewById(R.id.button16);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),ExpenseActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),ExpenseViewActivity.class);
                startActivity(i);
            }
        });



        return rootView;
    }
}




