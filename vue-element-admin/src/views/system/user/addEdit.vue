<template>
  <el-dialog
    class="add-edit"
    title="用户信息"
    :visible.sync="tempVisible"
    width="500px"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
    @close="close"
  >
    <el-form ref="formName" :rules="rules" :model="tempData" label-width="70px">
      <el-form-item label="用户名:" prop="userName">
        <el-input v-model="tempData.userName" :disabled="tempOperationType===2" />
      </el-form-item>
      <el-form-item label="昵称:">
        <el-input v-model="tempData.nickName" />
      </el-form-item>
      <el-form-item label="电话:">
        <el-input v-model="tempData.phone" />
      </el-form-item>
      <el-form-item label="角色:">
        <el-checkbox-group v-model="tempData.role">
          <el-checkbox v-for="r in roles" :label="r.id">{{ r.name }}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="状态:">
        <el-switch v-model="tempData.isEnable" />
        <span v-if="tempData.isEnable">启用</span>
        <span v-else>禁用</span>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="saveUpdate('formName')">
        <span v-if="tempOperationType===1">确认添加</span>
        <span v-else>确认修改</span>
      </el-button>
    </span>
  </el-dialog>
</template>

<script>
import systemUserApi from '@/api/system/user'

export default {
  name: 'AddEdit',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    operationType: {
      type: Number,
      default: 1
    },
    data: {
      type: Object,
      default: {}
    }
  },
  data() {
    return {
      tempVisible: false,
      tempOperationType: 1,
      roles: [],
      tempData: {
        userName: '',
        nickName: '',
        phone: '',
        role: [],
        isEnable: true
      },
      rules: {
        userName: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  watch: {
    dialogVisible: function(val) {
      this.tempVisible = val
    },
    operationType: function(val) {
      this.tempOperationType = val
    },
    data: function(val) {
      this.tempData = val
    }
  },
  mounted() {
    this.initRoles()
  },
  methods: {
    close() {
      this.$refs['formName'].clearValidate()
      this.$emit('close')
    },
    initRoles() {
      this.roles = [{
        id: 1,
        name: '管理员'
      },
      {
        id: 2,
        name: '操作員'
      },
      {
        id: 3,
        name: '测试'
      }]
    },
    saveUpdate(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const temp = {
            userName: this.tempData.userName,
            nickName: this.tempData.nickName,
            phone: this.tempData.phone,
            role: this.tempData.role,
            isEnable: this.tempData.isEnable
          }
          if (this.tempOperationType === 1) {
            systemUserApi.save(temp).then(res => {

            }).catch(() => {

            })
          } else {
            temp.id = this.tempData.id
            systemUserApi.update(temp).then(res => {

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
  .add-edit {
    .el-dialog {
      .el-dialog__body {
        padding: 10px 20px 0 20px !important;
      }
    }
  }
</style>
