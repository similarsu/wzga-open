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
'单位信息';

comment on column sys_department.id is
'关键字';

comment on column sys_department.dept_code is
'单位代码';

comment on column sys_department.dept_name is
'单位名称';

comment on column sys_department.dept_spell is
'单位拼音';

comment on column sys_department.dept_level is
'单位层次';

comment on column sys_department.other_name is
'其他名称';

comment on column sys_department.simple_name is
'简称';

comment on column sys_department.is_formal is
'是否正式机构（0：否，1：是）';

comment on column sys_department.enable_date is
'启用日期';

comment on column sys_department.is_enable is
'是否启用（0：否，1：是）';

comment on column sys_department.disable_date is
'停用日期';

comment on column sys_department.is_stopped is
'是否停用(0：否，1：是)';

comment on column sys_department.original_code is
'原机构代码';

comment on column sys_department.original_stopped_date is
'原机构代码停用日期';

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
'数据字典表';

comment on column sys_dic.id is
'关键字';

comment on column sys_dic.dic_type is
'数据类型';

comment on column sys_dic.dic_key is
'数据键';

comment on column sys_dic.dic_value is
'数据值';

comment on column sys_dic.sort is
'排序';

comment on column sys_dic.type_desc is
'数据类型描述';

comment on column sys_dic.state is
'伪删除状态（1：正常，2：删除）';

comment on column sys_dic.creator is
'创建人Id';

comment on column sys_dic.create_date is
'创建时间';

comment on column sys_dic.modify_man is
'修改人';

comment on column sys_dic.modify_date is
'修改时间';

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
'系统操作日志';

comment on column sys_log.id is
'关键字';

comment on column sys_log.identify_code is
'身份证';

comment on column sys_log.name is
'姓名';

comment on column sys_log.creator is
'操作人';

comment on column sys_log.operate_date is
'操作时间';

comment on column sys_log.operate_module is
'操作方式';

comment on column sys_log.operate_content is
'操作内容';

comment on column sys_log.ip is
'客户端Ip';

comment on column sys_log.create_unit_code is
'单位代码';

comment on column sys_log.create_unit is
'单位名称';

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
'登录日志';

comment on column sys_login_log.id is
'关键字';

comment on column sys_login_log.identify_code is
'身份证号';

comment on column sys_login_log.name is
'姓名';

comment on column sys_login_log.creator is
'登录/登出人';

comment on column sys_login_log.log_date is
'登录/登出时间';

comment on column sys_login_log.ip is
'登录ip';

comment on column sys_login_log.status is
'登录登出标志：1、登录，2、登出';

comment on column sys_login_log.create_unit_code is
'单位代码';

comment on column sys_login_log.create_unit is
'单位名称';

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
'资源表';

comment on column sys_resource.id is
'关键字';

comment on column sys_resource.parent_id is
'父关键字';

comment on column sys_resource.name is
'名称';

comment on column sys_resource.url is
'网址';

comment on column sys_resource.type is
'类别（1：菜单，2：功能）';

comment on column sys_resource.res_level is
'资源级别';

comment on column sys_resource.sort is
'排序';

comment on column sys_resource.state is
'伪删除状态（1：正常，2：删除）';

comment on column sys_resource.icon is
'默认图标';

comment on column sys_resource.icon_open is
'展开图标';

comment on column sys_resource.icon_close is
'关闭图标';

comment on column sys_resource.creator is
'创建人Id';

comment on column sys_resource.create_date is
'创建时间';

comment on column sys_resource.modify_man is
'修改人Id';

comment on column sys_resource.modify_date is
'修改时间';

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
'角色表';

comment on column sys_role.id is
'关键字';

comment on column sys_role.name is
'名称';

comment on column sys_role.remark is
'备注';

comment on column sys_role.creator is
'创建人';

comment on column sys_role.create_date is
'创建时间';

comment on column sys_role.modify_man is
'修改人';

comment on column sys_role.modify_date is
'修改时间';

comment on column sys_role.state is
'伪删除状态（1：正常，2：删除）';

create table sys_role_resource  (
   res_id               number(32)                      not null,
   role_id              number(32)                      not null
);

comment on table sys_role_resource is
'角色资源关联表';

comment on column sys_role_resource.res_id is
'关键字';

comment on column sys_role_resource.role_id is
'关键字';

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
'系统用户表';

comment on column sys_user.login_name is
'登录名';

comment on column sys_user.password is
'密码';

comment on column sys_user.chinese_name is
'中文名';

comment on column sys_user.identify_code is
'身份证号';

comment on column sys_user.police_code is
'警号';

comment on column sys_user.phone_number is
'联系方式';

comment on column sys_user.email is
'邮箱地址';

comment on column sys_user.dept_id is
'外键、单位ID';

comment on column sys_user.dept_code is
'单位代码';

comment on column sys_user.creator is
'创建人';

comment on column sys_user.create_date is
'创建时间';

comment on column sys_user.modify_man is
'修改人';

comment on column sys_user.modify_date is
'修改时间';

comment on column sys_user.state is
'伪删除状态（1：正常，2：删除）';

create table sys_user_role  (
   role_id              number(32)                      not null,
   user_id              number(32)                      not null
);

comment on table sys_user_role is
'用户角色关联表';

comment on column sys_user_role.role_id is
'关键字';

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
'应用表';

comment on column tbl_application.id is
'关键字';

comment on column tbl_application.app_name is
'名称';

comment on column tbl_application.app_id is
'应用id';

comment on column tbl_application.app_secret is
'应用secret';

comment on column tbl_application.creator is
'创建人';

comment on column tbl_application.create_date is
'创建时间';

comment on column tbl_application.modify_man is
'修改人';

comment on column tbl_application.modify_date is
'修改时间';

comment on column tbl_application.state is
'伪删除状态（1：正常，2：删除）';

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
'服务表';

comment on column tbl_service.id is
'关键字';

comment on column tbl_service.ser_name is
'名称';

comment on column tbl_service.ser_id is
'服务id';

comment on column tbl_service.ser_secret is
'服务secret';

comment on column tbl_service.creator is
'创建人';

comment on column tbl_service.create_date is
'创建时间';

comment on column tbl_service.modify_man is
'修改人';

comment on column tbl_service.modify_date is
'修改时间';

comment on column tbl_service.state is
'伪删除状态（1：正常，2：删除）';

