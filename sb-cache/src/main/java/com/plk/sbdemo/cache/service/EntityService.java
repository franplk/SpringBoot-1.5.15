package com.plk.sbdemo.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.cache.domain.Entitybean;

@Service
public class EntityService {

	@Cacheable("test.entity")
	public Entitybean getBean() {

		findBean();

		return new Entitybean("dev", "001");
	}

	private void findBean() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
