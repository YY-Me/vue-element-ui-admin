<template>
  <el-card shadow="always">
    <div slot="header">
      <el-form size="small" :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="用户名:">
          <el-input v-model="formInline.user" placeholder="审批人" clearable />
        </el-form-item>
        <el-form-item label="状态:">
          <el-select v-model="formInline.region" placeholder="活动区域" clearable>
            <el-option label="启用" value="shanghai" />
            <el-option label="已禁用" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column
          type="selection"
          width="50"
        />
        <el-table-column
          prop="name"
          label="用户名"
          min-width="120"
        />
        <el-table-column
          prop="province"
          label="昵称"
          min-width="120"
        />
        <el-table-column
          prop="city"
          label="电话"
          min-width="120"
        />
        <el-table-column
          prop="address"
          label="角色"
          min-width="200"
        />
        <el-table-column
          prop="zip"
          label="状态"
          min-width="120"
        />
        <el-table-column
          prop="date"
          label="更新时间"
          min-width="150"
        />
        <el-table-column
          fixed="right"
          label="操作"
          width="100"
        >
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
    </div>
  </el-card>
</template>
<script>
import Pagination from '@/components/Pagination'

export default {
  name: 'SystemUser',
  components: { Pagination },
  data() {
    return {
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
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
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

    }
  }
}
</script>
