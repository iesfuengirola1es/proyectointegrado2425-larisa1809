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

import com.example.golf_malaga.R;
import com.example.golf_malaga.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

//import de.hdodenhof.circleimageview.CircleImageView;


public class Registration extends AppCompatActivity {
    TextView loginbut;
    EditText rg_username, rg_email, rg_password, rg_repassword,rg_campo;
    TextView rg_signup;
    //CircleImageView rg_profileImg;
    FirebaseAuth auth;
    //Uri imageURI;
    //String imageuri;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creación de cuenta");
        progressDialog.setCancelable(false);
        //getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");


        auth = FirebaseAuth.getInstance();
        loginbut = findViewById(R.id.loginbut);
        rg_username = findViewById(R.id.rgusername);
        rg_email = findViewById(R.id.rgemail);
        rg_password = findViewById(R.id.rgpassword);
        rg_repassword = findViewById(R.id.rgrepassword);
        rg_campo = findViewById(R.id.clubnametext);

        //rg_profileImg = findViewById(R.id.profilerg0);
        rg_signup = findViewById(R.id.signupbutton);


        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, LoginClass.class);
                startActivity(intent);
                finish();
            }
        });

        rg_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee = rg_username.getText().toString();
                String emaill = rg_email.getText().toString();
                String Password = rg_password.getText().toString();
                String cPassword = rg_repassword.getText().toString();
                String campoo = rg_campo.getText().toString();;

                if (TextUtils.isEmpty(namee) || TextUtils.isEmpty(emaill) ||
                        TextUtils.isEmpty(Password) || TextUtils.isEmpty(cPassword)) {
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Inserta credenciales correctos", Toast.LENGTH_SHORT).show();
                } else if (!emaill.matches(emailPattern)) {
                    progressDialog.dismiss();
                    rg_email.setError("Inserta email correcto");
                } else if (Password.length() < 6) {
                    progressDialog.dismiss();
                    rg_password.setError("La contraseña debe tener 6 caracteres");
                } else if (!Password.equals(cPassword)) {
                    progressDialog.dismiss();
                    rg_password.setError("La contraseña no coinside");
                } else {
                    auth.createUserWithEmailAndPassword(emaill.trim(), Password)

                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(namee).build();
                                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    firebaseUser.updateProfile(userProfileChangeRequest);

                                    Users userModel = new Users(FirebaseAuth.getInstance().getUid(), namee, emaill, Password, campoo);
                                    databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);
                                    Intent intent = new Intent(Registration.this, LoginClass.class);
                                    intent.putExtra("name", namee);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e instanceof FirebaseAuthInvalidUserException) {
                                        Toast.makeText(Registration.this, "No ha salido bien, intentalo por nuevo", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
            }
        });
    }
}
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10){
            if (data!=null){
                imageURI = data.getData();
                rg_profileImg.setImageURI(imageURI);
            }
        }
    }*/

