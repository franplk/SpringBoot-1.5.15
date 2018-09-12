# SpringBoot-1.5.15
记录学习SpringBoot的一个过程，记录SpringBoot开发牵涉到的各个功能特点

# 项目介绍

 - sb-fileupload 结合FastDFS完成的一个文件上传服务
 - sb-schedule SPringBoot 提供的定时任务实现
  - cron表达式实现
  - fixDelay实现
  - fixRate实现
  - 开启并行任务执行（ScheduleConfig）
  - 可以开启异步支持（AsynchronizationConfig）使用时在任务实现上添加@Asyc注解
