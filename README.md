# vue-element-ui-admin

#### 介绍
vue-element-ui-admin，springboot基础的权限管理系统

#### 软件架构
提供了两种常用的后台ui，都是基于[vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/guide/)，没有更改作者整体结构，人家已经做得很好了，只是改了部分逻辑，比如路由的方法计算

UI-1：
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/105601_f5f0f7cc_1559021.png "Snipaste_2020-06-02_10-54-18.png")

UI-2：
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/105621_6121d0fa_1559021.png "Snipaste_2020-06-02_10-39-10.png")


#### 安装教程

1.  git，你会看到三个文件夹，其中boot-auth是后台，其中两个是不同的ui，之所以没做成一个，是感觉没必要，哪个符合就用哪个
2.  后台依赖redis，mysql，请先安装好并且yml也要配置好导入sql文件，前台vue环境是肯定的
3.  后台启动（`npm run serve`），前台开发环境启动即可，前台开发环境配置了proxy，以dev-api就会转发到对应的target，可以看vue.config.js的47行；正是环境是prod-api，如果你打包（`npm run build`）后，肯定proxy无效了，你需要配置自己的nginx代理

#### 使用说明

1.  后台角色只是一种简单的角色，没有上下级关系，如果您需要类似部门等等，可以自行修改即可，前台把角色改成部门即可
2.  由于菜单是动态生成的，所以你开发的时候可能需要添加一个模块，但是又要去配置菜单哪些。很烦，你可以在store/modules/permission.js里面的变量tempRoutes按照树形菜单给设置上去，具体规则您也可以看vue-element-admin官方，那么你的路由就不会收到后台限制了，等开发完了你在添加到后台
3.  此基础项目非常的基础简洁，前后台也没有添加任何杂乱的配置或模块进去。

ps：后台是依赖springboot2.3.0，由于最近发布的，部分应用有很多变动，比如data-es就弃用的弃用，移除的移除，第三方的框架可能会出现问题，比如stream-binder-rocketmq里面RocketMQMessageChannelBinder的还未支持，里面调用的方法已经被移除了。具体可参考官方[官方](http://https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.3-Release-Notes)

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
