<template>
  <el-dialog
    class="simple-dialog"
    title="用户信息"
    :visible.sync="tempVisible"
    width="500px"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
    @close="close"
  >
    <el-form ref="formName" :rules="rules" :model="tempData" label-width="70px">
      <el-form-item label="用户名:" prop="username">
        <el-input v-model="tempData.username" :disabled="tempData.id&&null!==tempData.id"/>
      </el-form-item>
      <el-form-item v-if="!(tempData.id&&null!==tempData.id)" label="密码:" prop="password">
        <el-input v-model="tempData.password"/>
      </el-form-item>
      <el-form-item label="昵称:">
        <el-input v-model="tempData.nickName"/>
      </el-form-item>
      <el-form-item label="电话:">
        <el-input v-model="tempData.phone"/>
      </el-form-item>
      <el-form-item label="角色:">
        <el-checkbox-group v-model="tempData.role">
          <el-checkbox v-for="r in roles" :label="r.id">{{ r.name }}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="状态:">
        <el-switch v-model="tempData.isEnable"/>
        <span v-if="tempData.isEnable">启用</span>
        <span v-else>禁用</span>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="saveUpdate('formName')">
        <span v-if="this.tempData.id">确认修改</span>
        <span v-else>确认添加</span>
      </el-button>
    </span>
  </el-dialog>
</template>

<script>
  import systemUserApi from '@/api/system/user'
  import roleApi from '@/api/system/role'

  export default {
    name: 'UserAddEdit',
    props: {
      dialogVisible: {
        type: Boolean,
        default: false
      },
      data: {
        type: Object,
        default: {}
      }
    },
    data() {
      return {
        tempVisible: false,
        roles: [],
        tempData: {
          id: null,
          username: '',
          password: '',
          nickName: '',
          phone: '',
          role: [],
          isEnable: true
        },
        rules: {
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
          ]
        }
      }
    },
    watch: {
      dialogVisible: function(val) {
        if (val) {
          this.initRoles()
        }
        this.tempVisible = val
      },
      data: function(val) {
        this.tempData = val
      }
    },
    mounted() {

    },
    methods: {
      close() {
        this.$refs['formName'].clearValidate()
        this.$emit('close')
      },
      initRoles() {
        roleApi.list({ pageSize: 200 }).then(res => {
          this.roles = res.data || []
        })
      },
      saveUpdate(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const temp = {
              username: this.tempData.username,
              nickName: this.tempData.nickName,
              phone: this.tempData.phone,
              role: this.tempData.role,
              isEnable: this.tempData.isEnable
            }
            if (this.tempData.id) {
              temp.id = this.tempData.id
              systemUserApi.update(temp).then(res => {
                this.$message.success('更新成功')
                this.close()
              }).catch(() => {

              })
            } else {
              systemUserApi.save(temp).then(res => {
                this.$message.success('添加成功')
                this.$emit('close')
              }).catch(() => {

              })
            }
          } else {
            return false
          }
        })
      }
    }
  }
</script>

<style lang="scss">

</style>
