<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CourtStaff" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Court Staff</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Court Staff</h2>
        <% CourtStaff staff = (CourtStaff) request.getAttribute("staff"); %>
        <form action="courtstaff" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="staffId" class="form-label">Staff ID</label>
                <input type="number" class="form-control" id="staffId" name="staffId" value="<%= staff.getStaffId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= staff.getName() %>" required>
            </div>
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <input type="text" class="form-control" id="role" name="role" value="<%= staff.getRole() %>" required>
            </div>
            <div class="mb-3">
                <label for="contact" class="form-label">Contact</label>
                <input type="text" class="form-control" id="contact" name="contact" value="<%= staff.getContact() %>" required>
            </div>
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" value="<%= staff.getCourtId() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Court Staff</button>
            <a href="courtstaff" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>