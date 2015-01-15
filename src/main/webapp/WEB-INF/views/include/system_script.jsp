<%@ page language="java" pageEncoding="UTF-8"%>
<%-- 必须紧挨着放在</body>之前 --%>
<script type="text/javascript" src="${ctx}/libs/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${ctx}/libs/js/framework.js"></script>

<script type="text/javascript">
    jQuery.renderPageComponent = function(compList) {
        if (!compList || 1 > compList.length) {
            return;
        }
        for (var comp in compList) {
            // 提示特效没有render方法
            $(compList[comp]).each(function() {
                var ele = $(this);

                ele.render();
                // copy from framework.js:369
                if(ele.attr("title")){
                    if(
                        ele.parents(".selectbox-tree").length>0
                        || ele.parents(".dbSelectionMode").length>0
                        ) {
                        // let it go
                    } else{
                        addTooltip(ele[0]);
                    }
                }

                return true;
            });
        }
    };
</script>
