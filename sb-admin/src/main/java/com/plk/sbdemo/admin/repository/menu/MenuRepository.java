package com.plk.sbdemo.admin.repository.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.admin.domain.menu.MenuItem;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {

	/**
	 * @param parentId
	 * @return
	 */
	List<MenuItem> findByParentId(Long parentId);

	/**
	 * 删除菜单以及子菜单 id=parentId
	 * @param id
	 * @param parentId
	 */
	void deleteByIdOrParentId(Long id, Long parentId);

}
