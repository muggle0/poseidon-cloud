package com.muggle.poseidon.user.pojo;

import java.time.LocalDateTime;
import com.muggle.poseidon.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

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
@ApiModel(value="UserRole对象", description="")
@Builder
public class UserRole extends BaseBean implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String roleCode;

    private boolean enable;

    private String roleName;

    private Integer sort;

    private String createName;

    private Long createId;

    private String scope;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}
