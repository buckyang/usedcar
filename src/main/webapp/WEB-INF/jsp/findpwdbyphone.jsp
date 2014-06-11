<head>
    <title><c:out value="${siteConfiguration.siteName }"/> -- 用户登录成功</title>
</head>
<body>
<div class="box2 gray-background phone">
    <h3 class="green-gradient box-title">XXXX密码找回！</h3>

    <form action="findpassword" id="findPwd" class="white-background phone">
        <div class="choices">
            <a href="#" class="orange-gradient link-button">手机找回</a>
            <a href="#" class="gray-gradient link-button mute">邮箱找回</a>
        </div>
        <div class="phone-number">
            <label for="phoneNumber" class="span4">注册手机号码：</label>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="请输入正确的手机号码" class="span6 error-input"/>
            <a href="#" class="gray-gradient link-button get-code">免费获取验证码</a>
            <span class="code-message">60秒后重新获取</span>
            <span class="error offset4">请输入正确手机号</span>
        </div>

        <div class="phone-code">
            <label for="code" class="span4">手机验证码：</label>
            <input type="text" id="code" name="code" class="span6 error-input" placeholder="手机短信验证码"/>
            <span class="error offset4">请输入正确验证码</span>
        </div>

        <div class="new-password">
            <label for="newPassword" class="span4">输入新密码：</label>
            <input type="text" id="newPassword" name="code" class="span6 error-input" placeholder="两次密码保持一致"/>
        </div>

        <div class="confirm-password">
            <label for="confirmPassword" class="span4">确认密码：</label>
            <input type="text" id="confirmPassword" name="code" class="span6 error-input"/>
            <span class="error offset4">两次密码不一致</span>
        </div>

        <button class="orange-gradient">确认</button>
    </form>
</div>
</body>
