package vip.bblog.cunadmin.modules.system.service;

import vip.bblog.cunadmin.modules.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统-角色-菜单（权限）关联表 服务类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据roleId删除
     * @param roleId 角色id
     */
    void deleteByRoleId(Integer roleId);

    /**
     * 根据menuId删除
     * @param menuId 菜单id
     */
    void deleteByMenuId(Integer menuId);

    /**
     * 根据roleId获取menuIds
     * @param roleId 角色id
     * @return R
     */
    List<Integer> getByRoleId(Integer roleId);

}
