package vip.bblog.cunadmin.modules.file.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.bblog.cunadmin.common.exception.BizException;
import vip.bblog.cunadmin.modules.file.entity.FileInfo;
import vip.bblog.cunadmin.modules.file.entity.ShardInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年06月11日 15:21
 * @desc LocalFileServiceImpl 本地存储
 */
@Service
@Slf4j
public class LocalFileServiceImpl extends AbstractFileServiceImpl {

    @Value("${localFile.basePath}")
    private String basePath;

    @Value("${localFile.shardPath}")
    private String shardPath;

    /**
     * 保存文件
     *
     * @param fileInfo 信息
     * @param file     文件
     */
    @Override
    protected void save(FileInfo fileInfo, MultipartFile file) {
        try {
            File destination = new File(String.format("%s%s%s%s", basePath, fileInfo.getPath(), File.separator,
                    fileInfo.getName()));
            FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
        } catch (IOException e) {
            throw new BizException("上传失败");
        }
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
        String path = basePath + keyPrefix;
        File file = new File(path);
        File[] files = file.listFiles();
        if (null != files) {
            List<FileInfo> result = new ArrayList<>(files.length);
            List<File> fileList = Arrays.asList(files);
            fileList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory()) {
                        if (o2.isDirectory()) {
                            return -1;
                        } else {
                            return o1.getName().compareTo(o2.getName());
                        }
                    } else {
                        if (o2.isDirectory()) {
                            return 1;
                        } else {
                            return o1.getName().compareTo(o2.getName());
                        }
                    }
                }
            });
            for (File temp : fileList) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(temp.getName());
                if (temp.isDirectory()) {
                    fileInfo.setDir(true);
                    fileInfo.setSize(0);
                } else {
                    fileInfo.setDir(false);
                    fileInfo.setSize(temp.length());
                    int i = fileInfo.getName().lastIndexOf(".");
                    if (i != -1) {
                        fileInfo.setSuffix(fileInfo.getName().substring(i + 1));
                    }
                }
                fileInfo.setUpdateTime(new Date(temp.lastModified()));
                result.add(fileInfo);
            }
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * 保存分片
     *
     * @param shard 信息
     * @return R
     */
    @Override
    public ShardInfo saveShard(ShardInfo shard) {
        MultipartFile data = shard.getData();
        try {
            long start = System.currentTimeMillis();
            File destination = new File(String.format("%s%s%s%s%s_%s_%s", shardPath, File.separator, shard.getUploadId(),
                    File.separator, shard.getUploadId(), shard.getShard(), shard.getTotalShard()));
            FileUtils.copyInputStreamToFile(data.getInputStream(), destination);
            log.info(String.format("shard size:%s,time:%s", data.getSize(), System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shard;
    }

    /**
     * 合并分片
     *
     * @param uploadId 唯一id
     */
    @Override
    public void mergeShard(String uploadId, String fileName) {
        String shardParent = String.format("%s%s%s", shardPath, File.separator, uploadId);
        Collection<File> shardFiles = FileUtils.listFiles(new File(shardParent), null, false);
        File[] sortShardFiles = shardFiles.stream().sorted((o1, o2) -> {
            int num1 = Integer.parseInt(o1.getName().split("_")[1]);
            int num2 = Integer.parseInt(o2.getName().split("_")[1]);
            return num1 - num2;
        }).toArray(File[]::new);
        File resultFile = new File(String.format("D:\\software\\nginx-1.18.0\\html\\shard\\%s", fileName));
        long start = System.currentTimeMillis();
        mergeFile(sortShardFiles, resultFile);
        long end = System.currentTimeMillis();
        System.err.println(end - start);
    }

    /**
     * 删除资源
     *
     * @param prefix 名称
     */
    @Override
    public void deleteSource(List<String> prefix) {
        for (String s : prefix) {
            if (StringUtils.isNotBlank(s) && !"/".equals(s)) {
                File file = new File(basePath + s);
                try {
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            FileUtils.deleteDirectory(file);
                        } else {
                            FileUtils.forceDelete(file);
                        }
                    } else {
                        throw new BizException("资源不存在");
                    }
                } catch (IOException e) {
                    throw new BizException("删除失败");
                }
            }
        }
    }

    public static void mergeFile(File[] shardFile, File resultFile) {
        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (File file : shardFile) {
                FileChannel shardChanel = new FileInputStream(file).getChannel();
                resultFileChannel.transferFrom(shardChanel, resultFileChannel.size(), shardChanel.size());
                shardChanel.close();
            }
            resultFileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
