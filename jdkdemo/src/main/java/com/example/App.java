package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        String sql = "select * from Persons";
   
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test1", "root", "root");
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
   
            ResultSet resultSet = preparedStatement.executeQuery();
   
            while (resultSet.next()) {
                int personid = resultSet.getInt("PersonId");
                String lname = resultSet.getString("LastName");
                String fname = resultSet.getString("FirstName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
               
                System.out.println(personid + ", " + lname + ", " + fname +","+ address + ", " + city);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


