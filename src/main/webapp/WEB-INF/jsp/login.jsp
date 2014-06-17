<head>
    <title><c:out value="${siteConfiguration.siteName }"/> -- 用户登录</title>
    <link href="<s:url value="/css/login.css" />" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="login-box">
    <h3 class="green-gradient">登陆</h3>
    <form:form id="loginForm" method="post" modelAttribute="loginDTO">
        <span class="error offset4">用户名或密码不对</span>
        <div class="necessary-info">
            <div>
                <img src='<s:url value="/image/yhmbg.jpg" />' alt="username"/>
                <form:input path="phoneOrEmail" placeholder="请输入手机号/注册邮箱"/>
                <form:errors path="phoneOrEmail" cssClass="error"/>
            </div>

            <div>
                <img src='<s:url value="/image/mmbg.jpg" />' alt="password"/>
                <form:password path="password" placeholder="******"/>
                <form:errors path="password" cssClass="error"/>
            </div>
        </div>

        <div class="remember">
            <form:checkbox path="rememberUserName" value="true"/>
            <label for="rememberUserName">记住用户名和密码</label>
            <a href="#">忘记用户名或密码?</a>
        </div>
        <div class="buttons">
            <button class="orange-gradient" type="submit" value="登陆">登陆</button>
            <a class="gray-gradient" href="<s:url value="/signon" />">注册</a>
        </div>
    </form:form>
    <p>
    </p>
</div>
</body>
