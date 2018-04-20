package com.example.root.expensemanager;



import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CursorAdapter;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

public class ReportActivity extends AppCompatActivity{
    Spinner sp;
    ListView lv;
    String []type={"Select","Trip Wise Amount Spend","Category Wise Amount Spend","Day Wise Amount Spend"};
    String str;
    int d1,d2,d3;
    String c1,c2,c3,c4,c5,c6,c7,c8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        SQLiteDatabase db=openOrCreateDatabase("EXPENSE_M",MODE_APPEND,null);
        sp=(Spinner)findViewById(R.id.spinner1);
        lv=(ListView)findViewById(R.id.listView1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str= type[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//end of oncreate

    public void show (View v) {
        try {

            if (str.equals("Trip Wise Amount Spend")) {
                SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                Cursor cursor = db.rawQuery("select Trip.tid,Trip.fromc,Trip.toc,Trip.sdate,Trip.fdate,Trip.abudget,sum(Expense.amount)," +
                        "Expense._id from Trip inner join Expense on Trip.tid = Expense.tid group by Trip.tid order by Trip.tid", null);

                CursorAdapter currentadapter = new MyCursorAdapter(this,cursor, 0);
                lv.setAdapter(currentadapter);
            }
            else if(str.equals("Category Wise Amount Spend")){

                try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    Cursor cursor = db.rawQuery("select Trip.tid,Trip.fromc,Trip.toc,sum(Expense.amount),Expense._id," +
                            "Expense.cate from Trip inner join Expense on Trip.tid = Expense.tid group by Expense.cate,Trip.tid order by Trip.tid ", null);
                    CursorAdapter currentadapter = new MyCursorAdapter(this, cursor, 0);
                    lv.setAdapter(currentadapter);
                }catch (Exception e){
                    Toast.makeText(this, ""+e , Toast.LENGTH_SHORT).show();
                }
            }
            else if (str.equals("Day Wise Amount Spend")){
                try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    Cursor cursor = db.rawQuery("select Trip.tid,Trip.fromc,Trip.toc,sum(Expense.amount),Expense._id," +
                            "Expense.date from Trip inner join Expense on Trip.tid = Expense.tid group by Expense.date,Trip.tid order by Trip.tid ", null);
                    CursorAdapter currentadapter = new MyCursorAdapter(this, cursor, 0);
                    lv.setAdapter(currentadapter);
                }catch (Exception e){
                    Toast.makeText(this, ""+e , Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Select Category First...!", Toast.LENGTH_SHORT).show();
            }

            }catch(Exception e){
                Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
            }

    }//end of show
    private class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View  view =super.getView(position, convertView, parent);
            if (position % 2 == 1) {
                view.setBackgroundColor(Color.parseColor("#dce9af"));
            } else {
                view.setBackgroundColor(Color.parseColor("#cbde87"));
            }
            return view;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.row,viewGroup,false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv1 = (TextView) view.findViewById(R.id.textView1);
            TextView tv2 = (TextView) view.findViewById(R.id.textView2);
            TextView tv3 = (TextView) view.findViewById(R.id.textView3);
            TextView tv4 = (TextView) view.findViewById(R.id.textView4);
            TextView tv5 = (TextView) view.findViewById(R.id.textView5);
            TextView tv6 = (TextView) view.findViewById(R.id.textView6);
            TextView tv7 = (TextView) view.findViewById(R.id.textView7);
            if(str.equals("Trip Wise Amount Spend")) {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    String id = cursor.getString(cursor.getColumnIndex("tid"));
                    String from = cursor.getString(cursor.getColumnIndex("fromc"));
                    String to = cursor.getString(cursor.getColumnIndex("toc"));
                    String sdate = cursor.getString(cursor.getColumnIndex("sdate"));
                    String fdate = cursor.getString(cursor.getColumnIndex("fdate"));
                    String amount = cursor.getString(6);
                    int a = Integer.parseInt(cursor.getString(cursor.getColumnIndex("abudget")));
                    int b = Integer.parseInt(amount);
                    String curr = String.valueOf(a - b);
                    tv1.setText("Trip ID : " + id);
                    tv2.setText("From : " + from);
                    tv3.setText("To : " + to);
                    tv4.setText("Start Date : " + sdate);
                    tv5.setText("End Date : " + fdate);
                    tv6.setText("Amount Spend : " + amount);
                    tv7.setText("Current Balance : " + curr);

                } catch (Exception e) {
                    Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
                }


            }// end of tripwise
            else if(str.equals("Category Wise Amount Spend"))
            {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    String id = cursor.getString(cursor.getColumnIndex("tid"));
                    String from = cursor.getString(cursor.getColumnIndex("fromc"));
                    String to = cursor.getString(cursor.getColumnIndex("toc"));
                    String am = cursor.getString(3);
                    String cate = cursor.getString(cursor.getColumnIndex("cate"));
                    tv1.setText("Trip ID : " + id);
                    tv2.setText("From : " + from);
                    tv3.setText("To : " + to);
                    tv4.setText("Category : " + cate);
                    tv6.setText("Amount Spend : " + am);

                }catch (Exception e){
                    Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
            }//end of category
            else if(str.equals("Day Wise Amount Spend")){
                try {
                    SQLiteDatabase db = openOrCreateDatabase("EXPENSE_M", MODE_APPEND, null);
                    String id = cursor.getString(cursor.getColumnIndex("tid"));
                    String from = cursor.getString(cursor.getColumnIndex("fromc"));
                    String to = cursor.getString(cursor.getColumnIndex("toc"));
                    String am = cursor.getString(3);
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    tv1.setText("Trip ID : " + id);
                    tv2.setText("From : " + from);
                    tv3.setText("To : " + to);
                    tv4.setText("Date: " + date);
                    tv6.setText("Amount Spend : " + am);

                }catch (Exception e){
                    Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
            }//end of day wise
        }
    }
}



