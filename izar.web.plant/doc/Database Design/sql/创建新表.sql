
------------1.用户表---------
-- Table: hys_user

-- DROP TABLE hys_user;

CREATE TABLE hys_user
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_user_name character varying(128) NOT NULL, -- 用户名
  hys_user_password character varying(128) NOT NULL, -- 用户密码
  hys_group_id bigint, -- 用户所属角色的id
  hys_desc character varying(128), -- 用户详细描述
  CONSTRAINT hys_users_pk PRIMARY KEY (hys_storage_id),--主键
  CONSTRAINT hys_user_unique_index UNIQUE (hys_user_name)--唯一约束
)
--
WITH (
  OIDS=FALSE
);
--assign owner
ALTER TABLE hys_user OWNER TO postgres;
--remark
COMMENT ON TABLE hys_user IS '用户表';
COMMENT ON COLUMN hys_user.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_user.hys_user_name IS '用户名';
COMMENT ON COLUMN hys_user.hys_user_password IS '用户密码';
COMMENT ON COLUMN hys_user.hys_group_id IS '用户所属角色的id';
COMMENT ON COLUMN hys_user.hys_desc IS '用户详细描述';





---------------2.角色表---------------------
-- Table: hys_group

-- DROP TABLE hys_group;

CREATE TABLE hys_group
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_group_name character varying(128) NOT NULL, -- 角色名称
  hys_desc character varying(128), -- 角色详细描述
  CONSTRAINT hys_group_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_group
  OWNER TO postgres;
COMMENT ON TABLE hys_group
  IS '角色表';
COMMENT ON COLUMN hys_group.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_group.hys_group_name IS '角色名称';
COMMENT ON COLUMN hys_group.hys_desc IS '角色详细描述';


-----------------3.权限表----------------------
-- Table: hys_right

-- DROP TABLE hys_right;

CREATE TABLE hys_right
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_right_name character varying(128) NOT NULL, -- 权限名称
  hys_right_code character varying(128) NOT NULL, -- 权限码
  hys_right_pid bigint NOT NULL, -- 权限树种对应的父id，指明属于哪个模块
  CONSTRAINT hys_rights_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_right
  OWNER TO postgres;
COMMENT ON TABLE hys_right
  IS '权限表';
COMMENT ON COLUMN hys_right.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_right.hys_right_name IS '权限名称';
COMMENT ON COLUMN hys_right.hys_right_code IS '权限码';
COMMENT ON COLUMN hys_right.hys_right_pid IS '权限树中对应的父id，指明属于哪个模块';



------------------4.角色权限关联表-------------------
-- Table: hys_group_right

-- DROP TABLE hys_group_right;

CREATE TABLE hys_group_right
(
  hys_group_id bigint NOT NULL, -- 角色id
  hys_right_id bigint NOT NULL, -- 权限id
  CONSTRAINT hys_group_right_unique_index UNIQUE (hys_group_id, hys_right_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_group_right
  OWNER TO postgres;
COMMENT ON TABLE hys_group_right
  IS '角色权限关联表';
COMMENT ON COLUMN hys_group_right.hys_group_id IS '角色id';
COMMENT ON COLUMN hys_group_right.hys_right_id IS '权限id';


-----------------5.数据集表--------------
-- Table: hys_data

-- DROP TABLE hys_data;

CREATE TABLE hys_data
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_data_name character varying(128) NOT NULL, -- 数据节点名称
  hys_data_pid bigint NOT NULL, -- 数据集节点对应的父节点
  hys_data_level smallint NOT NULL, -- 节点等级
  hys_data_state character varying(128), -- 用于区分公共区域。公共区域包括其下生产组 -- public ; 生产区域及其下生产线（组）-- null
  CONSTRAINT hys_data1_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_data
  OWNER TO postgres;
COMMENT ON TABLE hys_data
  IS '数据集表';
COMMENT ON COLUMN hys_data.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_data.hys_data_name IS '数据节点名称';
COMMENT ON COLUMN hys_data.hys_data_pid IS '数据集节点对应的父节点';
COMMENT ON COLUMN hys_data.hys_data_level IS '节点等级';
COMMENT ON COLUMN hys_data.hys_data_level IS '用于区分公共区域。公共区域包括其下生产组 -- public ; 生产区域及其下生产线（组）-- null';


----------6.用户数据集关联表--------------
-- Table: hys_users_data

-- DROP TABLE hys_users_data;

CREATE TABLE hys_users_data
(
  hys_user_id bigint NOT NULL, -- 用户id
  hys_data_id bigint NOT NULL, -- 数据集id
  CONSTRAINT hys_users_data_unique_index UNIQUE (hys_user_id, hys_data_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_users_data
  OWNER TO postgres;
COMMENT ON TABLE hys_users_data
  IS '用户数据集关联表';
COMMENT ON COLUMN hys_users_data.hys_user_id IS '用户id';
COMMENT ON COLUMN hys_users_data.hys_data_id IS '数据集id';


------------------7.设备与数据集关联表-------------------
-- Table: hys_data_meter

-- DROP TABLE hys_data_meter;

CREATE TABLE hys_data_meter
(
  hys_meter_id bigint NOT NULL, -- 设备对应的id
  hys_data_id bigint NOT NULL, --  data对应的id
  CONSTRAINT hys_data_meter_unique_index UNIQUE (hys_meter_id, hys_data_id)
)
-- row object tag
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_data_meter
  OWNER TO postgres;
COMMENT ON TABLE hys_data_meter
  IS '设备与数据集关联表';
COMMENT ON COLUMN hys_data_meter.hys_meter_id IS '设备对应的id';
COMMENT ON COLUMN hys_data_meter.hys_data_id IS 'data对应的id';


--------------8.设备表-------------------
-- Table: hys_meter

-- DROP TABLE hys_meter;

CREATE TABLE hys_meter
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_meter_number character varying(128) NOT NULL,--设备编号
  hys_meter_name  character varying(128) NOT NULL,--设备名称
  hys_meter_code character varying(128) NOT NULL, -- 设备类型，区分电、热表 ,electric --电表 ；heat --热表
  hys_meter_type character varying(128) NOT NULL, -- 设备具体型号，对应的是各个表的具体型号。这些类型包含在类型字典表中。
  hys_standard_power float(3) NOT NULL, -- 设备标准功率
  hys_manufacturer  character varying(128) NOT NULL,--厂商
  CONSTRAINT hys_meter_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_meter_unique_index UNIQUE (hys_meter_number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter
  OWNER TO postgres;
COMMENT ON TABLE hys_meter
  IS '设备表';
COMMENT ON COLUMN hys_meter.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_meter.hys_meter_number IS '设备编号';
COMMENT ON COLUMN hys_meter.hys_meter_name IS '设备名称';
COMMENT ON COLUMN hys_meter.hys_meter_code IS '设备类型，区分电、热表 ,electric --电表 ；heat --热表';
COMMENT ON COLUMN hys_meter.hys_meter_type IS '设备具体型号，对应的是各个表的具体型号。这些类型包含在类型字典表中。';
COMMENT ON COLUMN hys_meter.hys_standard_power IS '设备标准功率';
COMMENT ON COLUMN hys_meter.hys_manufacturer IS '厂商';


------------------9.采集器与设备关联表-------------------
-- Table hys_meter_collector: 

-- DROP TABLE hys_meter_collector;

CREATE TABLE hys_meter_collector
(
  hys_meter_id bigint NOT NULL, -- 设备id
  hys_collector_id bigint NOT NULL, -- 采集器id
  CONSTRAINT hys_meter_collector_unique_index UNIQUE (hys_meter_id, hys_collector_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter_collector
  OWNER TO postgres;
COMMENT ON TABLE hys_meter_collector
  IS '采集器与设备关联表';
COMMENT ON COLUMN hys_meter_collector.hys_meter_id IS '设备id';
COMMENT ON COLUMN hys_meter_collector.hys_collector_id IS '采集器id';


--------------10.采集器表------------------- 
-- Table: hys_collector

-- DROP TABLE hys_collector;

CREATE TABLE hys_collector
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_collector_number character varying(128) NOT NULL,--采集器编号
  hys_collector_type  character varying(128) NOT NULL, --采集器型号
  hys_manufacturer  character varying(128) NOT NULL,--厂商
  hys_remark   character varying(128), --备注
  CONSTRAINT hys_collector_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_collector_unique_index UNIQUE (hys_collector_number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_collector
  OWNER TO postgres;
COMMENT ON TABLE hys_collector
  IS '采集器表';
COMMENT ON COLUMN hys_collector.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_collector.hys_collector_number IS '设备编号';
COMMENT ON COLUMN hys_collector.hys_collector_type IS '采集器型号';
COMMENT ON COLUMN hys_collector.hys_manufacturer IS '厂商';
COMMENT ON COLUMN hys_collector.hys_remark IS '备注信息';

-------------11.设备类型字典表---------------------
-- Table: hys_meter_type

-- DROP TABLE hys_meter_type;

CREATE TABLE hys_meter_type
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_meter_code character varying(128) NOT NULL, -- 设备类型，对应设备表中的设备类型
  hys_meter_type character varying(128) NOT NULL, -- 对于各设备类型的具体类型区分
  CONSTRAINT hys_meter_type_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter_type
  OWNER TO postgres;
COMMENT ON TABLE hys_meter_type
  IS '设备类型字典表';
COMMENT ON COLUMN hys_meter_type.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_meter_type.hys_meter_code IS '设备类型，对应设备表中的设备类型';
COMMENT ON COLUMN hys_meter_type.hys_meter_type IS '对于各设备类型的具体类型区分';


---------------12.参数表---------------
-- Table: hys_parameter

-- DROP TABLE hys_parameter;

CREATE TABLE hys_parameter
(
  hys_storage_id bigint NOT NULL , -- 主键id  --DEFAULT nextval('hys_seq_storage_id'::regclass)
  hys_parameter_code character varying(128) NOT NULL, -- 参数码
  hys_parameter_value character varying(128) NOT NULL, -- 参数值
  hys_parameter_type character varying(128) NOT NULL, -- 参数类型
  hys_remark character varying(128), -- 参数备注
  CONSTRAINT hys_parameter_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_parameter
  OWNER TO postgres;
COMMENT ON TABLE hys_parameter
  IS '参数表';
COMMENT ON COLUMN hys_parameter.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_parameter.hys_parameter_code IS '参数码';
COMMENT ON COLUMN hys_parameter.hys_parameter_value IS '参数值';
COMMENT ON COLUMN hys_parameter.hys_parameter_type IS '参数类型';
COMMENT ON COLUMN hys_parameter.hys_remark IS '参数备注';

---------------------13.一条抄表记录（line）------------------------
-- DROP TABLE hys_value_line;

CREATE TABLE hys_value_line
(
  hys_storage_id bigint NOT NULL, -- 抄表记录对应的lineId
  hys_meter_number character varying(128) NOT NULL, -- 设备号
  hys_time_stamp timestamp with time zone NOT NULL, -- 抄表时间
  CONSTRAINT hys_value_line_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_value_line_index1 UNIQUE (hys_storage_id, hys_meter_number, hys_time_stamp)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_line
  OWNER TO postgres;
COMMENT ON TABLE hys_value_line
  IS '一条抄表记录';
COMMENT ON COLUMN hys_value_line.hys_storage_id IS '抄表记录对应的lineId';
COMMENT ON COLUMN hys_value_line.hys_meter_number IS '设备号';
COMMENT ON COLUMN hys_value_line.hys_time_stamp IS '抄表时间';

---------------------14.抄表数据表（原始表）------------------------
-- Table: hys_value

-- DROP TABLE hys_value;

CREATE TABLE hys_value
(
  hys_storage_id bigint NOT NULL, -- 主键id
  hys_line_id bigint NOT NULL, -- 对应hys_value_line.hys_storage_id
  hys_semantic_id character varying(128) NOT NULL, -- 数据类型，如：电量-energy，电压-voltage，电流-current，功率-power，尖电量-peak_energy_1，峰电量-peak_energy_2，平电量-valley_energy_1，谷电量-valley_energy_2，报警-alarm 等
  hys_org_value numeric(20,20) NOT NULL, -- 抄表数据
  hys_time_stamp timestamp with time zone NOT NULL, -- 抄表时间
  CONSTRAINT hys_value_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value
  OWNER TO postgres;
COMMENT ON TABLE hys_value
  IS '抄表数据表（原始表）';
COMMENT ON COLUMN hys_value.hys_storage_id IS '主键id';
COMMENT ON COLUMN hys_value.hys_line_id IS '对应hys_value_line.hys_storage_id';
COMMENT ON COLUMN hys_value.hys_semantic_id IS '数据类型，如：电量-energy，电压-voltage，电流-current，功率-power，尖电量-peak_energy_1，峰电量-peak_energy_2，平电量-valley_energy_1，谷电量-valley_energy_2，报警-alarm 等';
COMMENT ON COLUMN hys_value.hys_org_value IS '抄表数据';
COMMENT ON COLUMN hys_value.hys_time_stamp IS '抄表时间';



-----------------------15.以班次为统计的临时表---------------------------------
-- Table: hys_value_per_shift

-- DROP TABLE hys_value_per_shift;

CREATE TABLE hys_value_per_shift
(
  hys_storage_id bigint NOT NULL, -- 主键
  hys_meter_number character varying(128) NOT NULL, -- 设备编号
  hys_begin_time timestamp with time zone, -- 抄表开始时间
  hys_end_time timestamp with time zone, -- 抄表结束时间
  hys_actual_consumption numeric(20,20), -- 实际消耗
  hys_over_consumption numeric(20,20), -- 超耗
  hys_standard_consumption numeric(20,20), -- 标准消耗
  hys_work_time numeric(20,20), -- 工作时间时长
  CONSTRAINT hys_value_per_shift_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_per_shift
  OWNER TO postgres;
COMMENT ON TABLE hys_value_per_shift
  IS '以班次为统计的临时表';
COMMENT ON COLUMN hys_value_per_shift.hys_storage_id IS '主键';
COMMENT ON COLUMN hys_value_per_shift.hys_meter_number IS '设备号';
COMMENT ON COLUMN hys_value_per_shift.hys_begin_time IS '抄表开始时间';
COMMENT ON COLUMN hys_value_per_shift.hys_end_time IS '抄表结束时间';
COMMENT ON COLUMN hys_value_per_shift.hys_actual_consumption IS '实际消耗';
COMMENT ON COLUMN hys_value_per_shift.hys_over_consumption IS '超耗';
COMMENT ON COLUMN hys_value_per_shift.hys_standard_consumption IS '标准消耗';
COMMENT ON COLUMN hys_value_per_shift.hys_work_time IS '工作时间时长';

----------------16.以日为统计的临时表------------------------
-- Table: hys_value_per_day

-- DROP TABLE hys_value_per_day;

CREATE TABLE hys_value_per_day
(
  hys_storage_id bigint NOT NULL, -- 主键
  hys_meter_number character varying(128) NOT NULL, -- 设备编号
  hys_begin_time timestamp with time zone, -- 抄表开始时间
  hys_end_time timestamp with time zone, -- 抄表结束时间
  hys_actual_consumption numeric(20,20), -- 实际消耗
  hys_over_consumption numeric(20,20), -- 超耗
  hys_standard_consumption numeric(20,20), -- 标准消耗
  hys_work_time numeric(20,20), -- 工作时间时长
  CONSTRAINT hys_value_per_day_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_per_day
  OWNER TO postgres;
COMMENT ON TABLE hys_value_per_day
  IS '以日为统计的临时表';
COMMENT ON COLUMN hys_value_per_day.hys_storage_id IS '主键';
COMMENT ON COLUMN hys_value_per_day.hys_meter_number IS '设备编号';
COMMENT ON COLUMN hys_value_per_day.hys_begin_time IS '抄表开始时间';
COMMENT ON COLUMN hys_value_per_day.hys_end_time IS '抄表结束时间';
COMMENT ON COLUMN hys_value_per_day.hys_actual_consumption IS '实际消耗';
COMMENT ON COLUMN hys_value_per_day.hys_over_consumption IS '超耗';
COMMENT ON COLUMN hys_value_per_day.hys_standard_consumption IS '标准消耗';
COMMENT ON COLUMN hys_value_per_day.hys_work_time IS '工作时间时长';


