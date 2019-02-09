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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int i=0;

    static boolean isDoctor = false;
    ArrayList<PatientSigns> listSigns = new ArrayList<>();

    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    private DatabaseReference mDatabase;
    // shared preference for doctor
    static SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LineChart chart1 = findViewById(R.id.chart1);
        final LineChart chart2 = findViewById(R.id.chart2);
        final LineChart chart3 = findViewById(R.id.chart3);
        final LineChart chart4 = findViewById(R.id.chart4);

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

        final List<Entry> HRentries = new ArrayList<Entry>();
        final List<Entry> Sysentries = new ArrayList<Entry>();
        final List<Entry> Diasentries = new ArrayList<Entry>();
        final List<Entry> OSentries = new ArrayList<Entry>();
        final List<Entry> Respientries = new ArrayList<Entry>();
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

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child("patients");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    for(DataSnapshot vitals : child.getChildren()) {
                        Log.d("data", "onDataChange: " + vitals.toString());
                        PatientSigns signs = vitals.getValue(PatientSigns.class);
                        listSigns.add(signs);
                        Log.i("retrieved: ", Integer.toString(listSigns.get(i).getHeartRate()));
                        HRentries.add(new Entry(1+i, listSigns.get(i).getHeartRate()));
                        Sysentries.add(new Entry(1+i, listSigns.get(i).getSystolic()));
                        Diasentries.add(new Entry(1+i, listSigns.get(i).getDiastolic()));
                        OSentries.add(new Entry(1+i, listSigns.get(i).getOxygenSaturation()));
                        Respientries.add(new Entry(1+i, listSigns.get(i).getRespirationRate()));
                        //Log.d("class", "onDataChange: "+signs.getDiastolic());
                        //Log.d("entries retrieved", "onDataChange: "+HRentries.get(i).getY());
                        i=i+1;

                        LineDataSet dataSet1 = new LineDataSet(HRentries, "Heart Rate"); // add entries to dataset
                        dataSet1.setColor(R.color.maroon);
                        chart1.setBackgroundColor(getResources().getColor(R.color.lime));
                        Description description1 = new Description();
                        description1.setText("Heart Rate");
                        chart1.setDescription(description1);
                        LineData lineData1 = new LineData(dataSet1);
                        chart1.setData(lineData1);
                        Legend legend1 = chart1.getLegend();
                        legend1.setTextColor(R.color.colorPrimary);
                        legend1.setTextSize(12f);
                        chart1.invalidate(); // refresh
                        chart1.notifyDataSetChanged();

                        /*LineDataSet dataSet2 = new LineDataSet(OSentries, "Heart Rate"); // add entries to dataset
                        dataSet2.setColor(R.color.maroon);
                        chart2.setBackgroundColor(getResources().getColor(R.color.skin));
                        Description description2 = new Description();
                        description2.setText("Oxygen Saturation");
                        chart2.setDescription(description2);
                        LineData lineData2 = new LineData(dataSet2);
                        chart2.setData(lineData2);
                        Legend legend2 = chart2.getLegend();
                        legend2.setTextColor(R.color.colorPrimary);
                        legend2.setTextSize(12f);
                        chart2.invalidate(); // refresh
                        chart2.notifyDataSetChanged();*/

                        LineDataSet dataSet3 = new LineDataSet(Respientries, "Heart Rate"); // add entries to dataset
                        dataSet3.setColor(R.color.maroon);
                        chart3.setBackgroundColor(getResources().getColor(R.color.lightblue));
                        Description description3 = new Description();
                        description3.setText("Respiration Rate");
                        chart3.setDescription(description3);
                        LineData lineData3 = new LineData(dataSet3);
                        chart3.setData(lineData3);
                        Legend legend3 = chart3.getLegend();
                        legend3.setTextColor(R.color.colorPrimary);
                        legend3.setTextSize(12f);
                        chart3.invalidate(); // refresh
                        chart3.notifyDataSetChanged();

                        LineDataSet dataSet4 = new LineDataSet(HRentries, "Heart Rate"); // add entries to dataset
                        dataSet4.setColor(R.color.maroon);
                        chart4.setBackgroundColor(getResources().getColor(R.color.pink));
                        Description description4 = new Description();
                        description4.setText("Heart Rate");
                        chart4.setDescription(description4);
                        LineData lineData4 = new LineData(dataSet4);
                        chart4.setData(lineData4);
                        Legend legend4 = chart4.getLegend();
                        legend4.setTextColor(R.color.colorPrimary);
                        legend4.setTextSize(12f);
                        chart4.invalidate(); // refresh
                        chart4.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
            Intent settings = new Intent(this,SettingsActivity.class);
            startActivity(settings);
            return true;
        }
        if(id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
