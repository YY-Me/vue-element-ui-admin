package vip.bblog.cunadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.bblog.cunadmin.modules.system.mapper.SysRoleMapper;
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import vip.bblog.cunadmin.modules.system.service.SysRoleService;

import java.util.List;

/**
 * <p>
 * 系统-角色 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据用户id 获取角色列表
     *
     * @param userId 用户id
     * @return R
     */
    @Override
    public List<SysRole> listByUserId(Integer userId) {
        return sysRoleMapper.listByUserId(userId);
    }
}
