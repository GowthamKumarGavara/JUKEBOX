package Dao;
import Connection.ConnectDataBase;
import UserDefinedExceptions.UserNotFoundException;

import java.sql.*;
import java.util.Scanner;

public class UserDao {
    Scanner sc = new Scanner(System.in);

    public UserDao() throws SQLException, ClassNotFoundException {
    }

    Connection con = ConnectDataBase.connectToSql();

    public int userAction(){
        System.out.println("press 1 for Create Account and press 2 for signIn");
        int userId=0;
        int choice = sc.nextInt();
        if (choice==1){
            userId=createAccount();
        } else if (choice==2) {
           userId= signinAccount();
        }
        return userId;
    }



    public int createAccount(){
        System.out.println("Enter the userName :");
        String email = sc.next();
        System.out.println("Enter the password :");
        String password = sc.next();
        int userid=0;
        try{
            PreparedStatement ps = con.prepareStatement("insert into user (userEmail,password) values(?,?)");
            ps.setString(1,email);
            ps.setString(2,password);
            ps.executeUpdate();
            System.out.println("Account created Sucessfully :");
            Statement s = con.createStatement();
           ResultSet rs = s.executeQuery("select userid from user where userEmail='"+email+"'");
           if(rs.next())
               userid = rs.getInt(1);
            System.out.println("\nNote your user id for future references  "+userid);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("USER ALREADY EXISTS ");
        }
         return userid;
    }


    public int signinAccount() {
        System.out.println("Enter the userName :");
        String email = sc.next();
        int userId=0;
        System.out.println("Enter the password :");
        String password = sc.next();

        try {
            Statement s= con.createStatement();
            ResultSet rs=s.executeQuery("select userId from user where userEmail='"+email+"' and password='"+password+"'");
            if(rs.next())
            {
                userId= rs.getInt(1);
                System.out.println("Login Successful");
            }
            else
                throw new UserNotFoundException("No user found");
        }
        catch (SQLException e) {
            e.printStackTrace();
           // System.out.println("No active user found :( \nPlease try again...");

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }


        return userId;
}
}
