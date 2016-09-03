package com.chapter3.connector.http;


import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {
	
	boolean stopped;
	
	private String schema = "http";
	
	public String getSchema(){
		return this.schema;
	}
	
	@Override
	public void run() {
		//服务器Socket
		ServerSocket server = null;
		int port = 8080;
		
		try{
			server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
		
		while(!stopped){
			// accept incoming client from browser
			Socket client = null;
			try{
				client = server.accept();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			//
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
}
