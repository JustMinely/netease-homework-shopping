<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user>
            <#if user.usertype==1>
                卖家你好，<span class="name">${user.merchantName}</span>！<a href="/customer/logout">[退出]</a>
            <#else>
                买家你好,<span class="name">${user.consumerName}</span>！<a href="/customer/logout">[退出]</a>
            </#if>你好，
        <#else>
            请<a href="/customer/login">[登录]</a>
        </#if>
        </div>
        <ul class="nav">
            <li><a href="/product/getAllProducts">首页</a></li>
            <#if user && user.usertype==0>
            <li><a href="/customer/account">账务</a></li>
            </#if>
            <#if user && user.usertype==1>
            <li><a href="/merchant/publish">发布</a></li>
            </#if>
        </ul>
    </div>
</div>