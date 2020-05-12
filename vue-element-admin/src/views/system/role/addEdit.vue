<template>
  <el-dialog title="角色信息" :visible.sync="tempDialogVisible" width="550px" :close-on-click-modal="false"
             @close="close">
    <el-form status-icon :model="tempData" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="角色名称：" prop="name">
        <el-input size="medium" v-model="tempData.name"></el-input>
      </el-form-item>
      <el-form-item label="角色描述">
        <el-input size="medium" type="textarea" v-model="tempData.description"></el-input>
      </el-form-item>
      <el-form-item label="角色权限：">
        <tree :setting="setting" :nodes="treePermission"
              @onCreated="handleCreated"/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import tree from 'vue-giant-tree'
  import roleApi from '@/api/system/role'

  export default {
    name: 'RoleAddEdit',
    components: { tree },
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
        tempDialogVisible: false,
        tempData: {
          id: null,
          name: null,
          description: null,
          permission: []
        },
        ztreeObj: null,
        setting: {
          check: {
            enable: true
          },
          data: {
            simpleData: {
              enable: true,
              idKey: 'id',
              pIdKey: 'pId',
              rootPId: 0
            }
          }
        },
        treePermission: [],
        rules: {
          name: [
            { required: true, message: '请输入角色名称', trigger: 'blur' }
          ]
        }
      }
    },
    watch: {
      dialogVisible: function(val) {
        this.tempDialogVisible = val
      },
      data: function(val) {
        this.tempData = val
        console.log(val)
      }
    },
    mounted() {
      this.initRole()
    },
    methods: {
      initRole() {
        this.treePermission = [
          { id: 1, pId: 0, name: '首页' },
          { id: 2, pId: 1, name: '控制台' },
          { id: 3, pId: 0, name: '系统用户' },
          { id: 4, pId: 3, name: '用户管理' },
          { id: 5, pId: 0, name: '权限中心' },
          { id: 6, pId: 5, name: '角色管理' },
          { id: 7, pId: 5, name: '菜单管理' },
          { id: 8, pId: 0, name: '租户管理' },
          { id: 9, pId: 8, name: '租户管理' }
        ]
      },
      handleCreated: function(ztreeObj) {
        this.ztreeObj = ztreeObj
        this.ztreeObj.expandAll(true)
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let checked = this.ztreeObj.getCheckedNodes(true)
            let subData = {
              name: this.tempData.name,
              description: this.tempData.description,
              permission: []
            }
            if (checked && checked.length > 0) {
              checked.forEach(item => {
                subData.permission.push(item.id)
              })
            }
            if (this.tempData.id) {
              subData.id = this.tempData.id
              roleApi.update(subData).then(res => {

              })
            } else {
              roleApi.save(subData).then(res => {

              })
            }
            console.log(checked)
          } else {
            return false
          }
        })
      },
      close() {
        this.ztreeObj.checkAllNodes(false)
        this.$refs['ruleForm'].clearValidate()
        this.$emit('close')
      }
    }
  }
</script>

<style scoped>

</style>
