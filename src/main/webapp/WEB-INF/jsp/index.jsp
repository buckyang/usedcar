<div class="cars">
    <form action="search" method="post">
        <div class="inputs">
            <label>我理想的座驾:</label>
            <input type="text" name="brand" id="carBrand" value="品牌" readonly="readonly" style="height:20px;">
            <input type="text" name="type" id="carType" value="车型" readonly="readonly" style="height:20px;">
            <input type="text" name="price" id="carPrice" value="价格" readonly="readonly" style="height:20px;">
            <input type="text" name="age" id="carAge" value="车龄或里程" readonly="readonly" style="height:20px;">

            <input type="radio" name="isCertificated" value="是">
            <span class="certificate">是否认证</span>
            <button type="submit" value="搜索">搜索</button>
        </div>
        <div id="carDetails">
            <ul></ul>
        </div>
    </form>
</div>

<div class="info-wrapper">
    <div class="info">
        <div class="messages">
            <div class="recommends">
                <ul class="title">
                    <li>每日推荐</li>
                    <li class="middle">信息轮播</li>
                </ul>
                <ul class="content">
                    <li>
                        <a href="#">
                            <img src="/image/banner.jpg" alt="car" width="131" height="83"/>
                        </a>

                        <p>上海大众帕萨特</p>

                        <p>历程：2.5万公里</p>

                        <p>购买时间：2013年9月</p>

                        <p>一口价：&#65509<span class="cost">13.98万</span></p>
                    </li>
                    <li>
                        <a href="#">
                            <img src="/image/banner.jpg" alt="car" width="131" height="83"/>
                        </a>

                        <p>上海大众帕萨特</p>

                        <p>历程：2.5万公里</p>

                        <p>购买时间：2013年9月</p>

                        <p>一口价：&#65509<span class="cost">13.98万</span></p>
                    </li>
                    <li>
                        <a href="#">
                            <img src="/image/banner.jpg" alt="car" width="131" height="83"/>
                        </a>

                        <p>上海大众帕萨特</p>

                        <p>历程：2.5万公里</p>

                        <p>购买时间：2013年9月</p>

                        <p>一口价：&#65509<span class="cost">13.98万</span></p>
                    </li>
                    <li>
                        <a href="#">
                            <img src="/image/banner.jpg" alt="car" width="131" height="83"/>
                        </a>

                        <p>上海大众帕萨特</p>

                        <p>历程：2.5万公里</p>

                        <p>购买时间：2013年9月</p>

                        <p>一口价：&#65509<span class="cost">13.98万</span></p>
                    </li>
                </ul>
            </div>
            <div class="contract">
                <ul>
                    <li class="you_ka"><a href="#"></a></li>
                    <li class="di_san_fang"><a href="#"></a></li>
                    <li class="hui_gou"><a href="#"></a></li>
                </ul>
            </div>
            <div class="instructions">
                <div class="buy">
                    <a href="#">买车指南</a>
                </div>
                <div class="sell">
                    <a href="#">买车指南</a>
                </div>
                <div class="loan">
                    <a href="#">贷款&保险</a>
                </div>
                <div class="certificate">
                    <a href="#">认证二手车</a>
                </div>
            </div>
        </div>
        <div class="barcode">
            <h3>扫描二维码下载手机APP</h3>
            <img src="/image/ewm.jpg" alt="barcode" width="140" height="140"/>

            <p class="service">登陆享受更多专属服务<a href="#">了解更多</a></p>

            <div class="login">
                <div class="username">
                    <input type="text" id="username" placeholder="用户名"/>
                </div>
                <div class="password">
                    <input type="password" id="password" placeholder="******"/>
                </div>
            </div>
            <div class="buttons">
                <button class="logon">登陆</button>
                <button class="register">注册</button>
            </div>
        </div>
    </div>
</div>

<script src='<s:url value="/js/index.js" />' type="text/javascript"></script>
