<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// Redirect authenticated users to dashboard
if (session.getAttribute("userEmail") != null) {
    response.sendRedirect("dashboard.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Court Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-12">
                <img src="https://www.jurist.org/news/wp-content/uploads/sites/4/2021/05/India-Supreme-Court-3.jpg" alt="Court Management Banner" class="img-fluid w-100 h-auto rounded-lg mb-4" style="max-height: 200px; object-fit: cover;">
            </div>
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h1 class="text-3xl font-bold text-gray-800 mb-3">Welcome to the Court Management System</h1>
                        <p class="lead mt-3">
                            You can efficiently manage court cases, hearings, evidence, and more with our comprehensive platform.
                            Whether you're an admin overseeing all operations or a user handling case-related tasks,
                            our system provides the tools you need to stay organized and informed.
                        </p>
                        <div class="mt-4">
                            <a href="login" class="btn btn-primary btn-lg mx-2">Login</a>
                            <a href="signup" class="btn btn-outline-primary btn-lg mx-2">Sign Up</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>