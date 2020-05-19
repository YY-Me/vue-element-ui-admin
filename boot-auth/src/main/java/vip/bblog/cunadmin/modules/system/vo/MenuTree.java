package vip.bblog.cunadmin.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.bblog.cunadmin.modules.system.entity.SysMenu;

import java.io.Serializable;
import java.util.List;

/**
 * @author yy
 * @version V1.0
 * @date 2020-05-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTree extends SysMenu implements Serializable {

    private static final long serialVersionUID = 6513292876863682735L;

    private Boolean edit = false;

    @JsonProperty("tName")
    private String tName;

    private List<MenuTree> children;

}
