package vip.bblog.cunadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.common.entity.PageParams;
import vip.bblog.cunadmin.common.entity.PageResult;
import vip.bblog.cunadmin.modules.system.dto.UserAddDTO;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import vip.bblog.cunadmin.modules.system.entity.SysUser;
import vip.bblog.cunadmin.modules.system.entity.SysUserRole;
import vip.bblog.cunadmin.modules.system.mapper.SysUserMapper;
import vip.bblog.cunadmin.modules.system.service.SysMenuService;
import vip.bblog.cunadmin.modules.system.service.SysRoleService;
import vip.bblog.cunadmin.modules.system.service.SysUserRoleService;
import vip.bblog.cunadmin.modules.system.service.SysUserService;
import vip.bblog.cunadmin.util.UserUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    /**
     * 添加用户
     *
     * @param user 信息
     */
    @Override
    public void addUser(UserAddDTO user) {
        SysUser entity = new SysUser();
        if (StringUtils.isBlank(user.getPassword())) {
            entity.setPassword(UUID.randomUUID().toString());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        if (StringUtils.isBlank(user.getNickName())) {
            entity.setNickName(user.getUserName());
        }
        BeanUtils.copyProperties(user, entity);
        entity.setUpdateUserName(UserUtils.getUserName());
        this.save(entity);
        //保存用户-角色关联
        this.saveUserRole(entity.getId(), user.getRoleId());
    }

    /**
     * 更新用户信息
     *
     * @param user 信息
     * @return R
     */
    @Override
    public UserAddDTO updateUser(UserAddDTO user) {
        SysUser entity = new SysUser();
        entity.setId(user.getId());
        entity.setNickName(user.getNickName());
        if (StringUtils.isNotBlank(user.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.updateById(entity);
        //保存用户-角色关联
        sysUserRoleService.deleteByUserId(user.getId());
        this.saveUserRole(user.getId(), user.getRoleId());
        return user;
    }

    /**
     * 刪除用户
     *
     * @param userId 用户id
     */
    @Override
    public void delete(Integer userId) {
        this.removeById(userId);
        sysUserRoleService.deleteByUserId(userId);
    }

    /**
     * 分页查询
     *
     * @param pageParams 参数
     * @return R
     */
    @Override
    public PageResult<List<SysUser>> listPage(PageParams pageParams) {
        Page<SysUser> page = pageParams.getIPage();
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>lambdaQuery();
        Page<SysUser> result = this.page(page, queryWrapper);
        return PageResult.success(result.getRecords(), result.getTotal());
    }

    /**
     * 根据id查找
     *
     * @param userId 用户id
     * @return R
     */
    @Override
    public BaseResult<UserAddDTO> selectById(Integer userId) {
        UserAddDTO result = null;
        SysUser userInfo = this.getById(userId);
        if (null != userInfo) {
            result = new UserAddDTO();
            BeanUtils.copyProperties(userInfo, result);
            List<Integer> roleId = sysUserRoleService.getByRoleId(userId);
            result.setRoleId(roleId);
        }
        return BaseResult.success(result);
    }


    /**
     * @param userId 用户id
     * @param roleId 角色ids
     */
    private void saveUserRole(Integer userId, List<Integer> roleId) {
        if (CollectionUtils.isNotEmpty(roleId)) {
            List<SysUserRole> list = roleId.stream().map(id -> new SysUserRole(userId, id))
                    .collect(Collectors.toList());
            sysUserRoleService.saveBatch(list);
        }
    }

    /**
     * 根据账号查找
     *
     * @param username 账号
     * @return R
     */
    public SysUser getUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUserName, username);
        return this.getOne(queryWrapper, false);
    }
}
