<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/6/20
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/locale/easyui-lang-zh_CN.js"></script>
    <script>
        $(function () {
            $('#dg').datagrid({
                url:'/permission/list',
                fitColumns:true,
                //pagination:true,//分页插件
                /* pageSize:5,
                 pageList:[5,10,15,20,25],*/
                columns:[[
                     {field:'permissionId',title:'权限编号',width:100},
                    {field:'permissionName',title:'权限姓名',width:100},
                ]],toolbar: [{
                    iconCls: 'icon-add',
                    title:'增加',
                    handler: function(){
                        showDialog("addDialog","增加页面");
                    }
                },'-',{
                    iconCls: 'icon-cancel',
                    title:'删除',
                    handler: function(){
                        deletepermission();
                    }
                },'-', {
                    iconCls: 'icon-edit',
                    title: '修改',
                    handler: function () {
                        update();
                    }
                }]
            });

            function addStu() {
                $('#addStu').dialog({
                    title: '增加页面',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    modal: true
                });
            }
        })
        function showDialog(id,tit){
            $('#'+id).dialog({
                title: tit,
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                modal: true
            });
        }
        //删除单个数据
            function deletepermission(){
                // 返回第一个被选中的行或如果没有选中的行则返回null。
                var  permission= $('#dg').datagrid('getSelected');
                if( permission== null){
                    alert("请选择需要删除的数据！");
                    return;
                }
                $.messager.confirm('确认','确定要删除【'+permission.permissionId+'】吗？',function(r){
                    if (r){
                        var id = permission.permissionId;
                        $.ajax({
                            url:"/permission/delete/"+id,
                            type:"delete",
                        data:{
                        "_method":"delete"
                    },success:function(data){
                            if(data == "success"){
                                $.messager.alert('提示','删除成功');
                            }else{
                                $.messager.alert('提示',"删除失败")
                            }
                            //刷新
                            $('#dg').datagrid('reload');
                        }
                    })
            }
        });
        }
        function submitForm() {
            $.messager.progress();	// 显示进度条
            $('#ff').form('submit', {
                url: "/permission/add",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                    }
                    return isValid;	// 返回false终止表单提交
                },
                success: function (data) {
                    if (data == 'success') {
                        //close
                        $('#addDialog').dialog("close");
                        //刷新
                        $('#dg').datagrid('reload');
                        //重置表单
                        $('#ff').form("reset");
                    }
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                }
            });
        }
        function clearForm() {
            $('#ff').form('clear');
        }

        function update(){
            //添加修改弹框
            showDialog("updateDialog","修改页面");
            //数据回显
            var permission = $('#dg').datagrid('getSelected');
            if(permission==null){
                return;
            }
            $('#update').form('load','/permission/selectById/'+permission.permissionId);
        }

        function updateForm() {
            $.messager.progress();	// 显示进度条
            $('#update').form('submit', {
                url: "/permission/update",
                /*
                    onSubmit：在表单提交之前调用的函数
                        return true ：提交表单
                 */
                onSubmit: function () {
                    //验证表达是否通过验证
                    //required:true 必填项
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                    }
                    return isValid;	// 返回false终止表单提交
                },
                success: function (data) {
                    if (data == 'success') {
                        //close
                        $('#updateDialog').dialog("close");
                        //刷新
                        $('#dg').datagrid('reload');
                        //重置表单
                        $('#update').form("reset");
                    }
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                }
            });
        }
        function clearForm() {
            $('#update').form('clear');
        }
    </script>
</head>
<body>
<table id="dg">
</table>
<form action="" method="post">
    <input id="ss" type="hidden"></input>
    <div id="mm" style="width:120px">
        <div data-options="name:'all',iconCls:'icon-ok'"></div>
        <div data-options="name:'sports'"></div>
    </div>
</form>
<div id="addDialog" style="display: none;">
    <form id="ff" method="post">
        <table cellpadding="6">
            <tr>
                <td>权限姓名：</td>
                <td><input class="easyui-textbox" type="text" name="permissionName" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">重置</a>
    </div>
</div>

<div id="updateDialog" style="display: none;">
    <form id="update" method="post">
        <input type="hidden" name="permissionId">
        <input type="hidden" name="_method" value="put">
        <table cellpadding="6">
            <tr>
                <td>权限姓名：</td>
                <td><input class="easyui-textbox" type="text" name="permissionName" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="updateForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">重置</a>
    </div>
</div>
</body>
</html>
