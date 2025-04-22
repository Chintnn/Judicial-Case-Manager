<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Evidence, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Evidence List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Evidence List</h2>
        <a href="evidence?action=add" class="btn btn-primary mb-3">Add Evidence</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Evidence ID</th>
                    <th>Case ID</th>
                    <th>Type</th>
                    <th>Description</th>
                    <th>Collected By</th>
                    <th>Submission Date</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<Evidence> evidenceList = (List<Evidence>) request.getAttribute("evidenceList");
                   if (evidenceList != null) {
                       for (Evidence evidence : evidenceList) { %>
                <tr>
                    <td><%= evidence.getEvidenceId() %></td>
                    <td><%= evidence.getCaseId() %></td>
                    <td><%= evidence.getType() %></td>
                    <td><%= evidence.getDescription() %></td>
                    <td><%= evidence.getCollectedBy() %></td>
                    <td><%= evidence.getSubmissionDate() %></td>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="evidence?action=edit&evidenceId=<%= evidence.getEvidenceId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="evidence?action=delete&evidenceId=<%= evidence.getEvidenceId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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