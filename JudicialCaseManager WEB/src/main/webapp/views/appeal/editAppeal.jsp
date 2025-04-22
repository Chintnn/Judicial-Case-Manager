<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Appeal" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Appeal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Appeal</h2>
        <% Appeal appeal = (Appeal) request.getAttribute("appeal"); %>
        <form action="appeals" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="appealId" class="form-label">Appeal ID</label>
                <input type="number" class="form-control" id="appealId" name="appealId" value="<%= appeal.getAppealId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= appeal.getCaseId() %>" required>
            </div>
            <div class="mb-3">
                <label for="filedBy" class="form-label">Filed By</label>
                <input type="number" class="form-control" id="filedBy" name="filedBy" value="<%= appeal.getFiledBy() %>" required>
            </div>
            <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" value="<%= appeal.getDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="reason" class="form-label">Reason</label>
                <textarea class="form-control" id="reason" name="reason" required><%= appeal.getReason() %></textarea>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <input type="text" class="form-control" id="status" name="status" value="<%= appeal.getStatus() %>" required>
            </div>
            <div class="mb-3">
                <label for="appealLevel" class="form-label">Appeal Level</label>
                <input type="text" class="form-control" id="appealLevel" name="appealLevel" value="<%= appeal.getAppealLevel() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Appeal</button>
            <a href="appeals" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const dateInput = document.getElementById('date');
            const today = new Date();
            const firstDayOfCurrentMonth = new Date(today.getFullYear(), today.getMonth(), 1);
            const lastDayOfPreviousMonth = new Date(firstDayOfCurrentMonth);
            lastDayOfPreviousMonth.setDate(0);
            const maxDate = lastDayOfPreviousMonth.toISOString().split('T')[0];
            dateInput.setAttribute('max', maxDate);
        });
    </script>
</body>
</html>