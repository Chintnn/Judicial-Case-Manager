package main.servlet;

import main.dao.CourtRoomDAO;
import main.exception.DatabaseException;
import main.model.CourtRoom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/courtrooms")
public class CourtRoomServlet extends HttpServlet {
    private CourtRoomDAO courtRoomDAO;

    @Override
    public void init() throws ServletException {
        try {
            courtRoomDAO = new CourtRoomDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CourtRoomDAO", e);
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
                List<CourtRoom> courtRooms = courtRoomDAO.getAllCourtRooms();
                req.setAttribute("courtRooms", courtRooms);
                req.getRequestDispatcher("/views/courtroom/courtRoomList1.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/courtroom/addCourtroom.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int roomId = Integer.parseInt(req.getParameter("roomId"));
                // CourtRoomDAO lacks getCourtRoomById, so fetch from getAllCourtRooms
                List<CourtRoom> courtRooms = courtRoomDAO.getAllCourtRooms();
                CourtRoom courtRoom = courtRooms.stream()
                        .filter(r -> r.getRoomId() == roomId)
                        .findFirst()
                        .orElse(null);
                if (courtRoom != null) {
                    req.setAttribute("courtRoom", courtRoom);
                    req.getRequestDispatcher("/views/courtroom/editCourtroom.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Court room not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int roomId = Integer.parseInt(req.getParameter("roomId"));
                courtRoomDAO.deleteCourtRoom(roomId); // Void, throws DatabaseException
                resp.sendRedirect("courtrooms");
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
            CourtRoom courtRoom = new CourtRoom();
            courtRoom.setRoomId(Integer.parseInt(req.getParameter("roomId")));
            courtRoom.setCourtId(Integer.parseInt(req.getParameter("courtId")));
            courtRoom.setRoomNumber(req.getParameter("roomNumber"));
            courtRoom.setCapacity(Integer.parseInt(req.getParameter("capacity")));
            courtRoom.setAvailabilityStatus(req.getParameter("availabilityStatus"));

            if (action.equals("add")) {
                courtRoomDAO.addCourtRoom(courtRoom);
                resp.sendRedirect("courtrooms");
            } else if (action.equals("update")) {
                courtRoomDAO.updateCourtRoom(courtRoom);
                resp.sendRedirect("courtrooms");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}