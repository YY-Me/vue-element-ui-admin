package vip.bblog.cunadmin.modules.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月18日 16:17
 * @desc UserRoleVO
 */
@Data
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = -2376040060794014064L;

    private Integer userId;

    private Integer roleId;

    private String roleName;
}
