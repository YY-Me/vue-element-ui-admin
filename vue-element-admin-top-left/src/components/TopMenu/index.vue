<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <!--此写法排序没问题，当时top-menu-item加了div后样式需要重写-->
        <!--<top-menu-item v-if="!route.hidden" v-for="route in topMenu" :item="route"/>-->

        <!--一级菜单在前，多级菜单在后，请不要给菜单栏的图标设置el自带的，请使用svg图标，具体看官方，到阿里图标下载即可-->
        <el-menu-item v-if="!item.hidden&&(!item.children||(item.children&&item.children.length===0))"
                      :index="item.path"
                      v-for="item in permission_top_routes">
          <span :class="item.pId===0?'top-menu-span':''">
            <svg-icon class="mr-4" :icon-class="item.icon"/>
            {{item.title}}
          </span>
        </el-menu-item>

        <el-submenu v-if="!item.hidden&&item.children&&item.children.length>0" :index="item.path"
                    :show-timeout="100"
                    :hide-timeout="100"
                    v-for="item in permission_top_routes">
            <template slot="title">
                <span :class="item.pId===0?'top-menu-span':''">
                <svg-icon class="mr-4" :icon-class="item.icon"/>
                {{item.title}}
              </span>
            </template>
            <top-menu-item v-if="!route.hidden" v-for="route in item.children" :item="route" :path="item.path"/>
        </el-submenu>

    </el-menu>
</template>

<script>
    import {mapGetters} from 'vuex'
    import topMenuItem from './topMenuItem'
    import store from '@/store'

    export default {
        name: 'topMenu',
        components: {topMenuItem},
        computed: {
            ...mapGetters([
                'permission_routes',
                'permission_top_routes',
                'permission_top_left_routes'
            ])
        },
        watch: {
            $route(to, from) {
                this.activeIndex = to.path
                this.initMenu()
            }
        },
        data() {
            return {
                activeIndex: ''
            }
        },
        mounted() {
            this.init()
        },
        methods: {
            init() {
                this.activeIndex = this.$route.fullPath
                this.initMenu()
            },
            initMenu() {
                this.handleSelect(this.$route.fullPath)
            },
            handleSelect(key, keyPath) {
                console.log('key=' + key)
                if (key) {
                    key = key + ''
                    let tempItem = this.getTopMenuByPath(key)
                    if (tempItem) {
                        //标识第一层
                        this.resolveTopLeftMenuByPath(key)
                        this.leftFirstCheck()
                    } else {
                        let basePath = this.getBasePath(key)
                        if (basePath) {
                            let item = this.getTopMenuByBasePath(basePath)
                            if (item) {
                                this.activeIndex = item.path
                                this.resolveTopLeftMenu(item)
                            } else if (this.$route.path !== key) {
                                this.$router.push({
                                    path: key
                                })
                            }
                        }
                    }
                }
            },
            resolveTopLeftMenuByPath(path) {
                //根据path找到item
                let item = this.getTopMenuByPath(path)
                if (item) {
                    this.resolveTopLeftMenu(item)
                }
            },
            getTopMenuByPath(path) {
                for (let i = 0; i < this.permission_top_routes.length; i++) {
                    if (this.permission_top_routes[i].path === path) {
                        return this.permission_top_routes[i]
                    }
                }
            },
            getTopMenuByBasePath(path) {
                //根据此path获取所有路由，找到topId，再根据topId获取到topMenu
                let topId
                for (let i = 0; i < this.permission_routes.length; i++) {
                    if (this.permission_routes[i].path === path) {
                        topId = this.permission_routes[i].tId
                    }
                }
                if (topId) {
                    for (let i = 0; i < this.permission_top_routes.length; i++) {
                        if (this.permission_top_routes[i].id === topId) {
                            return this.permission_top_routes[i]
                        }
                    }
                }
            },
            async resolveTopLeftMenu(item) {
                let tempMenu = []
                this.permission_routes.forEach(tempItem => {
                    if (!tempItem.hidden && item.id === tempItem.tId) {
                        tempMenu.push(tempItem)
                    }
                })
                //重置左侧路由
                const generateTopRoutes = await store.dispatch('permission/generateTopLeftRoutes', tempMenu)
            },
            leftFirstCheck() {
                //默认选中左侧第一个菜单
                let tempMenu = this.permission_top_left_routes
                if (tempMenu && tempMenu[0] && tempMenu[0].children && tempMenu[0].children.length > 0) {
                    this.$router.push({
                        path: tempMenu[0].path === '/' ? tempMenu[0].path : (tempMenu[0].path + '/' + tempMenu[0].children[0].path)
                    })
                }
            },
            getBasePath(key) {
                let tempSplit = key.split('/')
                if (tempSplit && tempSplit.length > 1 && tempSplit[1]) {
                    return `/${tempSplit[1]}`
                }
                return null
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
