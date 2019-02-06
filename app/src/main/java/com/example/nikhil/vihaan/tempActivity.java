package com.example.nikhil.vihaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tempActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        b1= findViewById(R.id.hyp);
        b1.setOnClickListener(this);

        b2 = findViewById(R.id.hop);
        b2.setOnClickListener(this);

        b3 = findViewById(R.id.hr);
        b3.setOnClickListener(this);

        b4 = findViewById(R.id.oxy);
        b4.setOnClickListener(this);

        b5 = findViewById(R.id.tal);
        b5.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hyp :
                Intent hyp = new Intent(this,RemedyActivity.class);
                hyp.putExtra("title","Hypertension");
                startActivity(hyp);
                break;


            case R.id.hop :
                Intent hop = new Intent(this,RemedyActivity.class);
                hop.putExtra("title","Low Blood Presure");
                startActivity(hop);
                break;

            case R.id.hr :
                Intent hr = new Intent(this,RemedyActivity.class);
                hr.putExtra("title","High Heart Rate");
                startActivity(hr);
                break;

            case R.id.oxy:
                Intent oxy = new Intent(this,RemedyActivity.class);
                oxy.putExtra("title","Oxygen Saturation");
                startActivity(oxy);
                break;

            case R.id.tal:
                Intent tal = new Intent(this,RemedyActivity.class);
                tal.putExtra("title","Tachypnea");
                startActivity(tal);
                break;


        }
    }
}
