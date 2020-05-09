<template>
  <el-card shadow="always">
    <div>
      <el-table :data="tableData" style="width: 100%;"
                row-key="id" border
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column prop="name" label="名称" min-width="200">
          <template slot-scope="scope">
            <el-input style="width: 50%" size="small" v-model="scope.row.name" v-if="scope.row.edit"
                      placeholder="名称">
              {{scope.row.name}}
            </el-input>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="路由地址" min-width="180">
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.path" v-if="scope.row.edit" placeholder="路由地址">
              {{scope.row.path}}
            </el-input>
            <span v-else>{{scope.row.path}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="icon" label="图标" min-width="100">
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.icon" v-if="scope.row.edit" placeholder="图标">
              {{scope.row.icon}}
            </el-input>
            <span v-else>{{scope.row.icon}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="序号" min-width="100">
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.sort" v-if="scope.row.edit" placeholder="图标">
              {{scope.row.sort}}
            </el-input>
            <span v-else>{{scope.row.sort}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="permission" label="权限标识" min-width="120">
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.permission" v-if="scope.row.edit" placeholder="权限标识">
              {{scope.row.permission}}
            </el-input>
            <span v-else>{{scope.row.permission}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="类型" min-width="100">
          <template slot-scope="scope">
            <el-select v-if="scope.row.edit" size="small" v-model="scope.row.type" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
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
            <el-button style="margin-right: 8px" type="text" size="mini"
                       @click="deleteOne(scope.row)">
              删除
            </el-button>
            <el-dropdown @command="((type)=>handleCommand(type,scope.row))" size="mini">
              <el-button size="mini" type="text">
                增加菜单
                <i class="el-icon-arrow-down el-icon--right"/>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="1">同级菜单</el-dropdown-item>
                <el-dropdown-item command="2">子菜单</el-dropdown-item>
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
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 2,
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          id: 3,
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄',
          children: [{
            id: 31,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            id: 32,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }]
        }, {
          id: 4,
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!')
      }
    }
  }
</script>
