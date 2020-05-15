package vip.bblog.cunadmin.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.entity.SysRole;
import vip.bblog.cunadmin.service.SysRoleService;

/**
 * <p>
 * 系统-角色 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Api(tags = "web:角色管理")
@ApiSort(4)
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    public BaseResult<SysRole> addRole(){
        return sysRoleService.add();
    }


}

