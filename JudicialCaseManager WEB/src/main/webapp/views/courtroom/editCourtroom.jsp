<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CourtRoom" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Court Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Court Room</h2>
        <% CourtRoom courtRoom = (CourtRoom) request.getAttribute("courtRoom"); %>
        <form action="courtrooms" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="roomId" class="form-label">Room ID</label>
                <input type="number" class="form-control" id="roomId" name="roomId" value="<%= courtRoom.getRoomId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" value="<%= courtRoom.getCourtId() %>" required>
            </div>
            <div class="mb-3">
                <label for="roomNumber" class="form-label">Room Number</label>
                <input type="text" class="form-control" id="roomNumber" name="roomNumber" value="<%= courtRoom.getRoomNumber() %>" required>
            </div>
            <div class="mb-3">
                <label for="capacity" class="form-label">Capacity</label>
                <input type="number" class="form-control" id="capacity" name="capacity" value="<%= courtRoom.getCapacity() %>" required>
            </div>
            <div class="mb-3">
                <label for="availabilityStatus" class="form-label">Availability Status</label>
                <input type="text" class="form-control" id="availabilityStatus" name="availabilityStatus" value="<%= courtRoom.getAvailabilityStatus() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Court Room</button>
            <a href="courtrooms" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>