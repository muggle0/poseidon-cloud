package com.muggle.poseidon.service.impl;

import com.muggle.poseidon.entity.SimpleUserDO;
import com.muggle.poseidon.user.pojo.SystemMenu;
import com.muggle.poseidon.user.pojo.UserInfo;
import com.muggle.poseidon.user.entity.SystemMenuVO;
import com.muggle.poseidon.mapper.SystemMenuMapper;
import com.muggle.poseidon.service.ISystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muggle.poseidon.util.UserInfoUtils;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserInfo userInfo = (UserInfo)UserInfoUtils.getUserInfo();
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
