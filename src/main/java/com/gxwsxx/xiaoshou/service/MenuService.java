package com.gxwsxx.xiaoshou.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxwsxx.xiaoshou.mapper.MenuMapper;
import com.gxwsxx.xiaoshou.model.MenuModel;
import com.gxwsxx.xiaoshou.model.UserModel;

@Service
public class MenuService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	MenuMapper mm;

	public List<MenuModel> getAllByRole(UserModel.Role role) {
		List<MenuModel> roots = mm.selectRoot(role);
		if (null != roots && 0 != roots.size()) {
			roots.forEach(root -> {
				childrenByRole(root, role);
			});
		}
		return roots;
	}

	public MenuModel childrenByRole(MenuModel menu, UserModel.Role role) {
//		log.debug(menu.getId());
		if (menu.getGroup()) {
			List<MenuModel> children = mm.selectByParent(role, menu.getId());
			if (null != children && 0 != children.size()) {
				children.forEach(child -> {
					childrenByRole(child, role);
				});
			}
			menu.setChildren(children);
		}
		return menu;
	}

}
