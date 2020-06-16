package vip.bblog.cunadmin.modules.file.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import vip.bblog.cunadmin.common.exception.BizException;
import vip.bblog.cunadmin.modules.file.enums.FileSource;
import vip.bblog.cunadmin.modules.file.service.FileService;
import vip.bblog.cunadmin.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月11日 16:05
 * @desc FileServiceFactory
 */
@Configuration
public class FileServiceFactory {

    private final Map<FileSource, FileService> map = new HashMap<>();

    @Autowired
    private FileService aliyunFileServiceImpl;

    @Autowired
    private FileService localFileServiceImpl;

    @PostConstruct
    public void init() {
        map.put(FileSource.ALIYUN, aliyunFileServiceImpl);
        map.put(FileSource.LOCAL, localFileServiceImpl);
    }

    /**
     * 获取service
     *
     * @param source 类型
     * @return R
     */
    public FileService getFileService(String source) {
        if (StringUtils.isNotBlank(source)) {
            FileSource fileSource = FileSource.valueOf(source.toUpperCase());
            FileService fileService = map.get(fileSource);
            Assert.notNull(fileService, "操作源不存在");
            return fileService;
        }
        throw new BizException("操作源不存在");
    }

}
