<div class="user-info">
    <div class="logo">
        Logo个人中心
    </div>
    <div class="private">
        昵称、消息等个人相关信息
    </div>
</div>

<div class="user-nav">
    <ul>
        <li><a href="#">账号管理</a></li>
        <li><a href="#">出售二手车</a></li>
        <li><a href="#">身份认证</a></li>
        <li><a href="#">我的收藏</a></li>
        <li><a href="#">我的订单</a></li>
        <li><a href="#">买卖记录</a></li>
    </ul>
</div>

<div class="sell">
    <form action="sellcar" method="post">

        <h3>基本信息</h3>
        <div class="basic-info">
            <div class="location">
                <label><span class="required">*</span>车辆交易地：</label>
                <div>
                    <select name="province" id="province">
                        <option value>请选择省份</option>
                        <option value="四川">四川</option>
                        <option value="四川">新疆</option>
                        <option value="四川">重庆</option>
                        <option value="四川">河南</option>
                    </select>
                    <select name="city" id="city">
                        <option value>请选择城市</option>
                        <option value="四川">四川</option>
                        <option value="四川">新疆</option>
                        <option value="四川">重庆</option>
                        <option value="四川">河南</option>
                    </select>
                    <select name="prefecture" id="prefecture">
                        <option value>请选择县区</option>
                        <option value="四川">四川</option>
                        <option value="四川">新疆</option>
                        <option value="四川">重庆</option>
                        <option value="四川">河南</option>
                    </select>
                    <input type="text" name="street"/>
                </div>
            </div>

            <div class="type">
                <label><span class="required">*</span>车型：</label>
                <select name="carBrand" id="carBrand">
                    <option>请选择品牌</option>
                    <option value="宝马">宝马</option>
                    <option value="奥迪">奥迪</option>
                </select>
                <select name="carSet" id="carSet">
                    <option>请选择车系</option>
                    <option value="宝马">宝马</option>
                    <option value="奥迪">奥迪</option>
                </select>
                <select name="carType" id="carType">
                    <option>请选择车型</option>
                    <option value="大">大</option>
                    <option value="小">小</option>
                </select>
            </div>

            <div class="purchase-date">
                <label><span class="required">*</span>初次购买时间：</label>

                <select name="year" id="year">
                    <option>请选择年份</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                </select>
                <select name="month" id="month">
                    <option>请选择月份</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                </select>
            </div>

            <div class="mile">
                <label><span class="required">*</span>表显里程：</label>
                <input type="text" name="mile"/><span>万公里</span>
            </div>

            <div class="price">
                <label><span class="required">*</span>预售价格：</label>
                <input type="text" name="price"/>万
                <input type="radio" name="negotiable" value="yes"/><span>可议价</span>
                <input type="radio" name="negotiable" value="no"/><span>一口价</span>
            </div>

            <div class="car-identity">
                <label><span class="required">*</span>车辆识别码/VIN：</label>
                <input type="text" name="vin"/>
                <span>由17位数字组成，大多车辆可在行驶证或者仪表板左侧、挡风玻璃下找到</span>
            </div>

        </div>

        <h3>车辆外观</h3>
        <div class="appearance">
            <div class="images">
                <ul>
                    <li>建议上传示例</li>
                    <li><img src="#" alt="左前45度"/></li>
                    <li><img src="#" alt="右后45度"/></li>
                    <li><img src="#" alt="车侧正面"/></li>
                    <li><img src="#" alt="内饰前仪表台"/></li>
                    <li><img src="#" alt="发动机仓"/></li>
                    <li><img src="#" alt="后备箱"/></li>
                </ul>
            </div>
            <p>请点击相应的位置图片按照要求上传至少4张对应的车源图片，推荐上传800<span class="required">*</span>600额图片获得最佳显示，大小不超过2M</p>
        </div>

        <h3>车主信息</h3>
        <div class="owner">
            <div class="contactor">
                <label for="contact"><span class="required">*</span>联系人：</label>
                <input type="text" name="contact" id="contact"/>
            </div>
            <div class="telphone">
                <label for="telephone"><span class="required">*</span>手机：</label>
                <input type="text" name="telephone" id="telephone"/>
            </div>
        </div>
        <label class="agreement"><input type="radio"/>我同意XXXX服务条款</label>
        <button type="submit">发布</button>
    </form>
</div>