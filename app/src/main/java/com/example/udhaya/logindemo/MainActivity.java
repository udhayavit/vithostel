package com.example.udhaya.logindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Type;

@SuppressWarnings("FieldCanBeLocal")

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgetPassword = (TextView)findViewById(R.id.tvForgetPassword);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(Name.getText().toString(), Password.getText().toString());
    }
});

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

    }

    private void validate(String userName, String userPassword) {
        progressDialog.setMessage("Keep Calm Until U got Verified");
        progressDialog.show();

    firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
       if(task.isSuccessful()){
           progressDialog.dismiss();
           Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
           checkEmailVerification();
       }else{
           Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
           counter--;
           Toast.makeText(MainActivity.this, "No of Attempts remaining:"+ counter,Toast.LENGTH_SHORT).show();
           progressDialog.dismiss();
           if(counter == 0){
               Login.setEnabled(false);
           }
       }
        }
    });
       }
       private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        boolean emailflag = firebaseUser.isEmailVerified();


        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }else{
            Toast.makeText(this,"Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();;
        }
       }

    }
