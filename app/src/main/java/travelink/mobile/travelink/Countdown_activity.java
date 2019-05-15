package travelink.mobile.travelink;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Countdown_activity extends AppCompatActivity {
    TextView txt_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        txt_count = findViewById(R.id.timerCount);

        CountDownTimer timer=new CountDownTimer(43200000 , 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
                txt_count.setText(String.format("%d:%d:%d",hours,minutes,seconds));
            }
            public void onFinish() {
                txt_count.setText("Time Up");
            }
        };
        timer.start();

    }
}
