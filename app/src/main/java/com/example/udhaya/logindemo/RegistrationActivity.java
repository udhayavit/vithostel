package com.example.udhaya.logindemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.security.PrivateKey;

@SuppressWarnings("FieldCanBeLocal")

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password;
    private FirebaseStorage firebaseStorage;



    Uri imagePath;
    private  StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            imagePath = uri;
            userProfilePic.setImageURI(uri);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();



        storageReference = firebaseStorage.getReference();


        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });




        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //upload it to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload Complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userAge = (EditText)findViewById(R.id.etAge);
        userProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();
        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() || imagePath == null ){
            Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
    return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail has been sent", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Verification mail hasn't been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic"); //User Id/Images/profilepic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RegistrationActivity.this, "Hurray!! Upload Successful", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
    }

}
