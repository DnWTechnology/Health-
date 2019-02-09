package com.example.nikhil.vihaan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TakeAppointmentForm extends AppCompatActivity {

    SharedPreferences sref;
    TextView name, age, gender, date,time;
    EditText problem;
    DatePicker picker;
    TimePicker picker_time;
    TextView doc,fee;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_appointment_form);

        sref = getSharedPreferences("Info",MODE_PRIVATE);

        submit  = findViewById(R.id.submit);

        name = findViewById(R.id.name);
        age = findViewById(R.id.Age);
        gender = findViewById(R.id.Gender);

        final String Name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        final String Age = String.valueOf(sref.getInt("Age",20));
        final String Gender = sref.getString("Gender","M");

        name.setText(Name);
        age.setText(Age);
        gender.setText(Gender);

        picker = findViewById(R.id.date_picker);
        picker_time = findViewById(R.id.time_picker);

        problem = findViewById(R.id.et_problem);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);

        doc =  findViewById(R.id.doctor);
        fee = findViewById(R.id.fees);

        final String doctor = getIntent().getStringExtra("Doctor");
        String fees = getIntent().getStringExtra("Fees");

        doc.setText(doctor);
        fee.setText(fees);



        final String Time = String.valueOf(picker_time.getHour()) + ":" +
                String.valueOf(picker_time.getMinute());

       String Date = String.valueOf(picker.getDayOfMonth())
               + String.valueOf(picker.getMonth())+
               String.valueOf(picker.getYear());

       String Problem = problem.getText().toString();

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // update databse of appointments
               Toast.makeText(getApplicationContext(),String.valueOf("Appointment Scheduled for " + Name + "\nDate: "+date + "\nTime: " + time+ "\n At:"
               +"Dr. "+doctor), Toast.LENGTH_LONG).show();
           }
       });







    }
}
