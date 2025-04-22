<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CourtRoom, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Court Room List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Court Room List</h2>
        <a href="courtrooms?action=add" class="btn btn-primary mb-3">Add Court Room</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Room ID</th>
                    <th>Court ID</th>
                    <th>Room Number</th>
                    <th>Capacity</th>
                    <th>Availability Status</th>
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <th>Actions</th>
                    <%	}
                    } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% List<CourtRoom> courtRooms = (List<CourtRoom>) request.getAttribute("courtRooms");
                   if (courtRooms != null) {
                       for (CourtRoom courtRoom : courtRooms) { %>
                <tr>
                    <td><%= courtRoom.getRoomId() %></td>
                    <td><%= courtRoom.getCourtId() %></td>
                    <td><%= courtRoom.getRoomNumber() %></td>
                    <td><%= courtRoom.getCapacity() %></td>
                    <td><%= courtRoom.getAvailabilityStatus() %></td>
                    
                    <%if (permissionLevel != null) {
                        if (permissionLevel == 1) { // Admin %>
                    <td>
                        <a href="courtrooms?action=edit&roomId=<%= courtRoom.getRoomId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="courtrooms?action=delete&roomId=<%= courtRoom.getRoomId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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