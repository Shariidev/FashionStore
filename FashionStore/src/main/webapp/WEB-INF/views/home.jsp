<%@ page contentType="text/html;charset=UTF-8" %>
<%
String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fashion Store</title>

<link rel="stylesheet" href="<%= ctx %>/assets/css/home.css">
</head>

<body>

<div class="page-shell">

    <!-- NAVBAR -->
    <header class="navbar">

        <div class="brand">
            <div class="brand-mark">FS</div>

            <div>
                <h2>FashionStore</h2>
                <p>Bold style, modern mood</p>
            </div>
        </div>

        <nav class="nav-links">
            <a href="<%= ctx %>/home" class="active">Home</a>
            <a href="<%= ctx %>/products">Products</a>
            <a href="<%= ctx %>/products?category=1">Men</a>
            <a href="<%= ctx %>/products?category=2">Women</a>
            <a href="<%= ctx %>/products?category=3">Kids</a>
        </nav>

        <div class="nav-actions">
            <a href="<%= ctx %>/products" class="icon-btn">⌕</a>
            <a href="<%= ctx %>/cart" class="icon-btn">🛒</a>
            <a href="<%= ctx %>/orders">Orders</a>
            <a href="<%= ctx %>/login" class="action-btn">Login</a>
        </div>

    </header>

    <!-- HERO -->
    <section class="hero">

        <!-- LEFT -->
        <div class="hero-left">

            <div class="eyebrow">
                NEW COLLECTION 2026
            </div>

            <h1>
                STYLE<br>
                THAT FEELS<br>
                EDITORIAL
            </h1>

            <p>
                FashionStore brings a clean premium shopping experience
                with curated collections, modern visuals,
                and luxury inspired layouts.
            </p>

            <div class="hero-buttons">
                <a href="<%= ctx %>/products" class="btn-primary">
                    Shop Now
                </a>

                <a href="#categories" class="btn-secondary">
                    Browse Categories
                </a>
            </div>

            <div class="hero-stats">

                <div>
                    <strong>5+</strong>
                    <span>Categories</span>
                </div>

                <div>
                    <strong>15+</strong>
                    <span>Products</span>
                </div>

                <div>
                    <strong>Modern</strong>
                    <span>UI Design</span>
                </div>

            </div>

        </div>

        <!-- RIGHT -->
<div class="hero-right">

    <!-- MAIN -->
    <div class="hero-panel hero-panel-main">

        <img src="<%=ctx%>/assets/images/fashion-main.jpg"
             class="hero-bg">

        <div class="hero-overlay">

            <div class="hero-tag">
                TRENDING NOW
            </div>

            <div class="hero-fashion-word">
                FASHION
            </div>

            <div class="hero-model-card">

                <div class="mini-card card-one">
                    <span>Women</span>
                    <strong>Red Edit</strong>
                </div>

                <div class="mini-card card-two">
                    <span>Men</span>
                    <strong>Street Wear</strong>
                </div>

                <div class="mini-card card-three">
                    <span>Accessories</span>
                    <strong>Style Finish</strong>
                </div>

            </div>

        </div>

    </div>

    <!-- SIDE -->
    <div class="hero-panel hero-panel-side">

        <div class="side-image side-image-top">
            <img src="<%=ctx%>/assets/images/women-banner.jpg">
            <span>Women</span>
        </div>

        <div class="side-image side-image-middle">
            <img src="<%=ctx%>/assets/images/men-banner.jpg">
            <span>Men</span>
        </div>

        <div class="side-image side-image-bottom">
            <img src="<%=ctx%>/assets/images/kids-banner.jpg">
            <span>Kids</span>
        </div>

    </div>

</div>
    </section>

    <!-- CATEGORIES -->
    <section class="section-block" id="categories">

        <div class="section-head">

            <div>
                <h2>Browse Categories</h2>
                <p>Move through the store by style and collection.</p>
            </div>

            <a href="<%= ctx %>/products" class="section-link">
                View all products
            </a>

        </div>

        <div class="category-grid">

            <a href="<%= ctx %>/products?category=1"
               class="category-card">

                <img src="<%=ctx%>/assets/images/men-banner.jpg"
                     class="category-image">

                <div class="category-overlay">
                    <span>01</span>
                    <h3>Men</h3>
                    <p>T-shirts, jeans, and smart essentials.</p>
                </div>

            </a>

            <a href="<%= ctx %>/products?category=2"
               class="category-card">

                <img src="<%=ctx%>/assets/images/women-banner.jpg"
                     class="category-image">

                <div class="category-overlay">
                    <span>02</span>
                    <h3>Women</h3>
                    <p>Dresses, tops, and elevated fashion.</p>
                </div>

            </a>

            <a href="<%= ctx %>/products?category=3"
               class="category-card">

                <img src="<%=ctx%>/assets/images/kids-banner.jpg"
                     class="category-image">

                <div class="category-overlay">
                    <span>03</span>
                    <h3>Kids</h3>
                    <p>Comfort wear made for everyday ease.</p>
                </div>

            </a>

            <a href="<%= ctx %>/products?category=4"
               class="category-card">

                <img src="<%=ctx%>/assets/images/footwear-banner.jpg"
                     class="category-image">

                <div class="category-overlay">
                    <span>04</span>
                    <h3>Footwear</h3>
                    <p>Sneakers, heels, and running shoes.</p>
                </div>

            </a>

            <a href="<%= ctx %>/products?category=5"
               class="category-card">

                <img src="<%=ctx%>/assets/images/accessories-banner.jpg"
                     class="category-image">

                <div class="category-overlay">
                    <span>05</span>
                    <h3>Accessories</h3>
                    <p>Bags, belts, caps, wallets, and more.</p>
                </div>

            </a>

        </div>

    </section>

    <!-- PROMO -->
    <section class="split-promo">

        <!-- LEFT -->
        <div class="promo-text">

            <img src="<%=ctx%>/assets/images/style-edit.jpg"
                 class="promo-image">

            <div class="promo-overlay">

                <div class="eyebrow light">
                    STYLE EDIT
                </div>

                <h2>
                    Styles come and go,<br>
                    but fashion evolves.
                </h2>

                <p>
                    Create a shopping journey with strong contrast,
                    premium spacing, and bold visual rhythm.
                </p>

            </div>

        </div>

        <!-- RIGHT -->
        <div class="promo-card">

            <img src="<%=ctx%>/assets/images/new-season.jpg"
                 class="promo-image">

            <div class="promo-card-inner promo-overlay">

                <div class="promo-label">
                    NEW IN
                </div>

                <h3>
                    Fresh drops for every season
                </h3>

                <p>
                    Designed for a sleek fashion store experience.
                </p>

                <a href="<%= ctx %>/products"
                   class="btn-primary dark-btn">

                    Explore Collection

                </a>

            </div>

        </div>

    </section>

   

</div>

</body>
</html>