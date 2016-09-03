package com.chapter3.connector.http;


/**
 * this class is called by {@link HttpConnector} 
 * @author albery
 *
 */
public class HttpProcessor {
	
	private HttpConnector connector;
	
	public HttpProcessor(HttpConnector connector){
		this.connector = connector;
	}
	
}
