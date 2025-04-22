package main.servlet;

import main.dao.AppealDAO;
import main.exception.DatabaseException;
import main.model.Appeal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/appeals")
public class AppealServlet extends HttpServlet {
    private AppealDAO appealDAO;

    @Override
    public void init() throws ServletException {
        try {
            appealDAO = new AppealDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize AppealDAO", e);
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
                List<Appeal> appeals = appealDAO.getAllAppeals();
                req.setAttribute("appeals", appeals);
                req.getRequestDispatcher("/views/appeal/appealList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/appeal/addAppeal.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int appealId = Integer.parseInt(req.getParameter("appealId"));
                //getAllAppeals fetches all appeals
                List<Appeal> appeals = appealDAO.getAllAppeals();
                Appeal appeal = appeals.stream()
                        .filter(a -> a.getAppealId() == appealId)
                        .findFirst()
                        .orElse(null);
                if (appeal != null) {
                    req.setAttribute("appeal", appeal);
                    req.getRequestDispatcher("/views/appeal/editAppeal.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Appeal not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int appealId = Integer.parseInt(req.getParameter("appealId"));
                appealDAO.deleteAppeal(appealId); // Void, throws DatabaseException
                resp.sendRedirect("appeals");
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
            Appeal appeal = new Appeal();
            appeal.setAppealId(Integer.parseInt(req.getParameter("appealId")));
            appeal.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            appeal.setFiledBy(Integer.parseInt(req.getParameter("filedBy")));
            appeal.setDate(Date.valueOf(req.getParameter("date")));
            appeal.setReason(req.getParameter("reason"));
            appeal.setStatus(req.getParameter("status"));
            appeal.setAppealLevel(req.getParameter("appealLevel"));

            if (action.equals("add")) {
                appealDAO.addAppeal(appeal);
                resp.sendRedirect("appeals");
            } else if (action.equals("update")) {
                appealDAO.updateAppeal(appeal);
                resp.sendRedirect("appeals");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}