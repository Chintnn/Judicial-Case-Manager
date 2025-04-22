package main.servlet;

import main.dao.PersonDAO;
import main.exception.DatabaseException;
import main.model.Person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/persons")
public class PersonServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() throws ServletException {
        try {
            personDAO = new PersonDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize PersonDAO", e);
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
                List<Person> persons = personDAO.getAllPersons();
                req.setAttribute("persons", persons);
                req.getRequestDispatcher("/views/person/personList.jsp").forward(req, resp);
            } else if (action.equals("add")) {
                req.getRequestDispatcher("/views/person/addPerson.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int personId = Integer.parseInt(req.getParameter("personId"));
                Person person = personDAO.getPersonById(personId); // Exists in PersonDAO
                if (person != null) {
                    req.setAttribute("person", person);
                    req.getRequestDispatcher("/views/person/editPerson.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Person not found");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("delete")) {
                int personId = Integer.parseInt(req.getParameter("personId"));
                boolean deleted = personDAO.deletePerson(personId); // Returns boolean
                if (deleted) {
                    resp.sendRedirect("persons");
                } else {
                    req.setAttribute("error", "Failed to delete person");
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
            Person person = new Person();
            person.setPersonId(Integer.parseInt(req.getParameter("personId")));
            person.setName(req.getParameter("name"));
            person.setDateOfBirth(Date.valueOf(req.getParameter("dateOfBirth")));
            person.setContact(req.getParameter("contact"));
            person.setAddress(req.getParameter("address"));
            person.setNationalId(req.getParameter("nationalId"));

            if (action.equals("add")) {
                boolean added = personDAO.addPerson(person); // Returns boolean
                if (added) {
                    resp.sendRedirect("persons");
                } else {
                    req.setAttribute("error", "Failed to add person");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            } else if (action.equals("update")) {
                boolean updated = personDAO.updatePerson(person); // Returns boolean
                if (updated) {
                    resp.sendRedirect("persons");
                } else {
                    req.setAttribute("error", "Failed to update person");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            req.setAttribute("error", "Error processing request: " + e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}