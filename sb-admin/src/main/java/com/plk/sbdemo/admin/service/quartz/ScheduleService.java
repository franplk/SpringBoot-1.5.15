package com.plk.sbdemo.admin.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.domain.quartz.JobInfo;
import com.plk.sbdemo.admin.web.exception.quartz.QuartzException;

@Service
public class ScheduleService {

	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private Scheduler scheduler;
	
	@CacheEvict("JobCache")
	public void addJob(String jobGroup, String jobName, String cronExp, String desc, Class<? extends Job> jobCalss) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(jobCalss)
					.withIdentity(jobName, jobGroup)
					.build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .withDescription(desc)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取定时任务列表
	 * 根据分组获取所有触发器然后根据触发器获取触发器对应的定时任务
	 */
	@Cacheable("JobCache")
	public Map<String, List<JobInfo>> jobList() {
		try {
			List<String> triggerGroupList = scheduler.getTriggerGroupNames();
			if (triggerGroupList == null || triggerGroupList.isEmpty()) {
				return Collections.emptyMap();
			}
			return triggerGroupList.stream().collect(Collectors.toMap(g -> g, g-> getJobListByGroup(g)));
		} catch (Exception e) {
			throw new QuartzException(e.getMessage());
		}
	}
	
	/**
	 * 根据触发器分组获取对应的定时任务列表
	 * 
	 * @param group 触发器分组名称
	 */
	@Cacheable("JobCache")
	public List<JobInfo> getJobListByGroup(String group) {
		try {
			Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
			if (triggerKeySet == null || triggerKeySet.isEmpty()) {
				return Collections.emptyList();
			}
			return triggerKeySet.stream().map(k -> getJobByTriggerKey(k)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new QuartzException("根据分组获取触发器失败");
		}
	}
	
	/**
	 * 根据触发器获取定时任务
	 * 
	 * @param triggerKey
	 */
	@Cacheable("JobCache")
	public JobInfo getJobByTriggerKey(TriggerKey triggerKey) {
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			JobKey jobkey = trigger.getJobKey();

			JobInfo jobinfo = new JobInfo().setJobName(jobkey.getName())
					.setJobGroup(jobkey.getGroup())
					.setCronExps(trigger.getCronExpression())
					.setFuncDesc(trigger.getDescription());
			Date pDate = trigger.getPreviousFireTime();
			if (pDate != null) {
				jobinfo.setLastTriggerTime(SDF.format(pDate));
			}
			Date nDate = trigger.getNextFireTime();
			if (nDate != null) {
				jobinfo.setNextTriggerTime(SDF.format(nDate));
			}

			return jobinfo;
		} catch (Exception e) {
			throw new QuartzException("触发器获取失败");
		}
	}
	
	/**
	 * 停止任务
	 */
	public void cease(String name, String group) {
		JobKey jobKey = new JobKey(name, group);
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			throw new QuartzException("定时任务停止失败");
		}
	}
	
	/**
	 * 恢复任务
	 */
	public void resume(String name, String group) {
		JobKey jobKey = new JobKey(name, group);
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new QuartzException("定时任务恢复失败");
		}
	}

	/**
	 * 触发一次定时任务
	 */
	public void triggerJob(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			throw new QuartzException("手动触发任务失败");
		}
	}
}
