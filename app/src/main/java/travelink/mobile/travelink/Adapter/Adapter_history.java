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
import travelink.mobile.travelink.List.List_history;
import travelink.mobile.travelink.List.List_schedule;
import travelink.mobile.travelink.R;

/**
 * Created by GL552VW on 05/05/2019.
 */

public class Adapter_history extends RecyclerView.Adapter<Adapter_history.ViewHolder> {
private ArrayList<List_history> lIst_histories;
        Context context;

public Adapter_history(ArrayList<List_history> list_histories, Context context) {
        this.lIst_histories = list_histories;
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


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        List_history list_history = lIst_histories.get(i);

        viewHolder.bus.setText(list_history.getBus());
        viewHolder.from.setText(list_history.getFrom());
        viewHolder.destination.setText(list_history.getDestination());
        viewHolder.price.setText(list_history.getPrice());
        viewHolder.date.setText(list_history.getDate());
        viewHolder.time.setText(list_history.getTime());
        viewHolder.seat.setText(list_history.getSeat());
    }

    @Override
    public int getItemCount() {
        return lIst_histories.size();
    }
}