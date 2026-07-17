package model;

public class Booking {

    private int bookingId;
    private int customerId;
    private int roomId;
    private String checkIn;
    private String checkOut;

    // Default Constructor
    public Booking() {

    }

    // Parameterized Constructor
    public Booking(int customerId, int roomId, String checkIn, String checkOut) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Full Constructor
    public Booking(int bookingId, int customerId, int roomId, String checkIn, String checkOut) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", roomId=" + roomId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut + "]";
    }
}