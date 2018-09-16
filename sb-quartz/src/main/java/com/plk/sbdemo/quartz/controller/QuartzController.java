package com.plk.sbdemo.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quartz")
public class QuartzController {

	@RequestMapping("")
	public String index() {
		return "redirect:/quartz/joblist";
	}
	
	@RequestMapping("/joblist")
	public String jobListPage() {
		return "quartz/jobList";
	}
}
