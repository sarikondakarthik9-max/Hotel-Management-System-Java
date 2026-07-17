package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Room;
import util.DBConnection;

public class RoomDAO {

    // Add Room
    public boolean addRoom(Room room) {

        String sql = "INSERT INTO rooms(room_type, price, status) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, room.getRoomType());
            ps.setDouble(2, room.getPrice());
            ps.setString(3, room.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // View Available Rooms
    public void viewAvailableRooms() {

        String sql = "SELECT * FROM rooms WHERE status='Available'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n==========================================================");
            System.out.println("                  AVAILABLE ROOMS");
            System.out.println("==========================================================");
            System.out.printf("%-10s %-15s %-12s %-15s%n",
                    "Room ID", "Room Type", "Price", "Status");
            System.out.println("----------------------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-10d %-15s %-12.2f %-15s%n",
                        rs.getInt("room_id"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getString("status"));
            }

            System.out.println("==========================================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Room by ID
    public void searchRoom(int roomId) {

        String sql = "SELECT * FROM rooms WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== ROOM DETAILS ==========");
                System.out.println("Room ID   : " + rs.getInt("room_id"));
                System.out.println("Room Type : " + rs.getString("room_type"));
                System.out.println("Price     : " + rs.getDouble("price"));
                System.out.println("Status    : " + rs.getString("status"));
                System.out.println("==================================");

            } else {

                System.out.println("Room Not Found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Room
    public boolean updateRoom(int roomId, String roomType, double price) {

        String sql = "UPDATE rooms SET room_type=?, price=? WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, roomType);
            ps.setDouble(2, price);
            ps.setInt(3, roomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Room Status
    public boolean updateRoomStatus(int roomId, String status) {

        String sql = "UPDATE rooms SET status=? WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, roomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Room
    public boolean deleteRoom(int roomId) {

        String sql = "DELETE FROM rooms WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}