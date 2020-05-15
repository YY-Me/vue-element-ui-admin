package vip.bblog.cunadmin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统-角色
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="系统-角色")
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "是否启用，默认1")
    @TableField("is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "创建人id")
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建人账号")
    @TableField("create_user_name")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最近更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
