package com.plk.sbdemo.admin.config.quartz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务配置的注解
 * 配置定时任务的分组/名称/表达式/任务描述等信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JobConfig {

	/**
	 * job name
	 */
	String name() default "";
	
	/**
	 * job group
	 */
	String group() default "default";
	
	/**
	 * job cron
	 */
	String cron() default "";
	
	/**
	 * job description
	 */
	String desc() default "";
}