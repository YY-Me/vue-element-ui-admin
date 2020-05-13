<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <!--此写法排序没问题，当时top-menu-item加了div后样式需要重写-->
    <!--<top-menu-item v-if="!route.hidden" v-for="route in topMenu" :item="route"/>-->

    <!--一级菜单在前，多级菜单在后-->
    <el-menu-item @click="test(item)" v-if="!item.hidden&&(!item.children||(item.children&&item.children.length===0))"
                  :index="item.path"
                  v-for="item in topMenu">
      <span :class="item.pId===0?'top-menu-span':''"><svg-icon class="mr-4"
                                                               :icon-class="item.icon"/>{{item.title}}</span>
    </el-menu-item>

    <el-submenu v-if="!item.hidden&&item.children&&item.children.length>0" :index="item.path"
                :show-timeout="100"
                :hide-timeout="100"
                v-for="item in topMenu">
      <template slot="title">
        <span @click="test(item)" :class="item.pId===0?'top-menu-span':''"><svg-icon class="mr-4"
                                                                                     :icon-class="item.icon+''"/>{{item.title}}</span>
      </template>
      <top-menu-item v-if="!route.hidden" v-for="route in item.children" :item="route" :path="item.path"/>
    </el-submenu>

  </el-menu>
</template>

<script>
  import { mapGetters } from 'vuex'
  import topMenuItem from './topMenuItem'
  import store from '@/store'

  export default {
    name: 'topMenu',
    components: { topMenuItem },
    computed: {
      ...mapGetters([
        'permission_routes'
      ]),
      activeIndex() {
        return this.$route.fullPath
      }
    },
    data() {
      return {
        topMenu: [{
          id: 1,
          pId: 0,
          title: '业务系统',
          path: '/',
          icon: 'biz',
          children: []
        }, {
          id: 2,
          pId: 0,
          title: '用户中心',
          path: '/system',
          icon: 'system-user',
          children: [{
            id: 21,
            pId: 2,
            title: '用户管理',
            path: 'user',
            icon: 'user-manage-all',
            children: []
          }]
        }, {
          id: 3,
          pId: 0,
          title: '系统配置',
          path: '/permission',
          icon: 'system-config',
          children: [{
            id: 31,
            pId: 3,
            title: '角色管理',
            path: 'role',
            icon: 'role',
            children: []
          }, {
            id: 32,
            pId: 3,
            title: '菜单管理',
            path: 'menu',
            icon: 'menu',
            children: []
          }]
        }]
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      init() {
        this.activeIndex = this.$route.fullPath
        let item = this.getMenuByPath(this.activeIndex)
        if (item) {
          this.resolveTopLeftMenu(item)
        }
      },
      handleSelect(key, keyPath) {
        console.log('key:' + key)
        if (key) {
          key = key + ''
          let tempItem = this.getMenuByPath(key)
          if (tempItem) {
            //标识第一层
            this.resolveTopLeftMenuByPath(key)
          } else {
            this.$router.push({
              path: key
            })
          }
        }
      },
      test(item) {
        this.activeIndex = item.path
        if (item.children && item.children.length > 0) {
          this.resolveTopLeftMenu(item)
        }
      },
      async resolveTopLeftMenu(item) {
        let tempMenu = []
        this.permission_routes.forEach(tempItem => {
          if (!tempItem.hidden && item.id === tempItem.topId) {
            tempMenu.push(tempItem)
          }
        })
        //重置左侧路由
        const generateTopRoutes = await store.dispatch('permission/generateTopRoutes', tempMenu)
        console.log(tempMenu)
      },
      resolveTopLeftMenuByPath(path) {
        //根据path找到item
        let item = this.getMenuByPath(path)
        if (item) {
          this.resolveTopLeftMenu(item)
        }
      },
      getMenuByPath(path) {
        for (let i = 0; i < this.topMenu.length; i++) {
          if (this.topMenu[i].path === path) {
            return this.topMenu[i]
          }
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  .mr-4 {
    margin-right: 4px !important;
  }

  .top-menu-span {
    display: inline-block;
    line-height: 60px;
    height: 60px;
    overflow: hidden;
    font-weight: bold;
  }
</style>
