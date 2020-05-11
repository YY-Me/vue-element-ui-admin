<template>
  <el-card shadow="always">
    <div slot="header" ref="sysUserSearchHeader">
      <el-form :inline="true" :model="listQuery">
        <el-form-item label="用户名:">
          <el-input style="width: 160px" v-model="listQuery.namePhone" placeholder="用户名/电话" clearable/>
        </el-form-item>
        <el-form-item label="状态:">
          <el-select v-model="listQuery.isEnable" style="width: 120px" placeholder="用户状态" clearable>
            <el-option label="启用" :value="true"/>
            <el-option label="已禁用" :value="false"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addEdit(null)">增加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <!--table data-->
      <el-table ref="tableCot" v-loading="loading" element-loading-text="请稍后..." :data="tableData" border
                style="width: 100%" :max-height="customTableHeight">
        <el-table-column type="selection" width="40"/>
        <el-table-column prop="userName" label="用户名" min-width="100" show-overflow-tooltip/>
        <el-table-column prop="nickName" label="昵称" min-width="100" show-overflow-tooltip/>
        <el-table-column prop="phone" label="电话" min-width="100" show-overflow-tooltip/>
        <el-table-column label="角色" min-width="150" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag v-for="r in scope.row.roles" size="mini">{{ r.name }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="状态" min-width="100">
          <template slot-scope="scope">
            <el-tooltip :content="scope.row.isEnable?'已启用':'已禁用'" placement="top">
              <el-switch
                v-model="scope.row.isEnable"
                :active-value="true"
                :inactive-value="false"
                @change="switchChange(scope.row)"
              />
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="createUserName" label="创建人" min-width="100" show-overflow-tooltip/>
        <el-table-column prop="updateTime" label="最近更新时间" min-width="120"/>
        <el-table-column fixed="right" label="操作" width="126">
          <template slot-scope="scope">
            <el-tag style="cursor:pointer;" size="mini" @click="addEdit(scope.row)"><i class="el-icon-edit"/>编辑</el-tag>
            <el-tag style="cursor:pointer;" size="mini" type="danger" @click="remove(scope.row)"><i
              class="el-icon-delete"/>删除
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
      <!--分頁-->
      <pagination :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList"/>
      <!--添加/编辑-->
      <add-edit
        :dialog-visible="addEditVisible"
        :data="addEditData"
        @close="closeDialog"
      />
    </div>
  </el-card>
</template>
<script>
  import Pagination from '@/components/Pagination'
  import addEdit from '@/views/system/user/addEdit'
  import systemUserApi from '@/api/system/user'

  export default {
    name: 'SystemUser',
    components: { Pagination, addEdit },
    data() {
      return {
        loading: false,
        addEditVisible: false,
        customTableHeight: 200,
        formInline: {
          user: '',
          region: ''
        },
        total: 0,
        listQuery: {
          page: 1,
          pageSize: 10,
          namePhone: null,
          isEnable: null
        },
        tableData: [],
        addEditData: {}
      }
    },
    computed: {
      theme() {
        return this.$store.state.settings.theme
      }
    },
    mounted() {
      this.getList()
      let that = this
      window.onresize = () => {
        let tempHeaderHeight = this.$refs.sysUserSearchHeader.offsetHeight + 37
        let tempHeight = document.body.clientHeight - (200 + tempHeaderHeight)
        if (tempHeight < 300) {
          tempHeight = 300
        }
        that.customTableHeight = tempHeight
      }
      setTimeout(function() {
        const resizeEvent = new Event('resize')
        window.dispatchEvent(resizeEvent)
      }, 100)
    },
    destroyed() {
      window.onresize = null
    },
    methods: {
      search() {
        this.getList()
      },
      getList() {
        this.loading = true
        systemUserApi.list(this.listQuery).then(res => {
          this.tableData = res.data || []
          this.loading = false
        }).catch(() => this.loading = false)
      },
      switchChange(row) {
        const enable = row.isEnable
        let text = '是否禁用该账户？'
        if (enable) {
          text = '确认启用该账户？'
        }
        this.$confirm(text, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          closeOnPressEscape: false,
          closeOnClickModal: false,
          type: 'warning'
        }).then(() => {
          systemUserApi.status(enable).then(res => {

          }).catch(() => {
            row.isEnable = !enable
          })
        }).catch(() => {
          row.isEnable = !enable
        })
      },
      addEdit(row) {
        if (row) {
          row.role = []
          if (row.roles) {
            row.roles.forEach(item => {
              row.role.push(item.id)
            })
          }
          this.addEditData = JSON.parse(JSON.stringify(row))
        }
        this.addEditVisible = true
      },
      remove(row) {
        this.$confirm('删除后将无法恢复，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          closeOnPressEscape: false,
          closeOnClickModal: false,
          type: 'warning'
        }).then(() => {
          systemUserApi.remove(row.id).then(res => {

          })
        })
      },
      closeDialog() {
        this.addEditVisible = false
        this.addEditData = {
          id: null,
          userName: '',
          nickName: '',
          phone: '',
          role: [],
          isEnable: true
        }
      }
    }
  }
</script>
<style scoped lang="scss">

</style>
