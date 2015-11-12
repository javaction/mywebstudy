/*
*tree info and method
*/
var timesFlag = 0;//计数器
function ajaxDataFilter(treeId, parentNode, responseData) {
    if (responseData) {
        responseData = searchSingle(responseData.treeNode);
        for(var i =0, l= responseData.length;i < l; i++) {
            responseData[i].name =  responseData[i].name.replace(/\.n/g, '.');     
            responseData[i].icon = addIcon(responseData[i].currLevel);
        }
    }
    return responseData;
}
function zTreeBeforeAsync(treeId, treeNode) {
    if(treeNode != null){
        var treeObj = $.fn.zTree.getZTreeObj("tree");   
        treeObj.setting.async.otherParam = {
            "id":treeNode.id,
            "currLevel":treeNode.currLevel,
            "pid":treeNode.pid
        };      
    }
}
function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    if(timesFlag == 3){
        alert("load...error");
    }else{
        treeFresh();
    }
    timesFlag ++;
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
    
}
function zTreeBeforeDblClick(treeId, treeNode) {
    return false;
}
/*
 * tree node click
 */
function zTreeOnClick(event, treeId, treeNode) {
    alert(treeNode.tId + ", " + treeNode.name);
}
/*
 *fresh tree
 */
function treeFresh(){
    $.fn.zTree.destroy("tree");  //销毁tree  
    setting.async.otherParam= {
        "id":"",
        "currLevel":"0",
        "pid":"0"
    };
    $.fn.zTree.init($("#tree"), setting); 
}
/** 设置tree异步的基本信息 */
var setting={
    async: {
        enable: true,
        type: "POST",
        url:baseUrl+"hysdata/findSubNodes",
        contentType: "application/json",
        otherParam:{
            "id":"0",
            "pid":"0",
            "currLevel":"0"
        },
        dataFilter: ajaxDataFilter
    },
    data:{
        simpleDate: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: '0'
        }
    },      
    callback: {
        onAsyncError: zTreeOnAsyncError,
        onAsyncSuccess: zTreeOnAsyncSuccess,
        beforeAsync: zTreeBeforeAsync,
        onClick: zTreeOnClick,
        beforeDblClick: zTreeBeforeDblClick
      
    }
};
//设置icon和添加title
function addIcon(level) {
    var icon = "";
    switch (level) {
        case '0':
            icon = "../images/tree/office_opt.png";
            break;
        case '1':
            icon = "../images/tree/factory.png";
            break;
        case '2':
            icon = "../images/tree/line.png";
            break;
        case '3':
            icon = "../images/tree/group.png";
            break;
        default:
    }
    return icon;
}
/**********************************************************弹出框tree*****************************************************************************/
var current_input = {};//当前需要赋值的输入框,包含id和只能赋值的等级
/**
 * 显示弹出框下面的tree列表
 * @id 输入框input的id
 */
function zTreeBeforeAsyncDemo(treeId, treeNode) {
    if(treeNode != null){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");   
        treeObj.setting.async.otherParam = {
            "id":treeNode.id,
            "currLevel":treeNode.currLevel,
            "pid":treeNode.pid
        };      
    }
}
function zTreeOnClickDemo(event, treeId, treeNode) {
    if(treeNode.currLevel == current_input.level){
        $("#"+current_input.id).val(treeNode.name);
    }
}
var settDemoing={
    async: {
        enable: true,
        type: "POST",
        url:baseUrl+"hysdata/findSubNodes",
        contentType: "application/json",
        otherParam:{
            "id":"0",
            "pid":"0",
            "currLevel":"0"
        },
        dataFilter: ajaxDataFilter
    },
    data:{
        simpleDate: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: '0'
        }
    },      
    callback: {
        onAsyncError: zTreeOnAsyncError,
        onAsyncSuccess: zTreeOnAsyncSuccess,
        beforeAsync: zTreeBeforeAsyncDemo,
        onClick: zTreeOnClickDemo
    }
};
function showMenu(o) {
    current_input = o;
    var obj = $("#"+o.id);
    var objOffset = $("#"+o.id).offset();
    $("#menuContent").css({
        width:obj.width()*1.15,
        left:objOffset.left + "px", 
        top:objOffset.top + obj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
//    $.fn.zTree.destroy("treeDemo"); //暂时不进行销毁操作
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "beltlineName" || event.target.id == "areaName" ||  event.target.id == "productionGroup"  || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}



