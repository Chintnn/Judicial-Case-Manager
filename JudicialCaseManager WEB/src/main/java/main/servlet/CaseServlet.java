package main.servlet;

import main.dao.CaseDAO;
import main.exception.CaseAlreadyClosedException;
import main.exception.DatabaseException;
import main.model.CaseDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/cases")
public class CaseServlet extends HttpServlet {
    private CaseDAO caseDAO;

    @Override
    public void init() throws ServletException {
        try {
            caseDAO = new CaseDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CaseDAO", e);
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
        if (userPermissionLevel == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
            return;
        }
        try {
            if (action == null || action.equals("list")) {
                List<CaseDetails> cases = CaseDAO.getAllCases();
                req.setAttribute("cases", cases);
                req.getRequestDispatcher("/views/case/caseList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/case/addCase.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int caseId = Integer.parseInt(req.getParameter("caseId"));
                CaseDetails caseDetails = caseDAO.getCaseById(caseId); // Exists in CaseDAO
                if (caseDetails != null) {
                    req.setAttribute("caseDetails", caseDetails);
                    req.getRequestDispatcher("/views/case/editCase.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Case not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int caseId = Integer.parseInt(req.getParameter("caseId"));
                boolean deleted = caseDAO.deleteCase(caseId); // Returns boolean
                if (deleted) {
                    resp.sendRedirect("cases");
                } else {
                    req.setAttribute("error", "Failed to delete case");
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
            CaseDetails caseDetails = new CaseDetails();
            caseDetails.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            caseDetails.setCaseNumber(req.getParameter("caseNumber"));
            caseDetails.setFilingDate(Date.valueOf(req.getParameter("filingDate")));
            caseDetails.setCaseType(req.getParameter("caseType"));
            caseDetails.setStatus(req.getParameter("status"));
            caseDetails.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            caseDetails.setCourtId(Integer.parseInt(req.getParameter("courtId")));

            if (action.equals("add")) {
                caseDAO.addCase(caseDetails);
                resp.sendRedirect("cases");
            } else if (action.equals("update")) {
                caseDAO.updateCase(caseDetails); // Void, throws DatabaseException
                resp.sendRedirect("cases");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
        catch(CaseAlreadyClosedException ce) {
        	req.setAttribute("error", "Error processing request: " + ce.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}