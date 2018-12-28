package com.gxwsxx.xiaoshou.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.exception.XiaoshouException.Type;
import com.gxwsxx.xiaoshou.mapper.UserMapper;
import com.gxwsxx.xiaoshou.model.UserModel;

@Service
public class UserService {

	@Autowired
	UserMapper um;

	Map<String, UserModel> tokenMap;

	public final static String SECURITY = "***";

	public UserService() {
		tokenMap = new HashMap<>();
	}

	public String login(String username, String password) throws XiaoshouException {
		if (null == username || null == password || SECURITY.equals(password)) {
			throw new XiaoshouException(Type.USERNAME_PASSWORD);
		}
		UserModel u = um.selectByUsername(username);
		if (null != u && u.getPassword().equals(password)) {
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			u.setToken(token);
			um.updateToken(u);
			u.setPassword(SECURITY);
			u.setToken(SECURITY);
			tokenMap.put(token, u);
			return token;
		} else {
			throw new XiaoshouException(Type.USERNAME_PASSWORD);
		}
	}

	public void logout(String token) {
		UserModel u = tokenMap.get(token);
		if (null != u) {
			u.setToken(null);
			um.updateToken(u);
			tokenMap.remove(token);
		}
	}

	public UserModel getUserByToken(String token) throws XiaoshouException {
		UserModel u = tokenMap.get(token);
		if (null == u) {
			u = um.selectByToken(token);
			if (null != u) {
				tokenMap.put(token, u);
			}
		}
//		if (null == u || token.length() != 32) {
		if (null == u) {
			throw new XiaoshouException(Type.TOKEN_NOT_FIND);
		} else {
			return u;
		}
	}

	public UserModel getUserById(String id) {
		return um.selectById(id);
	}
}
