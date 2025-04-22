<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Person, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Person List</h2>
        <a href="persons?action=add" class="btn btn-primary mb-3">Add Person</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Person ID</th>
                    <th>Name</th>
                    <th>Date of Birth</th>
                    <th>Contact</th>
                    <th>Address</th>
                    <th>National ID</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<Person> persons = (List<Person>) request.getAttribute("persons");
                   if (persons != null) {
                       for (Person person : persons) { %>
                <tr>
                    <td><%= person.getPersonId() %></td>
                    <td><%= person.getName() %></td>
                    <td><%= person.getDateOfBirth() %></td>
                    <td><%= person.getContact() %></td>
                    <td><%= person.getAddress() %></td>
                    <td><%= person.getNationalId() %></td>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="persons?action=edit&personId=<%= person.getPersonId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="persons?action=delete&personId=<%= person.getPersonId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                    <%	}
                    } 
                    %>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>