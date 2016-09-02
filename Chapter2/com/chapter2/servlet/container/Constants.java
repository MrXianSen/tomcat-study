package com.chapter2.servlet.container;

import java.io.File;

public class Constants {
	public final static String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";
	
	public final static String SERVLET_ROOT = System.getProperty("user.dir") + "/com/chapter2/contianer/servlet/" ;
}
