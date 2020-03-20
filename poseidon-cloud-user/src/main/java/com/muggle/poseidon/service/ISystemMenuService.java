package com.muggle.poseidon.service;

import com.muggle.poseidon.user.pojo.SystemMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muggle.poseidon.user.entity.SystemMenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    List<SystemMenu> findMenus(SystemMenu systemMenu);

    void saveWithRole(List<SystemMenuVO> menuVOList);

    void updateMenu(List<SystemMenuVO> menuVOList);
}
