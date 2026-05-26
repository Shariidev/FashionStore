<%@ page contentType="text/html; charset=UTF-8" %>
<%
String ctx = request.getContextPath();
String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | FashionStore</title>
    <link rel="stylesheet" href="<%=ctx%>/assets/css/login.css">
</head>
<body>

<div class="login-shell">
    <div class="login-visual">
        <div class="brand-badge">FS</div>
        <h1>Welcome Back</h1>
        <p>Sign in to continue shopping, track your orders, and checkout faster.</p>

        <div class="feature-list">
            <div>Premium fashion collections</div>
            <div>Fast checkout</div>
            <div>Order history and tracking</div>
        </div>
    </div>

    <div class="login-card">
        <div class="login-header">
            <h2>Login</h2>
            <p>Enter your account details below.</p>
        </div>

        <% if (error != null) { %>
            <div class="error-box"><%= error %></div>
        <% } %>

        <form action="<%=ctx%>/login" method="post" class="login-form">
            <label>Email</label>
            <input type="email" name="email" placeholder="you@example.com" required>

            <label>Password</label>
            <input type="password" name="password" placeholder="Your password" required>

            <button type="submit" class="login-btn">Login</button>
        </form>

       <p class="login-note">
    			Don't have an account?
    <a href="<%=ctx%>/register">Register</a>
</p>
    </div>
</div>

</body>
</html>