package com.example.udhaya.logindemo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
//    Typeface tfc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        resetPassword = (Button)findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

//        tfc1 = Typeface.createFromAsset(getAssets(),"fonts/comic.ttf");
//        passwordEmail.setTypeface(tfc1);
//        resetPassword.setTypeface(tfc1);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();


                if(useremail.equals("")){
                    Toast.makeText(PasswordActivity.this, "Please Enter your Registered email-id",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PasswordActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(PasswordActivity.this, "Error in Sending Password", Toast.LENGTH_SHORT).show();

                        }
                        }
                    });
                }
            }
        });
    }
}
