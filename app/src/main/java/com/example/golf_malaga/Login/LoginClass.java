package com.example.golf_malaga.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.golf_malaga.Chat.ListUsersActivity;
import com.example.golf_malaga.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginClass extends AppCompatActivity {
    TextView logsignup;
    TextView button;
    EditText email, password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Espera...");
        progressDialog.setCancelable(false);
        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

        button = findViewById(R.id.logbutton);

        email = findViewById(R.id.editTexLogEmail);
        password = findViewById(R.id.passwordtext);

        logsignup = findViewById(R.id.logsignup);

        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginClass.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String pass = password.getText().toString();

                if ((TextUtils.isEmpty(Email))){
                    progressDialog.dismiss();
                    Toast.makeText(LoginClass.this, "Inserta tu email", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(pass)){
                    progressDialog.dismiss();
                    Toast.makeText(LoginClass.this, "Inserta tu contraseña", Toast.LENGTH_SHORT).show();
                }else if (!Email.matches(emailPattern)){
                    progressDialog.dismiss();
                    email.setError("inserta email real");
                }else if (password.length()<6){
                    progressDialog.dismiss();
                    password.setError("mas que 6 caracteres");
                    Toast.makeText(LoginClass.this, "La contraseña debe tener 6 caracteres", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressDialog.show();
                                try {
                                    Intent intent = new Intent(LoginClass.this , ListUsersActivity.class);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    Toast.makeText(LoginClass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(LoginClass.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

    }
}
