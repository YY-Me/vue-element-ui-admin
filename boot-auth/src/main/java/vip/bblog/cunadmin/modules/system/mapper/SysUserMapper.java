package vip.bblog.cunadmin.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.bblog.cunadmin.modules.system.entity.SysUser;
import vip.bblog.cunadmin.modules.system.vo.UserRoleVO;

import java.util.List;

/**
 * <p>
 * 系统-用户 Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 根据用户查询角色信息
     * @param userIds 用户ids
     * @return R
     */
    List<UserRoleVO> listByUserIds(@Param("userIds") List<Integer> userIds);
}
