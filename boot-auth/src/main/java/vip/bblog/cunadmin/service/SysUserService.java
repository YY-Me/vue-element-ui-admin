package vip.bblog.cunadmin.service;

import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
