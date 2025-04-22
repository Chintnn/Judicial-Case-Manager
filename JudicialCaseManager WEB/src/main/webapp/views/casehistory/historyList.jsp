<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CaseHistory, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Case History List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Case History List</h2>
        <a href="casehistory?action=add" class="btn btn-primary mb-3">Add Case History</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>History ID</th>
                    <th>Case ID</th>
                    <th>Date</th>
                    <th>Status Update</th>
                    <th>Notes</th>
                    <th>Updated By</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<CaseHistory> histories = (List<CaseHistory>) request.getAttribute("histories");
                   if (histories != null) {
                       for (CaseHistory history : histories) { %>
                <tr>
                    <td><%= history.getHistoryId() %></td>
                    <td><%= history.getCaseId() %></td>
                    <td><%= history.getDate() %></td>
                    <td><%= history.getStatusUpdate() %></td>
                    <td><%= history.getNotes() %></td>
                    <td><%= history.getUpdatedBy() %></td>
                    
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="casehistory?action=edit&historyId=<%= history.getHistoryId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="casehistory?action=delete&historyId=<%= history.getHistoryId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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