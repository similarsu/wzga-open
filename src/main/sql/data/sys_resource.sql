prompt PL/SQL Developer import file
prompt Created on 2014年12月29日 by Administrator
set feedback off
set define off
prompt Loading SYS_RESOURCE...
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (1, null, '系统管理', '#', 1, 2, 3, 1, 'libs/skin_frame/icons/icon_3_hover.png', 'libs/skin_frame/icons/icon_3_hover.png', 'libs/skin_frame/icons/icon_3.png', 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (2, 1, '用户管理', 'sys/user/list', 1, 3, 1, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (3, 2, '用户新增', 'sys/user/add', 2, 4, 1, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (4, 2, '用户修改', 'sys/user/*/update', 2, 4, 2, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (5, 2, '用户删除', 'sys/user/*/delete', 2, 4, 3, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (6, 1, '角色管理', 'sys/role/list', 1, 3, 2, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (7, 6, '角色新增', 'sys/role/add', 2, 4, 1, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (8, 6, '角色修改', 'sys/role/*/update', 2, 4, 2, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (9, 6, '角色删除', 'sys/role/*/delete', 2, 4, 3, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (10, 1, '资源管理', 'sys/res/list', 1, 3, 4, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (11, 10, '资源新增', 'sys/res/add', 2, 4, 1, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (12, 10, '资源修改', 'sys/res/*/update', 2, 4, 2, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (13, 10, '资源删除', 'sys/res/*/delete', 2, 4, 3, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (14, 1, '数据字典管理', 'sys/dic/list', 1, 3, 5, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (15, 14, '数据字典新增', 'sys/dic/add', 2, 4, 1, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (16, 14, '数据字典修改', 'sys/dic/*/update', 2, 4, 2, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (17, 14, '数据字典删除', 'sys/dic/*/delete', 2, 4, 3, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (18, 1, '系统日志', 'sys/log/list', 1, 3, 6, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SYS_RESOURCE (id, parent_id, name, url, type, res_level, sort, state, icon, icon_open, icon_close, creator, create_date, modify_man, modify_date)
values (19, 1, '登录日志', 'sys/log/login/list', 1, 3, 8, 1, null, null, null, 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-12-2014 16:19:12', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 94 records loaded
set feedback on
set define on
prompt Done.
