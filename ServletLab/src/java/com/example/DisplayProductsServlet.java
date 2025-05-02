package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/displayProducts")
public class DisplayProductsServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Optional but safe
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/stock_management?useSSL=false&serverTimezone=UTC",
            "root", "root" // change as needed
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM stock")) {

            out.println("<html><body><h1>Stock List</h1>");
            while (rs.next()) {
                out.println("<p>" + rs.getString("product_name") + ": " + rs.getInt("quantity") + "</p>");
            }
            out.println("</body></html>");

        } catch (SQLException e) {
            out.println("<h1>Database Error: " + e.getMessage() + "</h1>");
            e.printStackTrace(out);
        }
    }
}
