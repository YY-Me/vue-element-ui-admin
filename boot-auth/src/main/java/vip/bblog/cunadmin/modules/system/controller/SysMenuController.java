package vip.bblog.cunadmin.modules.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.bblog.cunadmin.annotation.group.Default;
import vip.bblog.cunadmin.annotation.group.Update;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;
import vip.bblog.cunadmin.modules.system.service.SysMenuService;
import vip.bblog.cunadmin.modules.system.vo.MenuTree;

import java.util.List;

/**
 * <p>
 * 系统-菜单 前端控制器
 * </p>
 *
 * @author yy
 * @since 2020-05-15
 */
@Api(tags = "web:菜单管理")
@ApiSort(3)
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "添加菜单")
    @PostMapping
    public BaseResult<SysMenu> addRole(@Validated(Default.class) @RequestBody SysMenu menu) {
        return BaseResult.success(sysMenuService.addMenu(menu));
    }

    @ApiOperation(value = "更新菜单信息")
    @PutMapping
    public BaseResult<SysMenu> updateRole(@Validated(Update.class) @RequestBody SysMenu menu) {
        return BaseResult.success(sysMenuService.updateMenu(menu));
    }

    @ApiOperation(value = "刪除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单id", paramType = "path")
    })
    @DeleteMapping("{menuId}")
    public BaseResult<String> deleteRole(@PathVariable Integer menuId) {
        sysMenuService.delete(menuId);
        return BaseResult.success();
    }

    @ApiOperation(value = "tree结构查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mType", value = "布局类型，1：左侧菜单，2：顶部菜单", paramType = "query")
    })
    @GetMapping("/tree")
    public BaseResult<List<MenuTree>> listTreeAll(Integer mType) {
        return BaseResult.success(sysMenuService.listTreeAll(mType));
    }

    @ApiOperation(value = "简单结构查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mType", value = "布局类型，1：左侧菜单，2：顶部菜单", paramType = "query")
    })
    @GetMapping
    public BaseResult<List<SysMenu>> listSimpleAll(Integer mType) {
        return BaseResult.success(sysMenuService.listSimpleAll(mType));
    }


}

