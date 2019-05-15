package travelink.mobile.travelink.Setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import travelink.mobile.travelink.Login_activity;
import travelink.mobile.travelink.R;

public class Setting_activity extends AppCompatActivity {
    Switch switchMode;
    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("SETTING",MODE_PRIVATE);
        if (AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES || sharedPreferences.getBoolean("darkMode",false)){
            setTheme(R.style.darktheme);
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_setting);
        switchMode = findViewById(R.id.switchMode);
        switchMode.setChecked(sharedPreferences.getBoolean("darkMode",false));
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            switchMode.setChecked(true);
        }
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("darkMode",isChecked);
                editor.apply();
                editor.commit();
                restartApp();
            }
        });
    }

    public void restartApp(){
        recreate();
    }

    public void logout(View view) {
        mAuth.signOut();
        startActivity(new Intent(Setting_activity.this, Login_activity.class));
        finish();
    }
}
