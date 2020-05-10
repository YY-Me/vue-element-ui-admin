<template>
  <el-card shadow="always">
    <div>
      <el-table :data="tableData" style="width: 100%;"
                row-key="id" border
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column prop="name" label="名称" min-width="150" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input style="width: 50%" size="small" v-model="scope.row.name" v-if="scope.row.edit"
                      placeholder="名称">
              {{scope.row.name}}
            </el-input>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="路由地址" min-width="150" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.path" v-if="scope.row.edit" placeholder="路由地址">
              {{scope.row.path}}
            </el-input>
            <span v-else>{{scope.row.path}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="icon" label="图标" min-width="60" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.icon">
              <i v-if="scope.row.icon.indexOf('el-icon')!==-1" :class="scope.row.icon"></i>
              <svg-icon v-else :icon-class="scope.row.icon"/>
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="序号" min-width="60" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.sort" v-if="scope.row.edit" placeholder="序号">
              {{scope.row.sort}}
            </el-input>
            <span v-else>{{scope.row.sort}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="permission" label="权限标识" min-width="120" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.permission" v-if="scope.row.edit" placeholder="权限标识">
              {{scope.row.permission}}
            </el-input>
            <span v-else>{{scope.row.permission}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="类型" min-width="100" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-select v-if="scope.row.edit" size="small" v-model="scope.row.type" placeholder="请选择">
              <el-option label="菜单" :value="1"></el-option>
              <el-option label="按钮" :value="2"></el-option>
            </el-select>
            <span v-else>
              <el-tag size="mini" v-if="scope.row.type===1" type="success">菜单</el-tag>
              <el-tag size="mini" v-else-if="scope.row.type===2" type="warning">按钮</el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column min-width="170" fixed="right" label="操作" align="center" class-name="small-padding"
                         style="padding: 7px 10px;">
          <template slot-scope="scope">
            <el-button v-if="scope.row.edit" style="margin-right: 8px" type="text" size="mini"
                       @click="save(scope.row)">
              保存
            </el-button>
            <el-button v-else style="margin-right: 8px" type="text" size="mini"
                       @click="edit(scope.row)">
              编辑
            </el-button>
            <el-dropdown @command="((type)=>handleCommand(type,scope.row))" size="mini">
              <el-button size="mini" type="text">
                增加菜单
                <i class="el-icon-arrow-down el-icon--right"/>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item :command="1">同级菜单</el-dropdown-item>
                <el-dropdown-item :command="2">子菜单</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</template>
<script>
  export default {
    name: 'systemMenu',
    data() {
      return {
        tableData: [{
          id: 1,
          name: '首页',
          path: '/',
          icon: 'dashboard',
          type: 1,
          sort: 0,
          permission: 'index',
          edit: false,
          children: [{
            id: 11,
            name: '控制台',
            path: 'dashboard',
            icon: 'dashboard',
            type: 1,
            permission: 'dashboard',
            sort: 0
          }]
        }, {
          id: 2,
          name: '系统用户',
          permission: 'sys:user:index',
          path: '/system',
          icon: 'system-user',
          type: 1,
          sort: 0,
          children: [{
            id: 21,
            name: '用户管理',
            permission: 'sys:user:list',
            path: 'user',
            icon: 'system-user',
            type: 1,
            sort: 0
          }]
        }, {
          id: 3,
          name: '权限中心',
          path: '/permission',
          icon: 'permission',
          permission: 'sys:permission:index',
          type: 1,
          sort: 0,
          children: [{
            id: 31,
            name: '角色管理',
            permission: 'sys:role',
            path: 'role',
            icon: 'role',
            type: 1,
            sort: 0
          }, {
            id: 32,
            name: '菜单管理',
            permission: 'sys:menu',
            path: 'menu',
            icon: 'menu',
            type: 1,
            sort: 0
          }]
        }, {
          id: 4,
          name: '租户管理',
          permission: 'sys:tenant:index',
          path: '/tenant',
          icon: 'tenant',
          type: 1,
          sort: 0,
          children: [{
            id: 41,
            name: '租户管理',
            permission: 'sys:tenant:list',
            path: 'tenant',
            icon: 'tenant',
            type: 1,
            sort: 0
          }]
        }]
      }
    },
    methods: {
      edit(row) {
        row.edit = true
        console.log(row)
      },
      save(row) {
        row.edit = false
      },
      handleCommand(type, row) {

      }
    }
  }
</script>
