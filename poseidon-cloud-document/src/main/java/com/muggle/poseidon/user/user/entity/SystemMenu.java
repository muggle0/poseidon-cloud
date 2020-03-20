package com.muggle.poseidon.user.user.entity;

import java.time.LocalDateTime;
import com.muggle.poseidon.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author muggle
 * @since 2019-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SystemMenu对象", description="")
public class SystemMenu extends BaseBean {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单描述")
    private String muenDesc;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "范围")
    private String scop;

    @ApiModelProperty(value = "模块")
    private String module;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "菜单类型")
    private String menuType;

    @ApiModelProperty(value = "内容")
    private String context;

    private LocalDateTime gmtCreate;

}
