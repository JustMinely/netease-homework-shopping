<%--
  Created by IntelliJ IDEA.
  User: qudi
  Date: 2018/2/28
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
    <div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
    <div class="n-head">
        <div class="g-doc f-cb">
            <div class="user">
                买家你好，<span class="name">mmmmm</span>！<a href="">[退出]</a>
                请<a href="./login.jsp">[登录]</a>
            </div>
            <ul class="nav">
                <li><a href="./index.jsp">首页</a></li>
                <li><a href="./account.jsp">账务</a></li>
                <li><a href="./public.jsp">发布</a></li>
            </ul>
        </div>
    </div>
    <div class="g-doc">
        <div class="m-tab m-tab-fw m-tab-simple f-cb">
            <div class="tab">
                <ul>
                    <li class="z-sel"><a href="./index.jsp">所有内容</a></li>
                    <li><a href="./index.jsp">未购买的内容</a></li>
                </ul>
            </div>
        </div>
        <div class="n-plist">
            <ul class="f-cb" id="plist">
                <li id="p-1">
                    <a href="./show.jsp" class="link">
                        <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                        <h3>内容</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                        <span class="had"><b>已售出</b></span>
                    </a>
                </li>
                <li id="p-2">
                    <a href="./show.jsp" class="link">
                        <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                        <h3>内容</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                        <span class="had"><b>已购买</b></span>
                    </a>
                </li>
                <li id="p-3">
                    <a href="./show.jsp" class="link">
                        <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                        <h3>内容</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    </a>
                    <span class="u-btn u-btn-normal u-btn-xs del" data-del="3">删除</span>
                </li>
                <li id="p-4">
                    <a href="./show.jsp" class="link">
                        <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                        <h3>内容</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    </a>
                    <span class="u-btn u-btn-normal u-btn-xs del" data-del="4">删除</span>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>
