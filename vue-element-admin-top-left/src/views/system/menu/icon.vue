<template>
    <el-dialog
            class="simple-dialog"
            title="图标选择"
            :visible.sync="tempVisible"
            width="500px"
            :close-on-press-escape="false"
            :close-on-click-modal="false"
            @close="close"
    >
        <div style="max-height: 300px;overflow-y: auto">
            <span :class="item===select?'selected':''" @click="select=item"
                  style="cursor:pointer;display: inline-block;margin: 5px 8px 5px 0;font-size: 18px"
                  v-if="item" v-for="(item) in iconList">
                <i v-if="item.indexOf('el-icon')!==-1" :class="item"></i>
                <svg-icon v-else :icon-class="item"/>
            </span>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button size="small" type="primary" @click="selected">
                确定
            </el-button>
            <el-button size="small" @click="close">取 消</el-button>
        </span>
    </el-dialog>
</template>

<script>
    export default {
        name: 'icon',
        props: {
            dialogVisible: {
                type: Boolean,
                default: false
            },
            bindData: {
                type: Object,
                default: null
            },
            selectedIcon: {
                type: String,
                default: ''
            }
        },
        data() {
            return {
                tempVisible: false,
                select: '',
                iconList: [
                    //请不要给菜单栏的图标设置el自带的，请使用svg图标，具体看官方，到阿里图标下载即可
                    'dashboard',
                    'system-user',
                    'permission',
                    'role',
                    'menu',
                    'tenant',
                    'system-config',
                    'biz',
                    'test'
                ]
            }
        },
        watch: {
            bindData: function (val) {
                this.bindData = val
            },
            dialogVisible: function (val) {
                this.tempVisible = val
            },
            selectedIcon: function (val) {
                this.selectedIcon = val
            }
        },
        mounted() {

        },
        methods: {
            selected() {
                this.$emit('selected', {data: this.bindData, value: this.select})
                this.close()
            },
            close() {
                this.select = ''
                this.$emit('close')
            }
        }
    }
</script>

<style lang="scss" scoped>
    .selected {
        color: deepskyblue;
    }
</style>
