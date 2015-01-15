package cn.wzga.open.util;

import java.lang.reflect.Field;

import cn.wzga.core.util.StringUtil;

/**
 * <p>
 * description:属性工具
 * </p>
 * 
 * @author czt
 * @since 2014年12月24日
 * @version v1.0
 */
public class PropertyUtil {
    /**
     * <p>
     * description:设置需要更新的属性
     * </p>
     * 
     * @param source
     * @param target
     * @throws Exception
     * @author czt
     * @since 2014年12月24日
     */
    public static void setUpdateProp(Object source, Object target) throws Exception {
        if (source == null || target == null) return;
        Field[] srcFields = source.getClass().getDeclaredFields();
        Field[] tagFields = source.getClass().getDeclaredFields();
        for (int i = 0; i < tagFields.length; i++) {
            Field tagField = tagFields[i];
            for (int j = 0; j < srcFields.length; j++) {
                Field srcField = srcFields[j];
                if (tagField.getName().equals(srcField.getName())) {
                    srcField.setAccessible(true);
                    if (srcField.getType() == String.class
                            && !StringUtil.isBlank((String) srcField.get(source))) {
                        tagField.setAccessible(true);
                        tagField.set(target, srcField.get(source));
                    } else if (srcField.getType() != String.class && srcField.get(source) != null) {
                        tagField.setAccessible(true);
                        tagField.set(target, srcField.get(source));
                    }
                }
            }
        }

    }
}
