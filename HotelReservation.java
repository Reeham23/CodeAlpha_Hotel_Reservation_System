import java.util.ArrayList;
import java.util.Scanner;


class Room {
    String roomType;
    int roomNumber;
    double pricePerNight;
    boolean isAvailable;

    Room(String roomType, int roomNumber, double pricePerNight) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; 
    }

    void bookRoom() {
        this.isAvailable = false; 
    }

    void freeRoom() {
        this.isAvailable = true;
    }
}


class Reservation {
    String guestName;
    Room room;
    int numberOfNights;
    double totalPrice;

    Reservation(String guestName, Room room, int numberOfNights) {
        this.guestName = guestName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalPrice = room.pricePerNight * numberOfNights;
        this.room.bookRoom(); 
    }

    void showDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.roomNumber + " (" + room.roomType + ")");
        System.out.println("Number of Nights: " + numberOfNights);
        System.out.println("Total Price: $" + totalPrice);
    }
}


public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        initializeRooms();

        
        while (true) {
            System.out.println("\nWelcome to the Hotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservation details");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservationDetails();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    public static void initializeRooms() {
        rooms.add(new Room("Single", 101, 100.00));
        rooms.add(new Room("Double", 102, 150.00));
        rooms.add(new Room("Suite", 201, 300.00));
        rooms.add(new Room("Single", 103, 100.00));
        rooms.add(new Room("Double", 104, 150.00));
    }

   
    public static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room Number: " + room.roomNumber + " (" + room.roomType + ") - $" + room.pricePerNight + " per night");
            }
        }
    }

    
    public static void makeReservation() {
        System.out.print("\nEnter your name: ");
        String guestName = scanner.nextLine();
        
        searchAvailableRooms();
        System.out.print("Enter the room number you want to book: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter the number of nights: ");
        int numberOfNights = scanner.nextInt();
        scanner.nextLine(); 

        
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            Reservation reservation = new Reservation(guestName, selectedRoom, numberOfNights);
            reservations.add(reservation);
            System.out.println("Reservation successfully made!");
            reservation.showDetails();
        } else {
            System.out.println("Sorry, the room is not available.");
        }
    }

   
    public static void viewReservationDetails() {
        System.out.print("\nEnter your name to view reservation: ");
        String guestName = scanner.nextLine();

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.guestName.equalsIgnoreCase(guestName)) {
                reservation.showDetails();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No reservation found for " + guestName);
        }
    }
}
