package vip.bblog.cunadmin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 系统-用户
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="系统-用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键，内部id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "账号")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "账户是否启用，默认1")
    @TableField("is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "账户是否删除，默认0")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

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
