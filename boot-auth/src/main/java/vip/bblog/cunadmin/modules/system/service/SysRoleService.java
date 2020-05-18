package vip.bblog.cunadmin.modules.system.service;

import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.PageParams;
import vip.bblog.cunadmin.common.entity.PageResult;
import vip.bblog.cunadmin.modules.system.dto.RoleAddDTO;
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统-角色 服务类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户id 获取角色列表
     *
     * @param id 用户id
     * @return R
     */
    List<SysRole> listByUserId(Integer id);

    /**
     * 添加角色信息
     *
     * @param role 信息
     */
    void addRole(RoleAddDTO role);

    /**
     * 更新角色信息
     *
     * @param role 信息
     * @return R
     */
    RoleAddDTO updateRole(RoleAddDTO role);

    /**
     * 刪除角色
     *
     * @param roleId 角色id
     */
    void delete(Integer roleId);

    /**
     * 分页查询
     *
     * @param pageParams 参数
     * @return R
     */
    PageResult<List<SysRole>> listPage(PageParams pageParams);

    /**
     * 根据id查找
     * @param roleId 角色id
     * @return R
     */
    BaseResult<RoleAddDTO> selectById(Integer roleId);

    /**
     * 更新状态
     * @param roleId id
     * @param enable boolean
     */
    void updateRoleStatus(Integer roleId, Boolean enable);
}
