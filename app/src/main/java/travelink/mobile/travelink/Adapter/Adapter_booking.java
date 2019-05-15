package travelink.mobile.travelink.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import travelink.mobile.travelink.Booking.Detail_book_activity;
import travelink.mobile.travelink.List.List_schedule;
import travelink.mobile.travelink.R;

/**
 * Created by GL552VW on 05/05/2019.
 */

public class Adapter_booking extends RecyclerView.Adapter<Adapter_booking.ViewHolder> {
    private ArrayList<List_schedule> list_schedules;
    Context context;

    public Adapter_booking(ArrayList<List_schedule> list_schedules, Context context) {
        this.list_schedules = list_schedules;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView from, destination, price, date, time, bus, seat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.txt_from);
            destination = itemView.findViewById(R.id.txt_destination);
            price       = itemView.findViewById(R.id.txt_price);
            date        = itemView.findViewById(R.id.txt_date);
            time        = itemView.findViewById(R.id.txt_time);
            bus         = itemView.findViewById(R.id.txt_bus);
            seat        = itemView.findViewById(R.id.txt_seat);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_schedule, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_book_activity.class);
                intent.putExtra("from", viewHolder.from.getText().toString());
                intent.putExtra("destination", viewHolder.destination.getText().toString());
                intent.putExtra("bus", viewHolder.bus.getText().toString());
                intent.putExtra("price", viewHolder.price.getText().toString());
                intent.putExtra("date", viewHolder.date.getText().toString());
                intent.putExtra("time", viewHolder.time.getText().toString());
                intent.putExtra("seat", viewHolder.seat.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        List_schedule list_schedule = list_schedules.get(i);

        viewHolder.bus.setText(list_schedule.getBus());
        viewHolder.from.setText(list_schedule.getFrom());
        viewHolder.destination.setText(list_schedule.getDestination());
        viewHolder.price.setText(list_schedule.getPrice());
        viewHolder.date.setText(list_schedule.getDate());
        viewHolder.time.setText(list_schedule.getTime());
        viewHolder.seat.setText(list_schedule.getSeat());
    }

    @Override
    public int getItemCount() {
        return list_schedules.size();
    }
}
