package com.newer.fileserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 服务器
 * @author lxm
 *
 */
public class Server   {

	//端口
	int port=9000;
	
	//服务器套接字
	ServerSocket serversocket;
	
	//线程池
	ExecutorService pool;
	
	
	HashMap<String, File> map = new HashMap<>();
	
	public void start() throws IOException	{
		
		System.out.println("服务器启动成功");
		serversocket = new ServerSocket(port);
		
		while(true) {
		System.out.println("等待连接.....");
		
		Socket socket=serversocket.accept();
		
		System.out.println("成功建立一个连接");
		
		
		InputStream in = socket.getInputStream();
		
		byte[] hash=new byte[32];
		
		in.read(hash);
		
		String Fhash=new BigInteger(1,hash).toString(16);
		
		
		//
		pool=Executors.newCachedThreadPool();
		
		
		Future<File> f=pool.submit(new ServerCallable(socket));
		
		File file=null;
		try {
			file = f.get();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
		if(!map.isEmpty()&&map.containsKey(Fhash)) {
			System.out.println("文件重复");
			continue;
		}
		
		map.put(Fhash, file);
		
		
		
		
		
		
		
//		Future<File>f= (Future<File>) pool.submit(new ServerRunnable(socket));
//		File file=null;
//		try {
//			file=f.get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		map.put(Fhash, file);
		
		}
		
	}


	
}
