/** 
 * Some of the settings on the dialog
 * 关于打开模态窗口赋值的操作都放在operations.js中
 **/
$(function(){
    /**
     *抄表信息
     */
    $('#myHistoryFilter').on('shown.bs.modal', function () {//历史抄表查询
        $.fn.zTree.init($("#treeDemo"), settDemoing);
    });
    /**
     * 基本信息
     */
    //厂区信息
    $("#addRegional").on('shown.bs.modal', function(){//增加区域的时候
        //每次打开的时候需要重新查询未关联的设备信息
        var type = $("#addRegional").attr("data-type");
        var content = updateGrid("jqGrid");
        var ids = {
            ids:""
        }//修改需要查询包含自身的内容
        if(type == "update"){
            ids = {
                ids:content.workTeamId
            }
        }
        $.ajax({
            type: 'POST', 
            contentType: "application/json; charset=utf-8",
            url: baseUrl+'hysmeter/findAllMeterForSet',  
            data: JSON.stringify(ids),
            dataType: 'json',
            error :function(xhr){},
            success: function(msg){ 
                msg = !msg?[]:msg;
                msg = searchSingle(msg.hysMeter);
                $("#equipmentId").empty();
                $("#equipmentId").append('<option value="">--请选择--</option>');
                $.each(msg,function(){
                    $("#equipmentId").append('<option value="'+this.hysStorageId+'">'+this.hysMeterName+'</option>');        
                });  
                if(type == "update"){//修改赋值
                    editRegional(content);
                }
            } 
        });
    //
    });
})
