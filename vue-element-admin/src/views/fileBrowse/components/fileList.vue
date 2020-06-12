<template>
    <div class="file-list" @click="fileOtherClick">
        <div class="file-list-top">
            <el-button size="small" type="primary" @click.stop="uploadFile">上传文件</el-button>
            <el-button size="small" @click="createFolder">新建目录</el-button>
            <el-button size="small" type="danger" icon="el-icon-delete" :disabled="canCallBack"
                       @click.stop="deleteResource">删除
            </el-button>
            <el-button size="small" @click="listFile">刷新</el-button>
        </div>
        <el-breadcrumb class="file-crumb" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>根目录</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
        <!--80是顶部和底部的大概高度，顶部的button固定为small-->
        <div class="file-list-center" :style="{height: (height-80) +'px'}">
            <div @click.stop="fileClick(file,index,$event)" v-for="(file,index) in fileList" class="file-item"
                 @dblclick.stop="fileDBClick(file,index,$event)"
                 :class="{'file-item-checked':checkSelected(index)}">
                <div class="file-head">
                    <img v-lazy="file.dir?fileTypeImgList['dir']:(fileTypeImgList[file.suffix] || getTypeImgByFileName(file.name))"
                         class="image" alt="">
                </div>
                <div class="file-name">
                    <span>{{file.name}}</span>
                </div>
            </div>
        </div>
        <div class="file-list-tip">
            <span v-if="fileSelectedList.length===0">{{fileList.length}}项</span>
            <span v-if="fileSelectedList.length>0">已选中{{fileSelectedList.length}}项</span>
            <template v-if="fileSelectedList.length===1">
                <span>，信息:</span>
                <span v-if="fileSelectedList[0].size&&fileSelectedList[0].size>0">大小:{{formatFileSize(fileSelectedList[0].size)}}</span>
                <span>修改时间:{{fileSelectedList[0].updateTime}}</span>
            </template>
            <el-button @click.stop="selectedCallBack" :disabled="canCallBack" class="callback-btn" type="primary"
                       size="small">确认选择
            </el-button>
        </div>
        <!--创建文件夹-->
        <create-folder :dialog-visible="createFolderVisible" @close="createFolderVisible = false" @created="listFile"/>
        <!--上传文件-->
        <upload-file :parent-path="currentPath" :dialog-visible="uploadFileVisible"
                     @uploaded="listFile"
                     @close="uploadFileVisible = false"/>
    </div>
</template>

<script>

    import {formatFileSize} from '@/utils/file'
    import {fileTypeImgList} from '@/utils/consts'
    import createFolder from "./createFolder"
    import uploadFile from "./uploadFile"
    import fileApi from '@/api/file/file'

    export default {
        name: "fileList",
        components: {createFolder, uploadFile},
        props: {
            height: {
                type: Number,
                default: 300
            },
            callback: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                fileTypeImgList,
                createFolderVisible: false,
                uploadFileVisible: false,
                currentPath: '/',
                fileList: [],
                fileSelectedList: []
            }
        },
        computed: {
            checkSelected() {
                return function (index) {
                    let exist = false
                    for (let i = 0; i < this.fileSelectedList.length; i++) {
                        if (this.fileSelectedList[i].index === index) {
                            exist = true
                            break
                        }
                    }
                    return exist
                }
            },
            canCallBack() {
                return this.fileSelectedList.length === 0
            }
        },
        mounted() {
            this.listFile()
        },
        methods: {
            formatFileSize,
            listFile() {
                fileApi.listFile({prefix: '/'}).then(res => {
                    this.fileList = res.data || []
                })
            },
            fileClick(file, index, event) {
                if (event.ctrlKey) {
                    this.fileCtrlClick(file, index)
                } else {
                    this.fileSelectedList = []
                    let temp = JSON.parse(JSON.stringify(file))
                    temp.index = index
                    this.fileSelectedList.push(temp)
                }
            },
            fileDBClick(file, index, event) {
                this.fileSelectedList = []
            },
            fileCtrlClick(file, index) {
                let exist = false
                for (let i = 0; i < this.fileSelectedList.length; i++) {
                    if (this.fileSelectedList[i].index === index) {
                        exist = true
                        this.fileSelectedList.splice(i, 1)
                        break
                    }
                }
                if (!exist) {
                    let temp = JSON.parse(JSON.stringify(file))
                    temp.index = index
                    this.fileSelectedList.push(temp)
                }
            },
            fileOtherClick() {
                this.fileSelectedList.splice(0)
            },
            selectedCallBack() {
                this.$emit('callback', JSON.parse(JSON.stringify(this.fileSelectedList)))
            },
            getTypeImgByFileName(fileName) {
                let type = this.fileTypeImgList.other
                if (fileName) {
                    let index1 = fileName.lastIndexOf(".")
                    if (index1 !== -1) {
                        let suffix = fileName.substring(index1 + 1, fileName.length)
                        type = this.fileTypeImgList[suffix] || type
                    }
                }
                return type
            },
            deleteResource() {
                this.$confirm('此操作将永久删除该资源, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let source = []
                    this.fileSelectedList.forEach(file => {
                        source.push(`${this.currentPath + file.name}`)
                    })
                    fileApi.deleteResource(source).then(res => {
                        this.$message({
                            type: 'success',
                            message: '删除成功'
                        })
                        this.listFile()
                    })
                })
            },
            uploadFile() {
                this.uploadFileVisible = true
            },
            createFolder() {
                this.createFolderVisible = true
            }
        }
    }
</script>
<style lang="scss" scoped>
    .file-list {
        height: 100%;
        position: relative;

        .file-list-top {

        }

        .file-crumb {
            line-height: 14px;
            overflow: hidden;
            margin-top: 15px;

            /deep/ .el-breadcrumb__item {

            }
        }

        .file-list-center {
            margin: 10px 0;
            overflow-y: auto;
            box-sizing: border-box;

            .file-item {
                position: relative;
                display: inline-block;
                width: 105px;
                height: 100px;
                overflow: hidden;
                padding: 10px 10px 0 10px;
                margin-right: 5px;

                &.file-item-checked {
                    border: 1px solid #1ed0ff;
                    padding: 9px 9px 0 9px;
                    border-radius: 1px;
                    background: #daf5ff;
                }

                .file-head {
                    height: 60px;
                    width: 100%;
                    overflow: hidden;
                    text-align: center;

                    img {
                        width: auto;
                        height: auto;
                        max-width: 100%;
                        max-height: 100%;
                        border-radius: 2px;
                    }
                }

                .file-name {
                    height: 30px;
                    line-height: 30px;
                    width: 100%;
                    overflow: hidden;
                    text-align: center;
                    font-size: 13px;
                    text-overflow: ellipsis;
                    white-space: nowrap;
                }
            }
        }

        .file-list-tip {
            position: relative;
            padding-left: 5px;
            font-size: 14px;
            color: #ababce;

            > span {
                margin-right: 5px;
                line-height: 26px;
            }

            .callback-btn {
                position: absolute;
                right: 10px;
            }
        }
    }
</style>
