# SpringBoot-1.5.15

SpringBoot为基于Spring的开发提供了快速的环境搭建工作，同时也提供了与其他各个组件的集成。
该项目的目的是完成SpringBoot是如何与各个组件实现集成的，以代码的方式完成

# 项目介绍

- sb-fileupload SpringBoot集成文件上传服务
  - 结合FastDFS完成
- sb-async SpringBoot提供的异步支持功能
- sb-mail 结合SpringBoot的邮件发送
  - 普通文本邮件的发送
  - HTML格式的邮件发送
  - 带附件的邮件发送
  - thymeleaf 模板邮件（同HTML方式）
- sb-schedule SpringBoot 提供的定时任务实现
  - cron表达式实现
  - fixDelay实现
  - fixRate实现
  - 开启并行任务执行（ScheduleConfig）
  - 可以开启异步支持（AsynchronizationConfig）使用时在任务实现上添加@Asyc注解
