<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
String ctx = request.getContextPath();
String userName = (String) session.getAttribute("userName");

List<Map<String, Object>> orders =
    (List<Map<String, Object>>) request.getAttribute("orders");
%>

<html>
<head>
<title>My Orders</title>
<link rel="stylesheet" href="<%=ctx%>/assets/css/products.css">
</head>

<body>

<div class="page-shell">

    <!-- TOPBAR -->
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">FS</div>
            <div>
                <h2>FashionStore</h2>
                <p>Order History</p>
            </div>
        </div>

        <div class="top-links">
            <a href="<%=ctx%>/home">Home</a>
            <a href="<%=ctx%>/products">Products</a>

            <% if (userName != null) { %>
                <span>Hello, <%= userName %></span>
                <a href="<%=ctx%>/logout">Logout</a>
            <% } else { %>
                <a href="<%=ctx%>/login">Login</a>
            <% } %>
        </div>
    </div>

    <h1 style="margin-top:20px;">My Orders</h1>

<%
if (orders == null || orders.isEmpty()) {
%>
    <p>No orders found</p>

    <a href="<%=ctx%>/products" class="view-btn">
        Start Shopping
    </a>

<%
} else {
    for (Map<String, Object> order : orders) {
%>

    <div class="product-card" style="margin-top:20px; padding:20px;">

        <h3>Order #<%= order.get("id") %></h3>

        <p>Date: <%= order.get("date") %></p>
        <p>Status: <%= order.get("status") %></p>

        <strong>Total: ₹ <%= order.get("total") %></strong>

        <div style="margin-top:10px;">
            <a href="<%=ctx%>/order-details?id=<%= order.get("id") %>" 
               class="view-btn">
               View Details
            </a>
        </div>

    </div>

<%
    }
}
%>

</div>

</body>
</html>