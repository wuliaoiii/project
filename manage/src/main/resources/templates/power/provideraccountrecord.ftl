<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品管理表格</title>

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
            });
        });


        function pageInit() {
            //创建jqGrid组件
            jQuery("#list2").jqGrid(
                    {
                        caption: "供应商账户使用记录管理",//表格的标题名字
                        mtype: "post",//向后台请求数据的ajax的类型。可选post,get
                        url: 'admin/provideraccountrecord/list',
                        //url : 'static/jqgrid/data/JSONData.json',//组件创建完成之后请求数据的url
                        //styleUI: 'Bootstrap',
                        datatype: "json",//请求数据返回的类型。可选json,mybatis,txt
                        emptyrecords: "当前无记录",
                        colNames: ['ID', '使用人', '订单编号', '交易方式', '交易金额'
                            , '创建时间', '来源账户', '来源账户类型', '目标账户', '目标账户类型'],//jqGrid的列显示名字
                        colModel: [  //这里会根据index去解析jsonReader中root对象的属性，填充cell
                            {name: 'id', index: 'id', width: 100, sortable: true, search: false},
                            {
                                name: 'userName', index: 'userName', width: 180, sortable: false, search: true,
                                //被该列搜索时的搜索条件有哪些
                                searchoptions: {sopt: ['eq']}
                                //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下面的配置可以去掉
                                /*
                                editable: true,
                                editoptions: {size: "20", maxlength: "30"}//当执行修改和新增的操作时，会显示输入框，输入框的配置*/
                            },
                            /*{name: 'aid', index: 'aid', width: 200, sortable: false, search: false},*/
                            {name: 'orderNum', index: 'orderNum', width: 180, sortable: false, search: false},
                            {name: 'type', index: 'type', width: 180, sortable: false, search: false},
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
                            {name: 'fromAccount', index: 'fromAccount', width: 180, sortable: false, search: false},
                            {
                                name: 'fromAccountType',
                                index: 'fromAccountType',
                                width: 180,
                                sortable: false,
                                search: false
                            },
                            {name: 'targetAccount', index: 'targetAccount', width: 180, sortable: false, search: false},
                            {
                                name: 'targetAccountType',
                                index: 'targetAccountType',
                                width: 180,
                                sortable: false,
                                search: false
                            }
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
</div>

<table id="list2"></table>
<div id="pager2"></div>

</body>
</html>