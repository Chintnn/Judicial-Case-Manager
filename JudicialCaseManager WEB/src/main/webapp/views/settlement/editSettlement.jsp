<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.model.Settlement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Settlement</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Edit Settlement</h2>
        <% Settlement settlement = (Settlement) request.getAttribute("settlement"); %>
        <form action="settlements" method="post">
            <input type="hidden" name="action" value="update">
            <div class="mb-3">
                <label for="settlementId" class="form-label">Settlement ID</label>
                <input type="number" class="form-control" id="settlementId" name="settlementId" value="<%= settlement.getSettlementId() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" value="<%= settlement.getCaseId() %>" required>
            </div>
            <div class="mb-3">
                <label for="terms" class="form-label">Terms</label>
                <textarea class="form-control" id="terms" name="terms" required><%= settlement.getTerms() %></textarea>
            </div>
            <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" value="<%= settlement.getDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="agreementSigned" class="form-label">Agreement Signed</label>
                <select class="form-control" id="agreementSigned" name="agreementSigned" required>
                    <option value="true" <%= settlement.isAgreementSigned() ? "selected" : "" %>>Yes</option>
                    <option value="false" <%= !settlement.isAgreementSigned() ? "selected" : "" %>>No</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update Settlement</button>
            <a href="settlements" class="btn btn-secondary">Cancel</a>
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