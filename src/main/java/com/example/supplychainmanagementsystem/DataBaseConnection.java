package com.example.supplychainmanagementsystem;

import java.sql.*;
public class DataBaseConnection {
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/supplychainmanagementsystem";
    private static final String userName = "root";
    private static final String password = "Chandu@190109";

    public Statement getStatement(){
        Statement statement = null ;
        Connection conn ;
        try{
            conn = DriverManager.getConnection(databaseUrl,userName,password) ;
            statement = conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        return statement ;
    }
    public ResultSet getQuearyTable(String queary){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(queary);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int excuteUpdateQuery(String query){
        Statement statement = getStatement();
        try{
            return statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ResultSet rs = dataBaseConnection.getQuearyTable("SELECT email, firstname FROM CUSTOMER");
        try{
            while(rs.next()){
                System.out.println(rs.getString("email")+" " + rs.getString("firstname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}