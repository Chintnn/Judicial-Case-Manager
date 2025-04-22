<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Evidence" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Evidence</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Evidence</h2>
        <% Evidence evidence = (Evidence) request.getAttribute("evidence"); %>
        <form action="evidence" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="evidenceId" class="form-label">Evidence ID</label>
                <input type="number" class="form-control" id="evidenceId" name="evidenceId" value="<%= evidence.getEvidenceId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= evidence.getCaseId() %>" required>
            </div>
            <div class="mb-3">
                <label for="type" class="form-label">Type</label>
                <input type="text" class="form-control" id="type" name="type" value="<%= evidence.getType() %>" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" required><%= evidence.getDescription() %></textarea>
            </div>
            <div class="mb-3">
                <label for="collectedBy" class="form-label">Collected By</label>
                <input type="number" class="form-control" id="collectedBy" name="collectedBy" value="<%= evidence.getCollectedBy() %>" required>
            </div>
            <div class="mb-3">
                <label for="submissionDate" class="form-label">Submission Date</label>
                <input type="date" class="form-control" id="submissionDate" name="submissionDate" value="<%= evidence.getSubmissionDate() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Evidence</button>
            <a href="evidence" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const dateInput = document.getElementById('submissionDate');
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