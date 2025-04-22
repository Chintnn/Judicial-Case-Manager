package main.servlet;

import main.dao.HearingDAO;
import main.exception.CaseAlreadyClosedException;
import main.exception.DatabaseException;
import main.exception.DuplicateHearingException;
import main.model.Hearing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/hearings")
public class HearingServlet extends HttpServlet {
    private HearingDAO hearingDAO;

    @Override
    public void init() throws ServletException {
        try {
            hearingDAO = new HearingDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize HearingDAO", e);
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

        // if user permission is null
        if (userPermissionLevel == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
            return;
        }
        try {
            if (action == null || action.equals("list")) {
                List<Hearing> hearings = hearingDAO.getAllHearings();
                req.setAttribute("hearings", hearings);
                req.getRequestDispatcher("/views/hearing/hearingList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/hearing/addHearing.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int hearingId = Integer.parseInt(req.getParameter("hearingId"));
                // HearingDAO lacks getHearingById, so fetch from getAllHearings
                List<Hearing> hearings = hearingDAO.getAllHearings();
                Hearing hearing = hearings.stream()
                        .filter(h -> h.getHearingId() == hearingId)
                        .findFirst()
                        .orElse(null);
                if (hearing != null) {
                    req.setAttribute("hearing", hearing);
                    req.getRequestDispatcher("/views/hearing/editHearing.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Hearing not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int hearingId = Integer.parseInt(req.getParameter("hearingId"));
                boolean deleted = hearingDAO.deleteHearing(hearingId); // Returns boolean
                if (deleted) {
                    resp.sendRedirect("hearings");
                } else {
                    req.setAttribute("error", "Failed to delete hearing");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
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
            Hearing hearing = new Hearing();
            hearing.setHearingId(Integer.parseInt(req.getParameter("hearingId")));
            hearing.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            hearing.setJudgeId(Integer.parseInt(req.getParameter("judgeId")));
            hearing.setDate(Date.valueOf(req.getParameter("date")));
            hearing.setTime(Time.valueOf(req.getParameter("time")));
            hearing.setDescription(req.getParameter("description"));
            hearing.setOutcome(req.getParameter("outcome"));

            if (action.equals("add")) {
				hearingDAO.addHearing(hearing);
                resp.sendRedirect("hearings");
            } else if (action.equals("update")) {
                hearingDAO.updateHearing(hearing);
                resp.sendRedirect("hearings");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        } catch (DuplicateHearingException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}