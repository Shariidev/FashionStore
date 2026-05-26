<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
String ctx = request.getContextPath();

Map<String, Object> order =
    (Map<String, Object>) request.getAttribute("order");

List<Map<String, Object>> items =
    (List<Map<String, Object>>) request.getAttribute("items");
%>

<html>
<head>
<title>Order Details</title>
<link rel="stylesheet" href="<%=ctx%>/assets/css/products.css">
</head>

<body>

<div class="page-shell">

    <h1>Order Details</h1>

    <div class="product-card" style="padding:20px; margin-bottom:20px;">
        <p><strong>Order ID:</strong> <%= order.get("id") %></p>
        <p><strong>Status:</strong> <%= order.get("status") %></p>
        <p><strong>Total:</strong> ₹ <%= order.get("total") %></p>
        <p><strong>Date:</strong> <%= order.get("date") %></p>
    </div>

    <h2>Items</h2>

<%
for (Map<String, Object> item : items) {
%>

    <div class="product-card" style="display:flex; gap:20px; margin-bottom:20px; align-items:center;">

        <div class="product-image" style="width:120px;">
            <img src="<%=ctx%>/assets/images/<%= item.get("image") %>">
        </div>

        <div class="product-info">
            <h3><%= item.get("name") %></h3>
            <p>Size: <%= item.get("size") %></p>
            <p>Qty: <%= item.get("quantity") %></p>
            <strong>₹ <%= item.get("price") %></strong>
        </div>

    </div>

<%
}
%>

    <a href="<%=ctx%>/products" class="view-btn">
        Continue Shopping
    </a>

</div>

</body>
</html>