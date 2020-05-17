package vip.bblog.cunadmin.modules.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.bblog.cunadmin.annotation.group.Default;
import vip.bblog.cunadmin.annotation.group.Update;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.PageParams;
import vip.bblog.cunadmin.common.entity.PageResult;
import vip.bblog.cunadmin.modules.system.dto.RoleAddDTO;
import vip.bblog.cunadmin.modules.system.dto.UserAddDTO;
import vip.bblog.cunadmin.modules.system.entity.SysUser;
import vip.bblog.cunadmin.modules.system.service.SysUserService;

import java.util.List;

/**
 * <p>
 * 系统-用户 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Api(tags = "web:系统用户管理")
@ApiSort(1)
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "添加用户")
    @PostMapping
    public BaseResult<String> addUser(@Validated(Default.class) @RequestBody UserAddDTO user) {
        sysUserService.addUser(user);
        return BaseResult.success();
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping
    public BaseResult<UserAddDTO> updateUser(@Validated(Update.class) @RequestBody UserAddDTO user) {
        return BaseResult.success(sysUserService.updateUser(user));
    }

    @ApiOperation(value = "刪除用户")
    @DeleteMapping("{userId}")
    public BaseResult<RoleAddDTO> deleteUser(@PathVariable Integer userId) {
        sysUserService.delete(userId);
        return BaseResult.success();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping
    public PageResult<List<SysUser>> listPage(PageParams pageParams) {
        return sysUserService.listPage(pageParams);
    }

    @ApiOperation(value = "根据id查找")
    @GetMapping("{userId}")
    public BaseResult<UserAddDTO> selectById(@PathVariable Integer userId) {
        return sysUserService.selectById(userId);
    }
}

