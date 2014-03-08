<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>used car - login</title>
    <link href="/css/reset.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="/css/login.css" type="text/css"/>
</head>
<body>
<div class="login">
    <form action="/login" method="post">
        <label for="username">用户名</label>
        <input type="text" id="username" name="username" placeholder="请输入手机号/注册邮箱"/>
        <label for="password">密码</label>
        <input type="password" id="password" name="password"/>
        <div>
            <input type="radio" name="remember" id="remember"/>
            <label for="remember">记住用户名和密码</label>
            <button type="submit" value="登陆">登陆</button>
        </div>
    </form>
    <p>
        <a href="/register">注册</a>|
        <a href="#">忘记用户名或密码</a>
    </p>
</div>
</body>
</html>
