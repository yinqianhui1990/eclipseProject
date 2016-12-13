package com.yqh.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Test1 {

	public static void main(String[] args) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		CloseableHttpResponse response =null;
		HttpGet httpget = new HttpGet("http://www.baidu.com");  
		try {  
			response= httpclient.execute(httpget);  
			System.out.println(response.getProtocolVersion());  
			System.out.println(response.getStatusLine().getStatusCode());  
			System.out.println(response.getStatusLine().getReasonPhrase());  
			System.out.println(response.getStatusLine().toString()); 
			System.out.println();
			InputStream in=response.getEntity().getContent();
			System.out.println(in.available());
			byte[] s=new byte[1000*100];
			int i=-1;
			while((i=in.read(s))!=-1){
			
				System.out.println("======>>"+i);
				System.out.println(new String(s));
			}
		   
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
		    response.close();  
		}  

	}

}
