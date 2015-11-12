---------------------------------测试数据--------------------------------

/*----------------------------------------采集器(hys_collector)-----------------------------------
insert into hys_collector(hys_storage_id,hys_collector_number,hys_collector_type,hys_manufacturer,hys_remark)
values(1,'000001','MDC','DMCN','《像风一样自由》');
insert into hys_collector(hys_storage_id,hys_collector_number,hys_collector_type,hys_manufacturer,hys_remark)
values(2,'000002','RDC','代傲表计(济南)有限公司','《害怕爱上你》');
insert into hys_collector(hys_storage_id,hys_collector_number,hys_collector_type,hys_manufacturer,hys_remark)
 values(3,'000003','RDC Standard','齐心科技有限公司','温柔的艾伦');
*/




/*----------------------------------------设备表(hys_meter)----------------------------------------
insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1001,'20001234','设备1','heat','SHARKEY 774',100,'上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1002,'20001235','设备2','heat','SHARKEY 774',200,'上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1003,'20001236','设备3','heat','SHARKEY 773',150.9,'上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1004,'20001237','设备4','heat','SHARKEY 774',99.99,'上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1005,'20001238','设备5','heat','SHARKEY 774','99.98','上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1006,'20001239','设备6','heat','SHARKEY 774','150.9','上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1007,'20001240','设备7','heat','SHARKEY 774','150.9','上海肯特');


insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1008,'20001241','公共设备1','heat','SHARKEY 774','150.9','上海肯特');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1009,'20001242','公共设备2','electric','SHARKEY 774','150.9','安科瑞电气');

insert into hys_meter (hys_storage_id,hys_meter_number,hys_meter_name,hys_meter_code,hys_meter_type,hys_standard_power,hys_manufacturer) 
values(1010,'20001243','公共设备3','electric','SHARKEY 774','150.9','安科瑞电气');
*/




/*----------------------------------------采集器与设备关联表(hys_meter_collector)-----------------
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1001,1);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1002,1);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1003,2);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1004,2);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1005,3);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1006,3);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1007,3);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1008,2);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1009,2);
insert into hys_meter_collector (hys_meter_id,hys_collector_id) 
values(1010,3);
*/



/*----------------------------------------数据集表(hys_data)--------------------------------------
--虚拟根节点
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
 values(999,'root',0,0,''); 

--区域
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(1,'1号生产区',999,1,'');

insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(3,'职工宿舍',999,1,'public');

--生产线
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(4,'包装车间',1,2,'');

--叶子节点
--生产组
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(5,'A1.二包机',4,3,'');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(6,'B1.一包机',4,3,'');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(7,'C4.一包机',4,3,'');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(8,'E2.二包机',4,3,'');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(9,'F5.二包机',4,3,'');

--公共区域子节点
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(10,'101室',3,2,'public');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(11,'102室',3,2,'public');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(12,'103室',3,2,'public');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(13,'104室',3,2,'public');
insert into hys_data(hys_storage_id,hys_data_name,hys_data_pid,hys_data_level,hys_data_state) 
values(14,'105室',3,2,'public');

*/



/*----------------------------------------设备与数据集关联表(hys_data_meter)----------------------

--1.设备关联区域  
--（1001~1005 设备 ）--生产区域
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1001,1);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1002,1);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1003,1);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1004,1);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1005,1);

-- （1006~1010 设备）--公共区域 
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1006,3);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1007,3);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1008,3);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1009,3);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1010,3);

--2.设备关联生产线 （1001~1005 设备）
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1001,4);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1002,4);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1003,4);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1004,4);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1005,4);

--3.设备关联叶子节点 
-- 关联生产组 （1001~1005 设备）
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1001,5);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1002,6);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1003,7);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1004,8);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1005,9);

--关联公共区域叶子节点（1006~1010 设备）
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1006,10);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1007,11);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1008,12);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1009,13);
insert into hys_data_meter(hys_meter_id,hys_data_id) values(1010,14);
*/



/*----------------------------------------角色表(hys_group)-----------------------------------------
insert into hys_group(hys_storage_id,hys_group_name,hys_desc)
values(1,'管理员','测试角色');
*/



/*----------------------------------------用户表(hys_user)------------------------------------------
insert into hys_user(hys_storage_id,hys_user_name,hys_user_password,hys_group_id,hys_desc) 
values(10001,'admin',md5('radio'),1,'desc...');
*/




/*---------------------------------------用户数据集关联表(hys_users_data)--------------------------
--用户只关联区域
insert into hys_users_data(hys_user_id,hys_data_id) values(10001,1) ;
insert into hys_users_data(hys_user_id,hys_data_id) values(10001,2) ;
insert into hys_users_data(hys_user_id,hys_data_id) values(10001,3) ;
--关联虚拟根节点
insert into hys_users_data(hys_user_id,hys_data_id) values(10001,999) ; 








*/






















