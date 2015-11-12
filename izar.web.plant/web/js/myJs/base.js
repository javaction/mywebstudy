/**
 * Vertically center Bootstrap 3 modals so they aren't always stuck at the top
 */
//公共提交路径
var baseUrl = '/plant/webresources/';
/*
 *Set a modal window in accordance with customary
 */
$(function() {
    function reposition() {
        var modal = $(this),
        dialog = modal.find('.modal-dialog');
        modal.css('display', 'block');
        
        // Dividing by two centers the modal exactly, but dividing by three 
        // or four works better for larger screens.
        dialog.css("margin-top", Math.max(0, ($(window).height() - dialog.height()) / 2));
    }
    // Reposition when a modal is shown
    $('.modal').on('show.bs.modal', reposition);
    // Reposition when the window is resized
    $(window).on('resize', function() {
        $('.modal:visible').each(reposition);
    });
});
/* order of data comparison 
 * values.sort(compare);
 * 正序
 */ 
function compare(value1,value2){
    if(value1 <value2){
        return -1;
    }else if(value1 > value2){
        return 1;
    }else {
        return 0;
    }
}
/*
 * js获取项目根路径，如： http://localhost:8080/plant
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8080/plant/index.html
    var curPath=window.document.location.href;
    //获取主机地址之后的目录，如： plant/index.html
    var pathName=window.document.location.pathname;
    var pos=curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPaht=curPath.substring(0,pos);
    //获取带"/"的项目名，如：/plant
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return {
        currPath:curPath,
        pathName:pathName,
        pos:pos,
        localhostPaht:localhostPaht,
        projectName:projectName
    }
}
/*get the url parm
 * url中的参数
 */
function getRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
/* 
 * changeURLArg('http://localhost:8088/plant?id=3&id=2','id',4); 
* url 目标url 
* arg 需要替换的参数名称 
* arg_val 替换后的参数的值 
* return url 参数替换后的url 
*/ 
function changeURLArg(url,arg,arg_val){ 
    var pattern=arg+'=([^&]*)'; 
    var replaceText=arg+'='+arg_val; 
    if(url.match(pattern)){ 
        var tmp='/('+ arg+'=)([^&]*)/gi'; 
        tmp=url.replace(eval(tmp),replaceText); 
        return tmp; 
    }else{ 
        if(url.match('[\?]')){ 
            return url+'&'+replaceText; 
        }else{ 
            return url+'?'+replaceText; 
        } 
    } 
    return url+'\n'+arg+'\n'+arg_val; 
} 
//将form中的值转换为键值对。
function getFormJson(form) {
    var o = {};
    var a = $('#'+form).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}
/**
 *处理查询出来的数据为一条的问题
 */
function searchSingle(data){
    var arr = [];
    if(!data){//无数据
        return arr;
    }else if(data.length == undefined){//有一条json格式的数据
        arr.push(data);
        return arr;
    }
    return data;
}
/**
 * 获得选中行的数据
 * @tId 表格id
 * @attribute 根据哪个属性进行获取
 */
function getAttributes(tId,attribute){
    var attributes = [];
    var sels = $("#"+tId).jqGrid('getGridParam','selarrrow');    
    var j = 0;
    for(var i=0;i<sels.length;i++){
        var ret = $("#"+tId).jqGrid('getRowData',sels[i]);
        if(ret[attribute] != ''){
            attributes[j] = ret[attribute];
            j++;
        }
    }
    return attributes;
}
/**
 * 获取需要修改数据
 */
function updateGrid(tId){
    var content;
    var sels = $("#"+tId).jqGrid('getGridParam','selarrrow');
    if(sels == "" || sels.length >1){
        layer.msg("请选且至多选择一条数据进行修改",layerMsgError);
        return false;
    }
    var rowId = $('#'+tId).jqGrid('getGridParam','selrow');
    if (rowId){
        content = jQuery('#'+tId).jqGrid('getRowData',rowId);
    } 
    return content;
}
//获得浏览器的分辨率,制定每页显示多好条信息
function getBrowser() {
    if (screen.width <= 1024 && screen.height <= 768) {
        return 20;
    } else if (screen.width <= 1440 && screen.height <= 900) {
        return 35;
    } else if (screen.width <= 1920 && screen.height <= 1080) {
        return 50;
    } else {
        return 50;
    }
}
var layerMsgStress = {//强调
    icon: 0,  
    offset:[$(window).height()*.2 ,""]
}
var layerMsgSuccess = {//成功
    icon: 1,  
    offset:[$(window).height()*.2 ,""]
}
var layerMsgError = {//错误
    icon: 5,  
    time: 3000, //3秒关闭（如果不配置，默认是3秒）
    offset:[$(window).height()*.2 ,""]
};
var layerMsgException = {//异常
    icon: 2,  
    time: 3000, //3秒关闭（如果不配置，默认是3秒）
    offset:[$(window).height()*.2 ,""]
};
var setJqgridConfig = function () {
    $.jgrid.defaults.responsive = true;
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $.jgrid.styleUI.Bootstrap.base.rowTable = "table table-bordered table-striped";   
}
var jqGridJsonReader = {
    root: function (obj) {
        if (obj.rows == undefined) {
            return null
        }
        return searchSingle(obj.rows);
    },
    records: "totalrecords",
    page: "currpage",
    total: "totalpages",
    repeatitems: false
};
var jqgirdPrmNames = {
    page: "currpage", // 表示请求页码的参数名称  
    rows: "pagesize"
}
//JQGrid 菜单栏，默认全部false  
var navGridParams = {
    refresh:false,
    edit: false,
    add: false,
    del: false,
    search: false
};
//JQGrid列表的一些公共参数，这里抽取出来  
var gridParamsNoTree = {
    mtype: 'POST', //get  post
    ajaxGridOptions: {
        contentType: 'application/json; charset=utf-8'
    },
    datatype: 'json',
    ajaxRowOptions: {
        contentType: 'application/json; charset=utf-8'
    },
    autowidth: true,
    height: $(window).height() - 338, //550
    viewrecords: true,
    rownumbers: true,
    multiboxonly: true,
    multiselect: true,
    sortorder: 'desc',
    rownumWidth: 40,
    gridview: true,
    rowNum: getBrowser(),
    stripeRows: true,
    hidegrid: false,
    loadui: 'block',
    altRows: true, //隔行变色
    altclass: 'altclass', //隔行变色样式
    emptyrecords: "数据为空！"
};
var gridParams = {
    mtype: 'POST', //get  post
    ajaxGridOptions: {
        contentType: 'application/json; charset=utf-8'
    },
    datatype: 'json',
    ajaxRowOptions: {
        contentType: 'application/json; charset=utf-8'
    },
    width: $(window).width() - $(window).width()*15/100,//参照bootstrap-responsive.css row-fluid .span2
    height: $(window).height() - 338, //550
    viewrecords: true,
    rownumbers: true,
    multiboxonly: true,
    multiselect: true,
    sortorder: 'desc',
    rownumWidth: 40,
    gridview: true,
    rowNum: getBrowser(),
    stripeRows: true,
    hidegrid: false,
    loadui: 'block',
    altRows: true, //隔行变色
    altclass: 'altclass', //隔行变色样式
    emptyrecords: "数据为空！"
}
