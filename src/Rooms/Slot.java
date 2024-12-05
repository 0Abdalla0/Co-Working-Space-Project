package Rooms;

import java.time.LocalDate;
import java.time.LocalTime;

public class Slot {
    private LocalDate date;
    private LocalTime time; // 10:30
    private double fees;

    public Slot(LocalDate date, LocalTime time, double fees) {
        this.date = date;
        this.time = time;
        this.fees = fees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}