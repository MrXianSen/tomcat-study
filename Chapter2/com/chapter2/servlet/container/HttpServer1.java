package com.chapter2.servlet.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

	/** shutdown command from client */
	private final static String SHUTDOWN = "/SHUTDOWN";
	
	/** when shutdown command receive will set true */
	boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer1 server = new HttpServer1();
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
		
		while(!shutdown){
			Socket client = null;
			InputStream in = null;
			OutputStream out = null;
			try {
				client = serverSocket.accept();
				in = client.getInputStream();
				out = client.getOutputStream();
				
				// Create request instance
				Request request = new Request(in);
				request.parse();
				
				// Create response instance
				Response response = new Response(out);
				response.setRequest(request);
				
				// 判断客户端是请求的是静态资源还是Servlet
				if(request.getUri().startsWith("/servlet/")){
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				}else{
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				//close the client socket
				client.close();
				shutdown = request.getUri().equals(SHUTDOWN);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
