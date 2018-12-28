package com.gxwsxx.xiaoshou.comtroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.model.ResponseModel;

@RestControllerAdvice
public class ExceptionController {

	Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public ResponseModel error(Exception e) {
		log.error(e.getMessage(), e);
		XiaoshouException ex = null;
		if (e instanceof XiaoshouException) {
			ex = (XiaoshouException) e;
		} else {
			ex = new XiaoshouException();
		}
		ResponseModel rm = new ResponseModel();
		rm.setStatus(ex.getStatus());
		rm.setMsg(ex.getMessage());
		return rm;
	}

}
