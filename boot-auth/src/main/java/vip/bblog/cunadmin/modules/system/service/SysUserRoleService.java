package vip.bblog.cunadmin.modules.system.service;

import vip.bblog.cunadmin.modules.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统-用户-角色关联表 服务类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户id删除关联
     *
     * @param userId 用户id
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据userId获取roleIds
     *
     * @param userId 用户id
     * @return R
     */
    List<Integer> getByRoleId(Integer userId);

    /**
     * 根据角色id删除关联
     * @param roleId 角色id
     */
    void deleteByRoleId(Integer roleId);

}
