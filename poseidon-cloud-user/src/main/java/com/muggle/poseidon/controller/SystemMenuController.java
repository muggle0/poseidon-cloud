package com.muggle.poseidon.controller;


import com.muggle.poseidon.user.pojo.SystemMenu;
import com.muggle.poseidon.base.BaseController;
import com.muggle.poseidon.base.ResultBean;
import com.muggle.poseidon.user.entity.SystemMenuVO;
import com.muggle.poseidon.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/system-menu")
public class SystemMenuController extends BaseController {

    @Autowired
    ISystemMenuService systemMenuService;

    @GetMapping("/menu")
    public ResultBean<List<SystemMenu>> getMenu(SystemMenu systemMenu){
        List<SystemMenu> menus=systemMenuService.findMenus(systemMenu);
        return ResultBean.successData(menus);
    }

    @PostMapping("/admin/save")
    public ResultBean saveMenu(List<SystemMenuVO> menuVOList){
        systemMenuService.saveWithRole(menuVOList);
        return ResultBean.success();
    }

    /**
     * 更新不为空的字段
     * @return
     */
    public ResultBean updateMenu(List<SystemMenuVO> menuVOList){
        systemMenuService.updateMenu(menuVOList);
        return ResultBean.success();
    }
}
