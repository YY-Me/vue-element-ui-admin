package vip.bblog.cunadmin.modules.file.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年06月19日 15:29
 * @desc CreateFolderDTO
 */
@Data
public class CreateFolderDTO implements Serializable {

    private static final long serialVersionUID = 4296577849683861983L;

    private String prefix;

    private String folderName;

}
