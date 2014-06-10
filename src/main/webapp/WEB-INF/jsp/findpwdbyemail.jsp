<head>
    <title><c:out value="${siteConfiguration.siteName }"/> -- 用户登录成功</title>
</head>
<body>
<div class="box2 gray-background">
    <h3 class="green-gradient box-title">XXXX密码找回！</h3>

    <form action="findpassword" id="findPwd" class="white-background">
        <div class="choices">
            <a href="#" class="gray-gradient link-button mute">手机找回</a>
            <a href="#" class="orange-gradient link-button">邮箱找回</a>
        </div>
        <div class="email">
            <label for="emailAddress" class="span4">注册邮箱：</label>
            <input type="text" id="emailAddress" name="email" placeholder="请输入正确的邮箱" class="span6"/>
        </div>
        <div class="verification-code">
            <label for="code" class="span4">验证码：</label>
            <input type="text" id="code" name="code" class="span6"/>
            <img src="/image/1.jpg" alt="code" width="112px" height="45px"/>
            <span>看不清楚，换一张</span>
        </div>
        <button class="orange-gradient">确认</button>
    </form>
</div>
</body>
