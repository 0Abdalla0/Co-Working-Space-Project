package Rooms;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Slot {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime; // 10:30
    private double fees;
    private boolean reserved;
    private int userID;
    private final Room room;

    public Room getRoom() {
        return room;
    }

    public Slot(LocalDate date, LocalTime startTime, LocalTime endTime, double fees, Room room) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fees = fees;
        this.room = room;
        this.reserved = false;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public Slot(LocalDate date, LocalTime startTime, LocalTime endTime, double fees, int userID, Room room) {
        this(date, startTime, endTime, fees, room); // Reuse the first constructor
        this.reserved = true;
        this.userID = userID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getFees() {
        return fees;
    }

    public boolean isReserved() {
        return reserved;
    }
}