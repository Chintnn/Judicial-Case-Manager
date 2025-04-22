<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.BailRequest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Bail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Bail</h2>
        <% BailRequest bail = (BailRequest) request.getAttribute("bail"); %>
        <form action="bails" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="bailId" class="form-label">Bail ID</label>
                <input type="number" class="form-control" id="bailId" name="bailId" value="<%= bail.getBailId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= bail.getCaseId() %>" required>
            </div>
            <div class="mb-3">
                <label for="personId" class="form-label">Person ID</label>
                <input type="number" class="form-control" id="personId" name="personId" value="<%= bail.getPersonId() %>" required>
            </div>
            <div class="mb-3">
                <label for="grantedDate" class="form-label">Granted Date</label>
                <input type="date" class="form-control" id="grantedDate" name="grantedDate" value="<%= bail.getGrantedDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="conditions" class="form-label">Conditions</label>
                <textarea class="form-control" id="conditions" name="conditions" required><%= bail.getConditions() %></textarea>
            </div>
            <div class="mb-3">
                <label for="bailAmount" class="form-label">Bail Amount</label>
                <input type="number" step="0.01" class="form-control" id="bailAmount" name="bailAmount" value="<%= bail.getBailAmount() %>" required>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <input type="text" class="form-control" id="status" name="status" value="<%= bail.getStatus() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Bail</button>
            <a href="bails" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const dateInput = document.getElementById('grantedDate');
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