package com.example.nikhil.vihaan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DoctorAppointmentActivity extends AppCompatActivity {

    private RecyclerView appointmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

//        mTextMessage = findViewById(R.id.message);
//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.appointment_new:
//                        mTextMessage.setText("New Appointment");
//                        return true;
//                    case R.id.appointment_confirmed:
//                        mTextMessage.setText("Confirmed Appointment");
//                        return true;
//                }
//                return false;
//            }
//        });

        appointmentList = findViewById(R.id.doctor_appointments_list);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("userbase")
                .child("doctors")
                .child(FirebaseAuth.getInstance().getUid())
                .child("appointments")
                .limitToFirst(50);

        AppointmentListAdapter appointmentListAdapter = new AppointmentListAdapter(R.layout.appointment_layout, query, getApplicationContext());
        appointmentList.setAdapter(appointmentListAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        appointmentList.setLayoutManager(layoutManager);
    }

}
