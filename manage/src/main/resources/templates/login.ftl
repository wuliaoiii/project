<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录-后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${basePath!}/static/pic.jpg">
    <link rel="stylesheet" href="${basePath!}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${basePath!}/static/css/public.css" media="all"/>
</head>
<body class="loginBody">
<form class="layui-form">
    <div class="login_face"><img src="${basePath!}/static/pic.jpg" class="userAvatar"></div>
<#--用户名-->
    <div class="layui-form-item input-item">
        <label for="userName">用户名</label>
        <input type="text" placeholder="请输入用户名" autocomplete="off" id="userName" name="username" class="layui-input"
               lay-verify="required|userName">
    </div>
<#--密码-->
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" id="password" name="pwd" class="layui-input"
               lay-verify="required|password">
    </div>
<#--验证码-->
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" id="imageCode" name="imageCode" autocomplete="off" id="code"
               class="layui-input">
    </div>

    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>点击登录</button>
    </div>

    <div class="layui-form-item layui-row">
        <a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
    </div>
</form>
<script type="text/javascript" src="${basePath!}/static/layui/layui.js"></script>
<script type="text/javascript" src="${basePath!}/static/page/login/login.js"></script>
<script type="text/javascript" src="${basePath!}/static/js/cache.js"></script>
<script type="text/javascript" src="${basePath!}/static/js/verifyCode.js"></script>
<script>
    layui.use(['jquery', 'layer', 'form'], function () {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        //监听表单提交事件
        form.on('submit(login)', function (data) {
            $.post("${basePath!}/user/login", {
                username: $("#userName").val(),
                pwd: $("#password").val(),
                verifyCode: $("#imageCode").val()
            }, function (result) {

                if (result.code = 200) {
                    window.location.href = "${basePath!}/welcome";
                } else {
                    layer.alert(result.msg);
                }
            }, "json");
            return false;
        });

    });
</script>

</body>
</html>

<script type="text/javascript">
    /*session过期后登陆页面跳出iframe页面问题
    登陆页面增加javascript*/
    if (top.location !== self.location) {
        top.location = self.location;
    }
</script>