# Blue Monster商城

## 自我介绍

> 无名小菜鸡，主打后端，想要全干:smile:。想一起写项目可以通过邮箱联系:mark_walen@qq.com。:two_men_holding_hands:如果对这个项目有任何疑问也可通过邮箱联系:heart:。

## 项目结构

## 技术栈

> 对于学习来说可以使用最新进行开发，但要注意不同包的可能引用相同的依赖而版本号不同，尽管 maven 可以忽略一些版本号不同的依赖，还是会造成版本冲突造成项目启动失败。:rage::cry::sob:

| 名称               | 版本号        | 官方文档 | 备注 |
| ------------------ | ------------- | -------- | ---- |
| Spring + SpringMVC | 5.0.5.RELEASE |          |      |
| tkMapper           |               |          |      |
| commons-fileupload | 1.3.1         |          |      |
| commons-io         | 2.4           |          |      |

## 开发过程中遇到的问题

### 文件上传问题

问题描述：前端发送请求数据后，后端的 file 为空

问题解决过程：

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

