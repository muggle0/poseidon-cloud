package com.muggle.poseidon.user.user.entity;

import java.time.LocalDateTime;
import com.muggle.poseidon.base.BaseBean;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="UserSign对象", description="")
public class UserSign extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String authType;

    private LocalDateTime  gmtCreate;

    private String credentials;

    private boolean enable;

    private String principal;

    private LocalDateTime gmtModified;

    private Long userId;

}
