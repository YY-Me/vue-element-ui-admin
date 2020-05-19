package vip.bblog.cunadmin.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vip.bblog.cunadmin.modules.system.vo.MenuTree;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年05月15日 11:51
 * @desc LoginUser 登录用户信息
 */
@Data
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -5393788672041186399L;

    @ApiModelProperty(value = "主键，内部id")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账户是否启用，默认1")
    private Boolean isEnable;

    @ApiModelProperty(value = "角色信息")
    private Set<String> roles;

    @ApiModelProperty(value = "权限信息")
    private Set<String> permission;

    private List<MenuTree> topMenu;

    private List<MenuTree> menu;

    private String token;

    private CustomToken customToken;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        //设置角色
        if (CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> {
                if (role.startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            });
        }
        //设置权限
        if (CollectionUtils.isNotEmpty(permission)) {
            permission.forEach(p -> {
                if (StringUtils.isNotBlank(p)) {
                    collection.add(new SimpleGrantedAuthority(p));
                }
            });
        }
        return collection;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return isEnable;
    }
}
