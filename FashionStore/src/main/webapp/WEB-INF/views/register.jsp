<%@ page contentType="text/html; charset=UTF-8" %>

<%
String ctx = request.getContextPath();
String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | FashionStore</title>

<link rel="stylesheet" href="<%=ctx%>/assets/css/register.css">

</head>

<body>

<div class="register-shell">

    <!-- LEFT -->
    <div class="register-visual">

        <div class="brand-badge">FS</div>

        <h1>Create Your Account</h1>

        <p>
            Join FashionStore and discover premium fashion,
            faster checkout, and seamless shopping.
        </p>

        <div class="feature-list">
            <div>Premium collections</div>
            <div>Exclusive fashion trends</div>
            <div>Fast order tracking</div>
        </div>

    </div>

    <!-- RIGHT -->
    <div class="register-card">

        <div class="register-header">
            <h2>Register</h2>
            <p>Create your FashionStore account</p>
        </div>

        <% if(error != null){ %>
            <div class="error-box">
                <%= error %>
            </div>
        <% } %>

        <form action="<%=ctx%>/register" method="post" class="register-form">

            <input type="text" name="name" placeholder="Full Name" required>

            <input type="email" name="email" placeholder="Email Address" required>

            <input type="text" name="phone" placeholder="Phone Number" required>

            <input type="password" name="password" placeholder="Password" required>

            <input type="text" name="address" placeholder="Address" required>

            <input type="text" name="city" placeholder="City" required>

            <input type="text" name="state" placeholder="State" required>

            <input type="text" name="pincode" placeholder="Pincode" required>

            <button type="submit" class="register-btn">
                Create Account
            </button>

        </form>

        <p class="bottom-text">
            Already have an account?
            <a href="<%=ctx%>/login">Login</a>
        </p>

    </div>

</div>

</body>
</html>