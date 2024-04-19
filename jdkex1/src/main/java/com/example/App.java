package com.example;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public final class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test1", "root", "root")) {

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Print");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertData(conn, scanner);
                        break;
                    case 2:
                        updateData(conn, scanner);
                        break;
                    case 3:
                        deleteData(conn, scanner);
                        break;
                    case 4:
                        printData(conn);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter PersonId: ");
        int personId = scanner.nextInt();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Address: ");
        String address = scanner.next();
        System.out.print("Enter City: ");
        String city = scanner.next();

        String sql = "INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, personId);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, city);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Failed to insert record.");
            }
        }
    }

    private static void updateData(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter PersonId whose record you want to update: ");
        int personIdToUpdate = scanner.nextInt();
        System.out.print("Enter New Last Name: ");
        String newLastName = scanner.next();
        System.out.print("Enter New First Name: ");
        String newFirstName = scanner.next();
        System.out.print("Enter New Address: ");
        String newAddress = scanner.next();
        System.out.print("Enter New City: ");
        String newCity = scanner.next();

        String sql = "UPDATE Persons SET LastName = ?, FirstName = ?, Address = ?, City = ? WHERE PersonID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, newLastName);
            preparedStatement.setString(2, newFirstName);
            preparedStatement.setString(3, newAddress);
            preparedStatement.setString(4, newCity);
            preparedStatement.setInt(5, personIdToUpdate);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with given PersonId.");
            }
        }
    }

    private static void deleteData(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter PersonId whose record you want to delete: ");
        int personIdToDelete = scanner.nextInt();

        String sql = "DELETE FROM Persons WHERE PersonID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, personIdToDelete);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No record found with given PersonId.");
            }
        }
    }

    private static void printData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Persons";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int personid = resultSet.getInt("PersonID");
                String lname = resultSet.getString("LastName");
                String fname = resultSet.getString("FirstName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                System.out.println("Person Id : "+personid + ", " +"LastName : "+ lname + ", "+ "FirstName : "+ fname +","+"Address : "+ address + ", " +"City : "+ city);
            }
        }
    }
}

 