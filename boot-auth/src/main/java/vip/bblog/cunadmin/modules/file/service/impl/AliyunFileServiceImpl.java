package vip.bblog.cunadmin.modules.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.bblog.cunadmin.modules.file.entity.FileInfo;
import vip.bblog.cunadmin.modules.file.entity.ShardInfo;

import java.util.List;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年06月11日 15:21
 * @desc AliyunFileServiceImpl 阿里云oss
 */
@Service
public class AliyunFileServiceImpl extends AbstractFileServiceImpl {

    /**
     * 保存文件
     *
     * @param fileInfo 信息
     * @param file     文件
     */
    @Override
    protected void save(FileInfo fileInfo, MultipartFile file) {

    }

    /**
     * 删除文件或文件夹
     *
     * @param file 文件
     * @return R
     */
    @Override
    public boolean deleteFile(FileInfo file) {
        return false;
    }

    /**
     * 文件夹创建
     *
     * @param prefix     前缀
     * @param folderName 文件夹名称
     * @return R
     */
    @Override
    public boolean createFolder(String prefix, String folderName) {
        return false;
    }

    /**
     * 根据前缀列举文件或文件夹
     *
     * @param keyPrefix 前缀，例如：/标识根目录，/images表示根目录下的images文件夹
     * @return R
     */
    @Override
    public List<FileInfo> listFileByKeyPrefix(String keyPrefix) {
        return null;
    }

    /**
     * 保存分片
     * @param shard 信息
     * @return R
     */
    @Override
    public ShardInfo saveShard(ShardInfo shard) {
        return null;
    }

    /**
     * 合并分片
     *
     * @param uploadId 唯一id
     */
    @Override
    public void mergeShard(String uploadId, String fileName) {

    }

    /**
     * 删除资源
     *
     * @param prefix 名称
     */
    @Override
    public void deleteSource(List<String> prefix) {

    }
}
