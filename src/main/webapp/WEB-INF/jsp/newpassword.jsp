<head>
    <title><c:out value="${siteConfiguration.siteName }"/> -- 用户登录成功</title>
</head>
<body>
<div class="box2 gray-background">
    <h3 class="green-gradient box-title">二搜车网站邮箱找回密码！</h3>

    <form action="newpassword" id="newPassword" class="white-background">
        <div class="new-password">
            <span class="error offset4">输入邮箱地址不存在</span>
            <label for="password" class="span4">输入新密码：</label>
            <input type="password" id="password" name="password" class="span6 error-input"/>
        </div>

        <div class="confirm-password">
            <label for="confirm" class="span4">确认密码：</label>
            <input type="password" id="confirm" name="confirmPassword" class="span6 error-input"/>
        </div>

        <button class="orange-gradient">确认</button>
    </form>
</div>
</body>
