package main;

import java.util.Scanner;

import dao.BookingDAO;
import dao.CustomerDAO;
import dao.RoomDAO;
import model.Booking;
import model.Customer;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CustomerDAO dao = new CustomerDAO();

        System.out.println("\n==================================");
        System.out.println("           MAIN MENU");
        System.out.println("==================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        System.out.print("Enter Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

        // ================= REGISTER =================
        case 1:

            System.out.print("Enter Name : ");
            String name = sc.nextLine();

            System.out.print("Enter Email : ");
            String email = sc.nextLine();

            System.out.print("Enter Phone : ");
            String phone = sc.nextLine();

            System.out.print("Enter Password : ");
            String password = sc.nextLine();

            Customer customer = new Customer(name, email, phone, password);

            if (dao.registerCustomer(customer)) {
                System.out.println("Registration Successful");
            } else {
                System.out.println("Registration Failed");
            }

            break;

        // ================= LOGIN =================
        case 2:

            System.out.print("Enter Email : ");
            String loginEmail = sc.nextLine();

            System.out.print("Enter Password : ");
            String loginPassword = sc.nextLine();

            Customer c = dao.login(loginEmail, loginPassword);

            if (c != null) {

                System.out.println("\nLogin Successful");
                System.out.println("Welcome " + c.getName());

                RoomDAO roomDAO = new RoomDAO();
                BookingDAO bookingDAO = new BookingDAO();

                boolean logout = false;

                while (!logout) {

                    System.out.println("\n==================================");
                    System.out.println("       CUSTOMER DASHBOARD");
                    System.out.println("==================================");
                    System.out.println("1. View Available Rooms");
                    System.out.println("2. Book Room");
                    System.out.println("3. My Bookings");
                    System.out.println("4. Cancel Booking");
                    System.out.println("5. Search Room");
                    System.out.println("6. Update Room");
                    System.out.println("7. Delete Room");
                    System.out.println("8. Logout");

                    System.out.print("Enter Choice : ");
                    int customerChoice = sc.nextInt();

                    switch (customerChoice) {

                    // View Available Rooms
                    case 1:
                        roomDAO.viewAvailableRooms();
                        break;

                    // Book Room
                    case 2:

                        roomDAO.viewAvailableRooms();

                        System.out.print("Enter Room ID : ");
                        int roomId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Check-In Date (yyyy-MM-dd) : ");
                        String checkIn = sc.nextLine();

                        System.out.print("Enter Check-Out Date (yyyy-MM-dd) : ");
                        String checkOut = sc.nextLine();

                        Booking booking = new Booking(
                                c.getCustomerId(),
                                roomId,
                                checkIn,
                                checkOut);

                        if (bookingDAO.bookRoom(booking)) {
                            System.out.println("Booking Successful!");
                        } else {
                            System.out.println("Booking Failed!");
                        }

                        break;

                    // My Bookings
                    case 3:
                        bookingDAO.viewBookings();
                        break;

                    // Cancel Booking
                    case 4:

                        System.out.print("Enter Booking ID : ");
                        int bookingId = sc.nextInt();

                        if (bookingDAO.cancelBooking(bookingId)) {
                            System.out.println("Booking Cancelled Successfully!");
                        } else {
                            System.out.println("Booking Not Found!");
                        }

                        break;

                    // Search Room
                    case 5:

                        System.out.print("Enter Room ID : ");
                        int searchRoomId = sc.nextInt();

                        roomDAO.searchRoom(searchRoomId);

                        break;

                    // Update Room
                    case 6:

                        System.out.print("Enter Room ID : ");
                        int updateRoomId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Room Type : ");
                        String newRoomType = sc.nextLine();

                        System.out.print("Enter New Price : ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();

                        if (roomDAO.updateRoom(updateRoomId, newRoomType, newPrice)) {
                            System.out.println("Room Updated Successfully!");
                        } else {
                            System.out.println("Room Not Found!");
                        }

                        break;

                    // Delete Room
                    case 7:

                        System.out.print("Enter Room ID : ");
                        int deleteRoomId = sc.nextInt();

                        if (roomDAO.deleteRoom(deleteRoomId)) {
                            System.out.println("Room Deleted Successfully!");
                        } else {
                            System.out.println("Room Not Found!");
                        }

                        break;

                    // Logout
                    case 8:
                        logout = true;
                        System.out.println("Logged Out Successfully");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                    }
                }

            } else {
                System.out.println("Invalid Email or Password");
            }

            break;

        // ================= EXIT =================
        case 3:
            System.out.println("Thank You");
            break;

        default:
            System.out.println("Invalid Choice");
        }

        sc.close();
    }
}