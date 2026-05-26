<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Fashion Store</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h1>Welcome to Fashion Store 👕</h1>

<p>Explore the latest fashion collections</p>

<a href="<%= request.getContextPath() %>/products">View Products</a><br><br>
<a href="<%= request.getContextPath() %>/cart?action=view">My Cart</a><br><br>
<a href="<%= request.getContextPath() %>/order?action=list">My Orders</a><br><br>
<a href="<%= request.getContextPath() %>/profile.jsp">My Profile</a>

</body>
</html>