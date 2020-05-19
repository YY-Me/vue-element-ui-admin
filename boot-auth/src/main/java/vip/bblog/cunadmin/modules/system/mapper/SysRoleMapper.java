package vip.bblog.cunadmin.modules.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统-角色 Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id 获取角色列表
     *
     * @param userId 用户id
     * @return R
     */
    @Select("SELECT b.* FROM sys_user_role a LEFT JOIN sys_role b ON a.role_id = b.id WHERE a.user_id = #{userId} AND b.is_enable=1")
    List<SysRole> listByUserId(@Param("userId") Integer userId);
}
