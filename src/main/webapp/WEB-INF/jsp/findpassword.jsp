<form action="/findpassword" method="post">
    <label>XXXX网密码找回：</label>
    <div class="telephone">
        <label for="telephone"><span>*</span>注册手机号码：</label>
        <input type="text" id="telephone" name="telephone"/>
        <button>免费获取验证码</button>
    </div>
    <div class="mobile-code">
        <label for="mobileCode"><span>*</span>手机验证码：</label>
        <input type="text" id="mobileCode" name="mobileCode"/>
    </div>
    <div class="password">
        <label for="password"><span>*</span>输入新密码：</label>
        <input type="text" id="password" name="password"/>
    </div>
    <div class="confirm-pwd">
        <label for="confirmPwd"><span>*</span>确认新密码：</label>
        <input type="password" id="confirmPwd" name="confirmPwd"/>
    </div>
    <button type="submit" value="confirm">确认</button>
</form>