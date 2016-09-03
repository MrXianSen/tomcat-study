package com.chapter2.servlet.container;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

public class ServletProcessor1 {
	public void process(Request request, Response response){
		// 获取请求的uri
		String uri = request.getUri();
		// 获取请求的Servlet类名
		String servletName = uri.substring(uri.lastIndexOf('/') + 1);
		URLClassLoader loader = null;
		
		try{
			//create a url class loader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		Class<?> myClass = null;
		try{
			myClass = loader.loadClass(servletName);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet)myClass.newInstance();
			servlet.service(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
