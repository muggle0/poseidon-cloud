package com.muggle.poseidon.service.impl;

import com.muggle.common.user.entity.SystemMenu;
import com.muggle.common.user.entity.UserInfo;
import com.muggle.poseidon.entity.SystemMenuVO;
import com.muggle.poseidon.mapper.SystemMenuMapper;
import com.muggle.poseidon.service.ISystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muggle.poseidon.util.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {
    @Override
    public List<SystemMenu> findMenus(SystemMenu systemMenu) {
        SystemMenuMapper baseMapper = this.baseMapper;
        UserInfo userInfo = UserUtils.getUserInfo();
        List<SystemMenu> menus= baseMapper.findAllByUser(systemMenu,userInfo.getId());
        return menus;
    }

    @Override
    public void saveWithRole(List<SystemMenuVO> menuVOList) {

    }

    @Override
    public void updateMenu(List<SystemMenuVO> menuVOList) {

    }
}
