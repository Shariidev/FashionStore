<%@ page contentType="text/html; charset=UTF-8" %>

<%
String ctx = request.getContextPath();
%>

<html>
<head>
<title>Order Placed</title>
<link rel="stylesheet" href="<%=ctx%>/assets/css/products.css">
</head>

<body>

<div class="page-shell" style="text-align:center; padding:60px;">

    <h1>🎉 Order Placed Successfully!</h1>
    <p>Your order has been placed.</p>

    <div style="margin-top:30px;">
        <a href="<%=ctx%>/products" class="view-btn">
            Continue Shopping
        </a>
    </div>

</div>

</body>
</html>