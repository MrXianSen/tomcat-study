package com.chapter2.servlet.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {

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
			File file = new File(Constants.WEB_ROOT, request.getUri());
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
	
	/** implementations of ServletResponse **/
	
	@Override
	public void flushBuffer() throws IOException {
		
	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// autoflush is true, the println() method will flush
		// but print() will not
		PrintWriter writer = new PrintWriter(out, true);
		return writer;
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public void resetBuffer() {

	}

	@Override
	public void setBufferSize(int size) {

	}

	@Override
	public void setCharacterEncoding(String charset) {

	}

	@Override
	public void setContentLength(int len) {

	}

	@Override
	public void setContentType(String type) {

	}

	@Override
	public void setLocale(Locale loc) {

	}

}
