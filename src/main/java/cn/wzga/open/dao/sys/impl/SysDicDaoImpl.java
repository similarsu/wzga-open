package cn.wzga.open.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.sys.SysDicDao;
import cn.wzga.open.entity.sys.SysDic;

@Repository("sysDicDao")
public class SysDicDaoImpl extends BaseDaoImpl<SysDic> implements SysDicDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<SysDic> findByGroup() {
        StringBuffer ql =
                new StringBuffer("select sysDic.dicType,sysDic.typeDesc from SysDic sysDic ");
        ql.append(" group by sysDic.dicType,sysDic.typeDesc order by sysDic.dicType asc");

        Query query = this.createQuery(ql.toString());
        List<Object[]> objsList = query.list();
        List<SysDic> sysDicList = new ArrayList<SysDic>();
        for (Object[] objs : objsList) {
            SysDic sysDic = new SysDic();
            sysDic.setDicType((String) objs[0]);
            sysDic.setTypeDesc((String) objs[1]);
            sysDicList.add(sysDic);
        }
        return sysDicList;
    }

    @Override
    public void updateDicType(String oldType, String newType, String newTypeDesc) {
        Session session = getSession();
        session.createSQLQuery("update sys_dic set dic_type=?,type_desc=? where dic_type=?")
                .setString(0, newType).setString(1, newTypeDesc).setString(2, oldType)
                .executeUpdate();
    }

}
