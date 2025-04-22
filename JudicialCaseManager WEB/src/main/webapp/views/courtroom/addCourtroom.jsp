<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Court Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Add Court Room</h2>
        <form action="courtrooms" method="post">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label for="roomId" class="form-label">Room ID</label>
                <input type="number" class="form-control" id="roomId" name="roomId" required>
            </div>
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" required>
            </div>
            <div class="mb-3">
                <label for="roomNumber" class="form-label">Room Number</label>
                <input type="text" class="form-control" id="roomNumber" name="roomNumber" required>
            </div>
            <div class="mb-3">
                <label for="capacity" class="form-label">Capacity</label>
                <input type="number" class="form-control" id="capacity" name="capacity" required>
            </div>
            <div class="mb-3">
                <label for="availabilityStatus" class="form-label">Availability Status</label>
                <input type="text" class="form-control" id="availabilityStatus" name="availabilityStatus" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Court Room</button>
            <a href="courtrooms" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>