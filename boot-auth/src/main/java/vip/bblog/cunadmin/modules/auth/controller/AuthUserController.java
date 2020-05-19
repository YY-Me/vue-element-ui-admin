package vip.bblog.cunadmin.modules.auth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.CustomToken;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.modules.auth.CustomTokenService;
import vip.bblog.cunadmin.util.UserUtils;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2019年10月14日 10:44
 * @desc UserController 用户相关控制器
 */
@RestController
@RequestMapping("/user")
@Api(tags = "我的:权限")
@ApiSort(15)
@Slf4j
public class AuthUserController {

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/userInfo")
    public BaseResult<LoginUser> getBaseInfo(@AuthenticationPrincipal UserDetails user) {
        LoginUser loginUser = (LoginUser) user;
        return BaseResult.success(loginUser);
    }

    @ApiOperation(value = "我的:权限:修改密码", notes = "用户(司机、普通用户)修改密码")
    @PutMapping("password/{password}")
    public BaseResult<Boolean> updatePass(@PathVariable String password) {
        LoginUser loginUser = UserUtils.getLoginUser();
        return BaseResult.success(true);
    }

    @ApiOperation(value = "我的:权限:刷新登录token")
    @PostMapping("refresh")
    public BaseResult<CustomToken> refresh() {
        LoginUser loginUser = UserUtils.getLoginUser();
        loginUser = (LoginUser) userDetailsService.loadUserByUsername(loginUser.getUsername());
        loginUser.setToken(loginUser.getToken());
        CustomToken result = customTokenService.refresh(loginUser);
        return BaseResult.success(result);
    }

}
