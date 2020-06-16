package vip.bblog.cunadmin.modules.file.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import vip.bblog.cunadmin.modules.file.dto.UploadInfo;
import vip.bblog.cunadmin.modules.file.entity.FileInfo;
import vip.bblog.cunadmin.modules.file.service.FileService;

import java.io.IOException;
import java.util.Date;


/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月11日 15:07
 * @desc AbstractFileService
 */
@Component
public abstract class AbstractFileServiceImpl implements FileService {

    /**
     * 保存文件或文件夹
     *
     * @param uploadInfo 信息
     * @return R
     */
    @Override
    public FileInfo saveFile(UploadInfo uploadInfo) {
        FileInfo fileInfo = new FileInfo();
        try {
            MultipartFile file = uploadInfo.getFile();
            fileInfo.setMd5(DigestUtils.md5DigestAsHex(file.getInputStream()));
            fileInfo.setName(file.getOriginalFilename());
            fileInfo.setSize(file.getSize());
            fileInfo.setUpdateTime(new Date());
            fileInfo.setDir(false);
            fileInfo.setPath(uploadInfo.getPrefix());
            save(fileInfo, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param fileInfo 信息
     * @param file     文件
     */
    protected abstract void save(FileInfo fileInfo, MultipartFile file);

}
