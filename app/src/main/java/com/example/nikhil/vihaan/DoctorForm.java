package com.example.nikhil.vihaan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

import mehdi.sakout.fancybuttons.FancyButton;

public class DoctorForm extends AppCompatActivity {
    EditText messCharge, appointCharge, address, qualification;
    FancyButton proceed;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_form);
        messCharge=findViewById(R.id.mess_charge);
        appointCharge=findViewById(R.id.app_charge);
        address=findViewById(R.id.address);
        qualification=findViewById(R.id.qual);
        proceed=findViewById(R.id.proceed_btn);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDetails details = new DoctorDetails();
                details.setAddress(address.getText().toString());
                details.setAppointmentCharge(Integer.parseInt(appointCharge.getText().toString()));
                details.setMessageCharge(Integer.parseInt(messCharge.getText().toString()));
                details.setName(user.getDisplayName());
                details.setQualification(qualification.getText().toString());



                FirebaseDatabase.getInstance().getReference()
                        .child("users")
                        .child("doctors")
                        .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                        .child(String.valueOf(new Date().getTime()))
                        .setValue(details);
                Toast.makeText(DoctorForm.this, "Details Updated Successfully", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("doctorDetails",true);
                editor.apply();
            }
        });
    }
}
