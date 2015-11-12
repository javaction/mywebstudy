
------------1.�û���---------
-- Table: hys_user

-- DROP TABLE hys_user;

CREATE TABLE hys_user
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_user_name character varying(128) NOT NULL, -- �û���
  hys_user_password character varying(128) NOT NULL, -- �û�����
  hys_group_id bigint, -- �û�������ɫ��id
  hys_desc character varying(128), -- �û���ϸ����
  CONSTRAINT hys_users_pk PRIMARY KEY (hys_storage_id),--����
  CONSTRAINT hys_user_unique_index UNIQUE (hys_user_name)--ΨһԼ��
)
--
WITH (
  OIDS=FALSE
);
--assign owner
ALTER TABLE hys_user OWNER TO postgres;
--remark
COMMENT ON TABLE hys_user IS '�û���';
COMMENT ON COLUMN hys_user.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_user.hys_user_name IS '�û���';
COMMENT ON COLUMN hys_user.hys_user_password IS '�û�����';
COMMENT ON COLUMN hys_user.hys_group_id IS '�û�������ɫ��id';
COMMENT ON COLUMN hys_user.hys_desc IS '�û���ϸ����';





---------------2.��ɫ��---------------------
-- Table: hys_group

-- DROP TABLE hys_group;

CREATE TABLE hys_group
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_group_name character varying(128) NOT NULL, -- ��ɫ����
  hys_desc character varying(128), -- ��ɫ��ϸ����
  CONSTRAINT hys_group_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_group
  OWNER TO postgres;
COMMENT ON TABLE hys_group
  IS '��ɫ��';
COMMENT ON COLUMN hys_group.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_group.hys_group_name IS '��ɫ����';
COMMENT ON COLUMN hys_group.hys_desc IS '��ɫ��ϸ����';


-----------------3.Ȩ�ޱ�----------------------
-- Table: hys_right

-- DROP TABLE hys_right;

CREATE TABLE hys_right
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_right_name character varying(128) NOT NULL, -- Ȩ������
  hys_right_code character varying(128) NOT NULL, -- Ȩ����
  hys_right_pid bigint NOT NULL, -- Ȩ�����ֶ�Ӧ�ĸ�id��ָ�������ĸ�ģ��
  CONSTRAINT hys_rights_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_right
  OWNER TO postgres;
COMMENT ON TABLE hys_right
  IS 'Ȩ�ޱ�';
COMMENT ON COLUMN hys_right.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_right.hys_right_name IS 'Ȩ������';
COMMENT ON COLUMN hys_right.hys_right_code IS 'Ȩ����';
COMMENT ON COLUMN hys_right.hys_right_pid IS 'Ȩ�����ж�Ӧ�ĸ�id��ָ�������ĸ�ģ��';



------------------4.��ɫȨ�޹�����-------------------
-- Table: hys_group_right

-- DROP TABLE hys_group_right;

CREATE TABLE hys_group_right
(
  hys_group_id bigint NOT NULL, -- ��ɫid
  hys_right_id bigint NOT NULL, -- Ȩ��id
  CONSTRAINT hys_group_right_unique_index UNIQUE (hys_group_id, hys_right_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_group_right
  OWNER TO postgres;
COMMENT ON TABLE hys_group_right
  IS '��ɫȨ�޹�����';
COMMENT ON COLUMN hys_group_right.hys_group_id IS '��ɫid';
COMMENT ON COLUMN hys_group_right.hys_right_id IS 'Ȩ��id';


-----------------5.���ݼ���--------------
-- Table: hys_data

-- DROP TABLE hys_data;

CREATE TABLE hys_data
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_data_name character varying(128) NOT NULL, -- ���ݽڵ�����
  hys_data_pid bigint NOT NULL, -- ���ݼ��ڵ��Ӧ�ĸ��ڵ�
  hys_data_level smallint NOT NULL, -- �ڵ�ȼ�
  hys_data_state character varying(128), -- �������ֹ������򡣹�������������������� -- public ; �����������������ߣ��飩-- null
  CONSTRAINT hys_data1_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_data
  OWNER TO postgres;
COMMENT ON TABLE hys_data
  IS '���ݼ���';
COMMENT ON COLUMN hys_data.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_data.hys_data_name IS '���ݽڵ�����';
COMMENT ON COLUMN hys_data.hys_data_pid IS '���ݼ��ڵ��Ӧ�ĸ��ڵ�';
COMMENT ON COLUMN hys_data.hys_data_level IS '�ڵ�ȼ�';
COMMENT ON COLUMN hys_data.hys_data_level IS '�������ֹ������򡣹�������������������� -- public ; �����������������ߣ��飩-- null';


----------6.�û����ݼ�������--------------
-- Table: hys_users_data

-- DROP TABLE hys_users_data;

CREATE TABLE hys_users_data
(
  hys_user_id bigint NOT NULL, -- �û�id
  hys_data_id bigint NOT NULL, -- ���ݼ�id
  CONSTRAINT hys_users_data_unique_index UNIQUE (hys_user_id, hys_data_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_users_data
  OWNER TO postgres;
COMMENT ON TABLE hys_users_data
  IS '�û����ݼ�������';
COMMENT ON COLUMN hys_users_data.hys_user_id IS '�û�id';
COMMENT ON COLUMN hys_users_data.hys_data_id IS '���ݼ�id';


------------------7.�豸�����ݼ�������-------------------
-- Table: hys_data_meter

-- DROP TABLE hys_data_meter;

CREATE TABLE hys_data_meter
(
  hys_meter_id bigint NOT NULL, -- �豸��Ӧ��id
  hys_data_id bigint NOT NULL, --  data��Ӧ��id
  CONSTRAINT hys_data_meter_unique_index UNIQUE (hys_meter_id, hys_data_id)
)
-- row object tag
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_data_meter
  OWNER TO postgres;
COMMENT ON TABLE hys_data_meter
  IS '�豸�����ݼ�������';
COMMENT ON COLUMN hys_data_meter.hys_meter_id IS '�豸��Ӧ��id';
COMMENT ON COLUMN hys_data_meter.hys_data_id IS 'data��Ӧ��id';


--------------8.�豸��-------------------
-- Table: hys_meter

-- DROP TABLE hys_meter;

CREATE TABLE hys_meter
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_meter_number character varying(128) NOT NULL,--�豸���
  hys_meter_name  character varying(128) NOT NULL,--�豸����
  hys_meter_code character varying(128) NOT NULL, -- �豸���ͣ����ֵ硢�ȱ� ,electric --��� ��heat --�ȱ�
  hys_meter_type character varying(128) NOT NULL, -- �豸�����ͺţ���Ӧ���Ǹ�����ľ����ͺš���Щ���Ͱ����������ֵ���С�
  hys_standard_power float(3) NOT NULL, -- �豸��׼����
  hys_manufacturer  character varying(128) NOT NULL,--����
  CONSTRAINT hys_meter_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_meter_unique_index UNIQUE (hys_meter_number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter
  OWNER TO postgres;
COMMENT ON TABLE hys_meter
  IS '�豸��';
COMMENT ON COLUMN hys_meter.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_meter.hys_meter_number IS '�豸���';
COMMENT ON COLUMN hys_meter.hys_meter_name IS '�豸����';
COMMENT ON COLUMN hys_meter.hys_meter_code IS '�豸���ͣ����ֵ硢�ȱ� ,electric --��� ��heat --�ȱ�';
COMMENT ON COLUMN hys_meter.hys_meter_type IS '�豸�����ͺţ���Ӧ���Ǹ�����ľ����ͺš���Щ���Ͱ����������ֵ���С�';
COMMENT ON COLUMN hys_meter.hys_standard_power IS '�豸��׼����';
COMMENT ON COLUMN hys_meter.hys_manufacturer IS '����';


------------------9.�ɼ������豸������-------------------
-- Table hys_meter_collector: 

-- DROP TABLE hys_meter_collector;

CREATE TABLE hys_meter_collector
(
  hys_meter_id bigint NOT NULL, -- �豸id
  hys_collector_id bigint NOT NULL, -- �ɼ���id
  CONSTRAINT hys_meter_collector_unique_index UNIQUE (hys_meter_id, hys_collector_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter_collector
  OWNER TO postgres;
COMMENT ON TABLE hys_meter_collector
  IS '�ɼ������豸������';
COMMENT ON COLUMN hys_meter_collector.hys_meter_id IS '�豸id';
COMMENT ON COLUMN hys_meter_collector.hys_collector_id IS '�ɼ���id';


--------------10.�ɼ�����------------------- 
-- Table: hys_collector

-- DROP TABLE hys_collector;

CREATE TABLE hys_collector
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_collector_number character varying(128) NOT NULL,--�ɼ������
  hys_collector_type  character varying(128) NOT NULL, --�ɼ����ͺ�
  hys_manufacturer  character varying(128) NOT NULL,--����
  hys_remark   character varying(128), --��ע
  CONSTRAINT hys_collector_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_collector_unique_index UNIQUE (hys_collector_number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_collector
  OWNER TO postgres;
COMMENT ON TABLE hys_collector
  IS '�ɼ�����';
COMMENT ON COLUMN hys_collector.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_collector.hys_collector_number IS '�豸���';
COMMENT ON COLUMN hys_collector.hys_collector_type IS '�ɼ����ͺ�';
COMMENT ON COLUMN hys_collector.hys_manufacturer IS '����';
COMMENT ON COLUMN hys_collector.hys_remark IS '��ע��Ϣ';

-------------11.�豸�����ֵ��---------------------
-- Table: hys_meter_type

-- DROP TABLE hys_meter_type;

CREATE TABLE hys_meter_type
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_meter_code character varying(128) NOT NULL, -- �豸���ͣ���Ӧ�豸���е��豸����
  hys_meter_type character varying(128) NOT NULL, -- ���ڸ��豸���͵ľ�����������
  CONSTRAINT hys_meter_type_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_meter_type
  OWNER TO postgres;
COMMENT ON TABLE hys_meter_type
  IS '�豸�����ֵ��';
COMMENT ON COLUMN hys_meter_type.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_meter_type.hys_meter_code IS '�豸���ͣ���Ӧ�豸���е��豸����';
COMMENT ON COLUMN hys_meter_type.hys_meter_type IS '���ڸ��豸���͵ľ�����������';


---------------12.������---------------
-- Table: hys_parameter

-- DROP TABLE hys_parameter;

CREATE TABLE hys_parameter
(
  hys_storage_id bigint NOT NULL , -- ����id  --DEFAULT nextval('hys_seq_storage_id'::regclass)
  hys_parameter_code character varying(128) NOT NULL, -- ������
  hys_parameter_value character varying(128) NOT NULL, -- ����ֵ
  hys_parameter_type character varying(128) NOT NULL, -- ��������
  hys_remark character varying(128), -- ������ע
  CONSTRAINT hys_parameter_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_parameter
  OWNER TO postgres;
COMMENT ON TABLE hys_parameter
  IS '������';
COMMENT ON COLUMN hys_parameter.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_parameter.hys_parameter_code IS '������';
COMMENT ON COLUMN hys_parameter.hys_parameter_value IS '����ֵ';
COMMENT ON COLUMN hys_parameter.hys_parameter_type IS '��������';
COMMENT ON COLUMN hys_parameter.hys_remark IS '������ע';

---------------------13.һ�������¼��line��------------------------
-- DROP TABLE hys_value_line;

CREATE TABLE hys_value_line
(
  hys_storage_id bigint NOT NULL, -- �����¼��Ӧ��lineId
  hys_meter_number character varying(128) NOT NULL, -- �豸��
  hys_time_stamp timestamp with time zone NOT NULL, -- ����ʱ��
  CONSTRAINT hys_value_line_pk PRIMARY KEY (hys_storage_id),
  CONSTRAINT hys_value_line_index1 UNIQUE (hys_storage_id, hys_meter_number, hys_time_stamp)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_line
  OWNER TO postgres;
COMMENT ON TABLE hys_value_line
  IS 'һ�������¼';
COMMENT ON COLUMN hys_value_line.hys_storage_id IS '�����¼��Ӧ��lineId';
COMMENT ON COLUMN hys_value_line.hys_meter_number IS '�豸��';
COMMENT ON COLUMN hys_value_line.hys_time_stamp IS '����ʱ��';

---------------------14.�������ݱ�ԭʼ��------------------------
-- Table: hys_value

-- DROP TABLE hys_value;

CREATE TABLE hys_value
(
  hys_storage_id bigint NOT NULL, -- ����id
  hys_line_id bigint NOT NULL, -- ��Ӧhys_value_line.hys_storage_id
  hys_semantic_id character varying(128) NOT NULL, -- �������ͣ��磺����-energy����ѹ-voltage������-current������-power�������-peak_energy_1�������-peak_energy_2��ƽ����-valley_energy_1���ȵ���-valley_energy_2������-alarm ��
  hys_org_value numeric(20,20) NOT NULL, -- ��������
  hys_time_stamp timestamp with time zone NOT NULL, -- ����ʱ��
  CONSTRAINT hys_value_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value
  OWNER TO postgres;
COMMENT ON TABLE hys_value
  IS '�������ݱ�ԭʼ��';
COMMENT ON COLUMN hys_value.hys_storage_id IS '����id';
COMMENT ON COLUMN hys_value.hys_line_id IS '��Ӧhys_value_line.hys_storage_id';
COMMENT ON COLUMN hys_value.hys_semantic_id IS '�������ͣ��磺����-energy����ѹ-voltage������-current������-power�������-peak_energy_1�������-peak_energy_2��ƽ����-valley_energy_1���ȵ���-valley_energy_2������-alarm ��';
COMMENT ON COLUMN hys_value.hys_org_value IS '��������';
COMMENT ON COLUMN hys_value.hys_time_stamp IS '����ʱ��';



-----------------------15.�԰��Ϊͳ�Ƶ���ʱ��---------------------------------
-- Table: hys_value_per_shift

-- DROP TABLE hys_value_per_shift;

CREATE TABLE hys_value_per_shift
(
  hys_storage_id bigint NOT NULL, -- ����
  hys_meter_number character varying(128) NOT NULL, -- �豸���
  hys_begin_time timestamp with time zone, -- ����ʼʱ��
  hys_end_time timestamp with time zone, -- �������ʱ��
  hys_actual_consumption numeric(20,20), -- ʵ������
  hys_over_consumption numeric(20,20), -- ����
  hys_standard_consumption numeric(20,20), -- ��׼����
  hys_work_time numeric(20,20), -- ����ʱ��ʱ��
  CONSTRAINT hys_value_per_shift_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_per_shift
  OWNER TO postgres;
COMMENT ON TABLE hys_value_per_shift
  IS '�԰��Ϊͳ�Ƶ���ʱ��';
COMMENT ON COLUMN hys_value_per_shift.hys_storage_id IS '����';
COMMENT ON COLUMN hys_value_per_shift.hys_meter_number IS '�豸��';
COMMENT ON COLUMN hys_value_per_shift.hys_begin_time IS '����ʼʱ��';
COMMENT ON COLUMN hys_value_per_shift.hys_end_time IS '�������ʱ��';
COMMENT ON COLUMN hys_value_per_shift.hys_actual_consumption IS 'ʵ������';
COMMENT ON COLUMN hys_value_per_shift.hys_over_consumption IS '����';
COMMENT ON COLUMN hys_value_per_shift.hys_standard_consumption IS '��׼����';
COMMENT ON COLUMN hys_value_per_shift.hys_work_time IS '����ʱ��ʱ��';

----------------16.����Ϊͳ�Ƶ���ʱ��------------------------
-- Table: hys_value_per_day

-- DROP TABLE hys_value_per_day;

CREATE TABLE hys_value_per_day
(
  hys_storage_id bigint NOT NULL, -- ����
  hys_meter_number character varying(128) NOT NULL, -- �豸���
  hys_begin_time timestamp with time zone, -- ����ʼʱ��
  hys_end_time timestamp with time zone, -- �������ʱ��
  hys_actual_consumption numeric(20,20), -- ʵ������
  hys_over_consumption numeric(20,20), -- ����
  hys_standard_consumption numeric(20,20), -- ��׼����
  hys_work_time numeric(20,20), -- ����ʱ��ʱ��
  CONSTRAINT hys_value_per_day_pk PRIMARY KEY (hys_storage_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hys_value_per_day
  OWNER TO postgres;
COMMENT ON TABLE hys_value_per_day
  IS '����Ϊͳ�Ƶ���ʱ��';
COMMENT ON COLUMN hys_value_per_day.hys_storage_id IS '����';
COMMENT ON COLUMN hys_value_per_day.hys_meter_number IS '�豸���';
COMMENT ON COLUMN hys_value_per_day.hys_begin_time IS '����ʼʱ��';
COMMENT ON COLUMN hys_value_per_day.hys_end_time IS '�������ʱ��';
COMMENT ON COLUMN hys_value_per_day.hys_actual_consumption IS 'ʵ������';
COMMENT ON COLUMN hys_value_per_day.hys_over_consumption IS '����';
COMMENT ON COLUMN hys_value_per_day.hys_standard_consumption IS '��׼����';
COMMENT ON COLUMN hys_value_per_day.hys_work_time IS '����ʱ��ʱ��';


