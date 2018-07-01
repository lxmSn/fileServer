package com.newer.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	
	//文件输入流
	FileInputStream fileIn;
	
	//输出流
	OutputStream out;
	
	
	public void start() throws UnknownHostException, IOException {
		
		System.out.println("申请连接");
		socket =new Socket(serverAddress, serverPort);
		System.out.println("连接成功");
		
		fileIn = new FileInputStream("CateHuntsman.txt");
		
		out = socket.getOutputStream();
		
		byte[] buf = new byte[1024*4];
		
		int size;
		
		while(-1!=(size=fileIn.read(buf))) {
			
			out.write(buf,0,size);
			out.flush();
		}
		System.out.println("发送完毕");
		
		fileIn.close();
		
		socket.close();
		
	}
	
	
	
}
