/**
 * 2018年8月17日 created by franp
 */
package com.plk.sbdemo.admin.domain.quartz;

import java.io.Serializable;

/**
 * @author {AB052634/康培亮}
 *
 */
public class JobInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jobName;
	private String jobGroup;
	private String cronExps;// 定时任务表达式
	private String funcDesc;// 功能描述
	private String lastTriggerTime;// 上一次触发时刻
	private String nextTriggerTime;// 下一次触发时刻

	public String getJobName() {
		return jobName;
	}

	public JobInfo setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public JobInfo setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
		return this;
	}

	public String getCronExps() {
		return cronExps;
	}

	public JobInfo setCronExps(String cronExps) {
		this.cronExps = cronExps;
		return this;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public JobInfo setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
		return this;
	}

	public String getLastTriggerTime() {
		return lastTriggerTime;
	}

	public JobInfo setLastTriggerTime(String lastTriggerTime) {
		this.lastTriggerTime = lastTriggerTime;
		return this;
	}

	public String getNextTriggerTime() {
		return nextTriggerTime;
	}

	public JobInfo setNextTriggerTime(String nextTriggerTime) {
		this.nextTriggerTime = nextTriggerTime;
		return this;
	}
	
}