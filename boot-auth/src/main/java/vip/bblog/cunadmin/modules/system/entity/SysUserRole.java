package vip.bblog.cunadmin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-用户-角色关联表
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value="SysUserRole对象", description="系统-用户-角色关联表")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "sys user id")
    @TableId("user_id")
    private Integer userId;

    @ApiModelProperty(value = "sys role id")
    @TableField("role_id")
    private Integer roleId;


}
