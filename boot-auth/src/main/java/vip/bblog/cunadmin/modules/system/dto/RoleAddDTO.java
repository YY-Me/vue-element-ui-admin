package vip.bblog.cunadmin.modules.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月15日 18:01
 * @desc RoleAddDTO 添加角色
 */
@Data
public class RoleAddDTO implements Serializable {

    private static final long serialVersionUID = -4882677300944973575L;

    private String name;

    private List<Integer> menuId;

}
