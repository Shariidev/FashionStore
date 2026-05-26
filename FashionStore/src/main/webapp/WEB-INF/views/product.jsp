<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h2>Products Page</h2>

<p>This page is handled by ProductController.</p>

<a href="<%= request.getContextPath() %>/products">Go to Product List</a>

</body>
</html>