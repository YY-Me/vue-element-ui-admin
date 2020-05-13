<template>
  <section class="app-main">
    <transition name="fade" mode="out-in">
      <keep-alive :include="cachedViews">
        <router-view :key="key"/>
      </keep-alive>
    </transition>
  </section>
</template>

<script>
  export default {
    name: 'AppMain',
    computed: {
      cachedViews() {
        return this.$store.state.tagsView.cachedViews
      },
      key() {
        return this.$route.path
      }
    }
  }
</script>

<style lang="scss" scoped>

  .app-main {
    /* 50= navbar  50  */
    //min-height: calc(100vh - 50px);
    width: 100%;
    position: relative;
    overflow: hidden;
  }

  .fixed-header + .app-main {
    padding-top: 70px;
    padding-left: 10px;
    padding-right: 10px;
    min-height: 100vh;
    position: relative;
    background-color: #f1f1f1;

    > .el-card {
      > .el-card__body {
        padding: 15px !important;
      }
    }
  }

  .hasTagsView {
    .app-main {
      /* 84 = navbar + tags-view = 50 + 34 */
      //min-height: calc(100vh - 84px);
      min-height: 100vh;
      background-color: #f1f1f1;
    }

    .fixed-header + .app-main {
      padding-top: 104px;
      padding-left: 10px;
      padding-right: 10px;
    }
  }
</style>

<style lang="scss">
  // fix css style bug in open el-dialog
  .el-popup-parent--hidden {
    .fixed-header {
      //padding-right: 15px;
    }
  }
</style>
