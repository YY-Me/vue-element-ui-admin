package vip.bblog.cunadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统-菜单 服务类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据角色id获取menu 列表
     * @param roleId 角色ids
     * @return R
     */
    List<SysMenu> listByRoleId(List<Integer> roleId);

}
