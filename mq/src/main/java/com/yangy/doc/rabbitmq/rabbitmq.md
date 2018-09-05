## rabbit mq windows 安装 注意事项 ##

### erlang 安装 ###

**下载地址** 

 [http://www.erlang.org/downloads](http://www.erlang.org/downloads "erlang 下载路径")

**环境变量配置** `ERLANG_HOME 安装路径`

**注意事项**
安装erlang 时注意 一致

    C:\Windows\System32\config\systemprofile\.erlang.cookie

	C:\Users\用户名\.erlang.cookie

----------

### rabbit mq 安装 ###
**下载地址**

[https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.7.5](https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.7.5 "rabbit mq下载路径")

出现失败时 
	
	rabbit-service start 启动服务
	rabbit-service stop 关闭服务
	rabbit-service remove 移除服务
	rabbitmq-plugins enable rabbitmq_management 安装页面管理工具

----------
### rabbit mq 使用 ###

	//获取连接
	ConnectionFactory connectionFactory = new ConnectionFactory();
	//地址
	connectionFactory.setHost("127.0.0.1");
	//端口号
	connectionFactory.setPort(5672);
	//用户名
	connectionFactory.setUserName("guest");
	//密码
	connectionFactory.setPassword("guest");
	//获取连接
	Connection connection = connectionFactory.getConnection();
	//获取通道
	Channel channel = connection.getChannel();
	//test 队列名称
	channel.queueDeclare("test",false,false,false,null);
	//消息
	String message = "this is a test message";
	//发送消息
	channel.basicPublish("","test",null,message.getBytes());
	//关闭频道 关闭连接
	channel.close();
	connection.close();

----------
### 基本知识简介 ###
1. ConnectionFactory 用来获取连接
2. connection rabbitMq的socket连接,丰庄路socket协议相关部分逻辑
3. channel用来定义Queue(队列)定义Exchange(交换机)绑定Queue与Exchange 发布消息等操作

### queue ###
rabbitMq的内部对象 用来存储消息 

    消息只能存储在队列中 队列不受任何限制，可以存储任何数量的消息—本质上是一个无限制的缓存.

#### Message acknowledgment ####
消息确认机制

	mq在收到消息回执时将消息从queue中移除,如果没有收到回执,并检测到消费者的rabbitmq连接断开,会将消息发送给其它消费者(如果存在多个消费者)处理

开发过程注意

	处理完业务逻辑后,没有发送回执给RabbitMQ,会导致Queue中的消息越来越多,消费者重启后出现消息的重复消费问题

消息的持久化以及消息的事务性

#### Prefetch count ####
可以设置该参数控制发给每个消费者的消息数.![Prefetch count](http://ostest.qiniudn.com/wordpress/wp-content/uploads/2014/02/2014-2-21-9-49-08.png)比如我们设置prefetchCount=1，则Queue每次给每个消费者发送一条消息；消费者处理完这条消息后Queue会再给该消费者发送一条消息。

#### 声明Queue ####
- 消费者无法订阅或者获取不存在的Queue中的信息
- 消息被Exchange接受之后,如果没有匹配的Queue,会被丢弃
	
	无论是消费者还是生产者,在使用时,即发送或者接受消息是,去尝试建立消息队列,因为加入客户端去尝试创建一个已经存在的消息队列时,是不会做任何操作,并返回成功.

	一个消费者在一个信道中正在监听一个队列的消息时,不允许该消费者在同一个channel中声明其它队列

声明方式 queue.declare

- **Exclusive 排他队列**

	该队列仅对首次声明它的连接可见,并在连接断开时自动删除.

	1.排他队列是基于连接可见的,同一连接的不同信道可以同时访问同一个连接创建的排他队列
	
	2.一个连接已经声明了一个排他队列,其它连接不允许建立同名的排他队列.
	
	3.即使设置该队列是持久化的,一旦连接关闭或者客户端退出,该排他队列都会被自动删除.
 
- **Auto-delete 自动删除** 适用于临时队列

- **durable持久化** 在连接断开或者客户端重启后仍会存活

**总结** 
	
	存活周期仅限于当前连接
	自动删除的特性
	仅能创建一次

----------

#### 路由规则 ####
- **direct 路由规则完全相同 *exp:* ==**
- **topic 模糊匹配路由规则 *exp:* like**
- **fanout 发布与订阅方式**

#### 消费者订阅消息 ####
- **basic.consume**

	订阅某队列中的消息后,channel自动在处理完上一条消息后,接受下一条消息.(同一个channel中消息处理是并行的)除非关闭channel或者取消订阅,否则客户端将会一直接收队列的消息.

- **basic.get**
	
	主动获取队列中的消息,但是不可以通过循环调用basic.get来代替basic.consume,basic,get在实际执行的时候,首先consume某一队列,然后检索第一条消息,然后在取消订阅,在高吞吐量的消费者,最好使用第一种方式

多个消费者同时订阅同一个队列,RabbitMQ是采用循环的方式分发消息的,每一条消息只能被一个订阅者接收.消费者在接到消息时,需要给服务器发送一套确认命令,可以在HandelDelivery中调用basic.ack实现,也可以在consume某个队列时,设置autoACK属性为true.如果消费者在接到消息以后还没来得及返回ACK就断开了连接,消息服务器会重传该消息给下一个订阅者,没有订阅者就会存储该消息.

----------	




		

 