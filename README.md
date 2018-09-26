# SpringBoot-1.5.15

通过SpringBoot-1.5.15版本说明了在实际应用中，SpringBoot如何完成各种环境的搭建工作。
建立本项目的是结合WEB开发中的常见功能，说明SpringBoot是如何快速搭建应用。

## 异步支持

- sb-async SpringBoot提供的异步支持功能
  - 通过Async注解实现
  - 线程池配置

## 缓存支持

- sb-cache SpringBoot完成Spring-cache的功能实现
  - 自定义CacheMananger与Key Generator
- sb-redis SpringBoot实现Spring-cache与Redis缓存的集成
  - RedisTemplate方式
  - Spring-Cache的注解方式（CacheManager自定义）
  - cache key Generator自定义
  
## 文件上传

- sb-fileupload SpringBoot集成文件上传服务
  - 结合FastDFS完成
  - 完成普通文件上传
  - 单线程文件分片上传（redis记录上传的状态--文件名、文件大小等）

## 邮件发送

- sb-mail 结合SpringBoot的邮件发送
  
  - 普通文本邮件的发送
  - HTML格式的邮件发送
  - 带附件的邮件发送
  - thymeleaf 模板邮件（同HTML方式）

## 数据库操作

- sb-JPA JPA方式对数据库进行操作（SQL）
- sb-mybatis SpringBoot结合Mybatis完成数据库操作
  - Mybatis注解方式配置SQL
  - Mybatis XML方式配置SQL
- sb-mongodb SpringBoot集成NoSQL数据库MongoDB

## 定时任务

- sb-schedule SpringBoot 提供的定时任务实现

  - cron表达式实现
  - fixDelay实现
  - fixRate实现
  - 开启并行任务执行（ScheduleConfig）
  - 可以开启异步支持（AsynchronizationConfig）使用时在任务实现上添加@Asyc注解
  
- sb-quartz SpringBoot结合Quartz实现定时任务的动态管理，前端由EasyUI设计的简单页面

  - 定时任务的启动
  - 定时任务的停止
  - 定时任务的触发

## 消息队列

- sb-mq-active SpringBoot集成ActiveMQ