<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>

<%
String ctx = request.getContextPath();
String userName = (String) session.getAttribute("userName");
List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>

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
                <p>Premium Collection</p>
            </div>
        </div>

        <div class="top-links">
            <a href="<%=ctx%>/home">Home</a>
            <a href="<%=ctx%>/products" class="active">Products</a>
            <a href="<%=ctx%>/orders">Orders</a>

            <% if(userName != null){ %>
                <span style="font-weight:600;">
                    Hello, <%= userName %>
                </span>

                <a href="<%=ctx%>/logout">Logout</a>

            <% } else { %>

                <a href="<%=ctx%>/login">Login</a>

            <% } %>
        </div>

    </div>

    <!-- HEADING -->
    <div class="grid-head" style="margin-top:30px;">

        <div>
            <h2>Explore Fashion</h2>

            <p style="color:#666; margin-top:8px;">
                Discover premium collections for every style.
            </p>
        </div>

    </div>

    <!-- PRODUCT GRID -->
    <div class="product-grid">

<%
if(products != null && !products.isEmpty()){

    for(Product p : products){
%>

        <a href="<%=ctx%>/product?id=<%= p.getProductId() %>"
           class="product-card">

            <!-- IMAGE -->
            <div class="product-image">

                <img src="<%=ctx%>/assets/images/<%= p.getImageUrl() %>"
                     alt="<%= p.getName() %>">

            </div>

            <!-- INFO -->
            <div class="product-info">

                <span class="product-meta">
                    Premium Fashion
                </span>

                <h3>
                    <%= p.getName() %>
                </h3>

                <p>
                    <%= p.getDescription() %>
                </p>

                <div class="product-footer">

                    <span class="price">
                        ₹ <%= p.getPrice() %>
                    </span>

                    <span class="view-btn">
                        View
                    </span>

                </div>

            </div>

        </a>

<%
    }

} else {
%>

    <div class="empty-state">

        <h3>No Products Found</h3>

        <p>
            Products will appear here once added.
        </p>

    </div>

<%
}
%>

    </div>

</div>

</body>
</html>