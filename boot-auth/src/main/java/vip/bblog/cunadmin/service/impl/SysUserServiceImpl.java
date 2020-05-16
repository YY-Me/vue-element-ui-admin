package vip.bblog.cunadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.mapper.SysUserMapper;
import vip.bblog.cunadmin.entity.SysMenu;
import vip.bblog.cunadmin.entity.SysRole;
import vip.bblog.cunadmin.entity.SysUser;
import vip.bblog.cunadmin.service.SysMenuService;
import vip.bblog.cunadmin.service.SysRoleService;
import vip.bblog.cunadmin.service.SysUserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统-用户 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 根据账号获取信息
     *
     * @param username 账号
     * @return R
     */
    @Override
    public LoginUser getLoginUserByUserName(String username) {
        SysUser info = this.getUserByUserName(username);
        if (null != info) {
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(info, loginUser);
            //权限与角色
            List<SysRole> roleList = sysRoleService.listByUserId(info.getId());
            if (CollectionUtils.isNotEmpty(roleList)) {
                List<SysMenu> menuList = sysMenuService.listByRoleId(roleList.stream()
                        .map(SysRole::getId).collect(Collectors.toList()));
                if (CollectionUtils.isNotEmpty(menuList)) {
                    Set<String> collect = menuList.stream().map(SysMenu::getPermission).collect(Collectors.toSet());
                    loginUser.setPermission(collect);
                }
            }
            return loginUser;
        }
        return null;
    }

    public SysUser getUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUserName, username);
        return this.getOne(queryWrapper, false);
    }
}
