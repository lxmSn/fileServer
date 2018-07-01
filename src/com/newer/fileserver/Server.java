package com.newer.fileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器
 * @author lxm
 *
 */
public class Server {

	//端口
	int port=9000;
	
	//服务器套接字
	ServerSocket serversocket;
	
	//线程池
	ExecutorService pool;
	
	public void start() throws IOException	{
		
		System.out.println("服务器启动成功");
		serversocket = new ServerSocket(port);
		
		System.out.println("等待连接.....");
		
		Socket socket=serversocket.accept();
		
		System.out.println("成功建立一个连接");
		
		//
		pool=Executors.newCachedThreadPool();
		
		
		pool.submit(new ServerRunnable(socket));
		
		
	}
	
	
}
