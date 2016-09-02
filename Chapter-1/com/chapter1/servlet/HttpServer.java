package com.chapter1.servlet;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	public final static String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	private final static String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try{
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch(Exception ex){
			System.exit(1);
		}
		while(!shutdown){
			Socket client = null;
			InputStream in = null;
			OutputStream out = null;
			
			try{
				//wait connection from client
				client = serverSocket.accept();
				in = client.getInputStream();
				out = client.getOutputStream();
				
				Request request = new Request(in);
				Response response = new Response(out);
				response.setRequest(request);
				response.sendStaticResource();
				client.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception ex){
				System.exit(1);
			}
		}
	}
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
}
