<template>
    <el-card shadow="never">
        <Tinymce ref="editor" v-model="postForm.content" :height="400" />
    </el-card>
</template>

<script>
    import Tinymce from '@/components/Tinymce'
    export default {
        name: 'richText',
        components: {Tinymce},
        data(){
            return{
                postForm:{
                    content: ''
                }
            }
        }
    }
</script>

<style scoped>

</style>
