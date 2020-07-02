<template>
    <el-menu-item v-if="!item.children||(item.children&&item.children.length===0)" :index="path+'/'+item.path">
    <span :class="item.pId===0?'bold':''">
      <svg-icon class="mr-4" :icon-class="item.icon"/>
      {{item.title}}
    </span>
    </el-menu-item>
    <el-submenu v-else :index="path+'/'+item.path" :show-timeout="100" :hide-timeout="100">
        <template slot="title">
      <span :class="item.pId===0?'bold':''">
        <svg-icon class="mr-4" :icon-class="item.icon+''"/>{{item.title}}</span>
        </template>
        <top-menu-item v-if="!route.hidden" v-for="route in item.children" :item="route" :path="path+'/'+item.path"/>
    </el-submenu>
</template>

<script>
    export default {
        name: 'topMenuItem',
        props: {
            item: {
                type: Object
            },
            path: {
                type: String
            }
        },
        data() {
            return {
                msg: ''
            }
        }
    }
</script>

<style lang="scss" scoped>
    .mr-4 {
        margin-right: 4px !important;
    }
</style>
