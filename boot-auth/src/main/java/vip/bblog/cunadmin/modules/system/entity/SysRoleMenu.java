package vip.bblog.cunadmin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统-角色-菜单（权限）关联表
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_menu")
@ApiModel(value="SysRoleMenu对象", description="系统-角色-菜单（权限）关联表")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "sys role id")
    @TableId("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "sys menu id")
    @TableField("menu_id")
    private Integer menuId;


}
