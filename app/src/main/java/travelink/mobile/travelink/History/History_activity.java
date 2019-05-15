package travelink.mobile.travelink.History;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import travelink.mobile.travelink.Adapter.Adapter_booking;
import travelink.mobile.travelink.Adapter.Adapter_history;
import travelink.mobile.travelink.List.List_history;
import travelink.mobile.travelink.List.List_schedule;
import travelink.mobile.travelink.R;

public class History_activity extends AppCompatActivity {
    private RecyclerView recyclerViewSchedule;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<List_history> lIst_histories;

    DatabaseReference dbSchedule;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerViewSchedule = findViewById(R.id.recyclerviewHistory);
        lIst_histories = new ArrayList<>();



        mUser = FirebaseAuth.getInstance().getCurrentUser();
        dbSchedule = FirebaseDatabase.getInstance().getReference("history").child(mUser.getUid());
        adapter = new Adapter_history(lIst_histories, getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerViewSchedule.setLayoutManager(layoutManager);
        recyclerViewSchedule.setHasFixedSize(true);
        recyclerViewSchedule.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        lIst_histories.clear();
        dbSchedule.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    System.out.println(dataSnapshot1.getValue());
                    lIst_histories.add(new List_history(
                            dataSnapshot1.child("from").getValue().toString(),
                            dataSnapshot1.child("destination").getValue().toString(),
                            dataSnapshot1.child("date").getValue().toString(),
                            dataSnapshot1.child("time").getValue().toString(),
                            dataSnapshot1.child("price").getValue().toString(),
                            dataSnapshot1.child("bus").getValue().toString(),
                            dataSnapshot1.child("seat").getValue().toString()
                    ));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
