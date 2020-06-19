package vip.bblog.cunadmin.modules.file.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.modules.file.config.FileServiceFactory;
import vip.bblog.cunadmin.modules.file.dto.CreateFolderDTO;
import vip.bblog.cunadmin.modules.file.dto.ShardMergeInfo;
import vip.bblog.cunadmin.modules.file.dto.UploadInfo;
import vip.bblog.cunadmin.modules.file.entity.FileInfo;
import vip.bblog.cunadmin.modules.file.entity.ShardInfo;
import vip.bblog.cunadmin.modules.file.enums.FileSource;
import vip.bblog.cunadmin.modules.file.service.FileService;

import java.util.List;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月11日 14:22
 * @desc FileController
 */
@Api(tags = "web:文件管理")
@ApiSort(1)
@RestController
@RequestMapping("/system/file")
public class FileController {

    @Autowired
    private FileServiceFactory fileServiceFactory;

    @GetMapping
    public BaseResult<List<FileInfo>> listFiles(String prefix) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        List<FileInfo> fileInfos = fileService.listFileByKeyPrefix(prefix);
        return BaseResult.success(fileInfos);
    }

    @ApiOperation(value = "创建文件夹", notes = "在某个路径下创建文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prefix", value = "文件夹创建target", example = "/test"),
            @ApiImplicitParam(name = "folderName", value = "文件夹名称", example = "hello")
    })
    @PostMapping("folder")
    public BaseResult<String> createFolder(@Validated @RequestBody CreateFolderDTO folder) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        fileService.createFolder(folder);
        return BaseResult.success();
    }

    @PostMapping
    public BaseResult<FileInfo> uploadFile(@Validated UploadInfo uploadInfo) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        FileInfo fileInfo = fileService.saveFile(uploadInfo);
        return BaseResult.success(fileInfo);
    }

    @PostMapping("shard")
    public BaseResult<String> uploadShard(@Validated ShardInfo shard) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        fileService.saveShard(shard);
        return BaseResult.success();
    }

    @PostMapping("mergeShard")
    public BaseResult<FileInfo> mergeShard(@Validated @RequestBody ShardMergeInfo shardMergeInfo) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        fileService.mergeShard(shardMergeInfo);
        return BaseResult.success();
    }

    @DeleteMapping
    public BaseResult<String> deleteSource(@RequestBody List<String> prefix) {
        FileService fileService = fileServiceFactory.getFileService(FileSource.LOCAL.toString());
        fileService.deleteSource(prefix);
        return BaseResult.success();
    }


}
