<template>
    <div>
        <el-dialog
                class="simple-dialog"
                title="上传文件"
                :visible.sync="tempVisible"
                width="700px"
                :close-on-press-escape="false"
                :close-on-click-modal="false"
                @close="close"
        >
            <el-form :rules="rules" :model="tempData" ref="ruleForm" label-width="70px" class="demo-ruleForm">
                <el-form-item label="目录名" prop="folderName">
                    <el-input type="text" placeholder="请输入目录名" v-model="tempData.folderName"
                              maxlength="10" show-word-limit >
                    </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="close">取 消</el-button>
              <el-button type="primary" @click="createFolder('ruleForm')">
            <span>立即创建</span>
      </el-button>
    </span>
        </el-dialog>
    </div>
</template>

<script>
    import fileApi from '@/api/file/file'
    export default {
        name: "uploadFile",
        props: {
            dialogVisible: {
                type: Boolean,
                default: false
            },
            parentPath: {
                type: String,
                default: ''
            }
        },
        data(){
            return{
                tempVisible: false,
                tempData:{
                    folderName: ''
                },
                rules: {
                    folderName: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
                    ],
                }
            }
        },
        watch: {
            dialogVisible: function(val) {
                this.tempVisible = val
            }
        },
        methods: {
            createFolder(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        fileApi.createFolder({
                            parentPath: this.parentPath,
                            folderName: this.tempData.folderName
                        }).then(res=>{
                            this.$message.success('创建成功')
                            this.close()
                            this.$emit('created')
                        })
                    } else {
                        return false
                    }
                })
            },
            close() {
                this.tempData.folderName = ''
                this.$refs['ruleForm'].clearValidate()
                this.$emit('close')
            },
        }
    }
</script>

<style scoped>

</style>
