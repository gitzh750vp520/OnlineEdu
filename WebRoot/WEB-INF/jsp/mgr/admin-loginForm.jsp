<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--后台管理员登录</title>
    <link rel="stylesheet" href="/oe/css/mgr-css1.css" type="text/css">
</head>
<body>
<div class="content">
    <div class="header">
        <h2>科多在线教育--后台管理员系统</h2>
    </div>
    <div class="container">
        <h4>管理员登录界面</h4>
        <form action="login" method="post">
            <table>
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="loginId" /></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input type="password" name="loginPsw" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="登录" /></td>
                </tr>
            </table>
        </form>
        <p>${loginMsg}</p>
    </div>
    <div class="footer">
        <p>科多在线教育--科多出品</p>
    </div>
</div>
</body>
</html>