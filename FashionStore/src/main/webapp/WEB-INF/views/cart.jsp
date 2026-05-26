<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
String ctx = request.getContextPath();
List<Map<String, Object>> cart =
    (List<Map<String, Object>>) session.getAttribute("cart");

double total = 0;
%>

<html>
<head>
<title>Cart</title>
<link rel="stylesheet" href="<%=ctx%>/assets/css/products.css">
</head>

<body>

<div class="page-shell">

    <h1>Your Cart</h1>

<%
if (cart == null || cart.isEmpty()) {
%>
    <p>Cart is empty</p>

    <div style="margin-top:20px;">
        <a href="<%=ctx%>/products" class="view-btn">
            ← Continue Shopping
        </a>
    </div>

<%
} else {
    for (Map<String, Object> item : cart) {

        double price = Double.parseDouble(item.get("price").toString());
        int qty = (int) item.get("quantity");
        total += price * qty;
%>

    <div class="product-card" style="display:flex; gap:20px; margin-bottom:20px; align-items:center;">

        <!-- IMAGE -->
        <div class="product-image" style="width:120px;">
            <img src="<%=ctx%>/assets/images/<%= item.get("image") %>">
        </div>

        <!-- INFO -->
        <div class="product-info" style="flex:1;">

            <h3><%= item.get("name") %></h3>
            <p>Size: <%= item.get("size") %></p>

            <p>Qty: <%= qty %></p>

            <strong>₹ <%= price %></strong>

            <!-- ACTION BUTTONS -->
            <div style="margin-top:10px; display:flex; gap:8px;">

                <!-- INCREASE -->
                <form action="<%=ctx%>/update-cart" method="post">
                    <input type="hidden" name="productId" value="<%= item.get("productId") %>">
                    <input type="hidden" name="size" value="<%= item.get("size") %>">
                    <input type="hidden" name="action" value="increase">
                    <button type="submit">+</button>
                </form>

                <!-- DECREASE -->
                <form action="<%=ctx%>/update-cart" method="post">
                    <input type="hidden" name="productId" value="<%= item.get("productId") %>">
                    <input type="hidden" name="size" value="<%= item.get("size") %>">
                    <input type="hidden" name="action" value="decrease">
                    <button type="submit">-</button>
                </form>

                <!-- REMOVE -->
                <form action="<%=ctx%>/update-cart" method="post">
                    <input type="hidden" name="productId" value="<%= item.get("productId") %>">
                    <input type="hidden" name="size" value="<%= item.get("size") %>">
                    <input type="hidden" name="action" value="remove">
                    <button type="submit">Remove</button>
                </form>

            </div>

        </div>

    </div>

<%
    }
%>

    <!-- TOTAL -->
    <h2>Total: ₹ <%= total %></h2>

    <!-- ACTION BUTTONS -->
    <div style="margin-top:20px; display:flex; gap:15px; align-items:center;">

        <!-- CONTINUE SHOPPING -->
        <a href="<%=ctx%>/products" class="view-btn">
            ← Continue Shopping
        </a>

        <!-- CHECKOUT BUTTON -->
        <form action="<%=ctx%>/checkout" method="post">
            <button type="submit" class="view-btn">
                Checkout →
            </button>
        </form>

    </div>

<%
}
%>

</div>

</body>
</html>