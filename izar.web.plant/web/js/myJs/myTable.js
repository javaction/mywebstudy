/*
 * Document   : myTable
 *   Created on : 2015-6-15, 10:03:11
 *   Author     : malinda.Wang
 *
 **/
//报表列表
function setTable() {
    setJqgridConfig();
    var params = {
        url: "",
        serializeGridData: function (postData)
        {
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [

        {
            label: "表号",
            name: '',
            key: true,
            width: 75,
            align: "center",
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "邮箱",
            name: 'CustomerID',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "客户地址",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "开始日期",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "手机号",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "版本号",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "类型",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },

        {
            label: "编号",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },

        {
            label: "结束日期",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParams);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-download-alt",
        caption: "下载",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {

        }
    });
}
/*
 * 设置基础信息
 */
//厂区信息
function setPlantInfo() {
    var params = {
        url: baseUrl+"hysdata/findAreaBasicInfo",
        serializeGridData: function (postData)
        {
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [
             {//为自定义
            label:"hysStorageId",
            hidden:true,
            name:"hysStorageId"  
        },
        {
          name:"areaId",
          hidden:true
        },
        {
            label: "区域名称",
            name: 'areaName',
            width: 75,
            align: "center",
            searchoptions: {
                clearSearch: false
            }
        },
        {
            name:'beltlineId',
            hidden:true
        },
        {
            label: "生产线名称",
            name: 'beltlineName',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "生产组名称",
            name: 'workTeamName',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },{
            name:"workTeamId",
            hidden:true
        },
        {
            label: "采集器",
            name: 'collectorNum',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "设备名称",
            searchoptions: {
                clearSearch: false
            },
            name: 'meterNum', 
            width: 150
        },
        {
            name: 'meterId', 
            width: 150,
            hidden:true
        },
        {
            label: "计量类型",
            stype: "select",
            formatter:function(cellvalue){
                var str;
                switch(cellvalue){
                    case "electric":
                        str = "电表";
                        break;
                    case "heat":
                        str = "热表";
                        break;
                    default :
                        str = "";
                }
                return str;
            },
            searchoptions: {
                clearSearch: false,
                value:":请选择;electric:电表;heat:热表"
            },
            name: 'meterCode', 
            width: 150
        },{
            name:'isPublic',
            hidden:true
        }
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParams);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-import",
        caption: "导入",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-export",
        caption: "导出",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {
            $("#addRegional").attr("data-type", "add");
            $('#addRegional').modal();
        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-edit",
        caption: "修改",
        onClickButton: function () {
            var content = updateGrid("jqGrid");
            $("#addRegional").attr("data-type", "update");
            if(content){
               $('#addRegional').modal();
            }
        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-trash",
        caption: "删除",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-cog",
        caption: "设置",
        onClickButton: function () {

        }
    })

}
//采集器信息
function setCollectorInfo() {
    var params = {
        url: baseUrl+"hyscollector/findCollectorBasicInfo",
        serializeGridData: function (postData)
        {
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [
        {
            label:"hysStorageId",
            hidden:true,
            name:"hysStorageId"  
        },
        {
            label: "采集器号",
            name: 'hysCollectorNumber',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "型号",
            name: 'hysCollectorType',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "厂商",
            name: 'hysManufacturer',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "备注",
            searchoptions: {
                clearSearch: false
            },
            name: 'hysRemark', 
            width: 150
        },
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParamsNoTree);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {
            $("#addConcentrator").modal();
        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-edit",
        caption: "修改",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-trash",
        caption: "删除",
        onClickButton: function () {

        }
    });
}
//设备信息
function setDeviceInfo() {
    var params = {
        url: baseUrl+"hysmeter/findMeterBasicInfo",
        serializeGridData: function (postData)
        {
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [
        {
            label:"hysStorageId",
            hidden:true,
            name:"hysStorageId"  
        },
        {
            label: "设备号",
            name: 'hysMeterNumber',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "设备型号",
            name: 'hysMeterType',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "计量类型",
            name: 'hysMeterCode',
            width: 150, 
            stype: "select",
            formatter:function(cellvalue){
                var str;
                switch(cellvalue){
                    case "electric":
                        str = "电表";
                        break;
                    case "heat":
                        str = "热表";
                        break;
                    default :
                        str = "";
                }
                return str;
            },
            searchoptions: {
                clearSearch: false,
                value:":请选择;electric:电表;heat:热表"
            }
        },
        {
            label: "标准功率",
            name: 'hysStandardPower',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "厂商",
            name: 'hysManufacturer',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "采集器号",
            searchoptions: {
                clearSearch: false
            },//sorttype: 'number',
            name: 'hysCollectorNum', 
            width: 150
        },
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParamsNoTree);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {
            $("#addEquipment").modal();
        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-edit",
        caption: "修改",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-trash",
        caption: "删除",
        onClickButton: function () {

        }
    });
}
/**
 * 抄表功能
 * @flag = Latest/history 区分最新抄表和历史抄表
 **/
function setReadMeterH(flag){
    var url = flag == "Latest"?"":"";
    setJqgridConfig();
    var params = {
        url: baseUrl+url,
        serializeGridData: function (postData)
        {
            if(flag == "history"){
                var str = sessionStorage.getItem("obj");
                var data = JSON.parse(str);
                 $.extend(postData,data); 
                 sessionStorage.clear();
            }
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [
        {
            label:"hysStorageId",
            hidden:true,
            name:"hysStorageId"  
        },
        {
            label: "区域名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "生产线名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "生产组名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "设备号",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "计量类型",
            name: '',
            width: 150, 
            stype: "select",
            formatter:function(cellvalue){
                var str;
                switch(cellvalue){
                    case "electric":
                        str = "电表";
                        break;
                    case "heat":
                        str = "热表";
                        break;
                    default :
                        str = "";
                }
                return str;
            },
            searchoptions: {
                clearSearch: false,
                value:":请选择;electric:电表;heat:热表"
            }
        },
        {
            label: "累计热量",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },    {
            label: "累计流量",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },    {
            label: "进水温度",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },    {
            label: "回水温度",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },    {
            label: "温差",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },    {
            label: "抄表时间",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParams);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager',{
        buttonicon: "glyphicon-download-alt",
        caption: "下载",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-edit",
        caption: "修改",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-trash",
        caption: "删除",
        onClickButton: function () {

        }
    });
}
/*
 * 电表信息查询
 */
function setReadMeterE(flag){
    setJqgridConfig();
    var params = {
        url: baseUrl+"",
        serializeGridData: function (postData)
        {
            if(flag == "history"){
                var str = sessionStorage.getItem("obj");
                var data = JSON.parse(str);
                $.extend(postData,data); 
                sessionStorage.clear();
            }
            return JSON.stringify(postData);
        },
        postData: {
            pagesize: getBrowser()
        }, 
        colModel: [
        {
            label:"hysStorageId",
            hidden:true,
            name:"hysStorageId"  
        },
        {
            label: "区域名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "生产线名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "生产组名称",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "设备号",
            name: '',
            width: 150, 
            searchoptions: {
                clearSearch: false
            }
        },
        {
            label: "计量类型",
            name: '',
            width: 150, 
            stype: "select",
            formatter:function(cellvalue){
                var str;
                switch(cellvalue){
                    case "electric":
                        str = "电表";
                        break;
                    case "heat":
                        str = "热表";
                        break;
                    default :
                        str = "";
                }
                return str;
            },
            searchoptions: {
                clearSearch: false,
                value:":请选择;electric:电表;heat:热表"
            }
        },
        {
            label: "累计电量",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },   {
            label: "抄表时间",
            searchoptions: {
                clearSearch: false
            },
            name: '', 
            width: 150
        },
        ],
        jsonReader: $.extend({}, jqGridJsonReader),
        prmNames: $.extend({}, jqgirdPrmNames),
        pager: "#jqGridPager",
        loadComplete: function (data) { //完成服务器请求后，回调函数  

        }, 
        loadError: function (xhr, status, error) {
        },
        gridComplete: function () {

        }
    };
    $.extend(params, gridParams);
    $('#jqGrid').jqGrid(params).jqGrid('filterToolbar');
    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", navGridParams);
    $('#jqGrid').navButtonAdd('#jqGridPager',{
        buttonicon: "glyphicon-download-alt",
        caption: "下载",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-plus",
        caption: "增加",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-edit",
        caption: "修改",
        onClickButton: function () {

        }
    }).navButtonAdd('#jqGridPager', {
        buttonicon: "glyphicon-trash",
        caption: "删除",
        onClickButton: function () {

        }
    });
}