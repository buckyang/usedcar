package com.amateur.menu.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amateur.domain.MenuItem;
import com.amateur.menu.dto.MenuManagerDTO;
import com.amateur.persistence.MenuMapper;

/**
 * 
 * @author jayhe
 * 
 *         Current logic just process first lever menu and it's sub-menus.In the
 *         future,we will change it to support multi-level menu.
 * 
 */
@Service
public class MenuManagerService {

	@Autowired
	private MenuMapper menuMapper;

	public Map<Integer, MenuManagerDTO> getMenuManagerDTO() {
		Map<Integer, MenuManagerDTO> menuIdToMenuItem = new HashMap<Integer, MenuManagerDTO>();
		List<MenuItem> topMenuItems = menuMapper.getTopMenuItems();
		if (topMenuItems == null || topMenuItems.isEmpty()) {
			return menuIdToMenuItem;
		}
		for (MenuItem menuItem : topMenuItems) {
			Integer menuId = menuItem.getMenuId();
			MenuManagerDTO menuManagerDTO = new MenuManagerDTO();
			BeanUtils.copyProperties(menuItem, menuManagerDTO);
			processChildItem(menuManagerDTO,
					menuMapper.getChildMenuItems(menuId));
			menuIdToMenuItem.put(menuId, menuManagerDTO);
		}
		return menuIdToMenuItem;
	}

	private void processChildItem(MenuManagerDTO menuItem,
			List<MenuItem> menuItems) {
		if (menuItems == null || menuItems.isEmpty()) {
			return;
		}

		List<MenuManagerDTO> childMenuManagerDTOList = new ArrayList<MenuManagerDTO>();
		for (MenuItem item : menuItems) {
			MenuManagerDTO menuManagerDTO = new MenuManagerDTO();
			BeanUtils.copyProperties(item, menuManagerDTO);
			childMenuManagerDTOList.add(menuManagerDTO);
			menuItem.setChildMenuItems(childMenuManagerDTOList);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void updateMenuItemTitle(MenuItem menuItem) {
		menuMapper.updateMenuItemTitle(menuItem);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void exchangeItemOrder(MenuItem sourceItem,MenuItem targetItem){
		menuMapper.updateItemOrder(sourceItem);
		menuMapper.updateItemOrder(targetItem);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void deleteMenuItem(MenuItem menuItem) {
		menuMapper.deleteMenuItem(menuItem);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addMenuItem(MenuItem menuItem) {
		menuMapper.batchUpdateItemOrder(menuItem);
		menuMapper.addMenuItem(menuItem);
	}
}
