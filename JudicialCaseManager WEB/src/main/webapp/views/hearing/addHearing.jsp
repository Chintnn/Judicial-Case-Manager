<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Hearing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Add Hearing</h2>
        <form action="hearings" method="post">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label for="hearingId" class="form-label">Hearing ID</label>
                <input type="number" class="form-control" id="hearingId" name="hearingId" required>
            </div>
            <div class="mb-3">
                <label for="caseId" class="form-label">Case ID</label>
                <input type="number" class="form-control" id="caseId" name="caseId" required>
            </div>
            <div class="mb-3">
                <label for="judgeId" class="form-label">Judge ID</label>
                <input type="number" class="form-control" id="judgeId" name="judgeId" required>
            </div>
            <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <div class="mb-3">
                <label for="time" class="form-label">Time</label>
                <input type="time" class="form-control" id="time" name="time" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <div class="mb-3">
                <label for="outcome" class="form-label">Outcome</label>
                <input type="text" class="form-control" id="outcome" name="outcome" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Hearing</button>
            <a href="hearings" class="btn btn-secondary">Cancel</a>
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