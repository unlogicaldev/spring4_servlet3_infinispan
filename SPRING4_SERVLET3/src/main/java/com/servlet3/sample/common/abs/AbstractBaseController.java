package com.servlet3.sample.common.abs;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractBaseController<T> {

	@SuppressWarnings("unchecked")
	final protected Logger log = LoggerFactory.getLogger(((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
	
	/**
	 * <PRE>
	 * 1. MethodName	:	getSessionId
	 * 2. Comment		:	sessionId 생성 --> 후 바로 소멸.
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 3:41:44
	 * </PRE>
	 *
	 *	@param req
	 *	@return
	 */
	protected String getSessionId(HttpServletRequest req) {
		HttpSession s = req.getSession();
		String id = s.getId();
		s.invalidate();
		return id;
	}
	
	/**
	 * 모든 에러케이스에 전부 적용시키는 에러헨들러 이다.
	 * Exception을 다시 던지는 이유 --> 모든 ajax요청에 Error처리가 되어있음.
	 * WAS에 접속하기 전 Error가 발생하면 WS에서 처리하도록 되어있다.
	 * @param e
	 * @param request
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	protected void handleException(Exception e, HttpServletRequest request) throws Exception{
		StackTraceElement[] st = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(new Date()).append("\r\n");
		sb.append(log.getName()).append("\r\n");
		for(StackTraceElement s: st){
			sb.append(s.toString()).append("\r\n");
		}
		throw new Exception();
	}
}
