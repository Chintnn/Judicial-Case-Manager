<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Court, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Court List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Court List</h2>
        <a href="courts?action=add" class="btn btn-primary mb-3">Add Court</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Court ID</th>
                    <th>Court Name</th>
                    <th>Location</th>
                    <th>Jurisdiction Level</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<Court> courts = (List<Court>) request.getAttribute("courts");
                   if (courts != null) {
                       for (Court court : courts) { %>
                <tr>
                    <td><%= court.getCourtId() %></td>
                    <td><%= court.getCourtName() %></td>
                    <td><%= court.getLocation() %></td>
                    <td><%= court.getJurisdictionLevel() %></td>
                    <td>
                        <a href="courts?action=edit&courtId=<%= court.getCourtId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="courts?action=delete&courtId=<%= court.getCourtId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>