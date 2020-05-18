package vip.bblog.cunadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;
import vip.bblog.cunadmin.modules.system.mapper.SysMenuMapper;
import vip.bblog.cunadmin.modules.system.service.SysMenuService;
import vip.bblog.cunadmin.modules.system.service.SysRoleMenuService;
import vip.bblog.cunadmin.modules.system.vo.MenuTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统-菜单 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据角色id获取menu 列表
     *
     * @param roleId 角色ids
     * @return R
     */
    @Override
    public List<SysMenu> listByRoleId(List<Integer> roleId) {
        List<SysMenu> sysMenus = sysMenuMapper.listByRoleId(roleId);
        return sysMenus;
    }

    /**
     * 添加菜单
     *
     * @param menu 信息
     * @return R
     */
    @Override
    public SysMenu addMenu(SysMenu menu) {
        menu.setId(null);
        this.save(menu);
        return menu;
    }

    /**
     * 更新菜单信息
     *
     * @param menu 信息
     * @return R
     */
    @Override
    public SysMenu updateMenu(SysMenu menu) {
        this.updateById(menu);
        return menu;
    }

    /**
     * 刪除菜单
     *
     * @param menuId 菜单id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer menuId) {
        this.removeById(menuId);
        sysRoleMenuService.deleteByMenuId(menuId);
    }

    /**
     * tree结构查询
     *
     * @param mType 布局类型
     * @return R
     */
    @Override
    public List<MenuTree> listTreeAll(Integer mType) {
        List<SysMenu> list = this.listSimpleAll(mType);
        return this.convertToTree(list, 0);
    }

    /**
     * 转换成树形
     *
     * @param list 数据
     * @param pId  pid
     */
    private List<MenuTree> convertToTree(List<SysMenu> list, Integer pId) {
        List<MenuTree> result = new ArrayList<>();
        List<SysMenu> parent = list.stream().filter(item -> item.getPId().equals(pId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(parent)) {
            //获取children
            for (SysMenu menu : parent) {
                MenuTree tempTree = new MenuTree();
                BeanUtils.copyProperties(menu, tempTree);
                tempTree.setChildren(this.convertToTree(list, menu.getId()));
                result.add(tempTree);
            }
        }
        return result;
    }

    /**
     * 简单结构查询
     *
     * @param mType 布局类型
     * @return R
     */
    @Override
    public List<SysMenu> listSimpleAll(Integer mType) {
        LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.<SysMenu>lambdaQuery();
        if (null != mType) {
            queryWrapper.eq(SysMenu::getMType, mType);
        }
        queryWrapper.orderByAsc(SysMenu::getMType).orderByAsc(SysMenu::getSort);
        return this.list(queryWrapper);
    }
}
