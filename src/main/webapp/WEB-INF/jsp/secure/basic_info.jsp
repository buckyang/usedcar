<div class="nav-and-info">
    <div class="user-nav">
        <ul>
            <li><a href="#">账号管理</a></li>
            <li><a href="/secure/basicinfo">个人信息</a></li>
            <li><a href="/secure/changepassword">密码修改</a></li>
            <li><a href="#">身份认证</a></li>
            <li><a href="#">出售二手车</a></li>
            <li><a href="#">我的收藏</a></li>
            <li><a href="#">购物车</a></li>
            <li><a href="#">买卖记录</a></li>
            <li><a href="#">出售中车辆</a></li>
        </ul>
    </div><div class="basic-info right-content">
        <h3>个人信息</h3>

        <form action="/secure/basicinfo" method="post">
            <div class="bind-mobile">
                <label>绑定手机：</label>
                <span>185*****707</span>
                <a href="#">修改绑定</a>
            </div>
            <div class="bind-email">
                <label>绑定邮箱：</label>
            <span>kuan***@126.com <span class="unbind">(未绑定)<span></span>
            <a href="#">立即绑定</a>
            </div>
            <div class="nickname">
                <label for="nickName">昵称：</label>
                <input type="text" name="nickName" id="nickName"/>
            </div>
            <div class="gender">
                <label>性别：</label>
                <input type="radio" name="gender" value="male"/>男
                <input type="radio" name="gender" value="female"/>女
            </div>
            <div class="real-name">
                <label for="realName">真实姓名：</label>
                <input type="text" name="realName" id="realName"/>
            </div>
            <div class="date-of-birth">
                <label>出生年月：</label>
                <select name="province" id="province">
                    <option value>请选择年份</option>
                    <option value="1950">1950</option>
                    <option value="1951">1951</option>
                    <option value="1952">1952</option>
                    <option value="1953">1953</option>
                </select>
                <select name="city" id="city">
                    <option value>请选择月份</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
                <select name="prefecture" id="prefecture">
                    <option value>请选择号数</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </div>
            <div class="id">
                <label>证件号码：</label>
                <select name="prefecture" id="one">
                    <option value>证件类型</option>
                    <option value="身份证">身份证</option>
                    <option value="驾照">驾照</option>
                    <option value="四川">重庆</option>
                    <option value="四川">河南</option>
                </select>
                <input type="text"/>
            </div>
            <div class="location">
                <label>所在地：</label>
                <select name="prefecture" id="two">
                    <option value>请选择省份</option>
                    <option value="四川">四川</option>
                    <option value="四川">新疆</option>
                    <option value="四川">重庆</option>
                    <option value="四川">河南</option>
                </select>
                <select name="prefecture" id="three">
                    <option value>请选择城市</option>
                    <option value="四川">四川</option>
                    <option value="四川">新疆</option>
                    <option value="四川">重庆</option>
                    <option value="四川">河南</option>
                </select>

                <div>
                    <input type="text"/>
                </div>
            </div>
            <button type="submit" class="orange-gradient">保存</button>
        </form>
    </div>
</div>