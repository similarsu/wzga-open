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
import cn.wzga.open.entity.sys.SysLoginLog;
import cn.wzga.open.service.sys.SysLoginLogService;

/**
 * <p>
 * Description:登录日志控制层
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-07-11
 */
@Controller
@RequestMapping(value = "/sys/log/login")
public class SysLoginLogController extends BaseController {
    @Resource(name = "sysLoginLogService")
    private SysLoginLogService sysLoginLogService;

    /**
     * <p>
     * description:列表
     * </p>
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysLoginLog
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
            ModelMap modelMap, @ModelAttribute SysLoginLog sysLoginLog) throws Exception {

        Where where =
                Where.getInstance(sysLoginLog).equal("name", sysLoginLog.getName())
                        .equal("identifyCode", sysLoginLog.getIdentifyCode())
                        .equal("ip", sysLoginLog.getIp());
        Order order = Order.getInstance().desc("sysLoginLog.logDate");
        Pager<SysLoginLog> pager = new Pager<SysLoginLog>(pageNo, pageSize, where, order);

        pager = sysLoginLogService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/log/sys_login_log_list";
    }

}
