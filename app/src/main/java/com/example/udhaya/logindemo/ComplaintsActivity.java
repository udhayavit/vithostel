package com.example.udhaya.logindemo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ComplaintsActivity extends AppCompatActivity {
    private  EditText Name;
    private  EditText Hometown;
    private  EditText Reason;
    private  EditText Phonenumber;
    private  EditText Pphone;
    private Button Submit;
    private ImageView Letter;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;

    Uri imgPath;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            imgPath = uri;
            Letter.setImageURI(uri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        Name = (EditText) findViewById(R.id.etName);
        Hometown = (EditText) findViewById(R.id.etHometown);
        Reason = (EditText) findViewById(R.id.etReason);
        Phonenumber = (EditText) findViewById(R.id.etMyphone);
        Pphone = (EditText) findViewById(R.id.etPphone);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Letter = (ImageView) findViewById(R.id.ivLetter);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        Letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendUserData();

            }
        });
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imgref = storageReference.child(firebaseAuth.getUid()).child("Images").child("Leave letters"); //User Id/Images/profilepic.jpg
        UploadTask uploadTask = imgref.putFile(imgPath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ComplaintsActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ComplaintsActivity.this, "Hurray!! Upload Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

