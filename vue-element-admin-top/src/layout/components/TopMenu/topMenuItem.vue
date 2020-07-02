<template>
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
        <top-menu-item v-for="route in item.children" :item="route" :base-path="basePath+'/'+item.path"/>
    </el-submenu>
    <el-menu-item v-else :index="resolvePath(basePath, item.path)">
        <app-link style="display: block;margin: 0 -10px;padding: 0 10px;" :to="resolvePath(basePath,item.path)">
                  <span class="top-menu-span">
                    <svg-icon class="mr-4" :icon-class="item.meta.icon"/>
                    {{item.meta.title}}
                  </span>
        </app-link>
    </el-menu-item>

</template>

<script>
    import path from 'path'
    import AppLink from './Link'
    import {isExternal} from "@/utils/validate"

    export default {
        name: 'topMenuItem',
        components: {AppLink},
        props: {
            item: {
                type: Object
            },
            basePath: {
                type: String
            }
        },
        data() {
            return {
                msg: ''
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
</style>
