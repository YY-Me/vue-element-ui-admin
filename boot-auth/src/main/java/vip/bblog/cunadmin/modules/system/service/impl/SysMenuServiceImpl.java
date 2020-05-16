package vip.bblog.cunadmin.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;
import vip.bblog.cunadmin.modules.system.mapper.SysMenuMapper;
import vip.bblog.cunadmin.modules.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 根据角色id获取menu 列表
     *
     * @param roleId 角色ids
     * @return R
     */
    @Override
    public List<SysMenu> listByRoleId(List<Integer> roleId) {
        return sysMenuMapper.listByRoleId(roleId);
    }
}
