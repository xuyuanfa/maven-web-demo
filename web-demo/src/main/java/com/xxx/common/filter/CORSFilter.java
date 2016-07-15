package com.xxx.common.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {
	
	public static int isWebCache;
	
	public static int isCORS;
	
	static{
		Properties properties = new Properties();
        String classPath = Thread.currentThread().getContextClassLoader().getResource("")
                .getPath();
        String filepath = classPath + "config.properties";
		try {
		  properties.load(new FileInputStream(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isWebCache = properties.getProperty("webcache.open")==null?0:Integer.parseInt(properties.getProperty("webcache.open").trim());
		isCORS = properties.getProperty("app.isCORS")==null?0:Integer.parseInt(properties.getProperty("app.isCORS").trim());
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		if(isWebCache!=1)response.setHeader("Cache-Control", "max-age=0");
		if(isCORS==1){
			response.setHeader("Access-Control-Allow-Origin", "http://xxxtest.com");
		}else{
			response.setHeader("Access-Control-Allow-Origin", "http://*.xxx.com");
		}
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, x-auth-token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}