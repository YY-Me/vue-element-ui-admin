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
import vip.bblog.cunadmin.common.entity.PageResult;
import vip.bblog.cunadmin.modules.system.dto.UserAddDTO;
import vip.bblog.cunadmin.modules.system.dto.UserQueryParams;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import vip.bblog.cunadmin.modules.system.entity.SysUser;
import vip.bblog.cunadmin.modules.system.entity.SysUserRole;
import vip.bblog.cunadmin.modules.system.mapper.SysUserMapper;
import vip.bblog.cunadmin.modules.system.service.SysMenuService;
import vip.bblog.cunadmin.modules.system.service.SysRoleService;
import vip.bblog.cunadmin.modules.system.service.SysUserRoleService;
import vip.bblog.cunadmin.modules.system.service.SysUserService;
import vip.bblog.cunadmin.modules.system.vo.SysUserVO;
import vip.bblog.cunadmin.modules.system.vo.UserRoleVO;
import vip.bblog.cunadmin.util.UserUtils;

import java.util.*;
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
    private SysUserMapper sysUserMapper;

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
                List<Integer> collect1 = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
                List<SysMenu> menuList = sysMenuService.listByRoleId(collect1);
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
        BeanUtils.copyProperties(user, entity);
        if (StringUtils.isBlank(user.getPassword())) {
            entity.setPassword(UUID.randomUUID().toString());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        if (StringUtils.isBlank(user.getNickName())) {
            entity.setNickName(user.getUserName());
        }
        entity.setUpdateUserName(UserUtils.getUserName());
        this.save(entity);
        //保存用户-角色关联
        this.saveUserRole(entity.getId(), user.getRole());
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
        this.saveUserRole(user.getId(), user.getRole());
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
     * @param params 参数
     * @return R
     */
    @Override
    public PageResult<List<SysUserVO>> listPage(UserQueryParams params) {
        Page<SysUser> page = params.getIPage();
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(params.getNamePhone())) {
            queryWrapper.likeLeft(SysUser::getUserName, params.getNamePhone());
        }
        if (null != params.getIsEnable()) {
            queryWrapper.eq(SysUser::getIsEnable, params.getIsEnable());
        }
        Page<SysUser> pageResult = this.page(page, queryWrapper);
        List<SysUser> records = pageResult.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            Map<Integer, List<UserRoleVO>> map = this.mapRoleByUserIds(records.stream().map(SysUser::getId)
                    .collect(Collectors.toList()));
            List<SysUserVO> collect = records.stream().map(item -> {
                SysUserVO sysUserVO = new SysUserVO();
                BeanUtils.copyProperties(item, sysUserVO);
                sysUserVO.setRoles(map.get(item.getId()));
                sysUserVO.setPassword("******");
                return sysUserVO;
            }).collect(Collectors.toList());
            return PageResult.success(collect, pageResult.getTotal());
        }
        return PageResult.success();
    }

    /**
     * 返回map结构
     *
     * @param userIds 用户ids
     * @return R
     */
    private Map<Integer, List<UserRoleVO>> mapRoleByUserIds(List<Integer> userIds) {
        List<UserRoleVO> list = sysUserMapper.listByUserIds(userIds);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(
                    Collectors.groupingBy(UserRoleVO::getUserId));
        }
        return new HashMap<>(0);
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
            result.setPassword("******");
            result.setRole(roleId);
        }
        return BaseResult.success(result);
    }

    /**
     * 更新状态
     *
     * @param userId 用户id
     * @param enable boolean
     */
    @Override
    public void updateRoleStatus(Integer userId, Boolean enable) {
        SysUser entity = new SysUser();
        entity.setId(userId);
        entity.setIsEnable(enable);
        entity.setUpdateUserName(UserUtils.getUserName());
        this.updateById(entity);
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
