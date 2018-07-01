package com.newer.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * 客户端
 * 
 * @author lxm
 *
 */
public class Client {

	// 客户端套接字
	Socket socket;

	// 服务器地址
	String serverAddress = "";

	// 服务器端口
	int serverPort = 9000;

	// 文件输入流
	FileInputStream fileIn;


	

	public void start() throws UnknownHostException, IOException{
		
		Scanner sc =new Scanner(System.in);
		System.out.println("输入想传输的文件名:");
		String file=sc.nextLine();
		
		

		System.out.println("申请连接");
		socket = new Socket(serverAddress, serverPort);
		System.out.println("连接成功");

		fileIn = new FileInputStream(file);

		ByteArrayOutputStream baot = new ByteArrayOutputStream();

		byte[] buf = new byte[1024 * 4];

		int size;
		
		while(-1!=(size=fileIn.read(buf))) {
			baot.write(buf,0,size);
		}
		//文件内容
		byte[] info=baot.toByteArray();
		
		//第一次传输文件的散列值
		
		String filehash="";
		byte[] hash=null;
		
		try {
			 hash= MessageDigest.getInstance("SHA-256").digest(info);
			 filehash=new BigInteger(1,hash).toString(16);
			System.out.println(filehash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedOutputStream out=new BufferedOutputStream(socket.getOutputStream());
		
		
		
		
		out.write(hash);
		out.flush();
		
		out.write(info);
		out.flush();

		System.out.println("发送完毕");

		fileIn.close();
		
		socket.close();

	}

}
