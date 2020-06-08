[TOC]

# 菜来啦
## 会议记录
### 2020年6月7日
1. 如何使用 controller
2. 如何加载图片
   1. 写好了一个线程加载图片的api
   2. 使用Glide加载
3. 将内容全都放到了 README.md
4. git 查看本地修改和远程修改
5. 用户controller、标题栏修改

## 关键代码

### 使用controller获取数据

使用子线程以及在子线程中使用ui线程的方式（其他方式请参考https://www.jianshu.com/p/c39203884209），具体流程如下。

1. 创建子线程，这里使用Runnable的方式。
2. 重写`run()`方法，将controller的代码写在其中。
3. 如果需要更新ui的操作（一般都会有），将相关代码写在`activity.runOnUiThread()`中。

**注意**：只要使用到 controller，全都需要创建线程，后期如果遇到线程过多的问题，会考虑使用线程库。
参考代码如下，

```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        List<Product> list = ProductController.list();
        activity.runOnUiThread(()->{
            // 设置布局
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            holder.recyclerView.setLayoutManager(layoutManager);
            // 设置adapter
            HomeRecommendRecyclerAdapter homeRecommendRecyclerAdapter = new HomeRecommendRecyclerAdapter(context, activity, list);
            holder.recyclerView.setAdapter(homeRecommendRecyclerAdapter);
        });
    }
};
Thread thread = new Thread(runnable);
thread.start();
```



## 资料



1. 安卓项目地址

   1. 项目首页：https://github.com/XDcat/cai_lai_la
   2. git地址：https://github.com/XDcat/cai_lai_la.git

2. 安卓教程：https://www.runoob.com/w3cnote/android-tutorial-project-src-analysis.html

3. api文档：https://documenter.getpostman.com/view/11279327/Szmk2G5W?version=latest

4. 图标：[https://www.iconfont.cn/search/index?q=%E4%B8%BB%E9%A1%B5](https://www.iconfont.cn/search/index?q=主页)

5. 配色：

   1. https://colorhunt.co/
   2. http://www.ecjson.com/rgbhex/

6. 服务器

   1. ip：121.89.194.185

   2. 图片路径：/root/cailaila/static

   3. ssh

      1. 端口：22

   4. 后端

      1. 端口：8080

   5. 数据库

      1. 端口：3306

## 第三方包

1. Butter Knife 成员变量直接绑定布局文件组件
   1. 官网：https://github.com/JakeWharton/butterknife
2. 十分有参考意义的项目，虽然只有首页的设计
   1. github：https://github.com/EasyToForget/TaobaoDemo
3. 导航栏 XBanner
   1. https://github.com/xiaohaibin/XBanner
4. 图片加载 Glide
5. 参考项目
   1. 完整项目
      1. https://github.com/zss945/shop-mall-android
      2. https://github.com/Liuqiuyue0/shop
   2. 购物车：https://github.com/DickyQie/android-shoppingcart
   3. 分类：https://github.com/windfallsheng/CommodityClassificationListPageExample

## 规范

1. 布局文件
   1. 当前布局文件所用的素材（例如图片、color、string），命名都以当前布局文件开头
   2. 布局文件中的id都已当前布局文件开头
   3. 命名用单词加_的形式
   4. 仅限于布局文件

## 任务

1. *界面设计
   1. layout
   2. 难点
      1. 列表项
2. 提供服务（与服务器交互）
   1. pojo类
   2. 具体与数据库交互的代码
3. 安卓代码编写
   1. activity 页面加载
   2. 页面跳转

## 界面

1. 商品
   1. 主页（曾连杰）
   2. 分类 (沈政烨)
   3. ~~搜索~~ (曾连杰)
   4. 搜索结果(曾连杰)
   5. 详情
   6. 付款
   7. 购物车（刘律宁）
2. 用户
   1. ~~个人信息（陈昶宇）~~
   2. 收货地址（陈昶宇）
   3. 订单
   4. 注册登录
3. ~~底边栏(刘律宁）~~

## 基本流程

​            ![img](https://qqadapt.qpic.cn/txdocpic/0/6dd43f2c1963fd2807898079e26d76d0/0?w=816&h=1071)            

#             ![img](https://qqadapt.qpic.cn/txdocpic/0/44f36ba2acaecf7421ecc706ac79037a/0?w=816&h=1071)            