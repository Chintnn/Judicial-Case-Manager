package main.servlet;

import main.dao.SettlementDAO;
import main.exception.DatabaseException;
import main.model.Settlement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/settlements")
public class SettlementServlet extends HttpServlet {
    private SettlementDAO settlementDAO;

    @Override
    public void init() throws ServletException {
        try {
            settlementDAO = new SettlementDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize SettlementDAO", e);
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
                List<Settlement> settlements = settlementDAO.getAllSettlements();
                req.setAttribute("settlements", settlements);
                req.getRequestDispatcher("/views/settlement/settlementList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/settlement/addSettlement.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int settlementId = Integer.parseInt(req.getParameter("settlementId"));
                // SettlementDAO lacks getSettlementById, so fetch from getAllSettlements
                List<Settlement> settlements = settlementDAO.getAllSettlements();
                Settlement settlement = settlements.stream()
                        .filter(s -> s.getSettlementId() == settlementId)
                        .findFirst()
                        .orElse(null);
                if (settlement != null) {
                    req.setAttribute("settlement", settlement);
                    req.getRequestDispatcher("/views/settlement/editSettlement.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Settlement not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int settlementId = Integer.parseInt(req.getParameter("settlementId"));
                boolean deleted = settlementDAO.deleteSettlement(settlementId); // Returns boolean
                if (deleted) {
                    resp.sendRedirect("settlements");
                } else {
                    req.setAttribute("error", "Failed to delete settlement");
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
            Settlement settlement = new Settlement();
            settlement.setSettlementId(Integer.parseInt(req.getParameter("settlementId")));
            settlement.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            settlement.setTerms(req.getParameter("terms"));
            settlement.setDate(Date.valueOf(req.getParameter("date")));
            settlement.setAgreementSigned(Boolean.parseBoolean(req.getParameter("agreementSigned")));

            if (action.equals("add")) {
                settlementDAO.addSettlement(settlement);
                resp.sendRedirect("settlements");
            } else if (action.equals("update")) {
                settlementDAO.updateSettlement(settlement);
                resp.sendRedirect("settlements");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}