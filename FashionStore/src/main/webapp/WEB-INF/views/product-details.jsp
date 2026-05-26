<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page import="java.util.List" %>

<%
String ctx = request.getContextPath();

Product p = (Product) request.getAttribute("product");

List<String> sizes =
(List<String>) request.getAttribute("sizes");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title><%= p.getName() %></title>

<link rel="stylesheet"
href="<%=ctx%>/assets/css/products.css">

</head>

<body>

<div class="page-shell">

    <!-- TOPBAR -->
    <div class="topbar">

        <div class="brand">

            <div class="brand-mark">
                FS
            </div>

            <div>
                <h2>FashionStore</h2>
                <p>Premium Collection</p>
            </div>

        </div>

        <div class="top-links">

            <a href="<%=ctx%>/home">Home</a>

            <a href="<%=ctx%>/products"
               class="active">

               Products

            </a>

        </div>

    </div>

    <!-- BACK -->
    <div style="margin:24px 0;">

        <a href="<%=ctx%>/products">

            ← Back to Products

        </a>

    </div>

    <!-- PRODUCT DETAILS -->
    <div class="product-details-layout">

        <!-- IMAGE -->
        <div class="details-image-box">

            <img src="<%=ctx%>/assets/images/<%= p.getImageUrl() %>"
                 alt="<%= p.getName() %>">

        </div>

        <!-- DETAILS -->
        <div class="details-content">

            <span class="product-meta">
                Premium Collection
            </span>

            <h1>
                <%= p.getName() %>
            </h1>

            <p>
                <%= p.getDescription() %>
            </p>

            <div class="details-price">
                ₹ <%= p.getPrice() %>
            </div>

            <!-- SIZES -->
            <div class="size-wrap">

                <h3>Available Sizes</h3>

<%
if(sizes != null && !sizes.isEmpty()){

    for(String s : sizes){
%>

        <span class="size-badge">
            <%= s %>
        </span>

<%
    }

} else {
%>

        <p>No sizes available</p>

<%
}
%>

            </div>

            <!-- FORM -->
            <form action="<%=ctx%>/add-to-cart"
                  method="post"
                  style="margin-top:30px;">

                <input type="hidden"
                       name="productId"
                       value="<%= p.getProductId() %>">

                <h3>Select Size</h3>

                <select name="size" required>

                    <option value="">
                        Choose Size
                    </option>

<%
if(sizes != null){

    for(String s : sizes){
%>

        <option value="<%= s %>">
            <%= s %>
        </option>

<%
    }
}
%>

                </select>

                <br><br>

                <button type="submit"
                        class="view-btn">

                    Add to Cart

                </button>

            </form>

        </div>

    </div>

</div>

</body>
</html>