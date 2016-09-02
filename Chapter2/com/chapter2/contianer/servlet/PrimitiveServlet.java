package com.chapter2.contianer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class PrimitiveServlet implements Servlet {
	
	/** 日志记录 */
	private final static Logger LOG = Logger.getLogger(PrimitiveServlet.class); 

	@Override
	public void init(ServletConfig config) throws ServletException {
		LOG.info("init");
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		LOG.info("from service");
		PrintWriter writer = response.getWriter();
		writer.write("Hello, Roses are red");
		writer.write("Violets are blue");
	}
	
	@Override
	public void destroy() {
		LOG.info("destory");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}


}
