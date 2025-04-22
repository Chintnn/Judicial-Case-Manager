<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CourtStaff, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Court Staff List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Court Staff List</h2>
        <a href="courtstaff?action=add" class="btn btn-primary mb-3">Add Court Staff</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Staff ID</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Contact</th>
                    <th>Court ID</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<CourtStaff> staffList = (List<CourtStaff>) request.getAttribute("staffList");
                   if (staffList != null) {
                       for (CourtStaff staff : staffList) { %>
                <tr>
                    <td><%= staff.getStaffId() %></td>
                    <td><%= staff.getName() %></td>
                    <td><%= staff.getRole() %></td>
                    <td><%= staff.getContact() %></td>
                    <td><%= staff.getCourtId() %></td>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="courtstaff?action=edit&staffId=<%= staff.getStaffId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="courtstaff?action=delete&staffId=<%= staff.getStaffId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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