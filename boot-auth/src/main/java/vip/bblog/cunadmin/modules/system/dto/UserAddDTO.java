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
 * @author yy
 * @version V1.0
 * @date 2020-05-16
 */
@Data
@ApiModel(value = "添加、更新用户信息")
public class UserAddDTO implements Serializable {

    private static final long serialVersionUID = 1171215105741391756L;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Integer id;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号", groups = Default.class)
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "角色")
    private List<Integer> roleId;
}
