package main.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/CourtDB");
        } catch (Exception e) {
            throw new ServletException("Cannot initialize DataSource", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // If already logged in, redirect to dashboard
        if (req.getSession().getAttribute("userEmail") != null) {
            resp.sendRedirect("dashboard");
            return;
        }
        req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int permissionLevel = Integer.parseInt(req.getParameter("permissionLevel"));

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts (email, password, permissionLevel) VALUES (?, ?, ?)")) {
            stmt.setString(1, email);
            stmt.setString(2, password); // Note: Use hashed passwords in production
            stmt.setInt(3, permissionLevel);
            stmt.executeUpdate();

            req.setAttribute("successMessage", "Registration successful! Please log in.");
            req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Duplicate key error
                req.setAttribute("errorMessage", "Email already exists");
            } else {
                req.setAttribute("errorMessage", "Database error: " + e.getMessage());
            }
            req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}