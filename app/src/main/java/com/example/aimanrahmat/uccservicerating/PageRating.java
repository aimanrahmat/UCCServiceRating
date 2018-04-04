package com.example.aimanrahmat.uccservicerating;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PageRating extends AppCompatActivity {

    TextView ctrName,avg,cStar;
    EditText commentText;
    Button rate;
    Toolbar mToolbar;
    RatingBar rBar;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingpage);

        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String msg = intent.getStringExtra(PageMain.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        ctrName = (TextView) findViewById(R.id.counterName);
        avg = (TextView) findViewById(R.id.average);
        cStar = (TextView) findViewById(R.id.currentStar);
        rate = (Button)findViewById(R.id.bRate);
        rBar = (RatingBar)findViewById(R.id.ratingBar);
        commentText = (EditText)findViewById(R.id.comment);

        ctrName.setText(msg);

        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cStar.setText(""+rating);
            }
        });

        final DBHandler db = new DBHandler(this);
        final String ctr = ctrName.getText().toString();

        float res=0;
        try{
            res = db.totalAvg(sqLiteDatabase,Integer.parseInt(msg.substring(0,1)));
        }catch (Exception e){
            avg.setText("");
        }

        DecimalFormat df = new DecimalFormat("####0.00");

        avg.setText(String.valueOf(df.format(res)));

        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date();

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("New DBHandler", "SUCCESSFUL");
                db.addRating(new Record(1, Integer.parseInt(ctr.substring(0,1)),Float.parseFloat(cStar.getText().toString()),commentText.getText().toString(),dateFormat.format(date)));
                Log.d("Insert Data: ", "SUCCESSFUL");

                Log.d("Reading: ", "Reading all records..");
                List<Record> records = db.getAllRecords();

                for (Record record : records) {
                    String log = "Id: " + record.getId() + ", Counter: " + record.getCounter()+", Rating: "+record.getStar()+", Comment: "
                            +record.getComment()+", Date: "+record.getDate();
                    // Writing records to log
                    Log.d("Reading record: ", log);

                }

                startActivity(new Intent(PageRating.this, PageEnd.class));
                finish();
            }
        });

    }

}
