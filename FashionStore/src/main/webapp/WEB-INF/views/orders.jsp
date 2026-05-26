<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.fashionstore.model.OrderItem" %>
<%@ page import="com.fashionstore.model.Order" %>

<html>
<head>
<meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h2>My Orders</h2>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");

    if (orders != null && !orders.isEmpty()) {

        for (Order o : orders) {
%>

    <div style="border:1px solid #ccc; margin:10px; padding:10px;">
        <p>Order ID: <%= o.getOrderId() %></p>
        <p>Total: ₹ <%= o.getTotalAmount() %></p>
        <p>Status: <%= o.getStatus() %></p>
        <p>Date: <%= o.getOrderDate() %></p>
        <a href="<%= request.getContextPath() %>/order?action=details&orderId=<%= o.getOrderId() %>">
    View Items
</a>
    </div>

<%
        }
    } else {
%>

    <p>No orders found</p>

<%
    }
%>

</body>
</html>