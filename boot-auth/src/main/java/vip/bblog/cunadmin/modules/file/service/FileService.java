package vip.bblog.cunadmin.modules.file.service;

import vip.bblog.cunadmin.modules.file.dto.UploadInfo;
import vip.bblog.cunadmin.modules.file.entity.FileInfo;
import vip.bblog.cunadmin.modules.file.entity.ShardInfo;

import java.util.List;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年06月11日 14:24
 * @desc FileService 文件管理
 */
public interface FileService {

    /**
     * 保存文件
     *
     * @param uploadInfo 信息
     * @return R
     */
    FileInfo saveFile(UploadInfo uploadInfo);

    /**
     * 删除文件或文件夹
     *
     * @param file 文件
     * @return R
     */
    boolean deleteFile(FileInfo file);

    /**
     * 文件夹创建
     *
     * @param prefix     前缀
     * @param folderName 文件夹名称
     * @return R
     */
    boolean createFolder(String prefix, String folderName);

    /**
     * 根据前缀列举文件或文件夹
     *
     * @param keyPrefix 前缀，例如：/标识根目录，/images表示根目录下的images文件夹
     * @return R
     */
    List<FileInfo> listFileByKeyPrefix(String keyPrefix);

    /**
     * 保存分片
     *
     * @param shard 信息
     * @return R
     */
    ShardInfo saveShard(ShardInfo shard);

    /**
     * 合并分片
     *
     * @param uploadId 唯一id
     * @param fileName 文件名
     */
    void mergeShard(String uploadId, String fileName);

    /**
     * 删除资源
     * @param prefix 名称
     */
    void deleteSource(List<String> prefix);
}
