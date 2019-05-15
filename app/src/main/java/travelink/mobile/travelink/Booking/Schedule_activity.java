package travelink.mobile.travelink.Booking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import travelink.mobile.travelink.Adapter.Adapter_booking;
import travelink.mobile.travelink.List.List_schedule;
import travelink.mobile.travelink.R;

public class Schedule_activity extends AppCompatActivity {
    String from, destination, date, seat;
    private RecyclerView recyclerViewSchedule;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<List_schedule> list_schedules;

    DatabaseReference dbSchedule;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        dbSchedule = FirebaseDatabase.getInstance().getReference("schedule");

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        destination = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        seat = intent.getStringExtra("seat");

        recyclerViewSchedule = findViewById(R.id.recyclerviewSchedule);
        list_schedules = new ArrayList<>();

        adapter = new Adapter_booking(list_schedules, getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerViewSchedule.setLayoutManager(layoutManager);
        recyclerViewSchedule.setHasFixedSize(true);
        recyclerViewSchedule.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbSchedule.child(from).child(destination).child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_schedules.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()){
                            for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()){
                                list_schedules.add(new List_schedule(
                                        from,
                                        destination,
                                        date,
                                        dataSnapshot3.getKey().toString(),
                                        dataSnapshot3.child("price").getValue().toString(),
                                        dataSnapshot3.child("bus").getValue().toString(),
                                        seat
                                ));
                                System.out.println(dataSnapshot3);
                            }
                        }
                    }
                } else {
                    Toast.makeText(Schedule_activity.this, "No schedule", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
