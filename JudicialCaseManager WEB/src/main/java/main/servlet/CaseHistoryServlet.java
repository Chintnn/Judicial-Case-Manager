package main.servlet;

import main.dao.CaseHistoryDAO;
import main.exception.DatabaseException;
import main.model.CaseHistory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/casehistory")
public class CaseHistoryServlet extends HttpServlet {
    private CaseHistoryDAO caseHistoryDAO;

    @Override
    public void init() throws ServletException {
        try {
            caseHistoryDAO = new CaseHistoryDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CaseHistoryDAO", e);
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
                List<CaseHistory> histories = caseHistoryDAO.getAllCaseHistories();
                req.setAttribute("histories", histories);
                req.getRequestDispatcher("/views/casehistory/historyList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/casehistory/addHistory.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int historyId = Integer.parseInt(req.getParameter("historyId"));
                // fetch from getAllCaseHistories
                List<CaseHistory> histories = caseHistoryDAO.getAllCaseHistories();
                CaseHistory history = histories.stream()
                        .filter(h -> h.getHistoryId() == historyId)
                        .findFirst()
                        .orElse(null);
                if (history != null) {
                    req.setAttribute("history", history);
                    req.getRequestDispatcher("/views/casehistory/editHistory.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Case history not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int historyId = Integer.parseInt(req.getParameter("historyId"));
                caseHistoryDAO.deleteCaseHistory(historyId); // Void, throws DatabaseException
                resp.sendRedirect("casehistory");
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
            CaseHistory history = new CaseHistory();
            history.setHistoryId(Integer.parseInt(req.getParameter("historyId")));
            history.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            history.setDate(LocalDate.parse(req.getParameter("date")));
            history.setStatusUpdate(req.getParameter("statusUpdate"));
            history.setNotes(req.getParameter("notes"));
            history.setUpdatedBy(Integer.parseInt(req.getParameter("updatedBy")));

            if (action.equals("add")) {
                caseHistoryDAO.addCaseHistory(history);
                resp.sendRedirect("casehistory");
            } else if (action.equals("update")) {
                caseHistoryDAO.updateCaseHistory(history);
                resp.sendRedirect("casehistory");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}