package com.plk.sbdemo.redis.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.redis.domain.DataBean;

/**
 * Use Mode-2: With Spring-Cache using annotation
 * 
 * Annotation {@code Cacheable} indicates find data from cache
 * Annotation {@code CacheEvict} indicates remove data from cache
 */
@Service
public class DataAccessService {

	@Cacheable(value = "dataAccess.test")
	public DataBean getData(DataBean bean) {
		return findData(bean);
	}
	
	@CacheEvict(value = "dataAccess.test")
	public void updateData(DataBean bean) {
		// do update
	}

	/**
	 * Simulation For Getting Data With 10 Seconds Cost
	 */
	private DataBean findData(DataBean bean) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return new DataBean();
	}
}
