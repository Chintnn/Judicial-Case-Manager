<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Error</h2>
        <div class="alert alert-danger" role="alert">
            <% String errorMessage = (String) request.getAttribute("error");
               if (errorMessage != null) { %>
                <%= errorMessage %>
            <% } else { %>
                An unexpected error occurred. Please try again later.
            <% } %>
        </div>
        <a href="dashboard" class="btn btn-primary">Back to Dashboard</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>