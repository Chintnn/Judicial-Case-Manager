<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.CaseDetails" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Case</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Case</h2>
        <% CaseDetails caseDetails = (CaseDetails) request.getAttribute("caseDetails"); %>
        <form action="cases" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= caseDetails.getCaseId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseNumber" class="form-label">Case Number</label>
                <input type="text" class="form-control" id="caseNumber" name="caseNumber" value="<%= caseDetails.getCaseNumber() %>" required>
            </div>
            <div class="mb-3">
                <label for="filingDate" class="form-label">Filing Date</label>
                <input type="date" class="form-control" id="filingDate" name="filingDate" value="<%= caseDetails.getFilingDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="caseType" class="form-label">Case Type</label>
                <input type="text" class="form-control" id="caseType" name="caseType" value="<%= caseDetails.getCaseType() %>" required>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <input type="text" class="form-control" id="status" name="status" value="<%= caseDetails.getStatus() %>" required>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label">Category ID</label>
                <input type="number" class="form-control" id="categoryId" name="categoryId" value="<%= caseDetails.getCategoryId() %>" required>
            </div>
            <div class="mb-3">
                <label for="courtId" class="form-label">Court ID</label>
                <input type="number" class="form-control" id="courtId" name="courtId" value="<%= caseDetails.getCourtId() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Case</button>
            <a href="cases" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const dateInput = document.getElementById('filingDate');
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