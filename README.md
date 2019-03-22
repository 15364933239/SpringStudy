## SSM框架第二季 Sping

### 一、Spring是什么？

Spring是于2003 年兴起的一个轻量级的Java 开源框架，它由Rod Johnson创建。传统J2EE应用的开发效率低，Spring作为开源的中间件，提供J2EE应用的各层的解决方案，Spring贯穿了表现层、业务层及持久层，而不是仅仅专注于某一层的方案。可以说Spring是企业应用开发的“一站式（full-stack）”选择。然而，Spring并不想取代那些已有的框架，而是与它们无缝地整合。

简单来说，Spring是一个轻量级**控制反转**(IoC)和**面向切面**(AOP)的**容器**框架。

### 二、为什么要使用Spring？

#### 1、方便解耦，简化开发

​	通过Spring提供的IoC容器，我们可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。

#### 2、AOP编程的支持

​	通过Spring提供的AOP功能，方便进行面向切面的编程，许多不容易用传统OOP实现的功能可以通过AOP轻松应付

#### 3、声明式事务的支持

​	在Spring中，我们可以从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活地进行事务的管理，提高开发效率和质量。

#### 4、方便程序的测试

​	通过Spring提供的IoC容器，我们可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。

#### 5、方便集成各种优秀的框架

​	Spring不排斥各种优秀的开源框架，相反，Spring可以降低各种框架的使用难度，Spring提供了对各种优秀框架（如Struts、Hibernate、MyBatis 、**Hessian**、**Quartz**）等的直接支持。

#### 6、降低 Java EE API 的使用难度

​	Spring对很多难用的Java EE API（如JDBC，JavaMail，远程调用等）提供了一个薄薄的封装层，通过Spring的简易封装，这些Java EE API的使用难度大为降低。

### 三、spring获取

1、Spring下载：<http://repo.spring.io/release/org/springframework/spring/>（5.0.8release）

2、导包：（以下5个包称为基本包，根据你使用的版本可能还需导入log4j包，咱们学习的版本暂时无需导入）

​	1）核心包：bean、context、core、expression；2）日志包：apache.commons.logging

3、引入约束：bean约束和引入主配置文件头；

4、测试：将自定义对象由自己创建交由Spring管理；

### 四、Spring-IoC  |  DI  概念介绍

//IOC是一种编程思想，是一种新的设计模式，他需要DI（依赖注入）技术的支持

IoC（Inversion of Control）：反转控制：将我们自己创建对象的工作交给Spring容器帮我们完成；

DI（DependencyInjection）：依赖注入：将值通过配置的方式为变量初始化/赋值(注入)；

```
	//根据spring配置文件获取容器对象
	//applicationContext配置的所有bean都会在容器创建的时候被创建出来
	//如果配置的bean较多，那么在创建容器的时候，会产生内存过大问题
	//延迟加载 true就是创建容器是不加载配置的bean对象，在获取的时候才会创建
	ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	User user = (User)aContext.getBean("user");
	//通过getBean获取配置好的user对象（程序员向spring容器要对象）
	User user = aContext.getBean(User.class);
	System.out.println(user);
```

### 五、Spring配置

#### 1、bean几种标签

1） Bean标签介绍和创建方式：空参构造、静态工厂、动态工厂，缓存延迟：lazy-init；

2）Scope属性介绍：singleton（创建为单例）、prototype（多个对象）、request、 session；

3）初始化方法Init-method和 销毁方法destroy-method介绍；

#### 2、属性注入

1）Set方法注入；

```
<!-- 将user对象交给spring管理，并注入值类型 -->
<bean name="user" class="com.lpt.bean.User">
	<property name="u_id" value="1"/>	
	<property name="u_username" value="老王"/>	
	<property name="u_password" value="123"/>	
	<!-- 引用类型注入 -->
	<property name="u_pet" ref="dog"/>
</bean>

<!-- 将pet对象交给spring对象管理 -->
<bean name="dog" class="com.lpt.bean.Pet">
	<property name="petType" value="二哈"/>
	<property name="color" value="灰白"/>
</bean>
```

2）构造函数注入；

```
<!-- 构造方法注入 -->
<bean name="user1" class="com.lpt.bean.User">
	<!-- name调用构造方法的参数名称  value是注入值类型  ref为注入引用类型 -->
	<!-- type是指定参数类型的 -->
	<!-- index是指定参数位置的 -->
	<constructor-arg name="u_username" value="66" type="java.lang.Integer" index="0"/>
	<constructor-arg name="u_pet" ref="dog"/>
</bean>
```

3）复杂类型注入：Array、List、Set、Map、Properties

```
<!-- 复杂类型注入 -->
<bean name="collection" class="com.lpt.bean.MyCollection">
<!-- array -->
<property name="array">
	<array>
		<!-- 给数组赋值 -->
		<value>123</value>
		<value>456</value>
		<!-- 注入引用类型 -->
		<ref bean="dog"/>
	</array>
</property>
<!-- list -->
<property name="list">
	<list>
		<!-- 给数组赋值 -->
		<value>111</value>
		<value>222</value>
		<!-- 注入引用类型 -->
		<ref bean="dog"/>
	</list>
</property>
<!-- set -->
<property name="set">
	<set>
		<!-- 给数组赋值 -->
		<value>333</value>
		<value>444</value>
		<!-- 注入引用类型 -->
		<ref bean="dog"/>
	</set>
</property>
<!-- map -->
<property name="map">
	<map>
		<entry key="username" value="root"/>
		<entry key="password" value="123"/>
		<entry key-ref="user1" value-ref="dog"/>
	</map>
</property>
<!-- properties -->
<property name="prop">
	<props>
		<prop key="name">老李</prop>
		<prop key="age">25</prop>
	</props>
</property>
</bean>
```

#### 3、注解配置

1)     导包和约束：基本包、aop包+context约束；

2）   将对象注册到容器内；（@Component、@Controller、@Service、@Repository）

3)      用注解配置Scope属性； 

4)      注解配置init-method与destroy-method； （@PostConstruct、@PreDestroy）

5)      注解配置属性注入，值类型与引用类型；（@Value加在set方法上、@Resource(name=" ")）

#### 4、Spring & JUnit进行测试：

###### a)      导包： test包（依赖 aop包）；

b)      使用@RunWith注解创建spring容器；

c)      使用@ContextConfigsration读取spring配置文件；

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_injection.xml")

	@Resource(name="dog")
	private Pet pet;
	@Test
	public void Test1() {
		System.out.println(pet);
	}	
```

d）在著配置文件中使用< import >标签可引入其他配置文档

### 六、用spring开发登录

#### 1、导包：web包

#### 2、设置监听器启动spring容器

```
<!-- 设置监听器，在web项目启动时让spring启动 -->
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <!-- 读取spring配置文件 -->
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>classpath:applicationContext.xml</param-value>
  </context-param>
```

#### 3、配置spring(applicationContext.xml)

```
<!-- 配置dataSource -->
<bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="driverClass" value="com.mysql.jdbc.Driver"/>
	<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/ssm_mybatis"/>
	<property name="user" value="root"/>
	<property name="password" value="root"/>
</bean>
<!-- 配置dao -->
<bean name="userDao" class="com.lpt.dao.daoImpl.UserDaoImpl">
	<property name="dataSource" ref="dataSource"/>
</bean>
<!-- 配置service -->
<bean name="userService" class="com.lpt.service.serviceImpl.UserServiceImpl"> 
	<property name="ud" ref="userDao"/>
</bean>
```

#### 4、web层调用

```
private UserService us;
//在erb项目中只需要一份spring容器
//application域
//servletContext() 生命周期 随着web项目的启动而创建 随着web的关闭而销毁
//servletContextListener 可以通过配置监听器来达到我们的需求，在web项目创建的时候创建spring容器，销毁的时候关闭spring容器
WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
us = (UserService) wac.getBean("userService");
User loginUser = us.getUserByInfo(user);
```

### 七、Spring-aop

1、  aop思想介绍（面向切面编程）：将纵向重复代码，横向抽取解决，简称：横切

2、  Spring中的aop：无需我们自己写动态代理的代码，spring可以将容器中管理对象生成动态代理对象，前提是我们对他进行一些设置；

3、  Spring-aop是基于动态代理的 – 优先选用JDKProxy动态代理；

a)      Proxy动态代理：被代理的对象必须要实现接口；

b)      Cglib动态代理：被代理的对象不能被final修饰，基于继承；

4、  Spring aop相关名词说明 – 详见 表-[Spring术语解释]；

![1551598418783](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551598418783.png)

5、使用：

```
<!-- 目标对象 -->
<bean name="userService" class="com.lpt.service.serviceImlp.UserServiceImpl"/>
<!-- 通知对象 -->
<bean name="myAdvice" class="com.lpt.aop.MyAdvice"/>

<aop:config>
	<!-- 切入点 expression切入点表达式 可配置要增强的方法 id是唯一标识
	public void com.lpt.service.userServiceImpl.save()
	* com.lpt.service.*ServiceImpl.*(..)  -->
	<aop:pointcut expression="execution(* com.lpt.service.serviceImlp.*ServiceImpl.*(..))" id="servicePc"/>
		
	<!-- 切面 通知+切入点 -->
	<aop:aspect ref="myAdvice">
		<!-- 通知类型 -->
		<aop:before method="before" pointcut-ref="servicePc"/>
		<!-- 最终通知 后置通知 -->
		<aop:after method="after" pointcut-ref="servicePc"/>
		<!-- 成功通知 后置通知 -->
		<aop:after-returning method="afterReturning" pointcut-ref="servicePc"/>
		<!-- 异常通知 后置通知 -->
		<aop:after-throwing method="afterThrowing" pointcut-ref="servicePc"/>
		<!-- 环绕通知 -->
		<aop:around method="around" pointcut-ref="servicePc"/>
	</aop:aspect>
</aop:config>
```

### 八、Spring-jdbc

#### 1、导包

​	新增jdbc、tx（事务包）

#### 2、书写

​	dao层和实现类，书写sql语句（JdbcDaoSupport）

#### 3、配置

​	applicationContext.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

<!-- 依赖关系 dao -> -> dataSource -->
<!-- 读取配置文件 -->
<context:property-placeholder location="db.properties"/>

<!-- dataSource -->
<bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="driverClass" value="${jdbc.driverClass}"/>
	<property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
	<property name="user" value="${jdbc.user}"/>
	<property name="password" value="${jdbc.password}"/>
</bean>

<!-- dao -->
<bean name="userDao" class="com.lpt.dao.UserDaoImpl">
	<property name="dataSource" ref="dataSource"/>
</bean>

<!-- 配置事务核心管理器 不同平台不一样 -->
<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"/>
</bean>

<!-- 事务通知 开启事务 -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
	<tx:attributes>
		<tx:method name="transferAccounts" isolation="DEFAULT" propagation="REQUIRED" read-only="false"/>
		<tx:method name="sava*" isolation="DEFAULT" propagation="REQUIRED" read-only="false"/>
		<tx:method name="delete*" isolation="DEFAULT" propagation="REQUIRED" read-only="false"/>
		<tx:method name="update*" isolation="DEFAULT" propagation="REQUIRED" read-only="false"/>
		<tx:method name="select*" isolation="DEFAULT" propagation="REQUIRED" read-only="true"/>
	</tx:attributes>
</tx:advice>

<!-- 配置aop -->
<aop:config>
	<aop:pointcut expression="execution(* com.lpt.service.*ServiceImpl.*(..))" id="txPc"/>
	<aop:advisor advice-ref="txAdvice" pointcut-ref="txPc"/>
</aop:config>

</beans>
```

### 九、Spring-aop事务绑定

#### 1、添加tx约束

#### 2、配置xml文档

```
	<!-- 配置事务核心管理器 不同平台不一样 -->
	<bean name="transactionManager" 	 class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!-- 开启注解事务 -->
	<tx:annotation-driven/>
```

#### 3、注解配置

```
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
```

​	注解可在方法上添加，可在类上添加（整个类的方法）

