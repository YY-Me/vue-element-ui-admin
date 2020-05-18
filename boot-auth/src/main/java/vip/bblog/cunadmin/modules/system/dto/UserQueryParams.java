package vip.bblog.cunadmin.modules.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.bblog.cunadmin.common.entity.PageParams;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月18日 16:55
 * @desc UserQueryParams
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryParams extends PageParams {

    private static final long serialVersionUID = -2491407591179246787L;

    private String namePhone;

    private Boolean isEnable;
}
