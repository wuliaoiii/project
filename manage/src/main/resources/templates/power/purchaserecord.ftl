<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>采购记录管理表格</title>

    <!-- jqGrid组件基础样式包-必要 -->
    <link href="${basePath!}/static/jqgrid/css/ui.jqgrid.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/jqgrid/css/jquery-ui.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/css/global.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/plugins/font-awesome/css/font-awesome.min.css" type="text/css" media="screen"
          rel="stylesheet"/>
    <link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>

    <!-- jquery插件包-必要 -->
    <!-- 这个是所有jquery插件的基础，首先第一个引入 -->
    <script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
    <!-- jqGrid插件的多语言包-非必要 -->
    <!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
    <script src="${basePath!}/static/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jqgrid/js/jquery-ui.js" type="text/javascript"></script>
    <script src="${basePath!}/static/layui/layui.js" type="text/javascript"></script>


    <base href="${basePath!}/">

    <script type="text/javascript">

        var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的

        $(function () {

            //页面加载完成之后执行
            pageInit();

            layui.use(['layer', 'form', 'layedit', 'laydate'], function () {
                var layer = layui.layer,
                        layedit = layui.layedit,
                        laydate = layui.laydate,
                        $ = layui.$,
                        form = layui.form;

                //创建一个编辑器
                var editIndex = layedit.build('LAY_demo_editor');
                //自定义验证规则
                form.verify({
                    userName: function (value) {
                        if (value.length <= 0) {
                            return '商品名称不能为空';
                        }
                    },
                    notNull: function (value) {
                        if (value.length <= 0) {
                            return '该参数不能为空';
                        }
                    },
                    isNum: [/^[0-9]+(.[0-9]{2})?$/, '数字必须大于0且包含两位小数'],
                    pNumber: [/^[1-9]\d*|0$/, '必须为非负整数'],
                    password: [/(.+){6,12}$/, '密码必须6到12位'],
                    content: function (value) {
                        layedit.sync(editIndex);
                    },

                });

                //监听提交
                form.on('submit(addeditsubmitfilter)', function (data) {

                    //为了防止form中的id值被重置后置空,将编辑的id存放在label中
                    /*$("#pid").val($("#editlabelid").html());
                    $("#editlabelid").html("");*/

                    $("#editid").val($("#editlabelid").html());
                    $("#editlabelid").html("");

                    console.log($('#addeditformid').serialize());

                    $.ajax({
                        type: "POST",
                        url: "admin/product/addupdateproduct",
                        data: $('#addeditformid').serialize(),
                        async: false,
                        error: function (request) {
                            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                        },
                        success: function (data) {
                            if (data.state == 'fail') {
                                layer.alert(data.mesg);
                            }
                            if (data.state == 'success') {
                                layer.open({
                                    skin: 'layui-layer-molv',
                                    type: 1,
                                    area: "10%",
                                    content: data.mesg,
                                    shadeClose: true,
                                    end: function () {
                                        layer.close(layerid);
                                        jQuery("#list2").jqGrid().trigger("reloadGrid");//重新加载数据
                                        $("#reset").click();//重置表单
                                    }
                                });

                            }
                        }
                    });


                    return false;//防止表单提交后跳转
                });


                //监听提交
                form.on('submit(editroleformsubmit)', function (data) {
                    //为了防止form中的id值被重置后置空,将编辑的id存放在label中
                    $("#editroleid").val($("#editrolelabelid").html());
                    $("#editrolelabelid").html("");


                    console.log($('#editroleformid').serialize());
                    $.ajax({
                        type: "POST",
                        url: "admin/purchaserecord/settlepurchaserecord",
                        data: $('#editroleformid').serialize(),// 你的formid
                        async: false,
                        error: function (request) {
                            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                        },
                        success: function (data) {
                            if (data.state == 'fail') {
                                layer.alert(data.mesg);
                            }
                            if (data.state == 'success') {
                                layer.open({
                                    skin: 'layui-layer-molv',
                                    type: 1,
                                    area: "10%",
                                    content: data.mesg,
                                    shadeClose: true,
                                    end: function () {
                                        layer.close(layerid);
                                        jQuery("#list2").jqGrid().trigger("reloadGrid");//重新加载数据
                                        $("#reset").click();//重置表单
                                    }
                                });

                            }
                        }
                    });


                    return false;
                });


            });
        });


        function pageInit() {
            //创建jqGrid组件
            jQuery("#list2").jqGrid(
                    {
                        caption: "商品管理",//表格的标题名字
                        mtype: "post",//向后台请求数据的ajax的类型。可选post,get
                        url: 'admin/purchaserecord/list',
                        //url : 'static/jqgrid/data/JSONData.json',//组件创建完成之后请求数据的url
                        //styleUI: 'Bootstrap',
                        datatype: "json",//请求数据返回的类型。可选json,mybatis,txt
                        emptyrecords: "当前无记录",
                        colNames: ['ID', '编号', '商品主键', '供应商主键', '经办人', '采购数量'
                            , '采购价格', '总花费', '创建时间', '结算状态'],//jqGrid的列显示名字
                        colModel: [  //这里会根据index去解析jsonReader中root对象的属性，填充cell
                            {name: 'id', index: 'id', width: 100, sortable: true, search: false},
                            {
                                name: 'orderNum', index: 'orderNum', width: 180, sortable: false, search: true,
                                //被该列搜索时的搜索条件有哪些
                                searchoptions: {sopt: ['eq']}
                                //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下面的配置可以去掉
                                /*
                                editable: true,
                                editoptions: {size: "20", maxlength: "30"}//当执行修改和新增的操作时，会显示输入框，输入框的配置*/
                            },
                            {name: 'productName', index: 'productName', width: 200, sortable: false, search: false},
                            {name: 'providerName', index: 'providerName', width: 180, sortable: false, search: false},
                            {name: 'userName', index: 'userName', width: 180, sortable: false, search: false},
                            {name: 'count', index: 'count', width: 180, sortable: false, search: false},
                            {name: 'price', index: 'price', width: 180, sortable: false, search: false},
                            {name: 'cost', index: 'cost', width: 180, sortable: false, search: false},
                            {
                                name: 'createDate',
                                index: 'createDate',
                                width: 180,
                                sortable: false,
                                search: false,
                                formatter: "date",
                                formatoptions: {srcformat: 'u', newformat: 'Y-m-d H:i:s'}
                            },
                            {name: 'settleStatus', index: 'settleStatus', width: 180, sortable: false, search: false}
                        ],
                        //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下可以去掉
                        //editurl: "admin/user/adduser",
                        //cellsubmit: "clientArray",
                        //cellEdit:true,//启用或者禁用单元格编辑功能
                        jsonReader: {
                            root: "datamap",//数据的根节点
                            page: "currpage",//返回数据的当前页
                            total: "totalpages",//总页数
                            records: "totalrecords",//总记录数
                            repeatitems: false,// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
                            id: "id"//主键字段名称
                        },
                        prmNames: { //如当前查询实体为ware，这些可以在查询对象的superObject中设定
                            page: "page", // 表示请求页码的参数名称
                            rows: "length", // 表示请求行数的参数名称
                            sort: "sidx", // 表示用于排序的列名的参数名称
                            order: "sord", // 表示采用的排序方式的参数名称
                            search: "search", // 表示是否是搜索请求的参数名称(实际上在搜索时会传给后台三个参数：String searchField;//搜索字段String searchString;//搜索值String searchOper;//搜索条件公式)
                            nd: "nd", // 表示已经发送请求的次数的参数名称
                            id: "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
                            oper: "oper", // operation参数名称
                            editoper: "edit", // 当在edit模式中提交数据时，操作的名称
                            addoper: "add", // 当在add模式中提交数据时，操作的名称
                            deloper: "del", // 当在delete模式中提交数据时，操作的名称
                            subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
                            npage: null,
                            totalrows: "totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
                        },
                        rowNum: 10,//一页显示多少条
                        rowList: [10, 20, 30],//可供用户选择一页显示多少条
                        pager: '#pager2',//表格页脚的占位符(一般是div)的id
                        sortname: 'id',//初始化的时候排序的字段
                        sortorder: "asc",//排序方式,可选desc,asc
                        viewrecords: true,//定义是否要显示总记录数
                        hidegrid: false,//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
                        height: "100%",
                        autowidth: true,//如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
                        shrinkToFit: true,
                        rownumbers: true, // 显示行号
                    });
            /*创建jqGrid的操作按钮容器*/
            /*可以控制界面上增删改查的按钮是否显示*/
            jQuery("#list2").jqGrid('navGrid', '#pager2', {
                //设置为false需要自己重新重新该方法
                edit: false,
                add: false,
                del: false,
                search: true,
            });

            /*删除事件*/
            $("#delete").click(function () {
                var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
                if (id) {
                    var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id
                    layer.open({
                        content: '请确定是否真的要删除id为' + ret.id + '的记录?',
                        btn: ['yes', 'no'],//定义两个按钮，是和否
                        yes: function (index, layero) {//点击是时候的回调
                            //do something
                            layer.close(index); //如果设定了yes回调，需进行手工关闭

                            //请求后台，执行删除操作
                            $.ajax({
                                type: "POST",
                                url: "admin/purchaseRecord/deletepurchaseRecord",
                                data: {id: ret.id},
                                async: false,
                                error: function (request) {
                                    layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                                },
                                success: function (data) {
                                    if (data.state == 'fail') {
                                        layer.alert(data.mesg);
                                    }
                                    if (data.state == 'success') {
                                        //打开成功消息提示
                                        layer.open({
                                            skin: 'layui-layer-molv',
                                            type: 1,
                                            area: "10%",
                                            content: data.mesg,
                                            shadeClose: true,
                                            end: function () {
                                                layer.close(layerid);//消息提示结束后回调，关闭上一级新建表单所在弹层
                                                jQuery("#list2").jqGrid().trigger("reloadGrid");//jqgrid数据表重新主动加载数据
                                            }
                                        });

                                    }
                                }
                            });
                        }
                    });
                } else {
                    layer.alert("请选择要删除的记录");
                }
            });

            /*修改事件*/
            $("#edit").click(function () {

                var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
                if (id) {
                    var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id

                    console.log(ret.pid);
                    //请求后台，获取该记录的详细记录，并填充进表单
                    $.ajax({
                        type: "POST",
                        url: "admin/purchaseRecord/selectpurchaseRecordbyid",
                        data: {id: ret.id},
                        async: false,
                        error: function (request) {
                            layer.alert(data.mesg);
                            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                        },
                        success: function (data) {
                            if (data.state == 'fail') {
                                layer.alert(data.mesg);
                                return false;
                            }
                            if (data.state == 'success') {
                                //向表单填充数据
                                $("#editlabelid").html(ret.id);//临时存放id，当提交时再去除赋值给input
                                $("#name").val(data.product.name);
                                $("#lastPrice").val(data.product.lastPrice);
                                $("#nowPrice").val(data.product.nowPrice);
                                $("#leftCount").val(data.product.leftCount);
                                $("#limitCount").val(data.product.limitCount);
                                $("#picUrl").val(data.product.picUrl);
                                $("#barCodeUrl").val(data.product.barCodeUrl);
                                $("#des").val(data.product.des);
                                $("#unit").val(data.product.unit);
                                $("#productionDate").val(timestampToTime(data.product.productionDate));
                                $("#expirationDate").val(timestampToTime(data.product.expirationDate));
                                $("#saleFlag").val(data.product.saleFlag);

                                //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                                layerid = layer.open({
                                    skin: 'layui-layer-molv',
                                    area: '60%',
                                    type: 1,
                                    title: '编辑用户',
                                    content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                                });

                            }
                        }
                    });
                } else {
                    layer.alert("请选择要编辑的记录");
                }
            });

            //编辑用户角色
            $("#editrole").click(function () {

                var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
                if (id) {
                    var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id

                    //获得当前用户已经拥有的角色集合和未拥有的角色集合，并组装表单的复选按钮
                    $.ajax({
                        type: "POST",
                        url: "admin/account/list",
                        data: {id: ret.id},
                        async: false,
                        error: function (request) {
                            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                        },
                        success: function (data) {
                            if (data.state == 'fail') {
                                layer.alert(data.mesg);
                                return false;
                            }
                            if (data.state == 'success') {
                                $("#editrolelabelid").html(ret.id);//临时存放id，当提交时再去除赋值给input
                                var accountList = [];
                                accountList = data.accountList;//该记录已经拥有的记录集合
                                console.log(data.accountList);
                                console.log(data);
                                var strs = "";
                                $.each(accountList, function (n, value) {//n从0开始自增+1；value为每次循环的单个对象
                                    console.log(value);
                                    strs += '<input type="radio" name="accountId" title="' + value.account + '" value="' + value.aid + '" >';
                                });
                                $("#checkboxlistid").empty();//每次填充前都要清空所有按钮，重新填充
                                $("#checkboxlistid").append(strs);

                                layui.form.render(); //更新全部

                                layerid = layer.open({
                                    skin: 'layui-layer-molv',
                                    area: '60%',
                                    type: 1,
                                    title: '选择账户',
                                    content: $('#editroleformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                                });

                            }
                        }
                    });


                } else {
                    layer.alert("请选择要编辑的记录");
                }


            });


            layui.use('laydate', function () {
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#productionDate'
                    , max: 0
                    , value: new Date()
                    , format: "yyyy-MM-dd HH:mm:ss"
                });
            });

            layui.use('laydate', function () {
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#expirationDate' //指定元素
                    , min: 0
                    , value: new Date()
                    , format: "yyyy-MM-dd HH:mm:ss"
                });
            });

            layui.use('upload', function () {
                var upload = layui.upload;

                //执行实例
                var uploadInst = upload.render({
                    elem: '#picUpload' //绑定元素
                    , url: '/fileUpload/fileUpload' //上传接口
                    , done: function (res) {
                        //上传完毕回调
                    }
                    , error: function () {
                        //请求异常回调
                    }
                });
            });

            layui.use('upload', function () {
                var upload = layui.upload;

                //执行实例
                var uploadInst = upload.render({
                    elem: '#barPicUpload' //绑定元素
                    , url: '/fileUpload/fileUpload' //上传接口
                    , done: function (res) {
                        //上传完毕回调
                    }
                    , error: function () {
                        //请求异常回调
                    }
                });
            });

            function timestampToTime(timestamp) {
                var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                Y = date.getFullYear() + '-';
                M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                return Y + M + D + h + m + s;
            }
        }


    </script>


</head>
<body>
<div class="layui-btn-group">
    <button class="layui-btn" id="edit">编辑</button>
    <button class="layui-btn" id="delete">删除</button>
    <button class="layui-btn" id="editrole">结算</button>
</div>

<table id="list2"></table>
<div id="pager2"></div>











<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓add↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="addeditformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden="true"/>
        <div class="layui-form-item">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" lay-verify="userName" autocomplete="off" placeholder="请输入商品名"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-block">
                <input type="number" id="count" name="count" lay-verify="pNumber" autocomplete="off"
                       placeholder="请输入数量" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addeditsubmitfilter">立即提交</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<#--↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑add↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->

<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓为用户设置角色↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->

<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="editroleformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="editroleformid">
        <label hidden="true" id="editrolelabelid"></label>
        <input id="editroleid" name="recordId" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">账户单选框</label>
            <div class="layui-input-block" id="checkboxlistid">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="editroleformsubmit">立即提交</button>
                <button id="editroleformreset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<#--↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑为用户设置角色↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->


</body>
</html>