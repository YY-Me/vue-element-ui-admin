package vip.bblog.cunadmin.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统-菜单 Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色id获取menu 列表
     *
     * @param roleId 角色ids
     * @return R
     */
    List<SysMenu> listByRoleId(@Param("roleId") List<Integer> roleId);
}
