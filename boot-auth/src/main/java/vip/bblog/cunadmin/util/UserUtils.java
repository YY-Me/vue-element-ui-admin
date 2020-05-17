package vip.bblog.cunadmin.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vip.bblog.cunadmin.common.entity.LoginUser;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年10月22日 13:58
 * @desc UserUtils 获取当前的登录用户信息
 */
public class UserUtils {

    public static LoginUser getLoginUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser;
    }

    public static String getUserName() {
        return getLoginUser().getUsername();
    }

}
