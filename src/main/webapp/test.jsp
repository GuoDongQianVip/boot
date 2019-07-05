<%--  Created by IntelliJ IDEA.  
User: Lenovo  
Date: 2019/7/2  
Time: 8:25 
To change this template use File | Settings | File Templates.--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <link rel="stylesheet" href="/webjars/layui/2.4.5/css/layui.css">
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/layui/2.4.5/layui.js"></script>
    </head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <font color="#a52a2a"><h5 align="right">当前用户:<shiro:principal/></h5></font>
       <h5 align="right"><a href="/user/logout">登出</a></h5>
        <div class="layui-logo">用户权限test</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <shiro:hasRole name="user">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="side-nav-class"
                        data-title="用户列表" data-id="yhgl" data-url="http://localhost:8083/user/tolistpage">用户列表</a></dd>
                    </dl>
                </li>
                </shiro:hasRole>
                <shiro:hasRole name="vip">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">角色</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="side-nav-class"
                               data-title="角色列表" data-id="jsgl" data-url="http://localhost:8083/role/tolistpage">角色列表</a></dd>
                    </dl>
                </li>
                </shiro:hasRole>
                <shiro:hasRole name="supervip">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">权限</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="side-nav-class"
                               data-title="权限列表" data-id="qxgl" data-url="http://localhost:8083/permission/tolistpage">权限列表</a></dd>
                    </dl>
                </li>
                </shiro:hasRole>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <%-- 选项卡主体  --%>
        <div class="layui-tab" lay-filter="myTabs" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="11">欢迎页面</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        var $ = layui.jquery;
        //触发事件
        var active = {
            tabAdd: function(title,url,id){
                //新增一个Tab项
                element.tabAdd('myTabs', {
                    title: title //用于演示
                    , content: '<iframe style="width:100%;height:100%;position:relative;" src="'+url+'" frameborder="0" scrolling="true" ></iframe>'
                    ,id:id
                })
            },tabChange: function(id){
                //切换到指定Tab项
                element.tabChange('myTabs', id); //切换到：用户管理
            }
        };
        $(".side-nav-class").on("click",function () {
            var data_id = $(this).attr("data-id");
            var data_url = $(this).attr("data-url");
            var data_title = $(this).attr("data-title");
            if($(".layui-tab-title li[lay-id]").length <= 0){
                //增加选择卡
                //新增一个Tab项
                //tabAdd("容器的 lay-filter ")
                //新增一个Tab项
                active.tabAdd(data_title,data_url,data_id);
            }else{
                //判断是否重复
                var flag = false;//没有重复
                $(".layui-tab-title li[lay-id]").each(function(json){
                    if($(this).attr("lay-id") == data_id){
                        flag = true;
                    }
                })
                if(flag == false){
                    active.tabAdd(data_title,data_url,data_id);
                }
            }
            //切换到指定Tab项
            active.tabChange(data_id);
        })
    });
</script>
</body>
</html>