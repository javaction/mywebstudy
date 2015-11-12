/*
 *存放验证信息，非弹出框性质
 **/
(function(){
    $.validator.setDefaults({//设置validator默认  
        errorClass: "error", //设置默认错误提示的css类名  
        errorPlacement: function(error, element) {
            console.log("Validation error,check Please")
        }
    });
    // 增加validator Method
    $.validator.addMethod("url", function(value, element) {
        var reg= new RegExp(/^[a-zA-Z]:(((\\(?! )[^\\/:*?"<>|]+)+\\?)|(\\))\s*$/);
        return this.optional(element) || reg.test(value); 
    }, "");
    //登录验证 
    $("#loginForm").validate({
        rules: {
            username:"required",
            password:"required"
        },	
        submitHandler: function(form) {
            // Use Ajax to submit form data
            //$form.serialize()
            $.ajax({
                type: 'POST', 
                contentType: "application/json; charset=utf-8",
                url: baseUrl+'hysuser/login',  
                data: JSON.stringify({
                    hysUserName:$("#loginForm input[name=username]").val(), 
                    hysUserPassword:$("#loginForm input[name=password]").val()
                }),
                dataType: 'json',
                beforeSend :function(){
                },
                success: function(msg){
                    if(msg.login == "successful"){
//                        location.href = getRootPath().projectName+'/html/firstPage.html?username='+$("#loginForm input[name=username]").val();
                        console.log("---projectName："+getRootPath().projectName+'/html/firstPage.html?username='+$("#loginForm input[name=username]").val());
                    }else{
                        layer.msg("用户名或密码错误，请重新输入！如果忘记请与系统管理员联系。",layerMsgError);
                    }
                },
                error: function(xhr){
                    alert("登录已超时，请刷新页面重试！");
                }
            });
        }

    })
})();
/*
 * 弹出框需要的验证信息
 */
function alertValidate(flag){
    if(flag == "system"){
        //系统设置验证
        $("#systemForm").validate({
            rules: {
                backupPath:{
                    required:true,
                    url:true
                },
                interval:"required"
            },	
            submitHandler: function(form) {
                // Use Ajax to submit form data
                //$form.serialize()
                $.ajax({
                    type: 'POST', 
                    contentType: "application/json; charset=utf-8",
                    url: baseUrl+'',  
                    data: JSON.stringify({
                       
                        }),
                    dataType: 'json',
                    beforeSend :function(){
                    },
                    success: function(msg){
                 
                    },
                    error: function(xhr){
                        alert("登录已超时，请刷新页面重试！");
                    }
                }); 
            }

        })
    }else if(flag == "password"){//密码进行验证
        $("#changePasswordForm").validate({
            rules: {
                oldPassword:"required",
                newPassword:"required",
                confirmPassword:{
                    required:true,
                    equalTo: "#newPassword"
                }
            },	
            submitHandler: function(form) {
                $.ajax({
                    type: 'POST', 
                    contentType: "application/json; charset=utf-8",
                    url: baseUrl+'hysuser/updateUserPwd',  
                    data: JSON.stringify({
                        oldPassword:$("#oldPassword").val(),
                        newPassword:$("#newPassword").val(),
                        confirmPassword:$("#newPassword").val()
                    }),
                    dataType: 'json',
                    success: function(data){
                        if(data.result == "successed"){
                            layer.msg("密码修改成功。",layerMsgSuccess);
                            $('#changePwdModal').modal('toggle')
                        }else {
                            layer.msg(data.message,layerMsgException);
                        }
                    },
                    error: function(xhr){
                    
                    }
                });
            }

        })
      
    }else if(flag == "history"){//历史抄表过滤查询
        $("#historyFlterForm").validate({
            rules: {
                beginTime: "required",
                endTime:"required"
            },
            submitHandler: function(form){
                var saveStorage = function(){//数据暂时存储到session中
                    var data = getFormJson("historyFlterForm");
                    var o = new Object();//保存要过滤的内容
                    o.beginTime = data.beginTime;
                    o.endTime  = data.endTime;
                    o.hysMeterNumber = data.hysMeterNumber;
                    o.areaName = data.areaName;
                    o.beltlineName = data.beltlineName;
                    o.productionGroup = data.productionGroup;
                    var json = JSON.stringify(o);
                    sessionStorage.setItem("obj",json);
                }
                var jump = function(parm){
                    var path = getRootPath().localhostPaht + getRootPath().projectName;//路径前缀
                    var object = getRequest();//获得路径之后的参数
                    //获得当前地址栏中名称、
                    var pathName = getRootPath().pathName;
                    pathName = pathName.substr(pathName.lastIndexOf("\/") + 1);// 取得当前页面的名称
                    if ("readMeter.html" != pathName) {//是否在抄表数据页面
                        location.href = path + "/html/readMeter.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : "");
                        return;//已经跳转页面下面的代码不需要执行
                    }else{
                        var url = changeURLArg(path + "/html/readMeter.html?id=" + parm + "&username=" + (object && 　object.username ? object.username : ""),'id',parm);
                        window.history.pushState({}, document.title, url);//http://www.welefen.com/use-ajax-and-pushstate.html
                        window.addEventListener('popstate', function(e){
                            if (window.history.state){
                                location.href= url;
                            }
                        }, false);
                    }
                }
                saveStorage();//数据暂时存储到session中
                var type = $("#myHistoryFilter").attr("data-type");//获取查询列表类型热表还是电表
                if(type == "heat"){//热表
                    jump("historyH")
                }else{//电表
                    jump("historyE")
                }
            }
        });
    }else if(flag == "addRegional"){//增加区域
        $("#regionalForm").validate({
            rules: {
                regionalName:"required",
                beltlineName:"required",
                workTeamName:"required"
            },	
            submitHandler: function(form) {
                var data = getFormJson("regionalForm");
                var url = "hysdata/addArea";
                var type = $("#addRegional").attr("data-type");
                if(type == "update"){
                    url = "hysdata/editArea";
                    data.areaId = $("#regionalName").attr("data-parm");
                    data.beltlineId = $("#productionLine").attr("data-parm");
                    data.workTeamId = $("#productionGroupRegional").attr("data-parm");
                }
                $.ajax({
                    type: 'POST', 
                    contentType: "application/json; charset=utf-8",
                    url: baseUrl+url,  
                    data: JSON.stringify(data),
                    dataType: 'json',
                    success: function(msg){
                        if(msg.result == "successed"){
                            $("#jqGrid").trigger("reloadGrid");
                            $("#addRegional").modal("toggle");
                            layer.msg("添加成功",layerMsgSuccess);
                        }else if(msg.result == "areaNameExists"){
                            layer.msg("区域名称重复",layerMsgError);
                        }else if(msg.result == "betlineNameExists"){
                            layer.msg("生产线名称重复",layerMsgError);
                        }else if(msg.result == "workTeamNameExists"){
                            layer.msg("生产组名称或者公共区域下的名称重复",layerMsgError);
                        }else{
                            layer.msg("出现未知错误！请与系统管理员联系。",layerMsgException);
                        }
                    },
                    error: function(xhr){
                        alert("登录已超时，请刷新页面重试！");
                    }
                });
            }
        });
        
    }else if(flag == "concentrator"){//增加采集器
        $("#concentratorForm").validate({
            rules: {
                username:"required",
                password:"required"
            },	
            submitHandler: function(form) {
            
            }
        });
        
    }else if(flag == "equipment"){//增加设备信息
        $("#equipmentForm").validate({
            rules: {
                username:"required",
                password:"required"
            },	
            submitHandler: function(form) {
            
            }
        });
    }
}