package travelink.mobile.travelink.Account;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import travelink.mobile.travelink.R;

public class Account_activity extends AppCompatActivity {

    TextView txt_email;
    EditText et_username;

    FirebaseUser mUser;
    DatabaseReference dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        txt_email = findViewById(R.id.txt_email);
        et_username = findViewById(R.id.et_username);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        dbUser = FirebaseDatabase.getInstance().getReference("users");

        txt_email.setText(mUser.getEmail());
        dbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user = String.valueOf(dataSnapshot.child(mUser.getUid()).child("displayName").getValue().toString());
                et_username.setText(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void save(View view) {
//        if (et_username != )
        dbUser.child(mUser.getUid()).child("displayName").setValue(et_username.getText().toString());
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}
