(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-984f5b24"],{9954:function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"index-pre-error"},[e.exist?e._e():r("i",{staticClass:"el-icon-warning",staticStyle:{"font-size":"30px"}}),r("p",[e._v(e._s(e.errorText))]),e.exist?e._e():r("el-tag",{staticStyle:{cursor:"pointer"},attrs:{size:"mini"},on:{click:e.logout}},[e._v("返回登录")])],1)},i=[],s=(r("ac1f"),r("5319"),r("96cf"),r("1da1")),o=r("5530"),a=r("2f62"),c={name:"indexPre",data:function(){return{exist:!1,errorText:"请稍后..."}},computed:Object(o["a"])({},Object(a["b"])(["permission_routes"])),mounted:function(){console.log(this.permission_routes);for(var e=0;e<this.permission_routes.length;e++)if(this.permission_routes[e].meta&&"首页"===this.permission_routes[e].meta.title){this.exist=!0;break}this.exist?this.$router.replace("/index/console"):this.errorText="该账户似乎未分配任何权限，请先分配后操作"},methods:{logout:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:r=e,e.$confirm("是否退出登录？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(Object(s["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,r.$store.dispatch("user/logout");case 2:return e.next=4,r.$router.push("/login");case 4:case"end":return e.stop()}}),e)})))).catch((function(){}));case 2:case"end":return t.stop()}}),t)})))()}}},u=c,l=(r("b0ba"),r("2877")),p=Object(l["a"])(u,n,i,!1,null,"1149671d",null);t["default"]=p.exports},b0ba:function(e,t,r){"use strict";var n=r("ef5e"),i=r.n(n);i.a},ef5e:function(e,t,r){}}]);