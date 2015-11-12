/* 
 * 列表增删改查操作类
 * 提交信息写作myValidator.js中进行操作
 */
/**
 * 基础信息
 */
//区域信息
function editRegional(content){
    publicChange(content.isPublic);//区域类型
    $("#regionalName").val(content.areaName);
    $("#regionalName").attr("data-parm",content.areaId);
    $("#productionLine").val(content.beltlineName);
    $("#productionLine").attr("data-parm",content.beltlineId);
    $("#productionGroupRegional").val(content.workTeamName);
    $("#productionGroupRegional").attr("data-parm",content.workTeamId);
    $("#equipmentId").val(content.meterId);
}


