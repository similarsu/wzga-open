prompt Importing table SYS_DIC...
set feedback off
set define off

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '01', '1', '²Ëµ¥', 1, '×ÊÔ´ÀàÐÍ', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '01', '2', '¹¦ÄÜ', 2, '×ÊÔ´ÀàÐÍ', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '02', '1', 'µÇÂ¼', 1, 'µÇÂ¼×´Ì¬', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '02', '2', 'ÍË³ö', 2, 'µÇÂ¼×´Ì¬', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '02', '3', 'PKIµÇÂ¼', 3, 'µÇÂ¼×´Ì¬', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into SYS_DIC (ID, DIC_TYPE, DIC_KEY, DIC_VALUE, SORT, TYPE_DESC, STATE, CREATOR, CREATE_DATE, MODIFY_MAN, MODIFY_DATE)
values (s_sys_dic.nextval, '02', '4', 'PKI×¢²á', 4, 'µÇÂ¼×´Ì¬', 1, 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-12-2014 14:30:28', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt Done.
