package com.amateur.menu.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amateur.controller.BaseController;
import com.amateur.domain.MenuItem;
import com.amateur.menu.dto.MenuManagerDTO;
import com.amateur.menu.manager.service.MenuManagerService;

@Controller
@RequestMapping("/admin")
public class MenuManagerController extends BaseController {

	@Autowired
	private MenuManagerService menuManagerService;

	@Override
	protected String getPostSuccessCode() {
		return "modify.menu.success";
	}


	@ModelAttribute("menuManagerDTO")
	public MenuManagerDTO createFormBean() {
		MenuManagerDTO menuManagerDTO = new MenuManagerDTO();
		return menuManagerDTO;
	}

	@RequestMapping(value = "/menuManager", method = RequestMethod.GET)
	public void viewMenuItems(HttpServletRequest req) {

	}

	@RequestMapping(value = "/updateItemTitle", method = RequestMethod.POST)
	public String updateMenuItemTitle(
			@ModelAttribute("menuManagerDTO") MenuManagerDTO menuManagerDTO,
			HttpServletRequest req) {
		String menuId=req.getParameter("menuId");
		String parentMenuId=req.getParameter("parentMenuId");
		String title=req.getParameter("title");
		MenuItem menuItem = new MenuItem();
		menuItem.setMenuId(Integer.valueOf(menuId));
		menuItem.setTitle(title);
		menuManagerService.updateMenuItemTitle(menuItem);
		req.setAttribute("parentMenuId", parentMenuId);
		req.getSession().getServletContext().setAttribute("MENU_ITEMS", menuManagerService.getMenuManagerDTO());
		return "admin/refreshMenuItem";
	}
	

	@RequestMapping(value = "/exchangeItemOrder", method = RequestMethod.POST)
	public String exchangeItemOrder(HttpServletRequest req) {
		String parentMenuId=req.getParameter("parentMenuId");
		String sourceMenuId = req.getParameter("sourceMenuId");
		String sourceMenuOrder = req.getParameter("sourceMenuOrder");
		String targetMenuId = req.getParameter("targetMenuId");
		String targetMenuOrder = req.getParameter("targetMenuOrder");
		
		MenuItem sourceItem = new MenuItem();
		MenuItem targetMenuItem = new MenuItem();
		
		sourceItem.setMenuId(Integer.valueOf(sourceMenuId));
		targetMenuItem.setDisplayOrder(Integer.valueOf(sourceMenuOrder));
		
		targetMenuItem.setMenuId(Integer.valueOf(targetMenuId));
		sourceItem.setDisplayOrder(Integer.valueOf(targetMenuOrder));
		
		menuManagerService.exchangeItemOrder(sourceItem, targetMenuItem);
		
		req.setAttribute("parentMenuId", parentMenuId);
		req.getSession().getServletContext().setAttribute("MENU_ITEMS", menuManagerService.getMenuManagerDTO());
		return "admin/refreshMenuItem";
	}

	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public String deleteMenuItem(@ModelAttribute("menuManagerDTO") MenuManagerDTO menuManagerDTO,HttpServletRequest req) {
		String menuId=req.getParameter("menuId");
		String parentMenuId=req.getParameter("parentMenuId");
		MenuItem menuItem = new MenuItem();
		menuItem.setMenuId(Integer.valueOf(menuId));
		menuManagerService.deleteMenuItem(menuItem);
		req.setAttribute("parentMenuId", parentMenuId);
		req.getSession().getServletContext().setAttribute("MENU_ITEMS", menuManagerService.getMenuManagerDTO());
		return "admin/refreshMenuItem";
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addMenuItem(
			@ModelAttribute("menuManagerDTO") MenuManagerDTO menuManagerDTO,
			HttpServletRequest req) {
		MenuItem menuItem = new MenuItem();
		BeanUtils.copyProperties(menuManagerDTO, menuItem);
		menuManagerService.addMenuItem(menuItem);
		req.setAttribute("parentMenuId", menuManagerDTO.getParentMenuId());
		req.getSession().getServletContext().setAttribute("MENU_ITEMS", menuManagerService.getMenuManagerDTO());
		return "admin/refreshMenuItem";
	}

}
