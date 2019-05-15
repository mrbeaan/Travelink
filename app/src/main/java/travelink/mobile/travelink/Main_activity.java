package travelink.mobile.travelink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import travelink.mobile.travelink.Account.Account_activity;
import travelink.mobile.travelink.Booking.Booking_activity;
import travelink.mobile.travelink.History.History_activity;
import travelink.mobile.travelink.Setting.Setting_activity;

public class Main_activity extends AppCompatActivity {

    FirebaseUser mUser;
    DatabaseReference dbUser;
    TextView txt_username;
    private int SETTING = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("SETTING",MODE_PRIVATE);
        if (AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES || sharedPreferences.getBoolean("darkMode",false)){
            Toast.makeText(this, "DARKKKK", Toast.LENGTH_SHORT).show();
            setTheme(R.style.darktheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        dbUser = FirebaseDatabase.getInstance().getReference("users").child(mUser.getUid());
        txt_username = findViewById(R.id.txt_username);

        dbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    txt_username.setText(dataSnapshot.child("displayName").getValue().toString());
                }
                else {
                    txt_username.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void booking(View view) {
        startActivity(new Intent(Main_activity.this, Booking_activity.class));
    }

    public void history(View view) {
        startActivity(new Intent(Main_activity.this, History_activity.class));
    }

    public void account(View view) {
        startActivity(new Intent(Main_activity.this, Account_activity.class));
    }

    public void setting(View view) {
//        startActivity(new Intent(Main_activity.this, Countdown_activity.class));
        startActivityForResult(new Intent(Main_activity.this,Setting_activity.class),SETTING);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SETTING){
            Toast.makeText(Main_activity.this, "KENAAFragment", Toast.LENGTH_SHORT).show();
            recreate();
        }
    }

    public void countdown(View view) {
        startActivity(new Intent(this, Countdown_activity.class));
    }
}
