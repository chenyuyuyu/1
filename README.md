# blog
SpringBoot+Vue前后端分离博客系统

## 技术栈
SpringBoot+MyBatis+Redis+MySQL

## 功能
**首页**
* 最新文章： 按照发布时间来进行倒序排序展示
* 热点文章：取阅读数前n个的文章,热点tag
* 导航栏：首页、分类（tag、category）、归档
* 文章/图片发布，统计阅读次数
* 评论，回复功能
* 登录，注册，退出登录


**后台管理**
* 登录
* 权限管理（添加，编辑，删除）

## 启动
**MySQL数据库**

    创建数据库：blog
    使用blog.sql文件创建表
**前端工程(blog-app)**

    npm install
    npm run build
    npm run dev

**blog-api**

    配置：src/main/resources/application.properties (数据库、Redis)
    运行：src/main/java/com/chenxy/blog/BlogAPP.java
    访问：http://localhost:8080/
    
**blog-admin**

    配置：src/main/resources/application.properties (数据库)
    后台：http://localhost:8889/login
    账号：超级管理员账号为 admin/123456



## 页面演示
**用户（前台）**

![QQ图片20220104114257](https://user-images.githubusercontent.com/22994760/148011014-8d137a3e-febe-49e7-a666-e041ef8876b8.png)
![QQ图片20220104114253](https://user-images.githubusercontent.com/22994760/148006852-56b3095c-c1ec-47eb-bb1a-64d2be878ef6.png)
![QQ图片20220104114244](https://user-images.githubusercontent.com/22994760/148006856-95ae78a1-442c-443a-b5ad-9369c73ffde5.png)
![QQ图片20220104114249](https://user-images.githubusercontent.com/22994760/148006860-239e7537-c1a4-4a4d-87fc-c40126d01516.png)
![QQ图片20220104114209](https://user-images.githubusercontent.com/22994760/148006865-d07b1046-feb6-443b-a409-91fe633147c3.png)

**管理员（后台）**
![QQ图片20220104114858](https://user-images.githubusercontent.com/22994760/148007156-34d71329-c9a6-4633-b4b1-ce7f4ab3df4a.png)
![QQ图片20220104114902](https://user-images.githubusercontent.com/22994760/148007160-20a1ea7a-8da2-4606-aacc-e88f47b706cf.png)
