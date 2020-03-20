package com.muggle.poseidon.user.user.entity;

import java.util.Date;

import com.muggle.poseidon.base.BaseBean;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="UserAuthority对象", description="")
public class UserAuthority extends BaseBean implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String url;

    private String description;

    private Date gmtCreate;

    private boolean enable;

    private String requestType;

    private String application;

    private String className;

    private String methodName;

    private Long parentId;


    @Override
    public String getAuthority() {
        return requestType+":"+url;
    }
}
