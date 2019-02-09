package com.example.nikhil.vihaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppointmentAdapter extends ArrayAdapter<MyAppointments_data> {
    TextView doc,fees,time;

    public AppointmentAdapter(@NonNull Context context, int resource, ArrayList<MyAppointments_data> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_appointments, parent, false);
        }
        doc = listItemView.findViewById(R.id.doc);
        fees = listItemView.findViewById(R.id.fees);
        time = listItemView.findViewById(R.id.time);


        // populate list

        return listItemView;
    }
}
