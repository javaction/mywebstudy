/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

/**
 * 基础信息部分 
 * @author Ussopp.Su
 */
public class HysBasicInfoSqlResource {
    // --- FIND_ALL_BASIC_INFO1 --- FIND_ALL_BASIC_INFO5 暂时不用 --- 先保留begin
    //关联用户、data、设备、采集器的设备查询
    public static final String FIND_ALL_BASIC_INFO1 ="select m.hys_storage_id ,m.hys_meter_number,m.hys_meter_code,m.hys_meter_type ,m.hys_standard_power ,m.hys_manufacturer ,c.hys_collector_number  "
            + "from hys_meter m,hys_collector c, hys_meter_collector mc   ,hys_data d, hys_data_meter dm  ";
    //区域节点时候需要拼装
    public static final String FIND_ALL_BASIC_INFO2 =" ,hys_user u ,hys_users_data ud  ";
    //都需要的拼装
    public static final String FIND_ALL_BASIC_INFO3 ="where m.hys_storage_id = mc.hys_meter_id and c.hys_storage_id = mc.hys_collector_id and m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d.hys_storage_id  "
            + "and d.hys_storage_id = ?dataId  and 1 = 1  ";
    //节点为区域时候，需要关联用户；其余的不需要（在左侧树查询时已过滤）
    public static final String FIND_ALL_BASIC_INFO4 ="and d.hys_storage_id = ud.hys_data_id and ud.hys_user_id = u.hys_storage_id and u.hys_storage_id  = ?userId and u.hys_user_name = ?userName and 1=1  ";
    
    
    //关联用户+节点+采集器+设备 的采集器 查询的查询字段
    public static final String FIND_ALL_BASIC_INFO5 ="select  c.hys_storage_id, c.hys_collector_number, c.hys_collector_type, c.hys_manufacturer, c.hys_remark  "
            + "from hys_meter m,hys_collector c, hys_meter_collector mc   ,hys_data d, hys_data_meter dm  ";
    //--- FIND_ALL_BASIC_INFO1 --- FIND_ALL_BASIC_INFO5 暂时不用 --- 先保留end
    
    //直接查询用户下关联到的设备sql1 //暂时不再使用
    public static final String FIND_ALL_METER_BASIC_INFO1 =""
            + "select hys_storage_id ,hys_meter_number,hys_meter_code,hys_meter_type ,hys_standard_power ,hys_manufacturer , collectorNum  from ( "
            + "select m.hys_storage_id ,m.hys_meter_number,m.hys_meter_code,m.hys_meter_type ,m.hys_standard_power , m.hys_manufacturer , "
            + "(select c1.hys_collector_number from hys_collector c1, hys_meter_collector mc1 where c1.hys_storage_id = mc1.hys_collector_id and mc1.hys_meter_id = m.hys_storage_id ) collectorNum "
            + "from hys_meter m ,hys_data d, hys_data_meter dm  ,hys_user u ,hys_users_data ud  "
            + "where  m.hys_storage_id = dm.hys_meter_id and dm.hys_data_id = d.hys_storage_id  "
            + "and d.hys_storage_id = ud.hys_data_id and ud.hys_user_id = u.hys_storage_id  "
            + "and u.hys_storage_id = ?userId and u.hys_user_name = ?userName "
            + ") t where 1 = 1 ";
    
    //查询所有设备及关联的采集器
    public static final String FIND_ALL_METER_BASIC_INFO = ""
            + "select hys_storage_id ,hys_meter_number,hys_meter_code,hys_meter_type ,hys_standard_power ,hys_manufacturer , collectorNum  from ( "
            + "select m.hys_storage_id ,m.hys_meter_number,m.hys_meter_code,m.hys_meter_type ,m.hys_standard_power , m.hys_manufacturer ,  "
            + "(select c1.hys_collector_number from hys_collector c1, hys_meter_collector mc1 where c1.hys_storage_id = mc1.hys_collector_id and  "
            + " mc1.hys_meter_id = m.hys_storage_id ) collectorNum  from hys_meter m ) t where 1 = 1 ";
            
    
    //直接查询用户下关联到的设备sql2   //统一过滤暂时不使用     
    public static final String FIND_ALL_METER_BASIC_INFO2 =""
            + " where t.hys_meter_number like ?search or  t.hys_meter_code like  ?search  or t.hys_meter_type like ?search  "
            + "or t.hys_standard_power  like  ?search  or t.hys_manufacturer  like ?search   or t.collectorNum like ?search "; 
    
    //查询未分配下去的设备，用于给节点分配设备.sql 
    public static final String FIND_ALL_METER_FOR_SET_SQL = ""
            + "select m.hys_storage_id ,m.hys_meter_number ,m.hys_meter_name ,'no' isSeting "
            + " from hys_meter m where not exists ( select dm.hys_meter_id from hys_data_meter dm  where dm.hys_meter_id = m.hys_storage_id ) "
            + " union all  "
            + " select m.hys_storage_id ,m.hys_meter_number ,m.hys_meter_name ,'true' isSeting  "
            + " from hys_meter m ,hys_data_meter d where m.hys_storage_id = d.hys_meter_id and d.hys_data_id =?workTeamId ";
    
    //查询所有的采集器，用于设备新增和修改时候设置
    public static final String FIND_ALL_COLLECTOR_FOR_SET_SQL = ""
            + "select c.hys_storage_id, c.hys_collector_number, (case when (select count(1) from hys_meter_collector mc where  "
            + "mc.hys_collector_id = c.hys_storage_id and mc.hys_meter_id =?meterId)> 0 then 'yes' else 'no' end) isSet from hys_collector c ";
    
    
    
}
