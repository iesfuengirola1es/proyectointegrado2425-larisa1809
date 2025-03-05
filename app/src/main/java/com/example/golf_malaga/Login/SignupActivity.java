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
import com.example.golf_malaga.Chat.UserModel;
import com.example.golf_malaga.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText userName, userEmail, userPassword, userClub;

    TextView signinBtn,signupBtn;
    String name, email, password,clubname;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        userName= findViewById(R.id.usernametext);
        userEmail = findViewById(R.id.emailtext);
        userPassword = findViewById(R.id.passwordtext);
        userClub=findViewById(R.id.clubnametext);
        signinBtn  = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signup);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = userEmail.getText().toString().trim();
                password = userPassword.getText().toString().trim();
                clubname=userClub.getText().toString().trim();
                name = userName.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {            userName.setError("Tienes que escribir nombre");
                    userName.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {            userEmail.setError("Tienes que escribir email");
                    userEmail.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {            userPassword.setError("Tienes que escribir contraseña");
                    userPassword.requestFocus();
                    return;
                }
                Signup();
            }
        });


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, SigningActivity.class));

            }
        });
    }

    private void Signup() {

        mAuth.createUserWithEmailAndPassword(email.trim(),password)

                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);

                        UserModel userModel= new UserModel(FirebaseAuth.getInstance().getUid(),name,email,password,clubname);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);
                        Intent intent = new Intent(SignupActivity.this, ListActivity.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e instanceof FirebaseAuthInvalidUserException){
                            Toast.makeText(SignupActivity.this, "No ha salido bien, intentalo por nuevo", Toast.LENGTH_SHORT).show();
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
        Intent mainIntent = new Intent(SignupActivity.this, ListActivity.class);
        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }


}


