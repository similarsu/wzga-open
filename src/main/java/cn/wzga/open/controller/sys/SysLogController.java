package cn.wzga.open.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysLog;
import cn.wzga.open.service.sys.SysLogService;

/**
 * <p>
 * Description:日志管理控制层
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-07-11
 */
@Controller
@RequestMapping(value = "/sys/log")
public class SysLogController extends BaseController {
    @Resource(name = "sysLogService")
    private SysLogService sysLogService;

    /**
     * <p>
     * description:列表
     * </p>
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysLog
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysLog sysLog) throws Exception {

        Where where =
                Where.getInstance(sysLog).equal("name", sysLog.getName())
                        .equal("identifyCode", sysLog.getIdentifyCode())
                        .equal("ip", sysLog.getIp());
        Order order = Order.getInstance().desc("sysLog.operateDate");
        Pager<SysLog> pager = new Pager<SysLog>(pageNo, pageSize, where, order);

        pager = sysLogService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/log/sys_log_list";
    }

}
