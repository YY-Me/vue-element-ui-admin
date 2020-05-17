package vip.bblog.cunadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import vip.bblog.cunadmin.modules.system.entity.SysRoleMenu;
import vip.bblog.cunadmin.modules.system.mapper.SysRoleMenuMapper;
import vip.bblog.cunadmin.modules.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统-角色-菜单（权限）关联表 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    /**
     * 根据roleId删除
     *
     * @param roleId 角色id
     */
    @Override
    public void deleteByRoleId(Integer roleId) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getRoleId, roleId);
        this.remove(queryWrapper);
    }

    /**
     * 根据menuId删除
     *
     * @param menuId 菜单id
     */
    @Override
    public void deleteByMenuId(Integer menuId) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getMenuId, menuId);
        this.remove(queryWrapper);
    }

    /**
     * 根据roleId获取menuIds
     *
     * @param roleId 角色id
     * @return R
     */
    @Override
    public List<Integer> getByRoleId(Integer roleId) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> list = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        }
        return null;
    }
}
