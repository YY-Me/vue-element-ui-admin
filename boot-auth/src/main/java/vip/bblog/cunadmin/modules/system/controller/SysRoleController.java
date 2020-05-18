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
import vip.bblog.cunadmin.modules.system.entity.SysRole;
import vip.bblog.cunadmin.modules.system.service.SysRoleService;

import java.util.List;

/**
 * <p>
 * 系统-角色 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Api(tags = "web:角色管理")
@ApiSort(2)
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "添加角色")
    @PostMapping
    public BaseResult<String> addRole(@Validated(Default.class) @RequestBody RoleAddDTO role) {
        sysRoleService.addRole(role);
        return BaseResult.success();
    }

    @ApiOperation(value = "更新角色信息")
    @PutMapping
    public BaseResult<RoleAddDTO> updateRole(@Validated(Update.class) @RequestBody RoleAddDTO role) {
        return BaseResult.success(sysRoleService.updateRole(role));
    }

    @ApiOperation(value = "更新状态")
    @PutMapping("{roleId}/status/{enable}")
    public BaseResult<RoleAddDTO> updateRole(@PathVariable Integer roleId, @PathVariable Boolean enable) {
        sysRoleService.updateRoleStatus(roleId, enable);
        return BaseResult.success();
    }

    @ApiOperation(value = "刪除角色")
    @DeleteMapping("{roleId}")
    public BaseResult<RoleAddDTO> deleteRole(@PathVariable Integer roleId) {
        sysRoleService.delete(roleId);
        return BaseResult.success();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping
    public PageResult<List<SysRole>> listPage(PageParams pageParams) {
        return sysRoleService.listPage(pageParams);
    }

    @ApiOperation(value = "根据id查找")
    @GetMapping("{roleId}")
    public BaseResult<RoleAddDTO> selectById(@PathVariable Integer roleId) {
        return sysRoleService.selectById(roleId);
    }

}

