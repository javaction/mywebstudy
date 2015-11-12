/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

/**
 * 
 * @author Ussopp.Su
 */
public class HysDataSqlResource {
    
    //查询厂区信息.sql  //后期可能需要关联用户或者设备 //不再使用
    public static final String FIND_ALL_AREA_BESIC_INFO =""
            //1.生产区域正常的
            + "select mm.areaId ,mm.areaName ,mm.beltlineId,mm.beltlineName,mm.workTeamId,mm.workTeamName ,mm.hys_data_state  from (  "
            + "select (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid) areaId ,  "
            + "(select dm.hys_data_name  from hys_data dm where dm.hys_storage_id = (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid)) areaName ,d0.hys_data_pid beltlineId,  "
            + "(select d.hys_data_name from hys_data d where d.hys_storage_id = d0.hys_data_pid ) beltlineName ,d0.hys_storage_id workTeamId,d0.hys_data_name workTeamName ,d0.hys_data_state from hys_data d0 where d0.hys_data_level = 3  "
            + "union  all "
            //2.公共区域正常的 ,公共区域通过 hys_data_state = 'public' 区分：其下级节点时生产组，level为2，这与生产区域的生产线等级一致
            + "select d1.hys_data_pid areaId ,(select d.hys_data_name from hys_data d where d.hys_storage_id = d1.hys_data_pid ) areaName , 0 beltlineId,''beltlineName,d1.hys_storage_id workTeamId,d1.hys_data_name workTeamName ,d1.hys_data_state  "
            + "from hys_data d1 where d1.hys_data_level = 2 and  d1.hys_data_state = 'public'  "
            + "union  all  "
            //3.生产区域只存在生产线的
            + "select d2.hys_data_pid areaId,(select d.hys_data_name from hys_data d where d.hys_storage_id = d2.hys_data_pid ) areaName ,d2.hys_storage_id beltlineId,d2.hys_data_name beltlineName ,0 workTeamId,''workTeamName ,d2.hys_data_state "
            + "from hys_data d2 where d2.hys_data_level = 2 and not exists (select hys_storage_id from hys_data where hys_data_pid = d2.hys_storage_id )and d2.hys_data_state is null "
            + "union  all "
            //4.只存在区域，其下没有任何节点的
            + "select d3.hys_storage_id areaId,d3.hys_data_name areaName ,0 beltlineId,''beltlineName ,0 workTeamId,''workTeamName ,d3.hys_data_state  from  hys_data d3 where d3.hys_data_level = 1 "
            + "and not exists (select d.hys_storage_id from hys_data d where d.hys_data_pid = d3.hys_storage_id ) "
            + ") mm , hys_users_data ud , hys_user u where mm.areaId = ud.hys_data_id and ud.hys_user_id = u.hys_storage_id and u.hys_user_name = ?userName and u.hys_storage_id = ?userId  " //区域关联用户
            + " and 1 = 1  ";
    
    //查询第一级的树节点（区域） //用户直接关联区域
    public static final String FIND_TREE_NODE_SQL1 =""
            + "select  d.hys_storage_id ,d.hys_data_name ,d.hys_data_pid ,d.hys_data_level, (case when exists(select 1 from hys_data dd where dd.hys_data_pid = d.hys_storage_id) then  'true' else 'false' end) isParent "
            + ",d.hys_data_state from  hys_data d, hys_user u ,hys_users_data ud where d.hys_storage_id = ud.hys_data_id  "
            + "and ud.hys_user_id = u.hys_storage_id  and u.hys_storage_id =?userId and u.hys_user_name =?userName and d.hys_data_pid = ?dataPid ";
    
     public static final String FIND_TREE_NODE_SQL2 =""
             + "select d.hys_storage_id ,d.hys_data_name ,d.hys_data_pid ,d.hys_data_level, (case when exists(select 1 from hys_data dd where dd.hys_data_pid = d.hys_storage_id) then  'true' else 'false' end) isParent , "
             + "d.hys_data_state from hys_data d where d.hys_data_pid = ?dataPid ";
    
    
     //查询厂区信息.sql----关联user的情况
     public static final String FIND_ALL_AREA_BESIC_INFO1 =""
             + "select mm.areaId ,mm.areaName ,mm.beltlineId,mm.beltlineName,mm.workTeamId,mm.workTeamName ,mm.hys_data_state ,mm.hys_meter_number ,mm.hys_meter_code ,mm.hys_collector_number from ( "
             + "select (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid) areaId ,(select dm.hys_data_name  from hys_data dm where dm.hys_storage_id = (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid)) areaName ,d0.hys_data_pid beltlineId,   "
             + " (select d.hys_data_name from hys_data d where d.hys_storage_id = d0.hys_data_pid ) beltlineName ,d0.hys_storage_id workTeamId,d0.hys_data_name workTeamName ,d0.hys_data_state, "
             + " m.hys_meter_number ,m.hys_meter_code ,(select c.hys_collector_number from hys_meter_collector mc ,hys_collector c where mc.hys_collector_id = c.hys_storage_id and m.hys_storage_id = mc.hys_meter_id ) hys_collector_number  "
             + " from hys_meter m ,hys_data_meter dm ,hys_data d0 where m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d0.hys_storage_id and d0.hys_data_level = 3  "
             + " union all "
             + " select d1.hys_data_pid areaId ,(select d.hys_data_name from hys_data d where d.hys_storage_id = d1.hys_data_pid ) areaName , 0 beltlineId,''beltlineName,d1.hys_storage_id workTeamId,d1.hys_data_name workTeamName ,d1.hys_data_state, "
             + " m.hys_meter_number ,m.hys_meter_code , ( select c.hys_collector_number from hys_meter_collector mc ,hys_collector c where mc.hys_collector_id = c.hys_storage_id and m.hys_storage_id = mc.hys_meter_id ) hys_collector_number  "
             + " from hys_meter m ,hys_data_meter dm ,hys_data d1  where  m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d1.hys_storage_id and d1.hys_data_level = 2 and  d1.hys_data_state = 'public' "
             + " union all  "
             + " select d2.hys_data_pid areaId,(select d.hys_data_name from hys_data d where d.hys_storage_id = d2.hys_data_pid ) areaName ,d2.hys_storage_id beltlineId,d2.hys_data_name beltlineName ,0 workTeamId,''workTeamName ,d2.hys_data_state , "
             + " ''hys_meter_number ,''hys_meter_code ,''hys_collector_number from hys_data d2 where d2.hys_data_level = 2 and not exists (select hys_storage_id from hys_data where hys_data_pid = d2.hys_storage_id )and d2.hys_data_state is null  "
             + " union all "
             + " select d3.hys_storage_id areaId,d3.hys_data_name areaName ,0 beltlineId,''beltlineName ,0 workTeamId,''workTeamName ,d3.hys_data_state ,''hys_meter_number ,''hys_meter_code ,''hys_collector_number  "
             + " from  hys_data d3 where d3.hys_data_level = 1 and not exists (select d.hys_storage_id from hys_data d where d.hys_data_pid = d3.hys_storage_id ) "
             + " union all "
             + " select (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid) areaId ,(select dm.hys_data_name  from hys_data dm where dm.hys_storage_id = (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid)) areaName ,d0.hys_data_pid beltlineId,  "
             + " (select d.hys_data_name from hys_data d where d.hys_storage_id = d0.hys_data_pid ) beltlineName ,d0.hys_storage_id workTeamId,d0.hys_data_name workTeamName ,d0.hys_data_state, "
             + " ''hys_meter_number,''hys_meter_code,'' hys_collector_number from hys_data d0 where d0.hys_data_level = 3 and d0.hys_storage_id not in(select dm.hys_data_id from hys_data_meter dm)  "
             + " )mm ,hys_users_data ud , hys_user u  "
             + " where mm.areaId = ud.hys_data_id and ud.hys_user_id = u.hys_storage_id and u.hys_user_name = ?userName and u.hys_storage_id = ?userId " //区域关联用户
             + " and 1 = 1 ";
     
     
     //查询厂区信息.sql---不考虑user的情况
     public static final String FIND_ALL_AREA_BESIC_INFO2 =""
             + "select mm.areaId ,mm.areaName ,mm.beltlineId,mm.beltlineName,mm.workTeamId,mm.workTeamName ,mm.hys_data_state ,mm.hys_meter_number, mm.hys_meter_name ,mm.hys_meter_code ,mm.hys_collector_number from ( "
             + "select (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid) areaId ,(select dm.hys_data_name  from hys_data dm where dm.hys_storage_id = (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid)) areaName ,d0.hys_data_pid beltlineId,   "
             + " (select d.hys_data_name from hys_data d where d.hys_storage_id = d0.hys_data_pid ) beltlineName ,d0.hys_storage_id workTeamId,d0.hys_data_name workTeamName ,d0.hys_data_state, "
             + " m.hys_meter_number ,m.hys_meter_name ,m.hys_meter_code ,(select c.hys_collector_number from hys_meter_collector mc ,hys_collector c where mc.hys_collector_id = c.hys_storage_id and m.hys_storage_id = mc.hys_meter_id ) hys_collector_number  "
             + " from hys_meter m ,hys_data_meter dm ,hys_data d0 where m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d0.hys_storage_id and d0.hys_data_level = 3  "
             + " union all "
             + " select d1.hys_data_pid areaId ,(select d.hys_data_name from hys_data d where d.hys_storage_id = d1.hys_data_pid ) areaName , 0 beltlineId,''beltlineName,d1.hys_storage_id workTeamId,d1.hys_data_name workTeamName ,d1.hys_data_state, "
             + " m.hys_meter_number ,m.hys_meter_name ,m.hys_meter_code , ( select c.hys_collector_number from hys_meter_collector mc ,hys_collector c where mc.hys_collector_id = c.hys_storage_id and m.hys_storage_id = mc.hys_meter_id ) hys_collector_number  "
             + " from hys_meter m ,hys_data_meter dm ,hys_data d1  where  m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d1.hys_storage_id and d1.hys_data_level = 2 and  d1.hys_data_state = 'public' "
             + " union all  "
             + " select d2.hys_data_pid areaId,(select d.hys_data_name from hys_data d where d.hys_storage_id = d2.hys_data_pid ) areaName ,d2.hys_storage_id beltlineId,d2.hys_data_name beltlineName ,0 workTeamId,''workTeamName ,d2.hys_data_state , "
             + " ''hys_meter_number ,''hys_meter_name ,''hys_meter_code ,''hys_collector_number from hys_data d2 where d2.hys_data_level = 2 and not exists (select hys_storage_id from hys_data where hys_data_pid = d2.hys_storage_id )and d2.hys_data_state is null  "
             + " union all "
             + " select d3.hys_storage_id areaId,d3.hys_data_name areaName ,0 beltlineId,''beltlineName ,0 workTeamId,''workTeamName ,d3.hys_data_state ,''hys_meter_number ,''hys_meter_name ,''hys_meter_code ,''hys_collector_number  "
             + " from  hys_data d3 where d3.hys_data_level = 1 and not exists (select d.hys_storage_id from hys_data d where d.hys_data_pid = d3.hys_storage_id ) "
             + " union all "
             + " select (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid) areaId ,(select dm.hys_data_name  from hys_data dm where dm.hys_storage_id = (select d.hys_data_pid from hys_data d where d.hys_storage_id = d0.hys_data_pid)) areaName ,d0.hys_data_pid beltlineId,  "
             + " (select d.hys_data_name from hys_data d where d.hys_storage_id = d0.hys_data_pid ) beltlineName ,d0.hys_storage_id workTeamId,d0.hys_data_name workTeamName ,d0.hys_data_state, "
             + " ''hys_meter_number,'' hys_meter_name ,''hys_meter_code,'' hys_collector_number from hys_data d0 where d0.hys_data_level = 3 and d0.hys_storage_id not in(select dm.hys_data_id from hys_data_meter dm)  "
             + " )mm where  1 = 1 ";
     
    
     //查询所有区域
     public static final String FIND_ALL_AREA_SQL = ""
             + "select d.hys_storage_id ,d.hys_data_name ,d.hys_data_pid ,d.hys_data_level ,d.hys_data_state from hys_data d where d.hys_data_level = 1 ";
     

        
}
