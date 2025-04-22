<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Records</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container { display: none; }
        .form-container.active { display: block; }
    </style>
</head>
<body>
	<%@ include file="/views/components/navbar.jsp" %>
    <div class="container mt-5">
        <h2>Search Records</h2>
        <div class="mb-3">
            <label for="tableSelect" class="form-label">Select Table</label>
            <select id="tableSelect" class="form-select" onchange="showForm()">
                <option value="">Select a table</option>
                <%
                if (permissionLevel != null) {
                    if (permissionLevel == 1) {
                %>
                <option value="Appeal">Appeal</option>
                <option value="Bail">Bail Request</option>
                <option value="Court">Court</option>
                <option value="Court_Room">Court Room</option>
                <option value="Court_Staff">Court Staff</option>
                <option value="Evidence">Evidence</option>
                <option value="Person">Person</option>
                <% } %>
                <option value="Case_Details">Case Details</option>
                <option value="Case_History">Case History</option>
                <option value="Hearing">Hearing</option>
                <option value="Settlement">Settlement</option>
                <% } %>
            </select>
        </div>

        <!-- Appeal Form -->
        <div id="Appeal-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Appeal">
                <div class="mb-3">
                    <label for="Appeal_ID" class="form-label">Appeal ID</label>
                    <input type="number" class="form-control" id="Appeal_ID" name="Appeal_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Filed_By" class="form-label">Filed By</label>
                    <input type="number" class="form-control" id="Filed_By" name="Filed_By">
                </div>
                <div class="mb-3">
                    <label for="Date" class="form-label">Date</label>
                    <input type="date" class="form-control" id="Date" name="Date">
                </div>
                <div class="mb-3">
                    <label for="Reason" class="form-label">Reason</label>
                    <input type="text" class="form-control" id="Reason" name="Reason">
                </div>
                <div class="mb-3">
                    <label for="Status" class="form-label">Status</label>
                    <input type="text" class="form-control" id="Status" name="Status">
                </div>
                <div class="mb-3">
                    <label for="Appeal_Level" class="form-label">Appeal Level</label>
                    <input type="text" class="form-control" id="Appeal_Level" name="Appeal_Level">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Bail Request Form -->
        <div id="Bail-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Bail">
                <div class="mb-3">
                    <label for="Bail_ID" class="form-label">Bail ID</label>
                    <input type="number" class="form-control" id="Bail_ID" name="Bail_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Person_ID" class="form-label">Person ID</label>
                    <input type="number" class="form-control" id="Person_ID" name="Person_ID">
                </div>
                <div class="mb-3">
                    <label for="Granted_Date" class="form-label">Granted Date</label>
                    <input type="date" class="form-control" id="Granted_Date" name="Granted_Date">
                </div>
                <div class="mb-3">
                    <label for="Conditions" class="form-label">Conditions</label>
                    <input type="text" class="form-control" id="Conditions" name="Conditions">
                </div>
                <div class="mb-3">
                    <label for="Bail_Amount" class="form-label">Bail Amount</label>
                    <input type="number" step="0.01" class="form-control" id="Bail_Amount" name="Bail_Amount">
                </div>
                <div class="mb-3">
                    <label for="Status" class="form-label">Status</label>
                    <input type="text" class="form-control" id="Status" name="Status">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Case Details Form -->
        <div id="Case_Details-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Case_Details">
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_Number" class="form-label">Case Number</label>
                    <input type="text" class="form-control" id="Case_Number" name="Case_Number">
                </div>
                <div class="mb-3">
                    <label for="Filing_Date" class="form-label">Filing Date</label>
                    <input type="date" class="form-control" id="Filing_Date" name="Filing_Date">
                </div>
                <div class="mb-3">
                    <label for="Case_Type" class="form-label">Case Type</label>
                    <input type="text" class="form-control" id="Case_Type" name="Case_Type">
                </div>
                <div class="mb-3">
                    <label for="Status" class="form-label">Status</label>
                    <input type="text" class="form-control" id="Status" name="Status">
                </div>
                <div class="mb-3">
                    <label for="Category_ID" class="form-label">Category ID</label>
                    <input type="number" class="form-control" id="Category_ID" name="Category_ID">
                </div>
                <div class="mb-3">
                    <label for="Court_ID" class="form-label">Court ID</label>
                    <input type="number" class="form-control" id="Court_ID" name="Court_ID">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Case History Form -->
        <div id="Case_History-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Case_History">
                <div class="mb-3">
                    <label for="History_ID" class="form-label">History ID</label>
                    <input type="number" class="form-control" id="History_ID" name="History_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Date" class="form-label">Date</label>
                    <input type="date" class="form-control" id="Date" name="Date">
                </div>
                <div class="mb-3">
                    <label for="Status_Update" class="form-label">Status Update</label>
                    <input type="text" class="form-control" id="Status_Update" name="Status_Update">
                </div>
                <div class="mb-3">
                    <label for="Notes" class="form-label">Notes</label>
                    <input type="text" class="form-control" id="Notes" name="Notes">
                </div>
                <div class="mb-3">
                    <label for="Updated_By" class="form-label">Updated By</label>
                    <input type="number" class="form-control" id="Updated_By" name="Updated_By">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Court Form -->
        <div id="Court-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Court">
                <div class="mb-3">
                    <label for="Court_ID" class="form-label">Court ID</label>
                    <input type="number" class="form-control" id="Court_ID" name="Court_ID">
                </div>
                <div class="mb-3">
                    <label for="Court_Name" class="form-label">Court Name</label>
                    <input type="text" class="form-control" id="Court_Name" name="Court_Name">
                </div>
                <div class="mb-3">
                    <label for="Location" class="form-label">Location</label>
                    <input type="text" class="form-control" id="Location" name="Location">
                </div>
                <div class="mb-3">
                    <label for="Jurisdiction_Level" class="form-label">Jurisdiction Level</label>
                    <input type="text" class="form-control" id="Jurisdiction_Level" name="Jurisdiction_Level">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Court Room Form -->
        <div id="Court_Room-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Court_Room">
                <div class="mb-3">
                    <label for="Room_ID" class="form-label">Room ID</label>
                    <input type="number" class="form-control" id="Room_ID" name="Room_ID">
                </div>
                <div class="mb-3">
                    <label for="Court_ID" class="form-label">Court ID</label>
                    <input type="number" class="form-control" id="Court_ID" name="Court_ID">
                </div>
                <div class="mb-3">
                    <label for="Room_Number" class="form-label">Room Number</label>
                    <input type="text" class="form-control" id="Room_Number" name="Room_Number">
                </div>
                <div class="mb-3">
                    <label for="Capacity" class="form-label">Capacity</label>
                    <input type="number" class="form-control" id="Capacity" name="Capacity">
                </div>
                <div class="mb-3">
                    <label for="Availability_Status" class="form-label">Availability Status</label>
                    <input type="text" class="form-control" id="Availability_Status" name="Availability_Status">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Court Staff Form -->
        <div id="Court_Staff-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Court_Staff">
                <div class="mb-3">
                    <label for="Staff_ID" class="form-label">Staff ID</label>
                    <input type="number" class="form-control" id="Staff_ID" name="Staff_ID">
                </div>
                <div class="mb-3">
                    <label for="Name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="Name" name="Name">
                </div>
                <div class="mb-3">
                    <label for="Role" class="form-label">Role</label>
                    <input type="text" class="form-control" id="Role" name="Role">
                </div>
                <div class="mb-3">
                    <label for="Contact" class="form-label">Contact</label>
                    <input type="text" class="form-control" id="Contact" name="Contact">
                </div>
                <div class="mb-3">
                    <label for="Court_ID" class="form-label">Court ID</label>
                    <input type="number" class="form-control" id="Court_ID" name="Court_ID">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Evidence Form -->
        <div id="Evidence-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Evidence">
                <div class="mb-3">
                    <label for="Evidence_ID" class="form-label">Evidence ID</label>
                    <input type="number" class="form-control" id="Evidence_ID" name="Evidence_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Type" class="form-label">Type</label>
                    <input type="text" class="form-control" id="Type" name="Type">
                </div>
                <div class="mb-3">
                    <label for="Description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="Description" name="Description">
                </div>
                <div class="mb-3">
                    <label for="Collected_By" class="form-label">Collected By</label>
                    <input type="number" class="form-control" id="Collected_By" name="Collected_By">
                </div>
                <div class="mb-3">
                    <label for="Submission_Date" class="form-label">Submission Date</label>
                    <input type="date" class="form-control" id="Submission_Date" name="Submission_Date">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Hearing Form -->
        <div id="Hearing-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Hearing">
                <div class="mb-3">
                    <label for="Hearing_ID" class="form-label">Hearing ID</label>
                    <input type="number" class="form-control" id="Hearing_ID" name="Hearing_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Judge_ID" class="form-label">Judge ID</label>
                    <input type="number" class="form-control" id="Judge_ID" name="Judge_ID">
                </div>
                <div class="mb-3">
                    <label for="Date" class="form-label">Date</label>
                    <input type="date" class="form-control" id="Date" name="Date">
                </div>
                <div class="mb-3">
                    <label for="Time" class="form-label">Time</label>
                    <input type="time" class="form-control" id="Time" name="Time">
                </div>
                <div class="mb-3">
                    <label for="Description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="Description" name="Description">
                </div>
                <div class="mb-3">
                    <label for="Outcome" class="form-label">Outcome</label>
                    <input type="text" class="form-control" id="Outcome" name="Outcome">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Person Form -->
        <div id="Person-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Person">
                <div class="mb-3">
                    <label for="Person_ID" class="form-label">Person ID</label>
                    <input type="number" class="form-control" id="Person_ID" name="Person_ID">
                </div>
                <div class="mb-3">
                    <label for="Name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="Name" name="Name">
                </div>
                <div class="mb-3">
                    <label for="Date_of_Birth" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="Date_of_Birth" name="Date_of_Birth">
                </div>
                <div class="mb-3">
                    <label for="Contact" class="form-label">Contact</label>
                    <input type="text" class="form-control" id="Contact" name="Contact">
                </div>
                <div class="mb-3">
                    <label for="Address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="Address" name="Address">
                </div>
                <div class="mb-3">
                    <label for="National_ID" class="form-label">National ID</label>
                    <input type="text" class="form-control" id="National_ID" name="National_ID">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Settlement Form -->
        <div id="Settlement-form" class="form-container">
            <form action="search" method="post">
                <input type="hidden" name="table" value="Settlement">
                <div class="mb-3">
                    <label for="Settlement_ID" class="form-label">Settlement ID</label>
                    <input type="number" class="form-control" id="Settlement_ID" name="Settlement_ID">
                </div>
                <div class="mb-3">
                    <label for="Case_ID" class="form-label">Case ID</label>
                    <input type="number" class="form-control" id="Case_ID" name="Case_ID">
                </div>
                <div class="mb-3">
                    <label for="Terms" class="form-label">Terms</label>
                    <input type="text" class="form-control" id="Terms" name="Terms">
                </div>
                <div class="mb-3">
                    <label for="Date" class="form-label">Date</label>
                    <input type="date" class="form-control" id="Date" name="Date">
                </div>
                <div class="mb-3">
                    <label for="Agreement_Signed" class="form-label">Agreement Signed</label>
                    <select class="form-control" id="Agreement_Signed" name="Agreement_Signed">
                        <option value="">Select</option>
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <!-- Search Results -->
        <% List<Map<String, Object>> results = (List<Map<String, Object>>) request.getAttribute("results");
           List<String> columnNames = (List<String>) request.getAttribute("columnNames");
           if (results != null && !results.isEmpty()) { %>
        <div class="mt-5">
            <h3>Search Results</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <% for (String col : columnNames) { %>
                        <th><%= col %></th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map<String, Object> row : results) { %>
                    <tr>
                        <% for (String col : columnNames) { %>
                        <td><%= row.get(col) != null ? row.get(col) : "" %></td>
                        <% } %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } else if (results != null) { %>
        <div class="mt-5">
            <p>No records found.</p>
        </div>
        <% } %>
    </div>

    <script>
        function showForm() {
            var select = document.getElementById("tableSelect");
            var forms = document.getElementsByClassName("form-container");
            for (var i = 0; i < forms.length; i++) {
                forms[i].classList.remove("active");
            }
            var selectedForm = document.getElementById(select.value + "-form");
            if (selectedForm) {
                selectedForm.classList.add("active");
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>