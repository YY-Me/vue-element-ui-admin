<template>
  <el-card shadow="always">
    <div slot="header">
      <el-form size="small" :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="用户名:">
          <el-input v-model="formInline.user" placeholder="用户名/电话" clearable/>
        </el-form-item>
        <el-form-item label="状态:">
          <el-select v-model="formInline.region" placeholder="用户状态" clearable>
            <el-option label="启用" :value="true"/>
            <el-option label="已禁用" :value="false"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" icon="el-icon-circle-plus">增加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column type="selection" width="50"/>
        <el-table-column prop="userName" label="用户名" min-width="120"/>
        <el-table-column prop="nickName" label="昵称" min-width="120"/>
        <el-table-column prop="phone" label="电话" min-width="120"/>
        <el-table-column label="角色" min-width="200">
          <template slot-scope="scope">
            <el-tag v-for="r in scope.row.role" size="mini">{{ r }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="状态" min-width="120">
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
        <el-table-column prop="updateTime" label="更新时间" min-width="150"/>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handleClick(scope.row)">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        :total="total"
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="getList"
      />
      <!--添加/编辑-->
      <add-edit :dialog-visible="addEditVisible" :operation-type="operationType"/>
    </div>
  </el-card>
</template>
<script>
  import Pagination from '@/components/Pagination'
  import addEdit from '@/views/system/user/addEdit'

  export default {
    name: 'system-user',
    components: { Pagination, addEdit },
    data() {
      return {
        addEditVisible: false,
        operationType: 1,
        formInline: {
          user: '',
          region: ''
        },
        total: 0,
        listQuery: {
          page: 1,
          pageSize: 10
        },
        tableData: [{
          userName: '王小虎',
          nickName: '王小虎',
          phone: '70983928',
          role: ['超级管理员'],
          isEnable: true,
          createTime: '2016-05-02',
          updateTime: '2016-05-02'
        }]
      }
    },
    computed: {
      theme() {
        return this.$store.state.settings.theme
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!')
      },
      getList() {

      },
      switchChange(row) {

      },
      userAdd(){

      }
    }
  }
</script>
<style scoped lang="scss">

</style>
