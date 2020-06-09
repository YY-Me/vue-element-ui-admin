<template>
    <div class="file-list" @click="fileOtherClick">
        <div class="file-list-top">
            <el-button size="small" type="primary">上传文件</el-button>
            <el-button size="small">新建目录</el-button>
            <el-button size="small" type="danger" icon="el-icon-delete">删除</el-button>
            <el-button size="small">刷新</el-button>
        </div>
        <!--80是顶部和底部的大概高度，顶部的button固定为small-->
        <div class="file-list-center" :style="{height: (height-80) +'px'}">
            <div @click.stop="fileClick(file,index,$event)" v-for="(file,index) in fileList" class="file-item"
                 :class="{'file-item-checked':checkSelected(index)}">
                <div class="file-head">
                    <img :src="fileTypeList[file.type] || getTypeImgByFileName(file.name)" class="image" alt="">
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
                <span v-if="fileSelectedList[0].size&&fileSelectedList[0].size>0">大小:{{getFormatFileSize(fileSelectedList[0].size)}}</span>
                <span>修改时间:{{fileSelectedList[0].updateTime}}</span>
            </template>
            <el-button @click.stop="selectedCallBack" :disabled="canCallBack" class="callback-btn" type="primary" size="small">确认选择</el-button>
        </div>
    </div>
</template>

<script>
    import { formatFileSize } from '@/utils/file'
    export default {
        name: "fileList",
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
        data(){
            return{
                fileTypeList: {
                    "audio": "/images/file/audio.svg",
                    "cad": "/images/file/cad.svg",
                    "collect": "/images/file/collect.svg",
                    "excel": "/images/file/excel.svg",
                    "exe": "/images/file/exe.svg",
                    "dir": "/images/file/folder.svg",
                    "html": "/images/file/html.svg",
                    "png": "/images/file/image.svg",
                    "jpg": "/images/file/image.svg",
                    "jpeg": "/images/file/image.svg",
                    "gif": "/images/file/image.svg",
                    "bmp": "/images/file/image.svg",
                    "iso": "/images/file/iso.svg",
                    "other": "/images/file/other.svg",
                    "pdf": "/images/file/pdf.svg",
                    "ppt": "/images/file/ppt.svg",
                    "psd": "/images/file/psd.svg",
                    "recycle": "/images/file/recycle.svg",
                    "txt": "/images/file/txt.svg",
                    "mp4": "/images/file/video.svg",
                    "word": "/images/file/word.svg",
                    "zip": "/images/file/zip.svg",
                    "rar": "/images/file/zip.svg",
                    "7z": "/images/file/zip.svg"
                },
                fileList:[
                    {
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    },{
                        name: '网站数据',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '视频文件',
                        size: 0,
                        type: 'dir',
                        updateTime: '2020-06-09 12:00'
                    },
                    {
                        name: '头像.png',
                        size: 114179,
                        type: 'png',
                        updateTime: '2020-06-09 12:00'
                    }
                ],
                fileSelectedList:[]
            }
        },
        computed:{
            checkSelected(){
                return function(index){
                    let exist = false
                    for (let i = 0; i < this.fileSelectedList.length; i++) {
                        if (this.fileSelectedList[i].index===index){
                            exist = true
                            break
                        }
                    }
                    return exist
                }
            },
            canCallBack(){
                return this.fileSelectedList.length===0
            }
        },
        mounted() {
            this.init()
        },
        methods: {
            init(){
            },
            fileClick(file,index,event){
                if (event.ctrlKey){
                    this.fileCtrlClick(file,index)
                }else {
                    this.fileSelectedList=[]
                    let temp = JSON.parse(JSON.stringify(file))
                    temp.index = index
                    this.fileSelectedList.push(temp)
                }
            },
            fileCtrlClick(file,index){
                let exist= this.checkSelected(index)
                if (exist){
                    this.fileSelectedList.splice(index,1)
                }else {
                    let temp=JSON.parse(JSON.stringify(file))
                    temp.index=index
                    this.fileSelectedList.push(temp)
                }
            },
            fileOtherClick(){
                this.fileSelectedList.splice(0)
            },
            selectedCallBack(){

            },
            getTypeImgByFileName(fileName){
                let type= this.fileTypeList.other
                if (fileName){
                    let index1=fileName.lastIndexOf(".")
                    if (index1!==-1){
                        let suffix=fileName.substring(index1+1,fileName.length)
                        type = this.fileTypeList[suffix] || type
                    }
                }
                return type
            },
            getFormatFileSize(size){
                return formatFileSize(size)
            }
        }
    }
</script>
<style lang="scss" scoped>
    .file-list{
        height: 100%;
        position: relative;
        .file-list-top {

        }
        .file-list-center {
            margin: 10px 0;
            overflow-y: auto;
            box-sizing: border-box;
            .file-item{
                position: relative;
                display: inline-block;
                width: 125px;
                height:120px;
                overflow:hidden;
                padding: 10px 10px 0 10px;
                margin: 0 5px;
                &.file-item-checked{
                    border: 1px solid #1ed0ff;
                    padding: 9px 9px 0 9px;
                    border-radius: 1px;
                    background: #daf5ff;
                }
                .file-head{
                    height: 80px;
                    width: 100%;
                    overflow:hidden;
                    text-align: center;
                    img{
                        width: auto;
                        height: auto;
                        max-width: 100%;
                        max-height: 100%;
                        border-radius: 2px;
                    }
                }
                .file-name{
                    height: 30px;
                    line-height: 30px;
                    width: 100%;
                    overflow:hidden;
                    text-align: center;
                    font-size: 13px;
                }
            }
        }
        .file-list-tip {
            position: relative;
            padding-left: 5px;
            font-size: 14px;
            color: #ababce;
            >span{
                margin-right: 5px;
                line-height: 26px;
            }
            .callback-btn{
                position: absolute;
                right: 10px;
            }
        }
    }
</style>
