<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">Court Management</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <% Integer permissionLevel = (Integer) session.getAttribute("userPermissionLevel");
                   if (permissionLevel != null) {
                       if (permissionLevel == 1) { // Admin %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/appeals">Appeals</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/bails">Bails</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/courts">Courts</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/courtrooms">Court Rooms</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/courtstaff">Court Staff</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/evidence">Evidence</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/persons">Persons</a></li>
                
                <% } %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cases">Cases</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/casehistory">Case History</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/hearings">Hearings</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/settlements">Settlements</a></li>
                <% } %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/search">Search</a></li>
            </ul>
            <form class="d-flex" action="${pageContext.request.contextPath}/logout" method="get">
                <button class="btn btn-outline-danger" type="submit">Logout</button>
            </form>
        </div>
    </div>
</nav>