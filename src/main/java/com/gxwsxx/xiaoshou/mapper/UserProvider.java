package com.gxwsxx.xiaoshou.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.gxwsxx.xiaoshou.model.UserModel;

public class UserProvider {

	public String select(UserModel model) {
		return new SQL() {
			{
				SELECT(UserMapper.USER_SECURITY).FROM("user").WHERE("status = '" + UserModel.Status.NORMAL + "'");
				if (null != model.getName()) {
					WHERE("name like '%" + model.getName() + "%'");
				}
			}
		}.toString();
	}

	public String update(UserModel model) {
		return new SQL() {
			{
				UPDATE("user");
				if (null != model.getName() && !model.getName().isEmpty()) {
					SET("name = #{name}");
				}
				if (null != model.getUsername() && !model.getUsername().isEmpty()) {
					SET("username = #{username}");
				}
				if (null != model.getPassword() && !model.getPassword().isEmpty()) {
					SET("password = #{password}");
				}
				if (null != model.getRole()) {
					SET("role = #{role}");
				}
				if (null != model.getEmail() && !model.getEmail().isEmpty()) {
					SET("email = #{email}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}

}
