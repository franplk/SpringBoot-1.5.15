package com.plk.sbdemo.admin.quartz.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.plk.sbdemo.admin.config.quartz.annotation.JobConfig;
import com.plk.sbdemo.admin.quartz.QuartzJob;

/**
 * 定时任务一
 */
@Component
@JobConfig(group="Test", name="MyQuartzJob", cron="0 0/5 * * * ?", desc="定时任务一")
public class MyQuartzJob extends QuartzJob {

	@Override
	public void executeInternal(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.err.println(sdf.format(new Date()) + "---A New Job Is Running...");
	}

	@Override
	public void doException(Exception e) {
	}
}
