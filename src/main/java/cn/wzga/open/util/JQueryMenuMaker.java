package cn.wzga.open.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import cn.wzga.core.util.Constant;
import cn.wzga.open.entity.sys.SysResource;

/**
 * <p>
 * Description: 资源树生成
 * </p>
 * 
 * @author sutong
 * 
 * @version 1.0 2014-07-03
 */
public class JQueryMenuMaker {

    /**
     * <p>
     * description:通过资源列表生成手风琴样式菜单
     * </p>
     * 
     * @param resList
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    public static String res2TreeAccordionMenu(Set<SysResource> resList) {
        if (resList == null) {
            return "";
        }

        List<SysResource> resSortList = new ArrayList<SysResource>(resList);

        Comparator<SysResource> cp = new Comparator<SysResource>() {
            @Override
            public int compare(SysResource o1, SysResource o2) {
                return o1.getSort() - o2.getSort();
            }
        };
        Collections.sort(resSortList, cp);

        // 菜单json样式
        // { id:3001, parentId:0, name:"表单模板",icon:"skin/icons/icon_1.png",iconSkin:"diy01"},
        // { id:101, parentId:3001, name:"页面中的表单",url:"../../sample_skin/normal/form-page.html",
        // target:"frmright",iconOpen:"../../libs/icons/tree_close.gif",iconClose:"../../libs/icons/tree_open.gif",icon:"skin/titlebar_arrow.gif"},
        StringBuffer result = new StringBuffer();
        for (SysResource res : resSortList) {
            if (res.getResLevel() == 2) {
                result.append("{");
                result.append("id:" + res.getId());
                result.append(",parentId:" + res.getParentId());
                result.append(",name:\"" + res.getName() + "\"");
                result.append(",iconOpen:\"" + res.getIconOpen() + "\"");
                result.append(",iconClose:\"" + res.getIconClose() + "\"");
                result.append(",icon:\"" + res.getIcon() + "\"");
                result.append(",iconSkin:\"diy01\"");
                result.append("},");

            } else if (res.getResLevel() == 3) {
                result.append("{");
                result.append("id:" + res.getId());
                result.append(",parentId:" + res.getParent().getId());
                result.append(",name:\"" + res.getName() + "\"");
                result.append(",url:\"" + res.getUrl() + "\"");
                result.append(",target:\"frmright\"");
                result.append(",iconOpen:\"" + res.getIconOpen() + "\"");
                result.append(",iconClose:\"" + res.getIconClose() + "\"");
                result.append(",icon:\"libs/skin_frame/titlebar_arrow.gif\"");
                result.append("},");
            }
        }
        if (result != null && result.length() > 0
                && result.substring(result.length() - 1).equals(",")) {
            result.delete(result.length() - 1, result.length());
        }

        return result.toString();

    }

    /**
     * 将资源转换成树状结构json
     * 
     * @param type
     * @return String
     */
    public static String res2ZTree(List<SysResource> resListAll) {
        return res2ZTree(null, resListAll);
    }

    /**
     * 将资源转换成树状结构json
     * 
     * @param resList
     * @param type
     * @return String
     */
    public static String res2ZTree(Set<SysResource> resList, List<SysResource> resListAll) {

        StringBuffer result = new StringBuffer();

        result.append("{ id:\"0\",  parentId:\"-1\", name:\"根目录\", open: true},");
        for (SysResource resTmp : resListAll) {
            String pid = "0";
            if (resTmp.getParent() != null) pid = resTmp.getParent().getId() + "";
            result.append("{ id:\"" + resTmp.getId() + "\",  parentId:\"" + pid + "\", name:\""
                    + resTmp.getName() + "\", open: true");
            if (resList != null) {
                for (SysResource res : resList) {
                    if (res.getState() != Constant.STATE_NOMAL) continue;
                    if (resTmp.getId().equals(res.getId())) {
                        result.append(", checked: true");
                    }
                }
            }
            result.append("},");
        }

        if (result != null && result.length() > 0
                && result.substring(result.length() - 1).equals(",")) {
            result.delete(result.length() - 1, result.length());
        }

        return result.toString();
    }
}
