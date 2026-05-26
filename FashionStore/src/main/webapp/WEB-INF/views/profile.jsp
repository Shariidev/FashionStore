<%@ page import="com.fashionstore.model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h2>User Profile</h2>

<% if (user != null) { %>

    <p>Email: <%= user.getEmail() %></p>
    <p>User ID: <%= user.getUserId() %></p>

<% } else { %>

    <p>No user logged in</p>

<% } %>

</body>
</html>