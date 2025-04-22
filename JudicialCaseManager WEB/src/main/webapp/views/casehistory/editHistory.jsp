<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CaseHistory" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Case History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Case History</h2>
        <% CaseHistory history = (CaseHistory) request.getAttribute("history"); %>
        <form action="casehistory" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="historyId" class="form-label">History ID</label>
                <input type="number" class="form-control" id="historyId" name="historyId" value="<%= history.getHistoryId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= history.getCaseId() %>" required>
            </div>
            <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" value="<%= history.getDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="statusUpdate" class="form-label">Status Update</label>
                <input type="text" class="form-control" id="statusUpdate" name="statusUpdate" value="<%= history.getStatusUpdate() %>" required>
            </div>
            <div class="mb-3">
                <label for="notes" class="form-label">Notes</label>
                <textarea class="form-control" id="notes" name="notes" required><%= history.getNotes() %></textarea>
            </div>
            <div class="mb-3">
                <label for="updatedBy" class="form-label">Updated By</label>
                <input type="number" class="form-control" id="updatedBy" name="updatedBy" value="<%= history.getUpdatedBy() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Case History</button>
            <a href="casehistory" class="btn btn-secondary">Cancel</a>
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