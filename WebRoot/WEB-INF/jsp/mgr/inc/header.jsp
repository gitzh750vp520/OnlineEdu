<%@ page language="java" pageEncoding="UTF-8"%>
<div class="header">
        <h2>科多在线教育--后台管理员系统</h2>
        <div id="header-welcome">
            管理员 <span>${currAdmin.name} </span> 欢迎你 <a href="/oe/mgr/loginOut">退出</a>
        </div>
        <div id="header-navigation">
            <ul>
                <li><a href="/oe/mgr/home">首页</a></li>
                <li><a href="/oe/mgr/userMgr">用户管理</a></li>
                <li><a href="/oe/mgr/categoryMgr">分类管理</a></li>
                <li><a href="/oe/mgr/courseMgr">课程管理</a></li>
                <li><a href="/oe/mgr/noteMgr">笔记管理</a></li>
                <li><a href="/oe/mgr/checkTeacher">审核老师</a></li>
                <li><a href="/oe/mgr/checkCourse">审核课程</a></li>
            </ul>
        </div>
    </div>
<div class="clear"></div>