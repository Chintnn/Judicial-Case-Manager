<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Court" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Court</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Court</h2>
        <% Court court = (Court) request.getAttribute("court"); %>
        <form action="courts" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" value="<%= court.getCourtId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="courtName" class="form-label">Court Name</label>
                <input type="text" class="form-control" id="courtName" name="courtName" value="<%= court.getCourtName() %>" required>
            </div>
            <div class="mb-3">
                <label for="location" class="form-label">Location</label>
                <input type="text" class="form-control" id="location" name="location" value="<%= court.getLocation() %>" required>
            </div>
            <div class="mb-3">
                <label for="jurisdictionLevel" class="form-label">Jurisdiction Level</label>
                <input type="text" class="form-control" id="jurisdictionLevel" name="jurisdictionLevel" value="<%= court.getJurisdictionLevel() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Court</button>
            <a href="courts" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>