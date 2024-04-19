package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public final class App {

    public static void main(String[] args) {
        // JDBC URL, username, and password
        String url = "jdbc:mysql://localhost:3306/test1";
        String username = "root";
        String password = "root";
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the PersonID
        System.out.print("Enter PersonID: ");
        int personId = scanner.nextInt();
        
        // SQL query with parameterized SELECT statement
        String sql = "SELECT * FROM Persons WHERE PersonID = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Prepare the statement with the SQL query
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // Set the parameter value for PersonID
            stmt.setInt(1, personId);
            
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    int personid = resultSet.getInt("PersonID");
                    String lastName = resultSet.getString("LastName");
                    String firstName = resultSet.getString("FirstName");
                    String address = resultSet.getString("Address");
                    String city = resultSet.getString("City");
                    
                    // Print the retrieved data
                    System.out.println(personid + ", " + lastName + ", " + firstName +","+ address + ", " + city);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }
}