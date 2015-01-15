drop table sys_department cascade constraints;

drop table sys_dic cascade constraints;

drop table sys_log cascade constraints;

drop table sys_login_log cascade constraints;

drop table sys_resource cascade constraints;

drop table sys_role cascade constraints;

drop table sys_role_resource cascade constraints;

drop table sys_user cascade constraints;

drop table sys_user_role cascade constraints;

drop table tbl_application cascade constraints;

drop table tbl_service cascade constraints;

drop sequence s_sys_department;

drop sequence s_sys_dic;

drop sequence s_sys_log;

drop sequence s_sys_login_log;

drop sequence s_sys_resource;

drop sequence s_sys_role;

drop sequence s_sys_user;

drop sequence s_tbl_application;

drop sequence s_tbl_service;

create sequence s_sys_department;

create sequence s_sys_dic;

create sequence s_sys_log;

create sequence s_sys_login_log;

create sequence s_sys_resource;

create sequence s_sys_role;

create sequence s_sys_user;

create sequence s_tbl_application;

create sequence s_tbl_service;

create table sys_department  (
   id                   number(32)                      not null,
   dept_code            varchar2(50)                    not null,
   dept_name            varchar2(100)                   not null,
   dept_spell           varchar2(100),
   dept_level           number(2),
   other_name           varchar2(100),
   simple_name          varchar2(100),
   is_formal            number(1),
   enable_date          date,
   is_enable            number(1),
   disable_date         date,
   is_stopped           number(1),
   original_code        varchar2(200),
   original_stopped_date date,
   constraint pk_sys_department primary key (id)
);

comment on table sys_department is
'��λ��Ϣ';

comment on column sys_department.id is
'�ؼ���';

comment on column sys_department.dept_code is
'��λ����';

comment on column sys_department.dept_name is
'��λ����';

comment on column sys_department.dept_spell is
'��λƴ��';

comment on column sys_department.dept_level is
'��λ���';

comment on column sys_department.other_name is
'��������';

comment on column sys_department.simple_name is
'���';

comment on column sys_department.is_formal is
'�Ƿ���ʽ������0����1���ǣ�';

comment on column sys_department.enable_date is
'��������';

comment on column sys_department.is_enable is
'�Ƿ����ã�0����1���ǣ�';

comment on column sys_department.disable_date is
'ͣ������';

comment on column sys_department.is_stopped is
'�Ƿ�ͣ��(0����1����)';

comment on column sys_department.original_code is
'ԭ��������';

comment on column sys_department.original_stopped_date is
'ԭ��������ͣ������';

create table sys_dic  (
   id                   number(32)                      not null,
   dic_type             varchar2(50)                    not null,
   dic_key              varchar2(50)                    not null,
   dic_value            varchar2(100)                   not null,
   sort                 number(6)                       not null,
   type_desc            varchar2(200)                   not null,
   state                number(2)                       not null,
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   constraint pk_sys_dic primary key (id)
);

comment on table sys_dic is
'�����ֵ��';

comment on column sys_dic.id is
'�ؼ���';

comment on column sys_dic.dic_type is
'��������';

comment on column sys_dic.dic_key is
'���ݼ�';

comment on column sys_dic.dic_value is
'����ֵ';

comment on column sys_dic.sort is
'����';

comment on column sys_dic.type_desc is
'������������';

comment on column sys_dic.state is
'αɾ��״̬��1��������2��ɾ����';

comment on column sys_dic.creator is
'������Id';

comment on column sys_dic.create_date is
'����ʱ��';

comment on column sys_dic.modify_man is
'�޸���';

comment on column sys_dic.modify_date is
'�޸�ʱ��';

create table sys_log  (
   id                   number(32)                      not null,
   identify_code        varchar2(18)                    not null,
   name                 varchar2(64),
   creator              number(32),
   operate_date         date,
   operate_module       varchar2(100),
   operate_content      varchar2(400),
   ip                   varchar2(50),
   create_unit_code     varchar2(12),
   create_unit          varchar2(128),
   constraint pk_sys_log primary key (id)
);

comment on table sys_log is
'ϵͳ������־';

comment on column sys_log.id is
'�ؼ���';

comment on column sys_log.identify_code is
'���֤';

comment on column sys_log.name is
'����';

comment on column sys_log.creator is
'������';

comment on column sys_log.operate_date is
'����ʱ��';

comment on column sys_log.operate_module is
'������ʽ';

comment on column sys_log.operate_content is
'��������';

comment on column sys_log.ip is
'�ͻ���Ip';

comment on column sys_log.create_unit_code is
'��λ����';

comment on column sys_log.create_unit is
'��λ����';

create table sys_login_log  (
   id                   number(32)                      not null,
   identify_code        varchar2(18)                    not null,
   name                 varchar2(64),
   creator              number(32),
   log_date             date                            not null,
   ip                   varchar2(20)                    not null,
   status               number(2)                       not null,
   create_unit_code     varchar2(12),
   create_unit          varchar2(128),
   constraint pk_sys_login_log primary key (id)
);

comment on table sys_login_log is
'��¼��־';

comment on column sys_login_log.id is
'�ؼ���';

comment on column sys_login_log.identify_code is
'���֤��';

comment on column sys_login_log.name is
'����';

comment on column sys_login_log.creator is
'��¼/�ǳ���';

comment on column sys_login_log.log_date is
'��¼/�ǳ�ʱ��';

comment on column sys_login_log.ip is
'��¼ip';

comment on column sys_login_log.status is
'��¼�ǳ���־��1����¼��2���ǳ�';

comment on column sys_login_log.create_unit_code is
'��λ����';

comment on column sys_login_log.create_unit is
'��λ����';

create table sys_resource  (
   id                   number(32)                      not null,
   parent_id            number(32),
   name                 varchar2(50)                    not null,
   url                  varchar2(100)                   not null,
   type                 number(2)                       not null,
   res_level            number(2)                       not null,
   sort                 number(4)                       not null,
   state                number(2)                       not null,
   icon                 varchar2(100),
   icon_open            varchar2(100),
   icon_close           varchar2(100),
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   constraint pk_sys_resource primary key (id)
);

comment on table sys_resource is
'��Դ��';

comment on column sys_resource.id is
'�ؼ���';

comment on column sys_resource.parent_id is
'���ؼ���';

comment on column sys_resource.name is
'����';

comment on column sys_resource.url is
'��ַ';

comment on column sys_resource.type is
'���1���˵���2�����ܣ�';

comment on column sys_resource.res_level is
'��Դ����';

comment on column sys_resource.sort is
'����';

comment on column sys_resource.state is
'αɾ��״̬��1��������2��ɾ����';

comment on column sys_resource.icon is
'Ĭ��ͼ��';

comment on column sys_resource.icon_open is
'չ��ͼ��';

comment on column sys_resource.icon_close is
'�ر�ͼ��';

comment on column sys_resource.creator is
'������Id';

comment on column sys_resource.create_date is
'����ʱ��';

comment on column sys_resource.modify_man is
'�޸���Id';

comment on column sys_resource.modify_date is
'�޸�ʱ��';

create table sys_role  (
   id                   number(32)                      not null,
   name                 varchar2(50)                    not null,
   remark               varchar2(200),
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   state                number(2)                       not null,
   is_admin             number(1)                       not null,
   constraint pk_sys_role primary key (id)
);

comment on table sys_role is
'��ɫ��';

comment on column sys_role.id is
'�ؼ���';

comment on column sys_role.name is
'����';

comment on column sys_role.remark is
'��ע';

comment on column sys_role.creator is
'������';

comment on column sys_role.create_date is
'����ʱ��';

comment on column sys_role.modify_man is
'�޸���';

comment on column sys_role.modify_date is
'�޸�ʱ��';

comment on column sys_role.state is
'αɾ��״̬��1��������2��ɾ����';

create table sys_role_resource  (
   res_id               number(32)                      not null,
   role_id              number(32)                      not null
);

comment on table sys_role_resource is
'��ɫ��Դ������';

comment on column sys_role_resource.res_id is
'�ؼ���';

comment on column sys_role_resource.role_id is
'�ؼ���';

create table sys_user  (
   id                   number(32)                      not null,
   login_name           varchar2(64)                    not null,
   password             varchar2(64)                    not null,
   chinese_name         varchar2(64)                    not null,
   identify_code        varchar2(18)                    not null,
   police_code          varchar2(20)                    not null,
   phone_number         varchar2(32),
   email                varchar2(64),
   dept_id              number(32)                      not null,
   dept_code            varchar2(50)                    not null,
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   state                number(2)                       not null,
   constraint pk_sys_user primary key (id)
);

comment on table sys_user is
'ϵͳ�û���';

comment on column sys_user.login_name is
'��¼��';

comment on column sys_user.password is
'����';

comment on column sys_user.chinese_name is
'������';

comment on column sys_user.identify_code is
'���֤��';

comment on column sys_user.police_code is
'����';

comment on column sys_user.phone_number is
'��ϵ��ʽ';

comment on column sys_user.email is
'�����ַ';

comment on column sys_user.dept_id is
'�������λID';

comment on column sys_user.dept_code is
'��λ����';

comment on column sys_user.creator is
'������';

comment on column sys_user.create_date is
'����ʱ��';

comment on column sys_user.modify_man is
'�޸���';

comment on column sys_user.modify_date is
'�޸�ʱ��';

comment on column sys_user.state is
'αɾ��״̬��1��������2��ɾ����';

create table sys_user_role  (
   role_id              number(32)                      not null,
   user_id              number(32)                      not null
);

comment on table sys_user_role is
'�û���ɫ������';

comment on column sys_user_role.role_id is
'�ؼ���';

create table tbl_application  (
   id                   number(32)                      not null,
   app_name             varchar2(50)                    not null,
   app_id               varchar2(200)                   not null,
   app_secret           varchar2(200)                   not null,
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   state                number(2)                       not null,
   constraint pk_tbl_application primary key (id)
);

comment on table tbl_application is
'Ӧ�ñ�';

comment on column tbl_application.id is
'�ؼ���';

comment on column tbl_application.app_name is
'����';

comment on column tbl_application.app_id is
'Ӧ��id';

comment on column tbl_application.app_secret is
'Ӧ��secret';

comment on column tbl_application.creator is
'������';

comment on column tbl_application.create_date is
'����ʱ��';

comment on column tbl_application.modify_man is
'�޸���';

comment on column tbl_application.modify_date is
'�޸�ʱ��';

comment on column tbl_application.state is
'αɾ��״̬��1��������2��ɾ����';

create table tbl_service  (
   id                   number(32)                      not null,
   ser_name             varchar2(50)                    not null,
   ser_id               varchar2(200)                   not null,
   ser_secret           varchar2(200)                   not null,
   creator              number(32)                      not null,
   create_date          date                            not null,
   modify_man           number(32)                      not null,
   modify_date          date                            not null,
   state                number(2)                       not null,
   constraint pk_tbl_service primary key (id)
);

comment on table tbl_service is
'�����';

comment on column tbl_service.id is
'�ؼ���';

comment on column tbl_service.ser_name is
'����';

comment on column tbl_service.ser_id is
'����id';

comment on column tbl_service.ser_secret is
'����secret';

comment on column tbl_service.creator is
'������';

comment on column tbl_service.create_date is
'����ʱ��';

comment on column tbl_service.modify_man is
'�޸���';

comment on column tbl_service.modify_date is
'�޸�ʱ��';

comment on column tbl_service.state is
'αɾ��״̬��1��������2��ɾ����';

