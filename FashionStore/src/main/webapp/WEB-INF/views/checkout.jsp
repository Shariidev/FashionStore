<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h2>Checkout</h2>

<form action="order" method="post">

    <input type="hidden" name="action" value="placeOrder">

    Address Line: <input type="text" name="addressLine"><br><br>
    City: <input type="text" name="city"><br><br>
    State: <input type="text" name="state"><br><br>
    Pincode: <input type="text" name="pincode"><br><br>

    <button type="submit">Place Order</button>

</form>

</body>
</html>