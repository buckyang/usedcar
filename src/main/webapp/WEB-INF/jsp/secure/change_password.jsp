<div class="basic-info right-content">
    <h3>基本信息</h3>

    <form action="/secure/changepassword" method="post">
        <div class="old-pwd">
            <label for="oldPwd">旧密码：</label>
            <input type="password" name="password" id="oldPwd"/>
        </div>
        <div class="new-pwd">
            <label for="newPwd">新密码：</label>
            <input type="password" name="password" id="newPwd"/>
        </div>
        <div class="confirm-pwd">
            <label for="confirmPwd">确认新密码：</label>
            <input type="password" name="password" id="confirmPwd"/>
        </div>
        <button type="submit">确认修改</button>
    </form>
</div>