(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5a8c5ad1"],{1733:function(t,e,n){"use strict";var i=n("2bf6"),o=n.n(i);o.a},"1dbc":function(t,e,n){"use strict";var i=n("595d"),o=n.n(i);o.a},"2bf6":function(t,e,n){},"595d":function(t,e,n){},"7cf6":function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-card",{attrs:{shadow:"never"}},[n("Tinymce",{ref:"editor",attrs:{height:400},model:{value:t.postForm.content,callback:function(e){t.$set(t.postForm,"content",e)},expression:"postForm.content"}})],1)},o=[],a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"tinymce-container",class:{fullscreen:t.fullscreen},style:{width:t.containerWidth}},[n("el-button",{on:{click:t.insertContent}},[t._v("插入内容")]),n("el-button",{on:{click:function(e){t.$message.success(t.getContent())}}},[t._v("获取内容")]),n("label",[n("textarea",{staticClass:"tinymce-textarea",attrs:{id:t.tinymceId}})]),n("el-dialog",{staticClass:"text-file-browser",attrs:{title:"文件浏览器",visible:t.fileBrowserVisible,width:"820px"},on:{"update:visible":function(e){t.fileBrowserVisible=e}}},[n("file-list",{attrs:{height:450,callback:!0},on:{callback:t.fileSelected}})],1)],1)},c=[],r=(n("99af"),n("4160"),n("b0c0"),n("a9e3"),n("159b"),["indent2em filebrowser advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools link lists media nonbreaking noneditable pagebreak preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount powerpaste"]),l=r,s=["code | styleselect | fontsizeselect | bold italic forecolor backcolor indent2em | alignleft aligncenter alignright alignjustify | bullist numlist table | link image media filebrowser | preview fullscreen"],u=s,d=n("b85c"),m=[];function f(){return window.tinymce}var h=function(t,e){var n=document.getElementById(t),i=e||function(){};if(!n){var o=document.createElement("script");o.src=t,o.id=t,document.body.appendChild(o),m.push(i);var a="onload"in o?c:r;a(o)}function c(e){e.onload=function(){this.onerror=this.onload=null;var t,n=Object(d["a"])(m);try{for(n.s();!(t=n.n()).done;){var i=t.value;i(null,e)}}catch(o){n.e(o)}finally{n.f()}m=null},e.onerror=function(){this.onerror=this.onload=null,i(new Error("Failed to load "+t),e)}}function r(t){t.onreadystatechange=function(){if("complete"===this.readyState||"loaded"===this.readyState){this.onreadystatechange=null;var e,n=Object(d["a"])(m);try{for(n.s();!(e=n.n()).done;){var i=e.value;i(null,t)}}catch(o){n.e(o)}finally{n.f()}m=null}}}n&&i&&(f()?i(null,n):m.push(i))},p=h,y=n("3109"),b=n("0e9a"),g=n("b775"),w="/plugins/tinymce5/tinymce/tinymce.min.js",v={name:"Tinymce",components:{FileList:y["a"]},props:{id:{type:String,default:function(){return"vue-tinymce-1"}},value:{type:String,default:""},toolbar:{type:Array,required:!1,default:function(){return[]}},menubar:{type:String,default:"file edit insert view format table"},height:{type:[Number,String],required:!1,default:360},width:{type:[Number,String],required:!1,default:"auto"}},data:function(){return{fileBrowserVisible:!1,hasChange:!1,hasInit:!1,tinymceId:this.id,fullscreen:!1}},computed:{containerWidth:function(){var t=this.width;return/^[\d]+(\.[\d]+)?$/.test(t)?"".concat(t,"px"):t}},watch:{value:function(t){var e=this;!this.hasChange&&this.hasInit&&this.$nextTick((function(){setTimeout((function(){window.tinymce.get(e.tinymceId).setContent(t||"")}),500)}))}},mounted:function(){this.init()},activated:function(){window.tinymce&&this.initTinymce()},deactivated:function(){this.destroyTinymce()},destroyed:function(){this.destroyTinymce()},methods:{init:function(){var t=this;p(w,(function(e){e?t.$message.error(e.message):t.initTinymce()}))},initTinymce:function(){var t=this;window.tinymce.init({selector:"#".concat(this.tinymceId),language:"zh_CN",height:this.height,body_class:"panel-body ",object_resizing:!1,toolbar:this.toolbar.length>0?this.toolbar:u,menubar:[],plugins:l,powerpaste_word_import:"propmt",powerpaste_html_import:"propmt",powerpaste_allow_local_images:!0,paste_data_images:!0,end_container_on_empty_block:!0,code_dialog_height:450,code_dialog_width:1e3,default_link_target:"_blank",link_title:!1,nonbreaking_force_tab:!0,init_instance_callback:function(e){t.value&&e.setContent(t.value),t.hasInit=!0,e.fileBrowser=function(){t.fileBrowserVisible=!0}},setup:function(e){e.on("FullscreenStateChanged",(function(e){t.fullscreen=e.state}))},images_upload_handler:function(t,e,n,i){var o=new FormData;o.append("prefix","/"),o.append("file",t.blob()),Object(g["a"])({url:"/aed-res/file/richText",method:"post",data:{data:o,NOSERI:!0,notLoading:!0},headers:{"Content-Type":"multipart/form-data"}}).then((function(t){e(t.data.url)})).catch((function(t){n(t&&t.data&&t.data.message||"上传失败")}))},video_template_callback:function(t){return'<video controls="controls" style="max-width: 100%;">\n              <source src="'.concat(t.source,'" type="').concat(t.sourcemime,'" /></video>')}})},destroyTinymce:function(){var t=window.tinymce.get(this.tinymceId);this.fullscreen&&t.execCommand("mceFullScreen"),t&&t.destroy()},setContent:function(t){window.tinymce.get(this.tinymceId).setContent(t)},getContent:function(){return window.tinymce.get(this.tinymceId).getContent()},getText:function(){return window.tinymce.get(this.tinymceId).getContent({format:"text"})},insertContent:function(t){var e=this;window.tinymce.get(e.tinymceId).insertContent('<img src="https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/a8ec8a13632762d0797a0962a6ec08fa503dc6a3.jpg"  alt="" />')},fileSelected:function(t){var e=this,n=t||[];n.forEach((function(t){Object(b["c"])(t.url)?window.tinymce.get(e.tinymceId).insertContent('<img src="'.concat(t.url,'"  alt="').concat(t.name,'" style="max-width: 100%;max-height: auto;" />')):Object(b["d"])(t.url)?window.tinymce.get(e.tinymceId).insertContent('<video controls="controls" style="max-width: 100%;">\n              <source src="'.concat(t.url,'" /></video>')):window.tinymce.get(e.tinymceId).insertContent('<p><a href="'.concat(t.url,'" target="_blank" rel="noopener">').concat(t.name,"</a></p>"))})),this.fileBrowserVisible=!1}}},_=v,k=(n("1733"),n("1dbc"),n("2877")),C=Object(k["a"])(_,a,c,!1,null,"423cb33e",null),x=C.exports,I={name:"richText",components:{Tinymce:x},data:function(){return{postForm:{content:""}}}},T=I,S=Object(k["a"])(T,i,o,!1,null,"08b46c42",null);e["default"]=S.exports}}]);