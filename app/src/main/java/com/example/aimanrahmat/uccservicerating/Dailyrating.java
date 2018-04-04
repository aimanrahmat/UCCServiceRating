package com.example.aimanrahmat.uccservicerating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class Dailyrating extends Fragment{

    ProgressBar pb1,pb2,pb3,pb4,pb5,pb6,pb7;
    TextView re1,re2,re3,re4,re5,re6,re7,totalR;
    SQLiteDatabase sqLiteDatabase;
    Context thiscontext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        View rootView = inflater.inflate(R.layout.tab1daily, container, false);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 5);

        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -5);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();

        pb1 = rootView.findViewById(R.id.progressBar);
        pb2 = rootView.findViewById(R.id.progressBar2);
        pb3 = rootView.findViewById(R.id.progressBar3);
        pb4 = rootView.findViewById(R.id.progressBar4);
        pb5 = rootView.findViewById(R.id.progressBar5);
        pb6 = rootView.findViewById(R.id.progressBar6);
        pb7 = rootView.findViewById(R.id.progressBar7);

        re1 = rootView.findViewById(R.id.res1);
        re2 = rootView.findViewById(R.id.res2);
        re3 = rootView.findViewById(R.id.res3);
        re4 = rootView.findViewById(R.id.res4);
        re5 = rootView.findViewById(R.id.res5);
        re6 = rootView.findViewById(R.id.res6);
        re7 = rootView.findViewById(R.id.res7);

        totalR = rootView.findViewById(R.id.totalRate);

        final DBHandler db = new DBHandler(this);

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String currentDate = dateFormat.format(date);
                DecimalFormat df = new DecimalFormat("####0.00");
                float res = 0;float res2 = 0;float res3 = 0;float res4 = 0;
                float res5 = 0;float res6 = 0;float res7 = 0;

                try{
                    res = db.getAvg(sqLiteDatabase,1,currentDate.substring(0,10));
                }catch (Exception e){
                    re1.setText("");
                }

                re1.setText(""+df.format(res));
                pb1.setProgress((int) (res));

                try{
                    res2 = db.getAvg(sqLiteDatabase,2,currentDate.substring(0,10));
                }catch (Exception e){
                    re2.setText("");
                }
                re2.setText(""+df.format(res2));
                pb2.setProgress((int) (res2));

                try{
                    res3 = db.getAvg(sqLiteDatabase,3,currentDate.substring(0,10));

                }catch (Exception e){
                    re3.setText("");
                }
                re3.setText(""+df.format(res3));
                pb3.setProgress((int) (res3));

                try{
                    res4 = db.getAvg(sqLiteDatabase,4,currentDate.substring(0,10));
                }catch (Exception e){
                    re4.setText("");
                }
                re4.setText(""+df.format(res4));
                pb4.setProgress((int) (res4));

                try{
                    res5 = db.getAvg(sqLiteDatabase,5,currentDate.substring(0,10));
                }catch (Exception e){
                    re5.setText("");
                }
                re5.setText(""+df.format(res5));
                pb5.setProgress((int) (res5));

                try{
                    res6 = db.getAvg(sqLiteDatabase,6,currentDate.substring(0,10));
                }catch (Exception e){
                    re6.setText("");
                }
                re6.setText(""+df.format(res6));
                pb6.setProgress((int) (res6));

                try{
                    res7 = db.getAvg(sqLiteDatabase,7,currentDate.substring(0,10));
                }catch (Exception e){
                    re7.setText("");
                }
                re7.setText(""+df.format(res7));
                pb7.setProgress((int) (res7));


                int ttl=0;

                try{
                    ttl = db.getTotal(sqLiteDatabase,currentDate.substring(0,10));
                }catch (Exception e){
                    totalR.setText("No. of raters: 0");
                }
                totalR.setText("No. of raters: "+ttl);

            }
        });

        return rootView;
    }


}
