package com.example.bayan_midt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(Splash.this,MainActivity.class));
            }
        };
        Timer opening = new Timer();
        opening.schedule(task,6000);

        TextView time = (TextView)findViewById(R.id.timer);

        new CountDownTimer(6000, 10) {
            public void onTick(long millisUntilFinished) {
                time.setText("seconds remaining: " +new SimpleDateFormat("ss").format(new
                        Date( millisUntilFinished)));
            }
            public void onFinish() {
                time.setText("done!");
            }
        }.start();
    }


}