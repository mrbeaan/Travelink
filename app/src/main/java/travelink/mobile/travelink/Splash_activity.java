package travelink.mobile.travelink;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_activity extends AppCompatActivity {
    private final int SPLASH_TIMER = 3000;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mUser != null) {
                    startActivity(new Intent(Splash_activity.this, Main_activity.class));
                }
                else {
                    startActivity(new Intent(Splash_activity.this, Login_activity.class));
                }
            }
        }, SPLASH_TIMER);
    }
}
