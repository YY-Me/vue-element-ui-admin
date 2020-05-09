<template>
  <el-card shadow="always">
    <div slot="header" ref="roleSearchHeader">
      <el-form :inline="true" :model="listQuery">
        <el-form-item label="角色名:">
          <el-input style="width: 160px" v-model="listQuery.name" placeholder="角色名" clearable/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table ref="tableCot" v-loading="loading" element-loading-text="请稍后..." :data="roleData" border
                style="width: 100%" :max-height="customTableHeight">
        <el-table-column type="index" width="40"/>
        <el-table-column prop="name" label="角色名" min-width="120" show-overflow-tooltip/>
        <el-table-column prop="description" label="角色描述" min-width="120" show-overflow-tooltip/>
        <el-table-column prop="createUserName" label="创建人" min-width="120" show-overflow-tooltip/>
        <el-table-column prop="phone" label="最近更新时间" min-width="120" show-overflow-tooltip/>
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
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-tag style="cursor:pointer;" size="mini" @click="addEdit(scope.row)">编辑</el-tag>
            <el-tag style="cursor:pointer;" size="mini" type="danger" @click="remove(scope.row)">删除</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList"/>
    </div>
  </el-card>
</template>
<script>
  import tree from 'vue-giant-tree'
  import Pagination from '@/components/Pagination'

  export default {
    name: 'systemRole',
    components: { Pagination, tree },
    data() {
      return {
        ztreeObj: null,
        nodes: [
          { id: 1, parentId: 0, name: '随意勾选 1', open: true }
        ],
        setting: {
          check: {
            enable: true
          },
          data: {
            simpleData: {
              enable: true,
              idKey: 'id',
              pIdKey: 'parentId',
              rootPId: -1
            }
          }
        },
        loading: false,
        listQuery: {
          name: '',
          page: 1,
          pageSize: 10
        },
        total: 0,
        roleData: [],
        customTableHeight: 200
      }
    },
    mounted() {

      let that = this
      window.onresize = () => {
        let tempHeaderHeight = this.$refs.roleSearchHeader.offsetHeight + 37
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
    methods: {
      onSubmit() {
        console.log('submit!')
      },
      search() {

      },
      getList() {

      }
    }
  }
</script>
