<%--  Created by IntelliJ IDEA.  
User: Lenovo  
Date: 2019/7/2  
Time: 9:01 
To change this template use File | Settings | File Templates.--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<body>
<table class="layui-hide" id="test" lay-filter="userTableFilter">
</table>

<div style="display: none;" id="givePermission">
    <form class="layui-form">
        <input type="hidden" name="roleId" id="roleId">
        <div class="layui-form-item">
            <label class="layui-form-label">权限：</label>
            <div class="layui-input-block" id="permissionId">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="givePermissionSubmit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<div style="display: none;" id="giveRole">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">增加角色：</label>
            <div class="layui-input-block" id="addpage">
                <input type="text" name="roleName">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="giveRoleSubmit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['table','layer','form','jquery'], function(){
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var $ =layui.jquery;
       var active = {
            "showPermissionByRoleId":function (roleId) {
                $.get("/role/rolePermissions",{"roleId":roleId},function (data) {
                    var permissions= data.permissions;
                    var permissionIds = data.permissionIds;
                    $("#permissionId").empty();//清除当前元素中的对象
                    for (var i = 0; i <permissions.length ; i++) {
                        var che = '';
                        if(permissionIds.indexOf(permissions[i].permissionId)>-1){
                            che = 'checked';
                        }
                        $("#permissionId").append('<input type="checkbox"'+che+' name="permissions['+i+']" value="'+permissions[i].permissionId+'"  title="'+permissions[i].permissionName+'"><br>');
                    }
                    form.render();//更新全部
                },'json');
            }
        }
        //监听提交
       form.on('submit(givePermissionSubmit)', function(data){
            console.log(data.field);//角色ids

            //ajax赋角色
            $.post("/role/rolePermission",data.field,function(data){
                if(data.success){

               table.reload("test", {page:{curr:1}});//从第一页加载

           }else{
                    layer.msg('失败！');
                }
                layer.closeAll();
                form.render();//更新全部
            },"json")

            return false;//防止表单自动提交
        });
        table.render({
            elem: '#test'
            ,height:'full-60'
            ,url:'/role/list'
            ,page:true
            ,toolbar:'#tableToolBar'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}//复选框
                ,{field:'roleId',title: '角色编号',sort: true, fixed: 'left'}
                ,{field:'roleName',title: '角色名程'}
                ,{field:'permissions',title: '权限名称',
                        templet: function(d){
                               var permissions=d.permissions;
                               var str = "";
                                for (var i = 0; i <permissions.length ; i++) {
                                    str += ','+permissions[i].permissionName;
                                }
                                str=str.substring(1);
                            return str;
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#userToolBar', width:150}
            ]]
        });
        table.on('tool(userTableFilter)',function (obj) {
            var data =obj.data;
            var layEvent = obj.event;
            var tr= obj.tr;
            if (layEvent == 'del'){
                layer.confirm('真的删除吗',function(index) {
                    $.post('/role/delete/'+data.roleId,{'_method':'delete'},function(data) {
                        if (data == 'success'){
                            obj.del();
                        }else{
                            layer.msg('删除失败！');
                        }
                    },'text');
                    layer.close(index);
                })
            }else if(layEvent === 'edit') {
                var roleId = data.roleId;
                $("#roleId").val(roleId);
                active.showPermissionByRoleId(roleId);
                layer.open({
                    type: 1,
                    title: "赋权限",
                    content: $("#givePermission") //这里content是一个普通的String
                    , area: ['500px', '300px']
                    , icon: 0
                });
            }
        })
        //对表格顶部工具栏的监听 toolbar
        table.on('toolbar(userTableFilter)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 1,
                        title:"增加",
                        content: $("#giveRole") //这里content是一个普通的String
                        ,area: ['500px', '300px']
                        ,icon: 0
                    });
                    break;

            };
        });
        form.on('submit(giveRoleSubmit)', function(data){
            console.log(data.field);//角色ids
            //ajax赋角色
            $.post("/role/add",{"roleName":data.field.roleName},function(data){
                if(data.success){
                    table.reload("test", {page:{curr:1}});//从第一页加载
                }else{
                    layer.msg('失败！');
                }
                layer.closeAll();
                form.render();//更新全部
            },"json")

            return false;//防止表单自动提交
        });
        function tabAdd(title,url,id){
            //如果没有选项卡
            if($(".layui-tab-title li[lay-id]").length <= 0){
                //增加选择卡
                //新增一个Tab项
                //tabAdd("容器的 lay-filter ")
                //新增一个Tab项
                active.tabAdd(title,url,id);
            }else{
                //判断是否重复
                var flag = false;//没有重复
                $(".layui-tab-title li[lay-id]").each(function(json){
                    if($(this).attr("lay-id") == id){
                        flag = true;
                    }
                })
                if(flag == false){
                    active.tabAdd(title,url,id);
                }
            }
            //切换到指定Tab项
            active.tabChange(id);
        }

    });
</script>
<script type="text/html" id="userToolBar">
    <shiro:hasRole name="supervip">
    <a class="layui-btn layui-btn-xs" lay-event="edit">赋权限</a>
    </shiro:hasRole>
    <shiro:hasRole name="vip">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasRole>
</script>
<script type="text/html" id="tableToolBar">
    <shiro:hasRole name="user">
    <a class="layui-btn layui-btn-xs" lay-event="add">增加</a>
    </shiro:hasRole>
</script>
</body>
</html>