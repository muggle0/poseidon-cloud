package com.muggle.poseidon.user.entity;

import com.muggle.poseidon.user.pojo.SystemMenu;
import lombok.Data;

/**
 * @program: poseidon-cloud-user
 * @description:
 * @author: muggle
 * @create: 2020-01-10
 **/
@Data
public class SystemMenuVO extends SystemMenu {
    private static final long serialVersionUID = 6969701887874112395L;

    private Long roleId;
}
