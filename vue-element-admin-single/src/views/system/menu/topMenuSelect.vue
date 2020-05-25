<template>
  <el-dialog
    class="simple-dialog"
    title="顶部导航选择"
    :visible.sync="tempVisible"
    width="500px"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
    @close="close"
  >
    <div style="max-height: 300px;overflow-y: auto">
      <el-radio-group v-model="select.id">
        <el-radio v-for="menu in topMenu" :label="menu.id">{{menu.title}}</el-radio>
      </el-radio-group>
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
  import menuApi from '@/api/system/menu'

  export default {
    name: 'topMenuSelect',
    props: {
      dialogVisible: {
        type: Boolean,
        default: false
      },
      bindData: {
        type: Object,
        default: null
      }
    },
    data() {
      return {
        topMenu: [],
        tempVisible: false,
        select: {
          id: null,
          name: null
        }
      }
    },
    watch: {
      bindData: function(val) {
        this.bindData = val
        if (val) {
          this.select.id = val.tId
        }
      },
      dialogVisible: function(val) {
        if (val) {
          this.getTopMenu()
        }
        this.tempVisible = val
      }
    },
    mounted() {

    },
    methods: {
      getTopMenu() {
        menuApi.listTree({ mType: 2 }).then(res => {
          let data = res.data || []
          data.forEach(item => {
            if (item.children.length === 0) {
              this.topMenu.push(item)
            }
          })
        })
      },
      selected() {
        this.topMenu.forEach(item => {
          if (this.select.id === item.id) {
            this.select.name = item.title
          }
        })
        this.$emit('selected', { data: this.bindData, value: this.select })
        this.close()
      },
      close() {
        this.topMenu = []
        this.select = {
          id: null,
          name: null
        }
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
