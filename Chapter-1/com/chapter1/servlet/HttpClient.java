package com.chapter1.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpClient {
	
	public static void main(String[] args) {
		Socket client = null;
		boolean autoFlush = true;
		try{
			client = new Socket("127.0.0.1", 8080);
			PrintWriter writer = new PrintWriter(client.getOutputStream(), autoFlush);
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			// send http request to HttpServer
			writer.println("GET /index/index.html HTTP/1.1");
			writer.println("Host: localhost:8080");
			writer.println("Connection: Close ");
			writer.println();
			
			// read response from HttpServer
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while(loop){
				if(reader.ready()){
					int i=0;
					while(i != -1){
						i = reader.read();
						sb.append((char)i);
					}
					loop = false;
				}
			}
			System.out.println(sb.toString());
			client.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
