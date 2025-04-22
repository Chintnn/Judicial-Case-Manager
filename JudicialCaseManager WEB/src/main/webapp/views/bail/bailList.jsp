<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.BailRequest, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bail List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Bail List</h2>
        <a href="bails?action=add" class="btn btn-primary mb-3">Add Bail</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Bail ID</th>
                    <th>Case ID</th>
                    <th>Person ID</th>
                    <th>Granted Date</th>
                    <th>Conditions</th>
                    <th>Bail Amount</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% List<BailRequest> bails = (List<BailRequest>) request.getAttribute("bails");
                   if (bails != null) {
                       for (BailRequest bail : bails) { %>
                <tr>
                    <td><%= bail.getBailId() %></td>
                    <td><%= bail.getCaseId() %></td>
                    <td><%= bail.getPersonId() %></td>
                    <td><%= bail.getGrantedDate() %></td>
                    <td><%= bail.getConditions() %></td>
                    <td><%= bail.getBailAmount() %></td>
                    <td><%= bail.getStatus() %></td>
                    <td>
                        <a href="bails?action=edit&bailId=<%= bail.getBailId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="bails?action=delete&bailId=<%= bail.getBailId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>