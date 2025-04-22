<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        // Redirect to login page after 3 seconds
        setTimeout(function() {
            window.location.href = "/Judicial_Web/login";
        }, 3000);
    </script>
</head>
<body class="bg-light">
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-12">
                <img src="https://www.jurist.org/news/wp-content/uploads/sites/4/2021/05/India-Supreme-Court-3.jpg" alt="Court Management Banner" class="img-fluid w-100 h-auto rounded-lg mb-4" style="max-height: 200px; object-fit: cover;">
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h2 class="text-2xl font-bold text-gray-800 mb-4">Logout</h2>
                        <p class="lead mb-4">You have been successfully logged out. Redirecting to login in a few seconds...</p>
                        <a href="/Judicial_Web/login" class="btn btn-primary">Go to Login Now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>