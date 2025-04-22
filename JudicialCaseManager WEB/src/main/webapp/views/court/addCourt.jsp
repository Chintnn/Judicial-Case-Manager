<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Court</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Add Court</h2>
        <form action="courts" method="post">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" required>
            </div>
            <div class="mb-3">
                <label for="courtName" class="form-label">Court Name</label>
                <input type="text" class="form-control" id="courtName" name="courtName" required>
            </div>
            <div class="mb-3">
                <label for="location" class="form-label">Location</label>
                <input type="text" class="form-control" id="location" name="location" required>
            </div>
            <div class="mb-3">
                <label for="jurisdictionLevel" class="form-label">Jurisdiction Level</label>
                <input type="text" class="form-control" id="jurisdictionLevel" name="jurisdictionLevel" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Court</button>
            <a href="courts" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>