package travelink.mobile.travelink.Booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import travelink.mobile.travelink.Countdown_activity;
import travelink.mobile.travelink.Main_activity;
import travelink.mobile.travelink.R;

public class Detail_book_activity extends AppCompatActivity {
    String from,destination,date,seat,bus,price,time;

    TextView From, Destination, Price, Date, Time, Bus, Seat;

    DatabaseReference dbBook, dbHistory;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        destination = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        seat = intent.getStringExtra("seat");
        time = intent.getStringExtra("time");
        price = intent.getStringExtra("price");
        bus = intent.getStringExtra("bus");

        From = findViewById(R.id.txt_from);
        Destination = findViewById(R.id.txt_destination);
        Price = findViewById(R.id.txt_price);
        Date = findViewById(R.id.txt_date);
        Time = findViewById(R.id.txt_time);
        Bus  = findViewById(R.id.txt_bus);
        Seat = findViewById(R.id.txt_seat);


        From.setText(from);
        Destination.setText(destination);
        Date.setText(date);
        Price.setText(price);
        Time.setText(time);
        Bus.setText(bus);
        Seat.setText(seat);

        dbBook = FirebaseDatabase.getInstance().getReference("schedule");
        dbHistory = FirebaseDatabase.getInstance().getReference("history");
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }


    public void acceptSchedule(View view) {
        System.out.println(destination);
        dbBook.child(from).child(destination).child(date).child(bus).child(price).child(time).child("seatPicked").setValue(seat);

        String idBooking = dbBook.push().getKey();
        dbHistory.child(mUser.getUid()).child(idBooking).child("from").setValue(from);
        dbHistory.child(mUser.getUid()).child(idBooking).child("destination").setValue(destination);
        dbHistory.child(mUser.getUid()).child(idBooking).child("price").setValue(price);
        dbHistory.child(mUser.getUid()).child(idBooking).child("date").setValue(date);
        dbHistory.child(mUser.getUid()).child(idBooking).child("time").setValue(time);
        dbHistory.child(mUser.getUid()).child(idBooking).child("bus").setValue(bus);
        dbHistory.child(mUser.getUid()).child(idBooking).child("seat").setValue(seat);
//        dbHistory.child(mUser.getUid()).child(idBooking).child("payment").setValue("unpaid");

        Toast.makeText(this, "Booking successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Main_activity.class));
//        Intent intent = new Intent(this, Countdown_activity.class);
//        intent.putExtra("idBook", idBooking);
//        startActivity(intent);
        finish();
    }
}
