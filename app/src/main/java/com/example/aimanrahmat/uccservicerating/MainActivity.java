package com.example.aimanrahmat.uccservicerating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private long delay = 4000;
    private TimerTask task = new TimerTask(){
        @Override
        public void run(){
            startActivity(new Intent(MainActivity.this, PageMain.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timer timer = new Timer();
        timer.schedule(task,delay);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
