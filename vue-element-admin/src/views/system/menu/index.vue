<template>
  <el-card shadow="always">
    <div>
      <el-table :data="tableData" style="width: 100%;"
                row-key="id" border default-expand-all
                :max-height="customTableHeight"
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
            <span style="cursor:pointer;" v-if="scope.row.icon" @click="editIcon(scope.row)">
              <i v-if="scope.row.icon.indexOf('el-icon')!==-1" :class="scope.row.icon"></i>
              <svg-icon v-else :icon-class="scope.row.icon"/>
            </span>
            <span style="cursor:pointer;" v-else @click="editIcon(scope.row)">
              无
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="序号" min-width="110">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.sort" size="small" controls-position="right" placeholder="序号"
                             v-if="scope.row.edit" :min="0" style="width: 90px"/>
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
        <el-table-column width="170" fixed="right" label="操作" align="center">
          <template slot-scope="scope">
            <el-button v-if="scope.row.edit" style="margin-right: 8px" type="text" size="mini"
                       @click="cancel(scope.row)">
              取消
            </el-button>
            <el-tag v-if="scope.row.edit" style="cursor:pointer;" size="mini" @click="save(scope.row)">
              <i class="el-icon-finished"/>保存
            </el-tag>
            <el-tag v-else style="cursor:pointer;" size="mini" @click="edit(scope.row)">
              <i class="el-icon-edit"/>编辑
            </el-tag>
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
      <icon
        :dialog-visible="iconVisible"
        :bind-data="iconBindData"
        @selected="saveIcon"
        @close="closeIconDialog"
      />
    </div>
  </el-card>
</template>
<script>
  import icon from '@/views/system/menu/icon'
  import menuApi from '@/api/system/menu'

  export default {
    name: 'systemMenu',
    components: { icon },
    data() {
      return {
        iconVisible: false,
        iconBindData: null,
        customTableHeight: 200,
        tableData: [{
          id: 1,
          pId: 0,
          name: '首页',
          path: '/',
          icon: 'dashboard',
          type: 1,
          sort: 0,
          permission: 'index',
          edit: false,
          children: [{
            id: 11,
            pId: 1,
            name: '控制台',
            path: 'dashboard',
            icon: 'dashboard',
            type: 1,
            permission: 'dashboard',
            edit: false,
            sort: 0,
            children: []
          }]
        }, {
          id: 2,
          pId: 0,
          name: '系统用户',
          permission: 'sys:user:index',
          path: '/system',
          icon: 'system-user',
          type: 1,
          sort: 0,
          edit: false,
          children: [{
            id: 21,
            pId: 2,
            name: '用户管理',
            permission: 'sys:user:list',
            path: 'user',
            icon: 'system-user',
            type: 1,
            edit: false,
            sort: 0,
            children: []
          }]
        }, {
          id: 3,
          pId: 0,
          name: '权限中心',
          path: '/permission',
          icon: 'permission',
          permission: 'sys:permission:index',
          type: 1,
          sort: 0,
          edit: false,
          children: [{
            id: 31,
            pId: 3,
            name: '角色管理',
            permission: 'sys:role',
            path: 'role',
            icon: 'role',
            type: 1,
            edit: false,
            sort: 0,
            children: []
          }, {
            id: 32,
            pId: 3,
            name: '菜单管理',
            permission: 'sys:menu',
            path: 'menu',
            icon: 'menu',
            type: 1,
            edit: false,
            sort: 0,
            children: []
          }]
        }, {
          id: 4,
          pId: 0,
          name: '租户管理',
          permission: 'sys:tenant:index',
          path: '/tenant',
          icon: 'tenant',
          type: 1,
          sort: 0,
          edit: false,
          children: [{
            id: 41,
            pId: 4,
            name: '租户管理',
            permission: 'sys:tenant:list',
            path: 'tenant',
            icon: 'tenant',
            type: 1,
            edit: false,
            sort: 0,
            children: []
          }]
        }]
      }
    },
    mounted() {
      let that = this
      window.onresize = () => {
        let tempHeight = document.body.clientHeight - (150)
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
      editIcon(row) {
        this.iconVisible = true
        this.iconBindData = row
      },
      saveIcon(data) {
        menuApi.update({ id: data.id, icon: data.value }).then(res => {
          data.data.icon = data.value
        })
      },
      edit(row) {
        row.edit = true
        console.log(row)
      },
      save(row) {
        if (row.newd && 1 === row.newd) {
          menuApi.save({
            pId: row.pId
          }).then(res => {
            row.edit = false
            row.id = res.data.id
            row.pId = res.data.pId
            row.name = res.data.name
            row.permission = res.data.permission
            row.path = res.data.path
            row.icon = res.data.icon
            row.type = res.data.type
            row.sort = res.data.sort
          })
        } else {
          menuApi.update({
            id: row.id,
            name: row.name,
            path: row.path,
            sort: row.sort,
            permission: row.permission,
            type: row.type
          }).then(res => {
            row.edit = false
            row.id = res.data.id
            row.pId = res.data.pId
            row.name = res.data.name
            row.permission = res.data.permission
            row.path = res.data.path
            row.icon = res.data.icon
            row.type = res.data.type
            row.sort = res.data.sort
          })
        }
      },
      cancel(row) {
        row.edit = false
      },
      handleCommand(type, row) {
        let tempData = {
          'id': new Date().getTime(),
          'pId': row.pId,
          'name': '菜单名称',
          'path': '',
          'icon': '',
          'sort': 0,
          'type': 2,
          'permission': '',
          'level': 1,
          'edit': true,
          'children': [],
          'newd': 1
        }
        if (type === 1) {
          let parent = this.getNode(row.pId)
          tempData.pId = row.pId
          tempData.name = '同级菜单'
          if (row.pId === 0) {
            parent.push(tempData)
          } else {
            parent.children.push(tempData)
          }
        } else {
          row.children = row.children || []
          tempData.pId = row.id
          tempData.name = '子菜单'
          row.children.push(tempData)
        }
      },
      closeIconDialog() {
        this.iconVisible = false
        this.iconBindData = null
      },
      getNode(id) {
        if (id === 0) {
          return this.tableData
        } else {
          return this.getChild(this.tableData, id)
        }
      },
      getChild(child, id) {
        for (let i in child) {
          if (child[i].id === id) {
            return child[i]
          } else {
            let result = this.getChild(child[i].children || [], id)
            if (result) {
              return result
            }
          }
        }
      }
    }
  }
</script>
