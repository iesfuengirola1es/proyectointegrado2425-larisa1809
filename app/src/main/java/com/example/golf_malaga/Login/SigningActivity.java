package com.example.golf_malaga.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.golf_malaga.Chat.ListActivity;
import com.example.golf_malaga.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigningActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText userEmail, userPassword;

    TextView signinBtn,signupBtn;
    String email, password;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        mAuth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        userEmail = findViewById(R.id.emailtext);
        userPassword = findViewById(R.id.passwordtext);
        signinBtn  = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signup);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userEmail.getText().toString().trim();
                password = userPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {            userEmail.setError("Tienes que escribir email, ejemplo: *****@gmail.com");
                             userEmail.requestFocus();
                             return;
                }
                if(TextUtils.isEmpty(password))
                {            userPassword.setError("Tienes que escribir contraseña");
                             userPassword.requestFocus();
                             return;
                }
                Signing();
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigningActivity.this, SignupActivity.class));

            }
        });
    }

    private void Signing() {

        mAuth.signInWithEmailAndPassword(email.trim(),password)

                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String username=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                        Intent intent = new Intent(SigningActivity.this, ListActivity.class);
                        intent.putExtra("name",username);
                        startActivity(intent);
                        finish();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e instanceof FirebaseAuthInvalidUserException){
                            Toast.makeText(SigningActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SigningActivity.this, "No ha salido bien, intentalo por nuevo", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            SendUserToMainActivity();
        }
    }
    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(SigningActivity.this, ListActivity.class);
        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }


}


