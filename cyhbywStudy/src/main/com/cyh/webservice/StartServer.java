package com.cyh.webservice;

import javax.xml.ws.Endpoint;

/**
 * @author zsy 启动web services服务
 */
public class StartServer {

	public static void main(String[] args) {
		Example serverBean = new Example();
		Endpoint.publish("http://localhost:8080/hello", serverBean);
		
		System.out.println("服务已启动 http://localhost:8080/hello");
	}
}