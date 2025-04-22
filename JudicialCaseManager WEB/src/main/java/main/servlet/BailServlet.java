package main.servlet;

import main.dao.BailRequestDAO;
import main.exception.DatabaseException;
import main.model.BailRequest;
import main.utils.IDGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/bails")
public class BailServlet extends HttpServlet {
    private BailRequestDAO bailDAO;

    @Override
    public void init() throws ServletException {
        try {
            bailDAO = new BailRequestDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize BailRequestDAO", e);
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
                List<BailRequest> bails = bailDAO.getAllBailRequests();
                req.setAttribute("bails", bails);
                req.getRequestDispatcher("/views/bail/bailList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.setAttribute("generatedBailId", IDGenerator.generateId("B"));
                req.getRequestDispatcher("/views/bail/addBail.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int bailId = Integer.parseInt(req.getParameter("bailId"));
                BailRequest bail = bailDAO.getBailRequestById(bailId); // Exists in BailRequestDAO
                if (bail != null) {
                    req.setAttribute("bail", bail);
                    req.getRequestDispatcher("/views/bail/editBail.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Bail request not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int bailId = Integer.parseInt(req.getParameter("bailId"));
                bailDAO.deleteBailRequest(bailId); // Void, throws DatabaseException
                resp.sendRedirect("bails");
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
            BailRequest bail = new BailRequest();
            bail.setBailId(Integer.parseInt(req.getParameter("bailId")));
            bail.setCaseId(Integer.parseInt(req.getParameter("caseId")));
            bail.setPersonId(Integer.parseInt(req.getParameter("personId")));
            bail.setGrantedDate(LocalDate.parse(req.getParameter("grantedDate")));
            bail.setConditions(req.getParameter("conditions"));
            bail.setBailAmount(new BigDecimal(req.getParameter("bailAmount")));
            bail.setStatus(req.getParameter("status"));

            if (action.equals("add")) {
                bailDAO.addBailRequest(bail);
                resp.sendRedirect("bails");
            } else if (action.equals("update")) {
                bailDAO.updateBailRequest(bail);
                resp.sendRedirect("bails");
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}