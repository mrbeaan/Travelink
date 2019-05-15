package travelink.mobile.travelink.List;

/**
 * Created by GL552VW on 05/05/2019.
 */

public class List_schedule {
    private String from, destination, date, time, price, bus, seat;

    public List_schedule(String from, String destination, String date, String time, String price, String bus, String seat) {
        this.from = from;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.bus = bus;
        this.seat = seat;
    }

    public String getFrom() {
        return from;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getBus() {
        return bus;
    }

    public String getSeat() {
        return seat;
    }

    public String getTime() {
        return time;
    }
}

