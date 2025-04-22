package main.servlet;

import main.dao.EvidenceDAO;
import main.exception.DatabaseException;
import main.model.Evidence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/evidence")
public class EvidenceServlet extends HttpServlet {
    private EvidenceDAO evidenceDAO;

    @Override
    public void init() throws ServletException {
        try {
            evidenceDAO = new EvidenceDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize EvidenceDAO", e);
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
                List<Evidence> evidenceList = evidenceDAO.getAllEvidence();
                req.setAttribute("evidenceList", evidenceList);
                req.getRequestDispatcher("/views/evidence/evidenceList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/evidence/addEvidence.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int evidenceId = Integer.parseInt(req.getParameter("evidenceId"));
                // EvidenceDAO lacks getEvidenceById, so fetch from getAllEvidence
                List<Evidence> evidenceList = evidenceDAO.getAllEvidence();
                Evidence evidence = evidenceList.stream()
                        .filter(e -> e.getEvidenceId() == evidenceId)
                        .findFirst()
                        .orElse(null);
                if (evidence != null) {
                    req.setAttribute("evidence", evidence);
                    req.getRequestDispatcher("/views/evidence/editEvidence.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Evidence not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int evidenceId = Integer.parseInt(req.getParameter("evidenceId"));
                boolean deleted = evidenceDAO.deleteEvidence(evidenceId); // Returns boolean
                if (deleted) {
                    resp.sendRedirect("evidence");
                } else {
                    req.setAttribute("error", "Failed to delete evidence");
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
            Evidence evidence = new Evidence();
            evidence.setEvidenceId(Integer.parseInt(req.getParameter("evidenceId")));
            evidence.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            evidence.setType(req.getParameter("type"));
            evidence.setDescription(req.getParameter("description"));
            evidence.setCollectedBy(Integer.parseInt(req.getParameter("collectedBy")));
            evidence.setSubmissionDate(Date.valueOf(req.getParameter("submissionDate")));

            if (action.equals("add")) {
                evidenceDAO.addEvidence(evidence);
                resp.sendRedirect("evidence");
            } else if (action.equals("update")) {
                evidenceDAO.updateEvidence(evidence);
                resp.sendRedirect("evidence");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}