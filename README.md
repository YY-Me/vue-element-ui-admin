# vue-element-ui-admin

#### 介绍
vue-element-ui-admin，springboot基础的权限管理系统

#### 软件架构，如果仓库有更新，请务必还是更新下，有时候会修复一些小bug，有时候也是写着写着bug才被发现
提供了两种常用的后台ui，都是基于[vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/guide/)，没有更改作者整体结构，人家已经做得很好了，只是改了部分逻辑，比如路由的方法计算

[点击查看文件管理和富文本](https://gitee.com/yy1122/vue-element-ui-admin/tree/master/vue-element-admin)


UI-1：左侧菜单类型
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/105601_f5f0f7cc_1559021.png "Snipaste_2020-06-02_10-54-18.png")

UI-2：左侧-顶部菜单联动类型，其中是由顶部菜单来控制
![输入图片说明](https://images.gitee.com/uploads/images/2020/0602/105621_6121d0fa_1559021.png "Snipaste_2020-06-02_10-39-10.png")

UI-3：顶部菜单类型，这种可能适合一些地图操作啊，或者其他，[点我进入制作logo](https://www.logoly.pro/#/)😀
![输入图片说明](https://images.gitee.com/uploads/images/2020/0702/174537_af19b8c7_1559021.png "Snipaste_2020-07-02_17-42-00.png")

#### 安装教程

1.  git，你会看到三个文件夹，其中boot-auth是后台，其中两个是不同的ui，之所以没做成一个，是感觉没必要，哪个符合就用哪个
2.  后台依赖redis，mysql，请先安装好并且yml也要配置好导入sql文件，前台vue环境是肯定的
3.  后台启动（`npm run serve`），前台开发环境启动即可，前台开发环境配置了proxy，以dev-api就会转发到对应的target，可以看vue.config.js的47行；正是环境是prod-api，如果你打包（`npm run build`）后，肯定proxy无效了，你需要配置自己的nginx代理

#### 使用说明

1.  后台角色只是一种简单的角色，没有上下级关系，如果您需要类似部门等等，可以自行修改即可，前台把角色改成部门即可
2.  由于菜单是动态生成的，所以你开发的时候可能需要添加一个模块，但是又要去配置菜单哪些。很烦。你可以在store/modules/permission.js里面的变量tempRoutes按照树形菜单给设置上去，但是有顶部菜单的那个ui如果你的当前路由是在你的tempRouters里面，那么当你刷新的时候你的左侧菜单可能是空白，这时你需要随便点一个顶部菜单就可以了。具体树形菜单规则您也可以看vue-element-admin官方或者当前文件。这样你的路由就不会收到后台限制了，等开发完了再添加到后台
3.  多级菜单，您需要重写store/modules/permission.js的filterAsyncRoutes方法，按照官方文档生成对应的树，而且需要添加对应router-view，具体您可到vue-element-admin官方有说明
4.  此基础项目非常的基础简洁，前后台也没有添加其他的配置或模块进去。
5.  我一般关闭了ESLint，对我个人来说没用
6.  此项目用途，据看你怎么用了，给人家做毕设，公司后台管理都可以

如果您遇到使用此项目遇到了什么问题可以@：1396513066@qq.com

<img src="https://images.gitee.com/uploads/images/2020/0602/120907_094790b5_1559021.jpeg"  height="100">

-----

<img src="https://images.gitee.com/uploads/images/2020/0602/120854_d13243ba_1559021.jpeg"  height="100">


ps：后台是依赖springboot2.3.0的单app，如果您需要cloud，后面有时间上传吧。由于2.3.0最近发布的，部分应用有很多变动，比如data-es就弃用的弃用，移除的移除，第三方的框架可能会出现问题，比如stream-binder-rocketmq里面RocketMQMessageChannelBinder的还未支持，里面调用的方法已经被移除了。具体可参考官方[官方](http://https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.3-Release-Notes)

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
