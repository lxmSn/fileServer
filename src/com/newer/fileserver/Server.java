package com.newer.fileserver;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 * 
 * @author lxm
 *
 */
public class Server {
	// 声明:服务端套接字
	ServerSocket serversocket;

	// 声明:服务端端口
	int port = 10001;

	// 建立一个线程池
	ExecutorService pool;

	// 定义一个键值对
	HashMap<String, File> map = new HashMap<>();

	int i = 1;

	ServerRunnable callable;

	public void start() throws IOException {
		// 实例化服务端套接字
		serversocket = new ServerSocket(port);
		System.out.println("服务器启动");

		// 实例化线程池
		pool = Executors.newFixedThreadPool(5);
		System.out.println("loading...");
		while (true) {
			// 侦听并返回连接至此的客户端套接字
			Socket socket = serversocket.accept();
			System.out.println("建立一个连接");

			pool.submit(callable = new ServerRunnable(socket, map, i));
			i++;
			map = callable.getMap();
		}

	}

	public static void main(String[] args) {
		Server fileServer = new Server();
		try {
			fileServer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
