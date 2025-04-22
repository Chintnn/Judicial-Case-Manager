<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Hearing, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hearing List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Hearing List</h2>
        <a href="hearings?action=add" class="btn btn-primary mb-3">Add Hearing</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Hearing ID</th>
                    <th>Case ID</th>
                    <th>Judge ID</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Description</th>
                    <th>Outcome</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<Hearing> hearings = (List<Hearing>) request.getAttribute("hearings");
                   if (hearings != null) {
                       for (Hearing hearing : hearings) { %>
                <tr>
                    <td><%= hearing.getHearingId() %></td>
                    <td><%= hearing.getCaseId() %></td>
                    <td><%= hearing.getJudgeId() %></td>
                    <td><%= hearing.getDate() %></td>
                    <td><%= hearing.getTime() %></td>
                    <td><%= hearing.getDescription() %></td>
                    <td><%= hearing.getOutcome() %></td>
					<%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="hearings?action=edit&hearingId=<%= hearing.getHearingId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="hearings?action=delete&hearingId=<%= hearing.getHearingId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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