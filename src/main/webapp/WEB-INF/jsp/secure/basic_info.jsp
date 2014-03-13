<div class="basic-info right-content">
    <h3>基本信息</h3>

    <form action="/secure/basicinfo" method="post">
        <div class="bind-mobile">
            <label>绑定手机：</label>
            <span>185*****707</span>
            <a href="#">修改绑定</a>
        </div>
        <div class="bind-email">
            <label>绑定邮箱：</label>
            <span>kuan***@126.com (未绑定)</span>
            <a href="#">立即绑定</a>
        </div>
        <div class="nickname">
            <label for="nickName">昵称：</label>
            <input type="text" name="nickName" id="nickName"/>
        </div>
        <div class="location">
            <label>所在地：</label>
            <select name="provience" id="provience">
                <option value="">请选择省份</option>
            </select>
            <select name="city" id="city">
                <option value="">请选择城市</option>
            </select>
        </div>
        <div class="gender">
            <label>性别：</label>
            <input type="radio" name="gender" value="male"/>男
            <input type="radio" name="gender" value="female"/>女
        </div>
        <button type="submit">保存</button>
    </form>
</div>