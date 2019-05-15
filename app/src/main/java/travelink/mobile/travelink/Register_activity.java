package travelink.mobile.travelink;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity {
    EditText et_email, et_password, et_username;
    String user;

    FirebaseAuth mAuth;
    DatabaseReference dbRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        dbRegister = FirebaseDatabase.getInstance().getReference("users");

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);

    }

    public void register(View view) {
        String email = et_email.getText().toString();
        String password = et_email.getText().toString();
         user = et_username.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser mUser = mAuth.getCurrentUser();

                            dbRegister.child(mUser.getUid()).child("displayName").setValue(user);
                            Toast.makeText(Register_activity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register_activity.this, Main_activity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register_activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
}
