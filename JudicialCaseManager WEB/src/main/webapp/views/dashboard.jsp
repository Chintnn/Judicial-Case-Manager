<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Welcome to the Court Management System</h2>
        <p>Welcome, <%= session.getAttribute("userEmail") != null ? session.getAttribute("userEmail") : "User" %>!</p>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Manage Cases</h5>
                        <p class="card-text">View and manage all court cases.</p>
                        <a href="cases" class="btn btn-primary">Go to Cases</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Manage Hearings</h5>
                        <p class="card-text">Schedule and update hearings.</p>
                        <a href="hearings" class="btn btn-primary">Go to Hearings</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Manage Evidence</h5>
                        <p class="card-text">Add and review case evidence.</p>
                        <a href="evidence" class="btn btn-primary">Go to Evidence</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>