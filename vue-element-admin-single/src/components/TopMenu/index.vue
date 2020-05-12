<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <top-menu-item v-if="!route.hidden" v-for="route in permission_routes_test" :item="route" :path="route.path"/>
  </el-menu>
</template>

<script>
  import topMenuItem from './topMenuItem'

  export default {
    name: 'topMenu',
    components: { topMenuItem },
    data() {
      return {
        activeIndex: '1',
        permission_routes_test: [{
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
      console.log(this.permission_routes_test)
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
