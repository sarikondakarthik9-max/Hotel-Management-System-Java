package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Booking;
import util.DBConnection;
import dao.BookingDAO;
import model.Booking;

public class BookingDAO {

    Connection con = DBConnection.getConnection();

    // Book a Room
    public boolean bookRoom(Booking booking) {
        String query = "INSERT INTO bookings(customer_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getRoomId());
            ps.setString(3, booking.getCheckIn());
            ps.setString(4, booking.getCheckOut());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // View All Bookings
    public void viewBookings() {
        String query = "SELECT * FROM bookings";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n================ BOOKING DETAILS ================");

            while (rs.next()) {
                System.out.println("Booking ID : " + rs.getInt("booking_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Room ID    : " + rs.getInt("room_id"));
                System.out.println("Check In   : " + rs.getString("check_in"));
                System.out.println("Check Out  : " + rs.getString("check_out"));
                System.out.println("-----------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cancel Booking
    public boolean cancelBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}