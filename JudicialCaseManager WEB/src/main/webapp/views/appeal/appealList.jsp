<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Appeal, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appeal List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Appeal List</h2>
        <a href="appeals?action=add" class="btn btn-primary mb-3">Add Appeal</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Appeal ID</th>
                    <th>Case ID</th>
                    <th>Filed By</th>
                    <th>Date</th>
                    <th>Reason</th>
                    <th>Status</th>
                    <th>Appeal Level</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% List<Appeal> appeals = (List<Appeal>) request.getAttribute("appeals");
                   if (appeals != null) {
                       for (Appeal appeal : appeals) { %>
                <tr>
                    <td><%= appeal.getAppealId() %></td>
                    <td><%= appeal.getCaseId() %></td>
                    <td><%= appeal.getFiledBy() %></td>
                    <td><%= appeal.getDate() %></td>
                    <td><%= appeal.getReason() %></td>
                    <td><%= appeal.getStatus() %></td>
                    <td><%= appeal.getAppealLevel() %></td>
                    <td>
                        <a href="appeals?action=edit&appealId=<%= appeal.getAppealId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="appeals?action=delete&appealId=<%= appeal.getAppealId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>