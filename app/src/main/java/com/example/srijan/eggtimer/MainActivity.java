package com.example.srijan.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    Boolean counterIsActive=false;
    Button goButton;

    public void buttonClicked(View view) {
        if (counterIsActive) {
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            countDownTimer.cancel();
            goButton.setText("GO!");
            counterIsActive = false;
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("Stop!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();


                }
            }.start();
        }
    }

        public void updateTimer(int secondsLeft){

            int minutes=secondsLeft/60;
            int seconds=secondsLeft-(minutes*60);
            String secondString=Integer.toString(seconds);
            if(seconds<=9){
              secondString="0"+seconds;
            }
            timerTextView.setText(Integer.toString(minutes)+":"+secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextView=findViewById(R.id.timerTextView);
        goButton=findViewById(R.id.timerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
