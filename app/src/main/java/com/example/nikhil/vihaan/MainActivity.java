package com.example.nikhil.vihaan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static boolean isDoctor = false;

    YourData[] dataObjects = ();

    List<Map.Entry> entries = new ArrayList<>();

    for(YourData data : dataObjects) {

        // turn your data into Entry objects
        entries.add(new Entry(data.getValueX(), data.getValueY()));
    }

    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    // shared preference for doctor
    static SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // using shared preferrence to distinguish between doctor and user
         sharedPref= getSharedPreferences("doctor",Context.MODE_PRIVATE);
         isDoctor = sharedPref.getBoolean("isDoctor",true);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

            else {
                if(isDoctor) {
                    finishAffinity();
                    startActivity(new Intent(MainActivity.this, DoctorActivity.class));
                }
                else
                    {
                }
        }

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView navText = header.findViewById(R.id.nav_text);
        TextView emailText=header.findViewById(R.id.nav_email);
        ImageView img = header.findViewById(R.id.img);
        if(user!=null) {
            navText.setText("Hi! " + user.getDisplayName());
            Picasso.get().load(user.getPhotoUrl()).into(img);
            emailText.setText(user.getEmail());

        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_vital_signs:
                        startActivity(new Intent(MainActivity.this,VitalSignsActivity.class));
                        return true;
                    case R.id.nav_medication:
                        startActivity(new Intent(MainActivity.this,MedicationsActivity.class));
                        return true;
                    case R.id.nav_consult:
                        startActivity(new Intent(MainActivity.this,ConsultActivity.class));
                        return true;
                    case R.id.nav_logout:
                        sharedPref.edit().clear().commit();
                        AuthUI.getInstance()
                                .signOut(MainActivity.this)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                                    }
                                });
                        return true;

                    case R.id.nav_abt_vitals:
                        startActivity(new Intent(MainActivity.this,AboutVitalSigns.class));
                }


                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // added temporary intent to test remedy functionality
            Intent temp = new Intent(this,tempActivity.class);
            startActivity(temp);
            return true;
        }
        if(id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
