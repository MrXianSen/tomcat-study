package com.chapter1.servlet;

import java.io.InputStream;

public class Request {
	private InputStream in;
	private String uri;
	
	public Request(InputStream in){
		this.in = in;
		parse();
	}
	
	public void parse(){
		// read characters from socket
		StringBuffer request = new StringBuffer(2048);
		byte[] buf = new byte[1024];
		
		int i = 0;
		try{
			i = in.read(buf);
		}catch(Exception ex){
			ex.printStackTrace();
			i = -1;
		}
		for(int j=0;j<i;j++){
			request.append((char)buf[j]);
		}
		System.out.println(request.toString());
		uri = this.parseUri(request.toString());
	}
	
	private String parseUri(String requestString){
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if(index1 != -1){
			index2 = requestString.indexOf(' ', index1+1);
			if(index2 > index1){
				return requestString.substring(index1+1, index2);
			}
		}
		return null;
	}
	
	public String getUri(){
		return this.uri;
	}
}
