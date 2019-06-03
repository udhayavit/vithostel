package com.example.udhaya.logindemo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button rd=(Button) findViewById(R.id.room_button);
        Button pb=(Button) findViewById(R.id.photos_button);
        Button ll= (Button)findViewById(R.id.leave_button);
        Button ss= (Button)findViewById(R.id.sports_button);
        Button oa= (Button)findViewById(R.id.others);
        Button ha= (Button)findViewById(R.id.hostelaff_button);
        Button si=(Button)findViewById(R.id.supervisor_button);
        Button hc=(Button) findViewById(R.id.health_button);

        rd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent r=new Intent(getBaseContext(),RoomActivity.class);
                startActivity(r);
            }

        });

        ll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent l=new Intent(getBaseContext(),ComplaintsActivity.class);
                startActivity(l);
            }

        });

        pb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent p=new Intent(getBaseContext(),PhotosActivity.class);
                startActivity(p);
            }

        });


        ss.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent s=new Intent(getBaseContext(),SportsActivity.class);
                startActivity(s);
            }

        });

        oa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent o=new Intent(getBaseContext(),OtherActivity.class);
                startActivity(o);
            }

        });

        ha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent h=new Intent(getBaseContext(),hostel.class);
                startActivity(h);
            }

        });

        si.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent si=new Intent(getBaseContext(),SuperActivity.class);
                startActivity(si);
            }

        });

        hc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {;
                Intent h=new Intent(getBaseContext(),HealthActivity.class);
                startActivity(h);
            }

        });


        firebaseAuth = FirebaseAuth.getInstance();

    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
