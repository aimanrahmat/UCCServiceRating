package com.example.aimanrahmat.uccservicerating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Overallrating extends Fragment {

    TextView totalR;
    SQLiteDatabase sqLiteDatabase;
    Context thiscontext;
    int res;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        View rootView = inflater.inflate(R.layout.tab2overall, container, false);

        totalR = rootView.findViewById(R.id.totalRater);

        DBHandler db = new DBHandler(this);

        try{
            res = db.totalRate(sqLiteDatabase);
        }catch (Exception e){
            totalR.setText("");
        }
        totalR.setText(String.valueOf(res)+" total");

        return rootView;
    }


}
