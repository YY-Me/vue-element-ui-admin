<template>
  <div class="navbar">
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container"
               @toggleClick="toggleSideBar"/>

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>

    <div class="right-menu">
      <template v-if="device!=='mobile'">

        <!-- <el-tooltip content="Global Size" effect="dark" placement="bottom">
           <size-select id="size-select" class="right-menu-item hover-effect" />
         </el-tooltip>-->

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="hover">
        <div class="avatar-wrapper">
          <el-avatar size="small" :src="avatar + '?imageView2/1/w/80/h/80'"/>
          <span class="login-user">管理员<i class="el-icon-caret-bottom"/></span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">
            <span style="display:block;"><svg-icon style="margin-right: 4px;" icon-class="log-out"/>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Breadcrumb from '@/components/Breadcrumb'
  import Hamburger from '@/components/Hamburger'
  import { addClass } from '@/utils'

  export default {
    components: {
      Breadcrumb,
      Hamburger
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'avatar',
        'device'
      ])
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch('app/toggleSideBar')
      },
      async logout() {
        this.$confirm('是否退出登录？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('user/logout')
          this.$router.push(`/login?redirect=${this.$route.fullPath}`)
        }).catch(() => {
        })
      },
      show() {
        console.log("ss")
        addClass(document.body, 'showRightPanel')
      }
    }
  }
</script>

<style lang="scss" scoped>
  .navbar {
    height: 50px;
    overflow: hidden;
    position: relative;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

    .hamburger-container {
      line-height: 46px;
      height: 100%;
      float: left;
      cursor: pointer;
      transition: background .3s;
      -webkit-tap-highlight-color: transparent;

      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }

    .breadcrumb-container {
      float: left;
    }

    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }

    .right-menu {
      float: right;
      height: 100%;
      line-height: 50px;

      &:focus {
        outline: none;
      }

      .right-menu-item {
        display: inline-block;
        padding: 0 8px;
        height: 100%;
        font-size: 18px;
        color: #5a5e66;
        vertical-align: text-bottom;

        &.hover-effect {
          cursor: pointer;
          transition: background .3s;

          &:hover {
            background: rgba(0, 0, 0, .025)
          }
        }
      }

      .avatar-container {
        margin-right: 15px;

        .avatar-wrapper {
          margin-top: 8px;
          position: relative;

          .login-user {
            font-size: 14px;
            display: inline-block;
            overflow: hidden;
            line-height: 28px;
          }
        }
      }
    }
  }
</style>
