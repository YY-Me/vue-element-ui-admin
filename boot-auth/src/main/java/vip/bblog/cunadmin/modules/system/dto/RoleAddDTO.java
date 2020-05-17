package vip.bblog.cunadmin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.bblog.cunadmin.annotation.group.Default;
import vip.bblog.cunadmin.annotation.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月15日 18:01
 * @desc RoleAddDTO 添加角色
 */
@Data
@ApiModel(value = "添加角色信息")
public class RoleAddDTO implements Serializable {

    private static final long serialVersionUID = -4882677300944973575L;

    @ApiModelProperty(value = "内部id")
    @NotNull(message = "id不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名不能为空", groups = {Default.class, Update.class})
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "是否启用，默认1")
    private Boolean isEnable;

    @ApiModelProperty(value = "是否启用，默认1")
    private List<Integer> menuId;

}
