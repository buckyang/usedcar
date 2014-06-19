<head>
    <title>二手车 -- 用户注册</title>
    <link href="<s:url value="/css/register.css" />" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="register">
    <div class="category">
        <div class="individual green-gradient button-border">
            个人注册
        </div>
        <div class="merchant orange-gradient button-border">
            <a href="<s:url value='/resellerSignon'/>">经销商注册</a>
        </div>
    </div>
    <div class="register-info">
        <p class="declaration">*&nbsp;所有注册信息均按照相关服务条款收到法律保护</p>

        <form:form id="signonForm" method="post" modelAttribute="registrationDTO">
            <div class="telephone">
                <label for="phone"><span>*</span>注册手机号码：</label>
                <form:input path="phone"/>
                <a href="#" class="code gray-gradient link-button">免费获取验证码</a>
                <form:errors path="phone" cssClass="error"/>
            </div>
            <div class="code">
                <label for="phoneVerifyCode"><span>*</span>手机验证码：</label>
                <form:input path="phoneVerifyCode"/>
                <form:errors path="phoneVerifyCode" cssClass="error"/>
            </div>
            <div class="password">
                <label for="password"><span>*</span>密码：</label>
                <form:password path="password"/>
                <form:errors path="password" cssClass="error"/>
            </div>
            <div class="pwd-confirm">
                <label for="repassword"><span>*</span>确认密码：</label>
                <form:password path="repassword"/>
                <form:errors path="repassword" cssClass="error"/>
            </div>
            <div class="username">
                <label for="nickname"><span></span>用户名：</label>
                <form:input path="nickname"/>
                <form:errors path="nickname" cssClass="error"/>
            </div>
            <div class="email">
                <label for="email">邮箱：</label>
                <form:input path="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <div class="agreement">
                <label for="acceptTerm">
                <form:checkbox path="acceptTerm" value="true"/>
                我已阅读并同意 <a href="#">《XXXX网服务条款》</a></label>
                <form:errors path="acceptTerm" cssClass="error"/>
            </div>
            <form:hidden path="accountType" value="1"/>
            <button type="submit" class="orange-gradient">注册</button>
        </form:form>
    </div>
</div>
</body>