package com.newer.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author lxm
 *
 */
public class Client {

	//客户端套接字
	Socket socket;
	
	//服务器地址
	String serverAddress = "";
	
	//服务器端口
	int serverPort=9000;
	
	
	
	
	public void start() throws UnknownHostException, IOException {
		
		System.out.println("申请连接");
		socket =new Socket(serverAddress, serverPort);
		System.out.println("连接成功");
		
		
		
	}
	
	
	
}
