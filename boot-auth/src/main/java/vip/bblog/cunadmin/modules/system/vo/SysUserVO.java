package vip.bblog.cunadmin.modules.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.bblog.cunadmin.modules.system.entity.SysUser;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年05月18日 16:27
 * @desc UserVO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserVO extends SysUser implements Serializable {

    private static final long serialVersionUID = 791914157994778956L;

    private List<UserRoleVO> roles;

}
