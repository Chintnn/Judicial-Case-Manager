<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CaseDetails, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Case List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Case List</h2>
        <a href="cases?action=add" class="btn btn-primary mb-3">Add Case</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Case ID</th>
                    <th>Case Number</th>
                    <th>Filing Date</th>
                    <th>Case Type</th>
                    <th>Status</th>
                    <th>Category ID</th>
                    <th>Court ID</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%}
                    } %>
                </tr>
            </thead>
            <tbody>
                <% List<CaseDetails> cases = (List<CaseDetails>) request.getAttribute("cases");
                   if (cases != null) {
                       for (CaseDetails caseDetails : cases) { %>
                <tr>
                    <td><%= caseDetails.getCaseId() %></td>
                    <td><%= caseDetails.getCaseNumber() %></td>
                    <td><%= caseDetails.getFilingDate() %></td>
                    <td><%= caseDetails.getCaseType() %></td>
                    <td><%= caseDetails.getStatus() %></td>
                    <td><%= caseDetails.getCategoryId() %></td>
                    <td><%= caseDetails.getCourtId() %></td>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="cases?action=edit&caseId=<%= caseDetails.getCaseId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="cases?action=delete&caseId=<%= caseDetails.getCaseId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                    <%}
                    } %>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>