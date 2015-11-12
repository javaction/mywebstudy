--hys_user
insert into hys_user(hys_storage_id,hys_user_name,hys_user_password)
values((select nextval('hys_seq_storage_id')),'admin','a398fb77df76e6153df57cd65fd0a7c5');


---------联合利华数据库（plant）参数表初始化数据------------------

--------系统设置**数据库备份基础参数设置 begin -----
insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaseBackupPath','c:\\databackup','path','数据库备份文件路径');
 
 insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databacePassword','hydroradio','password','数据库备份用户默认密码');
 
  insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaceAddress','localhost','address','数据库地址');
 
  insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaceName','plant','name','数据库名称');
 
  insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaceUser','postgres','user','数据库备份用户名');
 
  insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databacePortNum','5432','portNum','数据库备份的端口号');
 
insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaseBackupInterval','0 0 2 1 * ?','interval','数据库备份时间间隔');

 insert into hys_parameter(hys_storage_id,hys_parameter_code,hys_parameter_value,hys_parameter_type,hys_remark) 
 values((select nextval('hys_seq_storage_id')),'databaseBackupState','close','state','数据库备份开关设置');
 
 --------系统设置**数据库备份基础参数设置 end -----