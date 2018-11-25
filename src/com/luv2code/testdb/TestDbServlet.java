package com.luv2code.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestDbServlet")
public class TestDbServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = "springstudent";
        String pass = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try{
            PrintWriter out = response.getWriter();
            out.println("Connecting to database");

            Class.forName(driver);

            Connection myCon =
                    DriverManager.getConnection(jdbcUrl, user, pass);
            out.println("Connection successful!!!");
            myCon.close();
        } catch(Exception exc){
            exc.printStackTrace();
            throw new ServletException(exc);
        }
    }
}
