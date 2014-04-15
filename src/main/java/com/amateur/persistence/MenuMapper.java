package com.amateur.persistence;

import java.util.List;

import com.amateur.domain.MenuItem;

public interface MenuMapper {

	List<MenuItem> getTopMenuItems();
	
	List<MenuItem> getChildMenuItems(Integer parentMenuItemId);
	
	void updateMenuItemTitle(MenuItem menuItem);
	
	void batchUpdateItemOrder(MenuItem menuItem);
	
	void updateItemOrder(MenuItem menuItem);

	void deleteMenuItem(MenuItem menuItem);
	
	void addMenuItem(MenuItem menuItem);
}
