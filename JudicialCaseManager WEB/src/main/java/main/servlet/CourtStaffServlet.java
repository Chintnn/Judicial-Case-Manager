package main.servlet;

import main.dao.CourtStaffDAO;
import main.exception.DatabaseException;
import main.model.CourtStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/courtstaff")
public class CourtStaffServlet extends HttpServlet {
    private CourtStaffDAO courtStaffDAO;

    @Override
    public void init() throws ServletException {
        try {
            courtStaffDAO = new CourtStaffDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CourtStaffDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);

        //user not logged in
        Integer userPermissionLevel = null;
        if (session != null) {
            try {
                userPermissionLevel = (Integer) session.getAttribute("userPermissionLevel");
            } catch (ClassCastException e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid permission data");
                return;
            }
        }

        // if user permission is null or they arent Admin
        if (userPermissionLevel == null || userPermissionLevel != 1) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
            return;
        }
        try {
            if (action == null || action.equals("list")) {
                List<CourtStaff> staffList = courtStaffDAO.getAllCourtStaff();
                req.setAttribute("staffList", staffList);
                req.getRequestDispatcher("/views/courtstaff/staffList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/courtstaff/addStaff.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int staffId = Integer.parseInt(req.getParameter("staffId"));
                CourtStaff staff = courtStaffDAO.getCourtStaffById(staffId); // Exists in CourtStaffDAO
                if (staff != null) {
                    req.setAttribute("staff", staff);
                    req.getRequestDispatcher("/views/courtstaff/editStaff.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Court staff not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int staffId = Integer.parseInt(req.getParameter("staffId"));
                courtStaffDAO.deleteCourtStaff(staffId); // Void, throws DatabaseException
                resp.sendRedirect("courtstaff");
            }
        } catch (DatabaseException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            CourtStaff staff = new CourtStaff();
            staff.setStaffId(Integer.parseInt(req.getParameter("staffId")));
            staff.setName(req.getParameter("name"));
            staff.setRole(req.getParameter("role"));
            staff.setContact(req.getParameter("contact"));
            staff.setCourtId(Integer.parseInt(req.getParameter("courtId")));

            if (action.equals("add")) {
                courtStaffDAO.addCourtStaff(staff);
                resp.sendRedirect("courtstaff");
            } else if (action.equals("update")) {
                courtStaffDAO.updateCourtStaff(staff);
                resp.sendRedirect("courtstaff");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}