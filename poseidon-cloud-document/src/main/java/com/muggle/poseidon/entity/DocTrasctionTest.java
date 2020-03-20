package com.muggle.poseidon.entity;

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
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="测试", description="")
public class DocTrasctionTest extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String message;

    private Integer number;

    private Long id;


}
