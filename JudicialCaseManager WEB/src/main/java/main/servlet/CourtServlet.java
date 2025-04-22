package main.servlet;

import main.dao.CourtDAO;
import main.exception.DatabaseException;
import main.model.Court;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/courts")
public class CourtServlet extends HttpServlet {
    private CourtDAO courtDAO;

    @Override
    public void init() throws ServletException {
        try {
            courtDAO = new CourtDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CourtDAO", e);
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
                List<Court> courts = courtDAO.getAllCourts();
                req.setAttribute("courts", courts);
                req.getRequestDispatcher("/views/court/courtList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/court/addCourt.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int courtId = Integer.parseInt(req.getParameter("courtId"));
                // CourtDAO lacks getCourtById, so fetch from getAllCourts
                List<Court> courts = courtDAO.getAllCourts();
                Court court = courts.stream()
                        .filter(c -> c.getCourtId() == courtId)
                        .findFirst()
                        .orElse(null);
                if (court != null) {
                    req.setAttribute("court", court);
                    req.getRequestDispatcher("/views/court/editCourt.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Court not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int courtId = Integer.parseInt(req.getParameter("courtId"));
                courtDAO.deleteCourt(courtId); // Void, throws DatabaseException
                resp.sendRedirect("courts");
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
            Court court = new Court();
            court.setCourtId(Integer.parseInt(req.getParameter("courtId")));
            court.setCourtName(req.getParameter("courtName"));
            court.setLocation(req.getParameter("location"));
            court.setJurisdictionLevel(req.getParameter("jurisdictionLevel"));

            if (action.equals("add")) {
                courtDAO.addCourt(court);
                resp.sendRedirect("courts");
            } else if (action.equals("update")) {
                courtDAO.updateCourt(court);
                resp.sendRedirect("courts");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}