# vue-element-admin

## 此项目包含了文件浏览器、富文本编辑器

### 文件浏览器
文件浏览器，可以对文件进行操作，目前对接的本地的文件，也可以实现里面的阿里云oss或者是其他云存储，和本地实现一样的，本地的需要注意后台配置的application-other.yml的配置，
本地模式实现了大文件上传，防止大文件上传出现超时问题

![输入图片说明](https://images.gitee.com/uploads/images/2020/0701/110231_5a2f0bad_1559021.png "Snipaste_2020-07-01_11-02-06.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0701/120006_57b33829_1559021.gif "QQ录屏2020070111252520207111592614.gif")



### 文本编辑器
富文本选择的是vue-element-admin作者推荐的tinymce，具体可以看作者说明
[点击查看](https://panjiachen.github.io/vue-element-admin-site/zh/feature/component/rich-editor.html)

注意：
目前使用的tinymce5，插件在public下面，里面有两个插件，image，media，被修改过，其中image下面的js，style被修改，可以搜索max-width关键字，限制了图片的最大宽度
；
其二media，具体可以百度tinymce个人中文社区，作者魔改过，具体可以到js里面搜索魔改二字就可看到
其三：是加入了文件选择器，具体可以看插件filebrowser

在tinymce编辑器中，默认实现了对接上面的文件浏览器，可以插入文件浏览器的文件

![输入图片说明](https://images.gitee.com/uploads/images/2020/0727/225915_5c6a6e71_1559021.png "Snipaste_2020-07-27_22-57-04.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0701/120217_4ebf779b_1559021.png "Snipaste_2020-07-01_12-01-37.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0701/114741_395bec06_1559021.gif "QQ录屏202007011109162020711137258.gif")

### 如何将他们引入你的项目，很简单，复制views下面对应的文件夹，并且把里面的vue文件依赖相关的js等复制过去就行了

