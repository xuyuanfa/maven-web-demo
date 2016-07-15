package com.xxx.common.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SlowRequestInterceptor extends HandlerInterceptorAdapter {
	
	private final Log log = LogFactory.getLog(SlowRequestInterceptor.class);
	
	private Long minSlowTime;
	
	private Long start =0L;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		start = System.currentTimeMillis();
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			long spendTime = System.currentTimeMillis()-start;
		if(spendTime > minSlowTime){
			// TODO 
//			log.warn("慢请求:  "+BaseController.getRequestParameter(request)+" 请求耗时: "+ spendTime + "ms");
			log.warn("");
		}
	}
	public void setMinSlowTime(Long minSlowTime) {
		this.minSlowTime = minSlowTime;
	}
	
	
}