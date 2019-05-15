package travelink.mobile.travelink.Booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import travelink.mobile.travelink.R;

public class Booking_activity extends AppCompatActivity {

    Spinner spinnerFrom, spinnerDestination, spinnerSeat;
    TextView selectDate;
    String from, destination, seat, date;
    private DatePickerDialog mDateSetListener;

    DatabaseReference dbBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        selectDate = findViewById(R.id.txt_selectDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int Myear = calendar.get(Calendar.YEAR);
                final int Mmonth = calendar.get(Calendar.MONTH);
                int Mday = calendar.get(Calendar.DAY_OF_MONTH);

                mDateSetListener = new DatePickerDialog(Booking_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String[] month_name = {"January", "February",
                                "March", "April", "May", "June", "July",
                                "August", "September", "October", "November",
                                "December"};

                        String monthName = month_name[Mmonth];
                        selectDate.setText(dayOfMonth + "-" + monthName + "-" + year);
                        date = String.valueOf(selectDate.getText().toString());

                    }
                }, Mday, Mmonth, Myear);
                mDateSetListener.show();
            }
        });


        //Spinner
        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this, R.array.from, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapterFrom);
        spinnerFrom.setOnItemSelectedListener(new FromClass());

        spinnerDestination = (Spinner) findViewById(R.id.spinnerDestination);
        ArrayAdapter<CharSequence> adapterDestination = ArrayAdapter.createFromResource(this, R.array.destination, android.R.layout.simple_spinner_item);
        adapterDestination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(adapterDestination);
        spinnerDestination.setOnItemSelectedListener(new DestinationClass());

        spinnerSeat = (Spinner) findViewById(R.id.spinnerSeat);
        ArrayAdapter<CharSequence> adapterSeat = ArrayAdapter.createFromResource(this, R.array.seat, android.R.layout.simple_spinner_item);
        adapterSeat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeat.setAdapter(adapterSeat);
        spinnerSeat.setOnItemSelectedListener(new SeatClass());

        dbBooking = FirebaseDatabase.getInstance().getReference("schedule");


    }

    public void continueBooking(View view) {



        if (from.equals("-- From --") ){
            Toast.makeText(this, "Select from city", Toast.LENGTH_SHORT).show();
        }
        else if (destination.equals("-- Destination --")){
            Toast.makeText(this, "Select destination city", Toast.LENGTH_SHORT).show();
        }
        else if (date.equals("select")){
            Toast.makeText(this, "Select date", Toast.LENGTH_SHORT).show();
        }
        else if(seat.equals("-- Seat --")){
            Toast.makeText(this, "Select seat", Toast.LENGTH_SHORT).show();
        }

        else {
//            if(seat.equals("1") || seat.equals("2") || seat.equals("3")){
//                dbBooking.child(from).child(destination).child(date).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()){
//                            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()){
//                                    for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()){
//                                        String satu = dataSnapshot3.child("seat1").getValue().toString();
//                                        String dua = dataSnapshot3.child("seat2").getValue().toString();
//                                        String tiga = dataSnapshot3.child("seat3").getValue().toString();
//                                        switch (seat){
//                                            case "1":
//                                                if (satu.equals(null) || dua.equals(null) || tiga.equals(null))
//                                                    seat = "1";
//                                                break;
//                                            case "2":
//                                                if ()
//                                        }
//                                    }
//                                }
//                            }
//                        }else {
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }

            Intent intent = new Intent(Booking_activity.this, Schedule_activity.class);
            intent.putExtra("from", from);
            intent.putExtra("destination", destination);
            intent.putExtra("date", date);
            intent.putExtra("seat", seat);
            startActivity(intent);
        }


    }


    class FromClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String status_barang = parent.getItemAtPosition(position).toString();
            from = String.valueOf(status_barang);
            Toast.makeText(getApplication(), from, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

    class DestinationClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String status_barang = parent.getItemAtPosition(position).toString();
            destination = String.valueOf(status_barang);
            Toast.makeText(getApplication(), destination, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class SeatClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String status_barang = parent.getItemAtPosition(position).toString();
            seat = String.valueOf(status_barang);
            Toast.makeText(getApplication(), seat, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
