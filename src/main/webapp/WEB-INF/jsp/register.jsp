<div class="register">
    <div class="category">
        <div class="individual">
            个人注册
        </div>
        <div class="merchant">
            经销商注册
        </div>
    </div>
    <p class="declaration">所有注册信息均按照相关服务条款收到法律保护</p>

    <form action="/register" method="post">
        <div class="telephone">
            <label for="telephone"><span>*</span>注册手机号码：</label>
            <input type="text" id="telephone" name="telephone"/>
            <button>免费获取验证码</button>
        </div>
        <div class="code">
            <label for="code"><span>*</span>手机验证码：</label>
            <input type="text" id="code" name="code"/>
        </div>
        <div class="username">
            <label for="username"><span>*</span>用户名：</label>
            <input id="username" type="text" name="username"/>
        </div>
        <div class="password">
            <label for="password"><span>*</span>密码：</label>
            <input id="password" type="password" name="password"/>
        </div>
        <div class="pwd-confirm">
            <label for="pwdConfirm"><span>*</span>确认密码：</label>
            <input id="pwdConfirm" type="password" name="pwdConfirm"/>
        </div>
        <div class="email">
            <label for="email">邮箱：</label>
            <input id="email" type="email" name="email"/>
        </div>
        <div class="agreement">
            <label for="agreement">
                <input type="radio" id="agreement"/>
                我已阅读并同意《XXXX网服务条款》</label>
        </div>
        <button type="submit">注册</button>
    </form>
</div>