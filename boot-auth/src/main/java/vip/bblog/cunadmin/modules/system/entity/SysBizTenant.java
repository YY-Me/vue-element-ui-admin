package vip.bblog.cunadmin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 系统-租户列表
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_biz_tenant")
@ApiModel(value="SysBizTenant对象", description="系统-租户列表")
public class SysBizTenant implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "内部id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "租户名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "app_id")
    @TableField("app_id")
    private String appId;

    @ApiModelProperty(value = "app_secret")
    @TableField("app_secret")
    private String appSecret;

    @ApiModelProperty(value = "qps，默认0（无限制）")
    @TableField("qps")
    private Integer qps;

    @ApiModelProperty(value = "日请求上限，默认0（无限制）")
    @TableField("day_limit")
    private Integer dayLimit;

    @ApiModelProperty(value = "是否启用，默认1")
    @TableField("is_enable")
    private Boolean isEnable;


}
