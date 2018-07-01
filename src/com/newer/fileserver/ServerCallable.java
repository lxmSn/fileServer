package com.newer.fileserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class ServerCallable implements Callable<File> {

	Socket socket;

	int i = 0;

	// 输入流读取传来的消息
	InputStream in;

//	HashMap<String, File> map ;

	public ServerCallable(Socket socket) {

		this.socket = socket;

	}
	


	@Override
	public File call() throws Exception {
		ByteArrayOutputStream baot = new ByteArrayOutputStream();

		byte[] buf = new byte[1024 * 4];
//		byte[] hash = new byte[32];

		int size;

		// 散列值
		String file = "";

		try {
			in = socket.getInputStream();
//			in.read(hash);
//
//			file = new BigInteger(1,hash).toString(16);

			while (-1 != (size = in.read(buf))) {
				baot.write(buf, 0, size);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 文件内容
		byte[] info = baot.toByteArray();

		File f=null;
		i++;
		try (FileOutputStream fileOut = new FileOutputStream(f = new File(String.format("file_%3d.txt", i)))) {

//			if (!map.isEmpty() && map.containsKey(file)) {
//
//				System.out.println("已经存在文件，传输失败");
//
//			} else {
				fileOut.write(info);
//				map.put(file, f);

//			}
//			System.out.println(file);
//			boolean b=map.containsKey(file);
//			System.out.println(b);
			fileOut.flush();

		} catch (Exception e) {
			System.out.println("传送失败");
		}
		

		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
	}



}
