/*
 *公共方法
 *多个js使用的变量声明
 * Document   : myMethod
 * Created on : 2015-5-26, 15:25:11
 * Author     : malinda.Wang
 *
 **/

/*
 * 导航事件处理
 */
var currentPageInfo = "";//当前页面信息属性
var myChart = null;//图表对象
//初始导航事件
(function () {
    //导航事件
    //获得点击的页面信息，判断是否需要切换页面
    var pageInfo = function (o) {
        var name = o.attr("name");
        if (name != undefined) {
            return name;
        }
        return false;
    }
    var path = getRootPath().localhostPaht + getRootPath().projectName;//路径前缀
    var object = getRequest();//获得路径之后的参数
    $("#menu > li >a").click(function () {
        var name = $(this).text();//获得当前点击的内容
        var flag = pageInfo($(this));
        var jump = function (parm) {//是否需要跳转页面的判断，针对table
            if (flag) {
                //获得当前地址栏中名称、
                var pathName = getRootPath().pathName;
                pathName = pathName.substr(pathName.lastIndexOf("\/") + 1);// 取得当前页面的名称
                if (flag != pathName) {
                    location.href = path + "/html/report.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : "");
                    return;//已经跳转页面下面的代码不需要执行
                }
            }
        }
        switch (name) {
            case "首页"://点击首页直接跳转不经过其余方法
                location.href = path + "/html/firstPage.html?username=" + (object && 　object.username ? object.username : "");
                break;
            case  "报表":
                jump('report');
                controlTreeShow("report");
                setTable();
                break;
            case "系统设置":
                //  systemSet();通过data-target方式打开
                break;
            case  "操作员管理":
                //  opertion();data-target
                break;
            default:

        }

    });
    //基础信息二级导航添加事件
    $('#baseInfo >li >a').click(function () {
        var name = $(this).text();
        var flag = pageInfo($(this));
        var jump = function (parm) {//是否需要跳转页面的判断，针对chart
            if (flag) {
                //获得当前地址栏中名称、
                var pathName = getRootPath().pathName;
                pathName = pathName.substr(pathName.lastIndexOf("\/") + 1);// 取得当前页面的名称
                if (flag != pathName) {//路径不同进行跳转
                    location.href = path + "/html/baseInfo.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : "");
                    return;//已经跳转页面下面的代码不需要执行
                }
                else{//路径相同的情况下只改变路径后面的id属性内容，这种修改参数的方式会导致页面闪动
                    var url = changeURLArg(path + "/html/baseInfo.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : ""),'id',parm);
                    window.history.pushState({}, document.title, url);//http://www.welefen.com/use-ajax-and-pushstate.html
                    window.addEventListener('popstate', function(e){
                        if (window.history.state){
                            var state = e.state;
                            location.href= url;
                        }
                    }, false);
                }
            }
        }
        //修改为只显示列表
        switch (name) {
            case "厂区信息":
                jump("plant");
                controlTreeShow("plant");
                setPlantInfo();
                break;
            case "采集器信息":
                jump("collector");
                controlTreeShow();
                setCollectorInfo();
                break;
            case "设备信息":
                jump("device");
                controlTreeShow();
                setDeviceInfo();
                break;
            default:
                location.href = path + "/html/report.html?id=report&username=" + (object && 　object.username ? object.username : "");
        }
    });
    //抄表信息二级导航添加事件
    $("#readMeter > li > ul > li >a").click(function(){
        var name = $(this).text();
        var itemid = $(this).attr("itemid");
        var flag = pageInfo($(this));
        var jump = function (parm) {//是否需要跳转页面的判断，针对table
            if (flag) {
                //获得当前地址栏中名称、
                var pathName = getRootPath().pathName;
                pathName = pathName.substr(pathName.lastIndexOf("\/") + 1);// 取得当前页面的名称
                if (flag != pathName) {
                    location.href = path + "/html/readMeter.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : "");
                    return;//已经跳转页面下面的代码不需要执行
                }else{
                    var url = changeURLArg(path + "/html/readMeter.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : ""),'id',parm);
                    window.history.pushState({}, document.title, url);//http://www.welefen.com/use-ajax-and-pushstate.html
                    window.addEventListener('popstate', function(e){
                        if (window.history.state){
                            var state = e.state;
                            location.href= url;
                        }
                    }, false);
                }
            }
        };
        //修改为只显示列表
        switch (itemid) {
            case "Latest"://最新抄表数据
                if(name == "热表抄表数据"){
                    jump("latestH");
                    controlTreeShow("Latest");
                    setReadMeterH("Latest");
                }else{//电表
                    jump("latestE");
                    controlTreeShow("Latest");
                    setReadMeterE("Latest");
                }
                break;
            case "history":
                if(name == "热表抄表数据"){
                    $("#myHistoryFilter").attr("data-type", "heat");
                }else{
                    $("#myHistoryFilter").attr("data-type", "electric");
                }
                break;
            default:
        }
    })
    //图表的二级导航添加事件
    $('#chartType >li >a').click(function () {
        if (myChart != null) {
            // 图表清空-------------------
            myChart.clear();
            // 图表释放-------------------
            myChart.dispose();
        }
        var flag = pageInfo($(this));
        var jump = function (parm) {//是否需要跳转页面的判断，针对table
            if (flag) {
                //获得当前地址栏中名称、
                var pathName = getRootPath().pathName;
                pathName = pathName.substr(pathName.lastIndexOf("\/") + 1);// 取得当前页面的名称
                if (flag != pathName) {
                    location.href = path + "/html/chart.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : "");
                    return;//已经跳转页面下面的代码不需要执行
                }else{
                    var url = changeURLArg(path + "/html/chart.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : ""),'id',parm);
                    window.history.pushState({}, document.title, url);//http://www.welefen.com/use-ajax-and-pushstate.html
                    window.addEventListener('popstate', function(e){
                        if (window.history.state){
                            var state = e.state;
                            location.href= url;
                        }
                    }, false);
                }
            }
        }
        var name = $(this).text();
        changeChartMenu();//非超耗监测类型
        controlTreeShow("chart");
        switch (name) {
            case "消耗对比图":
                jump("consumCompared");
                setOption1();
                break;
            case  "消耗趋势图":
                jump("consumTrends");
                setOption2();
                break;
            case "超耗明细图":
                jump("hyperDetails");
                setOption3();
                break;
            case  "超耗统计图":
                jump("hyperCount");
                setOption4();
                break;
            case "能耗环比图":
                jump("energyChain");
                setOption5();
                break;
            case "设备超耗监测":
                jump("hyperMonitoring");
                changeChartMenu("hyperMonitoring");
                setTyperConsumptionTest();
                break;
            default:
                jump("");
        }
    });
})();
//初始一些事件包含点击事件,不包含ajax引入的文件
(function () {
    //设置用户信息
    var object = getRequest();
    if (object && object.username)
        $("#userName").html("Hi! " + object.username);
    else
        $("#userName").html("Hi! ");

})();
/**事件处理
 *设置基本信息中初始化查询的方法
 **/
function changeBaseInfo() {
    var object = getRequest();
    var id = 0;
    if (object)
        id = object.id;
    switch (id) {
        case "plant":
            controlTreeShow("plant");
            setPlantInfo();
            break;
        case "collector":
            controlTreeShow();
            setCollectorInfo();
            break;
        case "device":
            controlTreeShow();
            setDeviceInfo();
            break;
        case "report":
            controlTreeShow("report");
            setTable();
            break;
        case "latestH":
            controlTreeShow("Latest");
            setReadMeterH("Latest");
            break;
        case "latestE":
            controlTreeShow("Latest");
            setReadMeterE("Latest");
            break;
        case "historyH":
            controlTreeShow("history");
            setReadMeterH("history");
            break;
        case "historyE":
            controlTreeShow("history");
            setReadMeterE("history");
            break;
        case "consumCompared":
            controlTreeShow(id);
            setOption1();
            break;
        case "consumTrends":
            controlTreeShow(id);
            setOption2();
            break;
        case "hyperDetails":
            controlTreeShow(id);
            setOption3();
            break;
        case "hyperCount":
            controlTreeShow(id);
            setOption4();
            break;
        case "energyChain":
            controlTreeShow(id);
            setOption5();
            break;
        case "hyperMonitoring":
            controlTreeShow(id);
            changeChartMenu("hyperMonitoring");
            setTyperConsumptionTest();
            break;
        default :
            controlTreeShow();
    }
}
///修改视图类型
function changeChartMenu(flag) {
    var html = '<li><a href="#"> 年视图</a></li><li class="divider"></li><li><a href="#">季度视图</a></li><li class="divider"></li><li><a href="#">月视图</a></li><li class="divider"></li><li><a href="#">日视图</a></li><li class="divider"></li><li><a href="#">班次</a></li>';
    if (flag == "hyperMonitoring") {
        html = '<li><a href="#"> 显示全部</a></li><li class="divider"></li><li><a href="#">只显示超耗设备</a></li>';
        $("#chartChoice").html(html);
        $("#chartMenuText").html("超耗报警：");
    } else if (flag == "firstPage") {

    } else {
        $("#chartChoice").html(html);
        $("#chartMenuText").html("选择视图：");
    }
}
/*@flag 是否为厂区信息
 *控制页面的列表，区分是否有tree
 */
function controlTreeShow(flag) {
    if (flag == "plant" || flag == "report" || flag == "chart" || flag == "Latest"  || flag == "history") {//查询厂区信息 //查询报表 图表  最新抄表数据 历史抄表数据
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        if($("#tree").length > 0  && treeObj){//不需要重新加载tree
            $("#dataGrid").html('<table id="jqGrid"></table><div id="jqGridPager"></div>');
        }else{//需要重新加载tree
            $("#infoTable").html('<div class="row-fluid" style="white-space:nowrap; margin:0 auto;"><div class="span2" id="treeSpan" style="display:inline;"><ul id="tree" class="ztree pull-left"></ul></div><div class="span10" style="margin-left:.2em;" id="dataGrid"><table id="jqGrid"></table><div id="jqGridPager"></div></div></div>');
            timesFlag = 0;//重新计数
            $.fn.zTree.init($("#tree"), setting);
        }
       
    }else if(flag == "consumCompared" || flag == "consumTrends" || flag == "hyperDetails" || flag == "hyperCount" || flag == "energyChain" || flag == "hyperMonitoring"){
        $.fn.zTree.init($("#tree"), setting);
    } else {//没有tree的情况，例如采集器 设备信息,
        $("#infoTable").html('<table id="jqGrid"></table><div id="jqGridPager"></div>');
    }
    $("#content,#treeSpan").height($(window).height() - 210);
    setJqgridConfig();//设置bootstrap-jqgrid style
}
/*
 * 退出系统
 */
function loginOut() {
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: baseUrl + 'hysuser/logout',
        data: {},
        dataType: 'json',
        success: function (msg) {
            if (msg.login == "logoutSuccess") {
                var path = getRootPath().localhostPaht + getRootPath().projectName;//路径前缀
                location.href = path + '/login.html';
            }
        },
        error: function (xhr) {
            loadErrorLogin(xhr)
        }
    });
}
/**********************************************************系统参数设置功能*************************************************/
/*
 *查询所有的参数信息
 */
function findAllConfigs() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: baseUrl + 'hysparameter/findAllParameters',
        dataType: "JSON",
        data: JSON.stringify({}),
        cache: false,
        success: function (data) {
            data = searchSingle(data.hysParameterRows);
            //设置数据
            for (var i in data) {
                var code = data[i].hysParameterCode;
                var value = data[i].hysParameterValue;
                var id = data[i].hysStorageId;
                switch (code) {
                    case "databaseBackupPath"://备份路径
                        $("#backupPath").attr("hyStorageId", id);
                        $("#backupPath").val(value);
                        break;
                    case "databaseBackupState"://备份开关
                        $("#backUpSet").attr("hyStorageId", id);
                        if (value == "close") {//初始为不可用状态
                            $("#backUpSet").attr("checked", false);
                            $("#backupPath").attr("disabled", true);
                            $("#everyInterval").attr("disabled", true);
                            $("#interval").attr("disabled", true);
                            $("#backUpSetLb").text("备份已关闭");
                        }
                        break;
                    case "databaseBackupInterval":
                        $("#everyInterval").attr("hyStorageId", id);
                        var str = value.split(";");//monthly;01;02
                        $("#everyInterval").val(str[0]);
                        $("#everyInterval").change();
                        if (str[0] == "yearly") {
                            $("#interval").val(str[1] + "月 " + str[2] + "日 " + str[3] + "时");
                        } else if (str[0] == "monthly") {
                            $("#interval").val(str[1] + "日 " + str[2] + "时");
                        } else if (str[0] == "weekly") {
                            $("#weekEvery").val(str[1]);
                            $("#interval").val(str[2] + "时");
                        }
                        break;
                    case "":
                        break;
                    default:

                }
            }
        }
    });
}
/*
 *编辑参数设置
 */
function editConfig() {
    // var formValue = getFormJson('systemForm'); //具体的值  
    //判断是否开启数据备份功能
    if (!$("#interval").val() || !$("#backupPath").val()) {
        layer.msg("请检查数据不能为空！", layerMsgStress);
        return;
    }
    var str = "";
    if ($("#backUpSet").attr("checked") == "checked") { //数据库为开启备份功能
        str = $("#backUpSet").attr("hyStorageId") + ";open," + $("#backupPath").attr("hyStorageId") + ";" + $("#backupPath").val() + "," + $("#everyInterval").attr("hyStorageId") + ";" + $("#everyInterval").val() + ";";
        var strInter = $("#interval").val().split(" ");
        if ($("#everyInterval").val() == "yearly") {
            str += strInter[0].replace(/[^0-9]/ig, "") + ";" + strInter[1].replace(/[^0-9]/ig, "") + ";" + strInter[2].replace(/[^0-9]/ig, "");
        } else if ($("#everyInterval").val() == "monthly") {
            str += strInter[0].replace(/[^0-9]/ig, "") + ";" + strInter[1].replace(/[^0-9]/ig, "");
        } else {
            str += $("#weekEvery").val() + ";" + strInter[0].replace(/[^0-9]/ig, "");
        }
    }
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: baseUrl + 'hysparameter/editHySysParameter',
        data: JSON.stringify({
            ids: str
        }),
        dataType: "JSON",
        success: function (message) {
            if (message.result == "successed") {
                layer.msg("参数设置成功", layerMsgSuccess);
            } else {
                layer.msg("参数设置错误，请重试！", layerMsgException);
            }
        }
    });
}
/*
 * 设置备份状态
 */
function setBackUp() {
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: baseUrl + 'hysparameter/databaseBackupSwitch',
        data: JSON.stringify({
            flag: $("#backUpSet").attr("checked") == "checked" ? "open" : "close"
        }), //封装好的参数进行提交
        dataType: 'json',
        success: function (msg) {
            if (msg.result == "successed") {
                layer.msg("设置成功", layerMsgSuccess);
            }
        },
        error: function (xhr) {
            loadErrorLogin(xhr);
        }
    });
}
/****************************************浏览器方法****************************************************************/
$(window).on("resize", function() {
    if ($("#jqGrid").length > 0){
        jQuery("#jqGrid").setGridHeight($(window).height() - 338);
        if($("#tree").length > 0){
            console.log($(window).width())
            jQuery("#jqGrid").setGridWidth($(window).width() - $(window).width()*15/100);
//             jQuery("#jqGrid").setGridWidth("auto");
        }else{
            jQuery("#jqGrid").setGridWidth("auto");
        }
    }
    jQuery('.ui-jqgrid .ui-jqgrid-bdiv').css({
        'overflow-x' : 'hidden'
    });
});
             
