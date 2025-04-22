<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-12">
                <img src="https://www.jurist.org/news/wp-content/uploads/sites/4/2021/05/India-Supreme-Court-3.jpg" alt="Court Management Banner" class="img-fluid w-100 h-auto rounded-lg mb-4" style="max-height: 200px; object-fit: cover;">
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h2 class="text-2xl font-bold text-center text-gray-800 mb-4">Court Management System Sign Up</h2>
                        <% String errorMessage = (String) request.getAttribute("errorMessage");
                           String successMessage = (String) request.getAttribute("successMessage");
                           if (errorMessage != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= errorMessage %>
                        </div>
                        <% } else if (successMessage != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= successMessage %> <a href="/Judicial_Web/login">Login here</a>.
                        </div>
                        <% } %>
                        <form action="/Judicial_Web/signup" method="post">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="permissionLevel" class="form-label">Permission Level</label>
                                <select class="form-control" id="permissionLevel" name="permissionLevel" required>
                                    <option value="0">User</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Sign Up</button>
                        </form>
                        <p class="mt-3 text-center">Already have an account? <a href="/Judicial_Web/login">Login here</a>.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>