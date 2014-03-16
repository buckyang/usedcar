<head>
    <title><c:out value="${siteConfiguration.siteName }" /> -- 用户登录</title>
</head>
<body>
<div class="login">
	<form:form id="loginForm" method="post" modelAttribute="loginDTO">
		<label for="username">用户名</label>
		<form:input path="phoneOrEmail"  placeholder="请输入手机号/注册邮箱"/>
		<form:errors path="phoneOrEmail" cssClass="error" />
		<label for="password">密码</label>
		<form:password path="password"/>
		<form:errors path="password" cssClass="error" />
        <div>
        	<form:checkbox path="rememberUserName" value="true"/>
            <label for="rememberUserName">记住用户名</label>
            <button type="submit" value="登陆">登陆</button>
        </div>
	</form:form>
    <p>
        <a href="<s:url value="/signon.html" />">注册</a>|
        <a href="#">忘记用户名或密码</a>
    </p>
</div>
</body>
