<template>
  <el-tabs class="top-menu" v-model="activeName" @tab-click="handleClick">
    <el-tab-pane>
      <span slot="label"><i class="el-icon-date"></i> 我的行程</span>
      我的行程
    </el-tab-pane>
    <el-tab-pane label="用户管理">用户管理</el-tab-pane>
    <el-tab-pane label="配置管理">配置管理</el-tab-pane>
    <el-tab-pane label="角色管理">角色管理</el-tab-pane>
    <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
  </el-tabs>
</template>

<script>
  export default {
    name: 'topMenu',
    data() {
      return {
        activeName: 'second'
      }
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event)
      }
    }
  }
</script>

<style lang="scss">
  .top-menu {
    .el-tabs__header {
      margin-bottom: 0 !important;

      .el-tabs__item {
        font-size: 16px !important;
        height: 60px !important;
        line-height: 60px !important;
      }
    }

    .el-tabs__content {
      height: 0 !important;
      overflow: hidden;
    }
  }
</style>
