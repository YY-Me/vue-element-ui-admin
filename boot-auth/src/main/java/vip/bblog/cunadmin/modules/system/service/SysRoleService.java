package vip.bblog.cunadmin.modules.system.service;

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
     * @param id 用户id
     * @return R
     */
    List<SysRole> listByUserId(Integer id);
}
