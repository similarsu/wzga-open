-- ϵͳ�û���ʼ
insert into sys_user
  (ID,
   LOGIN_NAME,
   PASSWORD,
   CHINESE_NAME,
   IDENTIFY_CODE,
   POLICE_CODE,
   DEPT_ID,
   DEPT_CODE,
   CREATOR,
   CREATE_DATE,
   MODIFY_MAN,
   MODIFY_DATE,
   STATE)
values
  (s_sys_user.nextval,
   'admin',
   'admin',
   '���׶�',
   '330324198304221014',
   '036419',
   (select id from SYS_DEPARTMENT t where dept_code = '330300230400'),
   '330300230400',
   s_sys_user.currval,
   sysdate,
   s_sys_user.currval,
   sysdate,
   1);
-- ϵͳ����Ա��ɫ
insert into sys_role
  (ID,
   NAME,
   CREATOR,
   CREATE_DATE,
   MODIFY_MAN,
   MODIFY_DATE,
   STATE,
   IS_ADMIN)
values
  (s_sys_role.nextval,
   'ϵͳ����Ա',
   s_sys_user.currval,
   sysdate,
   s_sys_user.currval,
   sysdate,
   1,
   1);
-- ϵͳ��ɫ��ϵ��
insert into sys_user_role value
  (role_id, user_id)
values
  (s_sys_role.currval, s_sys_user.currval);
-- Ϊϵͳ����Ա����������Դ
insert into sys_role_resource
  (RES_ID, ROLE_ID)
  select id, s_sys_role.currval from sys_resource;

-- ���´����� sys_dic 
update sys_dic
   set CREATOR     = s_sys_user.currval,
       MODIFY_MAN  = s_sys_user.currval,
       CREATE_DATE = sysdate,
       MODIFY_DATE = sysdate;

-- ���´����� sys_resource
update sys_resource
   set CREATOR     = s_sys_user.currval,
       MODIFY_MAN  = s_sys_user.currval,
       CREATE_DATE = sysdate,
       MODIFY_DATE = sysdate;

-- ������Դ�㼶
alter sequence s_sys_resource increment by 160;
select s_sys_resource.nextval from dual;
alter sequence s_sys_resource increment by 1;
-- ����ͣ�����ڸ���ͣ�ñ�ʶ
update SYS_DEPARTMENT
   set IS_ENABLE = 1
 where IS_STOPPED = 0
    or (IS_STOPPED is null and DISABLE_DATE is null);

-- commit
commit;
