# Blue Monster商城

## 自我介绍

> 无名小菜鸡，主打后端，想要全干:smile:。想一起写项目可以通过邮箱联系:mark_walen@qq.com。:two_men_holding_hands:如果对这个项目有任何疑问也可通过邮箱联系:heart:。

## 项目结构

```html
BlueMonster
├─ BlueMonster_Common
│	├─ BlueMonster_Common.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	└─ resources
│	 	│	 	├─ applicationContext-common.xml
│	 	│	 	├─ log4j.properties
│	 	│	 	└─ zk.properties
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Common_Service
│	├─ BlueMonster_Common_Service.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	└─ resources
│	 	│	 	├─ applicationContext-dao.xml
│	 	│	 	├─ applicationContext-dubbo.xml
│	 	│	 	├─ applicationContext-redis.xml
│	 	│	 	└─ redis-config.properties
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Common_web
│	├─ BlueMonster_Common_web.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	│	└─ cn.blue.phoenix
│	 	│	│	 	 └─ controller
│	 	│	│	 	 	 └─ BaseExceptionHandler.java
│	 	│	└─ resources
│	 	│	 	├─ applicationContext-dubbo.xml
│	 	│	 	└─ applicationContext-task.xml
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Interface
│	├─ BlueMonster_Interface.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	│	└─ cn.blue.phoenix
│	 	│	│	 	└─ service
│	 	│	│	 	 	└─ goods
│	 	│	│	 	 	 	├─ AlbumService.java
│	 	│	│	 	 	 	├─ BrandService.java
│	 	│	│	 	 	 	├─ CategoryService.java
│	 	│	│	 	 	 	├─ ParamService.java
│	 	│	│	 	 	 	├─ SpecService.java
│	 	│	│	 	 	 	└─ TemplateService.java
│	 	│	└─ resources
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Pojo
│	├─ BlueMonster_Pojo.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	│	└─ cn
│	 	│	│	 	└─ blue.phoenix
│	 	│	│	 	 	├─ entity
│	 	│	│	 	 	| 	├─ PageResult.java
│	 	│	│	 	 	| 	└─ Result.java
│	 	│	│	 	 	└─ pojo
│	 	│	│	 	 	 	└─ goods
│	 	│	│	 	 	 	 	├─ Album.java
│	 	│	│	 	 	 	 	├─ Brand.java
│	 	│	│	 	 	 	 	├─ Category.java
│	 	│	│	 	 	 	 	├─ Param.java
│	 	│	│	 	 	 	 	├─ Spec.java
│	 	│	│	 	 	 	 	└─ Template.java
│	 	│	└─ resources
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Service_Goods
│	├─ BlueMonster_Service_Goods.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	│	└─ cn.blue.phoenix
│	 	│	│	 	├─ dao
│	 	│	│	 	│	├─ AlbumMapper.java
│	 	│	│	 	│	├─ BrandMapper.java
│	 	│	│	 	│	├─ CategoryMapper.java
│	 	│	│	 	│	├─ ParamMapper.java
│	 	│	│	 	│	├─ SpecMapper.java
│	 	│	│	 	│	└─ TemplateMapper.java
│	 	│	│	 	├─ service
│	 	│	│	 	│	└─ impl
│	 	│	│	 	│	 	├─ AlbumServiceImpl.java
│	 	│	│	 	│	 	├─ BrandServiceImpl.java
│	 	│	│	 	│	 	├─ CategoryServiceImpl.java
│	 	│	│	 	│	 	├─ ParamServiceImpl.java
│	 	│	│	 	│	 	├─ SpecServiceImpl.java
│	 	│	│	 	│	 	└─ TemplateServiceImpl.java
│	 	│	│	 	└─ utils
│	 	│	│	 	 	└─ PageHelperUtils.java
│	 	│	├─ resources
│	 	│	│	├─ db.properties
│	 	│	│	└─ dubbo.properties
│	 	│	└─ webapp
│	 	│	 	└─ WEB-INF
│	 	│	 	 	└─ web.xml
│	 	└─ test
│	 	 	└─ java
├─ BlueMonster_Web_Manager
│	├─ BlueMonster_Web_Manager.iml
│	├─ pom.xml
│	└─ src
│	 	├─ main
│	 	│	├─ java
│	 	│	│	└─ cn.blue.phoenix
│	 	│	│	 	├─ controller
│	 	│	│	 	│	├─ AlbumController.java
│	 	│	│	 	│	├─ BrandController.java
│	 	│	│	 	│	├─ CategoryController.java
│	 	│	│	 	│	├─ GoodsPageController.java
│	 	│	│	 	│	├─ ParamController.java
│	 	│	│	 	│	├─ SpecController.java
│	 	│	│	 	│	├─ TemplateController.java
│	 	│	│	 	│	└─ UploadController.java
│	 	│	│	 	└─ utils
│	 	│	│	 	 	└─ FileUtils.java
│	 	│	├─ resources
│	 	│	│	├─ applicationContext-json.xml
│	 	│	│	├─ applicationContext-web.xml
│	 	│	│	└─ dubbo.properties
│	 	│	└─ webapp
│	 	│	 	├─ WEB-INF
│	 	│	 	│	├─ view
│	 	│	 	│	│	└─ goods
│	 	│	 	│	│	 	├─ album-list.html
│	 	│	 	│	│	 	├─ album.html
│	 	│	 	│	│	 	├─ brand.html
│	 	│	 	│	│	 	├─ category.html
│	 	│	 	│	│	 	├─ param.html
│	 	│	 	│	│	 	├─ spec.html
│	 	│	 	│	│	 	└─ template.html
│	 	│	 	│	└─ web.xml
│	 	│	 	└─ static
│	 	│	 	 	├─ css
│	 	│	 	 	│	├─ elementui.css
│	 	│	 	 	│	├─ fonts
│	 	│	 	 	│	│	├─ element-icons.ttf
│	 	│	 	 	│	│	└─ element-icons.woff
│	 	│	 	 	│	└─ style.css
│	 	│	 	 	├─ ico
│	 	│	 	 	│	└─ favicon.ico
│	 	│	 	 	├─ img
│	 	│	 	 	└─ js
│	 	│	 	 	 	├─ axios.js
│	 	│	 	 	 	├─ elementui.js
│	 	│	 	 	 	├─ util.js
│	 	│	 	 	 	├─ vue-router.js
│	 	│	 	 	 	└─ vue.js
│	 	└─ test
│	 	 	└─ java
├─ Blue_monster_log4j.log
├─ README.md
├─ pom.xml
└─ sql
 	└─ goods.sql
```

## 技术栈

> 对于学习来说可以使用最新进行开发，但要注意不同包的可能引用相同的依赖而版本号不同，尽管 maven 可以忽略一些版本号不同的依赖，还是会造成版本冲突造成项目启动失败。:rage::cry::sob:

**前端：vue.js + elementui + axios** 

(建议用vue cli 去构建，该项目的后台管理系统有大量重复的代码。在该项目中没有用本地的tomcat做服务器，而是用tomcat插件做服务器，controller控制视图。缺点是不能热启动（或者说操作配置热启动有些麻烦），所以不管是前端还是后端代码修改后需要重启项目，其它的没有多大区别)

后端：SSM 框架 + Dubbo

## 依赖

| 名称                 | 版本号        | 官方文档/API                                                 | 备注                                                         |
| -------------------- | ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Spring相关依赖       | 5.0.5.RELEASE | [Overview (Spring Framework 5.3.12 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/) |                                                              |
| mybatis-spring       | 2.0.6         | [mybatis-spring](http://mybatis.org/spring/zh/index.html)    |                                                              |
| MyBatis              | 3.5.7         | [mybatis – MyBatis 3 入门](https://mybatis.org/mybatis-3/zh/getting-started.html) |                                                              |
| mapper               | 4.1.5         | [Home · abel533/Mapper Wiki · GitHub](https://github.com/abel533/Mapper/wiki) | 通用 Mapper4 是一个可以实现任意 MyBatis 通用方法的框架，项目提供了常规的增删改查操作以及`Example` 相关的单表操作。 |
| pagehelper           | 5.1.9         | [如何使用分页插件 (pagehelper.github.io)](https://pagehelper.github.io/docs/howtouse/) |                                                              |
| mysql-connector-java | 8.0.25        |                                                              | mysql驱动程序                                                |
| druid                |               | [首页 · alibaba/druid Wiki · GitHub](https://github.com/alibaba/druid/wiki/首页) | Druid是一个JDBC组件，它包括三部分： DruidDriver 代理Driver，能够提供基于Filter－Chain模式的插件体系。 DruidDataSource 高效可管理的数据库连接池。 |
| dubbo                | 2.7.8         | [快速开始 Apache Dubbo](https://dubbo.apache.org/zh/docs/quick-start/) |                                                              |
| curator-framework    | 4.2.0         | [Apache Curator Framework](http://curator.apache.org/curator-framework/) | zookeeper-client                                             |
| curator-recipes      | 4.2.0         |                                                              | curator-framework 包含curator-recipes这个依赖，但是必须得添加，否则会报错，项目运行不起来。 |
| fastjson             | 1.2.78        | [Quick Start CN · alibaba/fastjson Wiki · GitHub](https://github.com/alibaba/fastjson/wiki/Quick-Start-CN) | Fastjson 是一个 Java 库，可以将 Java 对象转换为 JSON 格式，当然它也可以将 JSON 字符串转换为 Java 对象。 |
| commons-codec        | 1.15          | [Overview (Apache Commons Codec 1.15 API)](https://commons.apache.org/proper/commons-codec/apidocs/) | `commons-codec`是Apache开源组织提供的用于摘要运算、编码的包。在该包中主要分为四类加密：BinaryEncoders、DigestEncoders、LanguageEncoders、NetworkEncoders。 |
| commons-fileupload   | 1.3.1         | [FileUpload – Home (apache.org)](http://commons.apache.org/proper/commons-fileupload/index.html)、[ Apache Commons FileUpload 1.4 API](http://commons.apache.org/proper/commons-fileupload/javadocs/api-release/org/apache/commons/fileupload/package-summary.html) | Commons FileUpload 包可以轻松地为您的 servlet 和 Web 应用程序添加强大的、高性能的文件上传功能。 |
| commons-io           | 2.4           | [Commons IO – User guide](https://commons.apache.org/proper/commons-io/description.html)、[Overview (Apache Commons IO 2.11.0 API)](https://commons.apache.org/proper/commons-io/apidocs/index.html) | Apache Commons IO 是一个实用程序库，用于帮助开发 IO 功能。   |

## 开发过程中遇到的问题

### 文件上传问题

#### 问题描述

> 前端发送请求数据后，后端的 file 为空

#### 问题解决过程：

1. 在检查文件上传的所有配置没有问题后，就去百度遛了一圈，发现都不能解决我的问题。

2. 当我将 `BlueMonster-Common-web` 中的 `applicationContext-json.xml` 如下代码：

   ```xml
   <!-- 多部分文件上传 -->
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
   		<property name="maxUploadSize" value="104857600" />
   		<property name="maxInMemorySize" value="4096" />
   		<property name="defaultEncoding" value="UTF-8"/>
   </bean>
   ```

   粘贴至 `BlueMonster-Web-Mannager`包下的 `applicationContext-web.xml`时，问题得到解决。

3. 在该项目中`BlueMonster-Web-Mannager`依赖于 `BlueMonster-Common-web`，springmvc却无法去加载`applicationContext-json.xml`，在web.xml中指定加载配置文件的配置如下：

   ```xml
   <init-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:applicationContext-*.xml</param-value>
   </init-param>
   ```

文件上传的步骤（*并不是完整的教程，只是为了说明一些要注意的点*）：

1. 在pom.xml中添加文件上传依赖

   ```xml
   <properties>
       <commons-fileupload.version>1.3.1</commons-fileupload.version>
       <commons-io.version>2.4</commons-io.version>
   </properties>
   
   <dependencies>
       <!-- spring的依赖也要添加哦 -->
       <!-- 文件上传的依赖 -->
       <dependency>
         <groupId>commons-io</groupId>
         <artifactId>commons-io</artifactId>
         <version>${commons-io.version}</version>
       </dependency>
   
       <dependency>
         <groupId>commons-fileupload</groupId>
         <artifactId>commons-fileupload</artifactId>
         <version>${commons-fileupload.version}</version>
         <exclusions>
           <exclusion>
             <groupId>commons-io</groupId>
             <artifactId>commons-io</artifactId>
           </exclusion>
         </exclusions>
       </dependency>
   </dependencies>
   ```

2. spring的配置

   ```xml
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
   		<property name="maxUploadSize" value="104857600" />
   		<property name="maxInMemorySize" value="4096" />
   		<property name="defaultEncoding" value="UTF-8"/>
   </bean>
   ```

3. UploadController 代码

   ```java
   @Autowired
   private HttpServletRequest request;
   @PostMapping("/native")
   public ResponseEntity<Result> nativeUpload(@RequestPart MultipartFile file) {
       
   }
   ```

4. 前端代码

   ```html
   <input type="file" name="file" style="display:none;" οnchange="upload()">
   ```

   ==注意==：在第三步`MultipartFile 变量名`和第四步中 `name="变量名"` ，“变量名”要相同，否则后端会报错 "Required request part 'file' is not present"。

   ### 关于 el-upload 修改自动上传为 `false` 后，无法获取图片`imageUrl`的问题

   #### 问题描述：

   > 在提交表单时，我会让图片先上传至服务端，然后进行更新或增加操作，但是将 `pojo.image` 输出到 console 却显示 `undefined`，但在控制台的 Vue 插件中可以看到 `pojo.image`是服务端返回的url。代码如下：

   ```html
   <!-- 前端文件上传代码 -->
   <el-upload
              class="avatar-uploader"
              action="/upload/native"
              accept="image/png, image/jpeg, image/jpg"
              ref="upload"
              :auto-upload="false"
              :show-file-list="false"
              :on-change="imgSaveToUrl"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
       <img v-if="imageUrl" :src="imageUrl" class="avatar">
       <i v-else class="el-icon-plus avatar-uploader-icon"></i>
   </el-upload>
   <el-button type="primary" @click="addAndUpdateAlbum">确 定</el-button>
   ```

   在点击确认后，会执行如下代码：

   ```js
   // 上传代码逻辑
   addAndUpdateAlbum () {
       // 调用 submit() 上传图片，执行成功后会触发 on-success 事件，执行handleAvatarSuccess()方法
       this.$refs.upload.submit();
       let url = this.pojo.id ? 'update' : 'add'
       // 执行更新或插入操作
       axios.post(`/album/${url}`, this.pojo).then(res => {
           console.log(res);
       })
   }
   
   handleAvatarSuccess(res, file) {
       this.pojo.image = res.message
   }
   ```

   #### 问题解决过程

1. 在发现图片的数据为空时，先在控制台输出一下 `pojo.image` ，发现为 undefined

2. 在一次偶然的查错过程中，发现 更新操作 要比 上传图片文件请求要快一步。我想我应该忘记 `handleAvatarSuccess` 是一个异步过程。于是我进行了如下代码的调整，使用 `async await`语法糖

   ```js
   // 上传代码逻辑
   async addAndUpdateAlbum () {
       // 调用 submit() 上传图片，执行成功后会触发 on-success 事件，执行handleAvatarSuccess()方法
       await this.$refs.upload.submit();
       let url = this.pojo.id ? 'update' : 'add'
       // 执行更新或插入操作
       axios.post(`/album/${url}`, this.pojo).then(res => {
           console.log(res);
       })
   }
   
   handleAvatarSuccess(res, file) {
       this.pojo.image = res.message
   }
   ```

3. 问题依旧没有得到解决，看来我对 javascript 和 elementui还是不太熟悉:sweat_smile:，最后选了一个看得过去的方法，将插入操作 放置 `handleAvatarSuccess` 中，代码如下：

   ```js
   // 上传代码逻辑
   addAndUpdateAlbum () {
       // 调用 submit() 上传图片，执行成功后会触发 on-success 事件，执行handleAvatarSuccess()方法
       if (this.pojo.id) this.$refs.upload.submit();
       else addAlbum('update')
   }
   
   addAlbum (url) {
       axios.post(`/album/${url}`, this.pojo).then(res => {
           console.log(res);
       })
   }
   
   handleAvatarSuccess(res, file) {
       this.pojo.image = res.message
       addAlbum('add')
   }
   ```

   如有更好的解决方法，欢迎提出你的 issue。

