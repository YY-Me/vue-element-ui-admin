<template>
    <el-menu :default-active="activeMenu" class="el-menu-demo" mode="horizontal">
        <!--一级菜单在前，多级菜单在后，请不要给菜单栏的图标设置el自带的，请使用svg图标，具体看官方，到阿里图标下载即可-->
        <template v-if="!item.hidden" v-for="item in permission_routes">
            <el-submenu v-if="item.children&&item.children.length>1"
                        :index="item.path"
                        :show-timeout="100"
                        :hide-timeout="100">
                <template slot="title">
                    <span class="top-menu-span">
                        <svg-icon class="mr-4" :icon-class="item.meta.icon"/>
                        {{item.meta.title}}
                      </span>
                </template>
                <top-menu-item v-for="route in item.children" :item="route" :base-path="item.path"/>
            </el-submenu>
            <el-menu-item v-else :index="resolvePath(item.path,item.children[0].path)">
                <app-link style="display: inline-block;margin: 0 -20px;padding: 0 20px;" :to="resolvePath(item.path,item.children[0].path)">
                  <span class="top-menu-span">
                    <svg-icon class="mr-4" :icon-class="item.meta.icon"/>
                    {{item.meta.title}}
                  </span>
                </app-link>
            </el-menu-item>
        </template>

    </el-menu>
</template>

<script>
    import path from 'path'
    import {mapGetters} from 'vuex'
    import topMenuItem from './topMenuItem'
    import AppLink from './Link'
    import {isExternal} from "@/utils/validate";

    export default {
        name: 'topMenu',
        components: {topMenuItem, AppLink},
        computed: {
            ...mapGetters([
                'permission_routes'
            ]),
            activeMenu() {
                const route = this.$route
                const {meta, path} = route
                if (meta.activeMenu) {
                    return meta.activeMenu
                }
                return path
            }
        },
        methods: {
            resolvePath(basePath, routePath) {
                if (isExternal(routePath)) {
                    return routePath
                }
                if (isExternal(basePath)) {
                    return basePath
                }
                return path.resolve(basePath, routePath)
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
