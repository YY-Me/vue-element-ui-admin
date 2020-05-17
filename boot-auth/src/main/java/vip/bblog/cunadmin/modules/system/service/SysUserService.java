package vip.bblog.cunadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.common.entity.PageParams;
import vip.bblog.cunadmin.common.entity.PageResult;
import vip.bblog.cunadmin.modules.system.dto.UserAddDTO;
import vip.bblog.cunadmin.modules.system.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 系统-用户 服务类
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据账号获取信息
     * @param username 账号
     * @return R
     */
    LoginUser getLoginUserByUserName(String username);

    /**
     * 添加用户
     * @param user 信息
     */
    void addUser(UserAddDTO user);

    /**
     * 更新用户信息
     * @param user 信息
     * @return R
     */
    UserAddDTO updateUser(UserAddDTO user);

    /**
     * 刪除用户
     * @param userId 用户id
     */
    void delete(Integer userId);

    /**
     * 分页查询
     * @param pageParams 参数
     * @return R
     */
    PageResult<List<SysUser>> listPage(PageParams pageParams);

    /**
     * 根据id查找
     * @param userId 用户id
     * @return R
     */
    BaseResult<UserAddDTO> selectById(Integer userId);

}
