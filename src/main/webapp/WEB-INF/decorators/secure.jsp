<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Language" content="en,zh-CN;"/>
    <decorator:head/>
    <title><decorator:title default="${siteConfiguration.siteName }"/></title>
    <link href="<s:url value="/css/reset.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/home.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/common.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/secure.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/sellcar.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/userinfo.css" />" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/sections/header.jsp"/>
    <div class="content">


        <div class="user-info">
            <div class="logo">
                Logo个人中心
            </div><div class="private">
                <div>
                    <p>昵称：大师兄</p>
                    <p>性别：男</p>
                    <p>手机号码：18888888888</p>
                    <p>邮箱：123456@123.com</p>
                </div>
            </div>
        </div>
        <%--<div class="user-nav">--%>
            <%--<ul>--%>
                <%--<li><a href="#">账号管理</a></li>--%>
                <%--<li><a href="/secure/basicinfo">个人信息</a></li>--%>
                <%--<li><a href="/secure/changepassword">密码修改</a></li>--%>
                <%--<li><a href="#">身份认证</a></li>--%>
                <%--<li><a href="#">出售二手车</a></li>--%>
                <%--<li><a href="#">我的收藏</a></li>--%>
                <%--<li><a href="#">购物车</a></li>--%>
                <%--<li><a href="#">买卖记录</a></li>--%>
                <%--<li><a href="#">出售中车辆</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <decorator:body/>

    </div>

    <jsp:include page="/WEB-INF/jsp/sections/footer.jsp"/>
</div>
</body>
</html>
