<template>
  <el-card shadow="never">
    <div>
      <div style="margin-bottom: 10px">
        <el-radio-group v-model="listQuery.mType" @change="getList">
          <el-radio :label="1">左侧菜单</el-radio>
          <el-radio :label="2">顶部菜单</el-radio>
        </el-radio-group>
        <span style="font-size: 14px;margin-left: 10px;" v-if="listQuery.mType===1" type="primary">
          <i class="el-icon-warning"/>
          点击一级标题可设置顶部菜单
        </span>
        <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" style="margin-left: 5px;"
                   v-if="this.tableData.length===0" @click="handleCommand(1,{pId: 0})">增加
        </el-button>
      </div>
      <el-table :data="tableData" style="width: 100%;" v-loading="loading" element-loading-text="请稍后..."
                row-key="id" border default-expand-all
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column prop="title" label="标题" min-width="100" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input style="width: 50%" size="small" v-model="scope.row.title" v-if="scope.row.edit" placeholder="标题">
              {{scope.row.title}}
            </el-input>
            <span @click="setTopMenu(scope.row)" class="menu-title" v-else-if="scope.row.pId===0&&scope.row.mType===1">
              {{scope.row.title}}
            </span>
            <span v-else>{{scope.row.title}}</span>
            <span v-if="scope.row.tName">（{{scope.row.tName}}）</span>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="name" min-width="80" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.name" v-if="scope.row.edit" placeholder="name">
              {{scope.row.name}}
            </el-input>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="路由地址" min-width="80" show-overflow-tooltip>
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
        <el-table-column prop="sort" label="序号" min-width="60">
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
            <el-tag v-else style="cursor:pointer;margin-right: 4px;" size="mini" @click="edit(scope.row)">
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
      <icon :dialog-visible="iconVisible" :bind-data="tempBindData" @selected="saveIcon" @close="closeTempDialog"/>
      <top-menu-select :dialog-visible="topMenuVisible" :bind-data="tempBindData" @selected="saveTopMenu"
                       @close="closeTempDialog"/>
    </div>
  </el-card>
</template>
<script>
  import icon from '@/views/system/menu/icon'
  import topMenuSelect from '@/views/system/menu/topMenuSelect'
  import menuApi from '@/api/system/menu'

  export default {
    title: 'systemMenu',
    components: { icon, topMenuSelect },
    data() {
      return {
        loading: false,
        iconVisible: false,
        topMenuVisible: false,
        tempBindData: null,
        tableData: [],
        listQuery: {
          mType: 1
        }
      }
    },
    mounted() {
      this.getList()
    },
    methods: {
      getList() {
        this.loading = true
        menuApi.listTree(this.listQuery).then(res => {
          this.tableData = res.data || []
          this.loading = false
        }).catch(() => this.loading = false)
      },
      editIcon(row) {
        this.iconVisible = true
        this.tempBindData = row
      },
      saveIcon(data) {
        console.log(data)
        if (data.data.edit) {
          data.data.icon = data.value
        } else {
          menuApi.update({ id: data.data.id, title: data.data.title, icon: data.value }).then(res => {
            data.data.icon = data.value
          })
        }
      },
      edit(row) {
        row.edit = true
        console.log(row)
      },
      save(row) {
        let data = JSON.parse(JSON.stringify(row))
        if (row.newd && 1 === row.newd) {
          data.id = null
          data.mType = this.listQuery.mType
          menuApi.save(data).then(res => {
            row.edit = false
            row.id = res.data.id
            row.newd = 2
          })
        } else {
          menuApi.update(data).then(res => {
            row.edit = false
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
          'title': '菜单名称',
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
          tempData.title = '同级菜单'
          if (row.pId === 0) {
            parent.push(tempData)
          } else {
            parent.children.push(tempData)
          }
        } else {
          row.children = row.children || []
          tempData.pId = row.id
          tempData.title = '子菜单'
          row.children.push(tempData)
        }
      },
      closeTempDialog() {
        this.iconVisible = false
        this.topMenuVisible = false
        this.tempBindData = null
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
      },
      saveTopMenu(data) {
        menuApi.update({ id: data.data.id, title: data.data.title, tId: data.value.id }).then(res => {
          this.$message.success('设置成功')
          data.data.tId = data.value.id
          data.data.tName = data.value.name
        })
      },
      setTopMenu(row) {
        this.tempBindData = row
        this.topMenuVisible = true
      }
    }
  }
</script>
<style lang="scss" scoped>
  .menu-title {
    font-weight: bold;
    cursor: pointer;
    text-decoration: underline;
  }
</style>
