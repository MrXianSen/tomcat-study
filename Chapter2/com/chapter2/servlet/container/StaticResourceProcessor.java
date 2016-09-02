package com.chapter2.servlet.container;

public class StaticResourceProcessor {
	
	public void process(Request request, Response response){
		try{
			response.sendStaticResource();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
