package com.chapteri.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private final static int BUFFER_SIZE = 1024;
	
	private OutputStream out;
	
	Request request;
	
	public Response(OutputStream out){
		this.out = out;
	}
	
	public void setRequest(Request req){
		this.request = req;
	}
	
	public void sendStaticResource() throws IOException{
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try{
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()){
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while(ch != -1){
					out.write(bytes);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			}else{
				String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
						"Content-Type: text/html\r\n" +
						"Content-Lenght: 23\r\n" +
						"\r\n" + 
						"<h1>File not found</h1>";
				out.write(errorMessage.getBytes());
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(fis != null){
				fis.close();
			}
		}
	}
}
