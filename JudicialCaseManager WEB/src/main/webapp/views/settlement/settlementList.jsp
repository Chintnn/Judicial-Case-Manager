<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Settlement, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Settlement List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Settlement List</h2>
        <a href="settlements?action=add" class="btn btn-primary mb-3">Add Settlement</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Settlement ID</th>
                    <th>Case ID</th>
                    <th>Terms</th>
                    <th>Date</th>
                    <th>Agreement Signed</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<Settlement> settlements = (List<Settlement>) request.getAttribute("settlements");
                   if (settlements != null) {
                       for (Settlement settlement : settlements) { %>
                <tr>
                    <td><%= settlement.getSettlementId() %></td>
                    <td><%= settlement.getCaseId() %></td>
                    <td><%= settlement.getTerms() %></td>
                    <td><%= settlement.getDate() %></td>
                    <td><%= settlement.isAgreementSigned() %></td>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="settlements?action=edit&settlementId=<%= settlement.getSettlementId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="settlements?action=delete&settlementId=<%= settlement.getSettlementId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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