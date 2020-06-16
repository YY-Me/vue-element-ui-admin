<template>
    <div class="file-item">
        <div class="file-head">
            <img v-lazy="file.dir?fileTypeImgList['dir']:(fileTypeImgList[file.suffix] || getTypeImgByFileName(file.name))"
                 class="image" alt="">
        </div>
        <div class="file-name">
            <span>{{file.name}}</span>
        </div>
    </div>
</template>

<script>
    import {fileTypeImgList} from '@/utils/consts'

    export default {
        name: "fileItem",
        props: {
            file: {
                type: Object,
                default: {}
            }
        },
        data() {
            return {
                fileTypeImgList
            }
        },
        methods: {
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
        }
    }
</script>

<style scoped lang="scss">
    .file-item {
        position: relative;
        display: inline-block;
        width: 105px;
        height: 100px;
        overflow: hidden;
        padding: 10px 10px 0 10px;
        margin-right: 5px;

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
</style>
