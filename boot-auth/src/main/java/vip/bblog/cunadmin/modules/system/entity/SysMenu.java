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
 * 系统-菜单
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="系统-菜单")
public class SysMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "menu id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "唯一，找路由使用")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "权限标识")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "父类id，默认0（第一级）")
    @TableField("p_id")
    private Integer pId;

    @ApiModelProperty(value = "顶部id")
    @TableField("t_id")
    private Integer tId;

    @ApiModelProperty(value = "1：菜单，2：按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "1：左侧菜单，2：顶部菜单")
    @TableField("m_type")
    private Integer mType;

    @ApiModelProperty(value = "路径，一级加/，其他不要加/")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;


}
