package com.chapter3.connector.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletInputStream;

import org.apache.catalina.util.ParameterMap;

/**
 * http request from client
 * 
 * @author albery
 * 
 */
public class HttpRequest {

	private String contentType;
	private int contentLength;
	private InetAddress inetAddress;
	private InputStream input;
	private String method;
	private String protocol;
	private String queryString;
	private String requestUri;
	private String serverName;
	private int serverPort;
	private Socket socket;
	private boolean requestedSessionCookie;
	private String requestedSessionId;
	private boolean requestedSessionURL;

	/**
	 * 本次请求的请求参数
	 */
	protected HashMap attributes = new HashMap();

	/**
	 * 请求中的认证证书
	 */
	protected String authorization = null;

	/**
	 * 请求的上下文路径
	 */
	protected String contextPath = "";

	/**
	 * 请求中的cookie集合
	 */
	protected ArrayList cookies = new ArrayList();

	/**
	 * An empty collection to use for returning empty Enumerations. Do not add
	 * any elements to this collection!
	 */
	protected static ArrayList empty = new ArrayList();
	
	protected SimpleDateFormat formats[] = {
			new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss zzz", Locale.CHINA),
			new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.CHINA),
		    new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.CHINA)
	};
	
	/**
	 * HTTP请求头
	 */
	protected HashMap headers = new HashMap();
	
	/**
	 * 请求参数
	 */
	protected ParameterMap patameters = null;
	
	/**
	 * 记录这个请求是否被处理过
	 */
	protected boolean parsed = false;
	protected String pathInfo = null;
	
	protected BufferedReader reader = null;
	
	protected ServletInputStream stream = null;
	
	public HttpRequest(InputStream input){
		this.input = input;
	}
	
	
}
