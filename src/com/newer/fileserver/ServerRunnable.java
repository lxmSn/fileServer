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

public class ServerRunnable implements Runnable {

	Socket socket;

	// 输入流读取传来的消息
	InputStream in;

	public ServerRunnable(Socket socket) {

		this.socket = socket;

	}

	@Override
	public void run() {

		ByteArrayOutputStream baot = new ByteArrayOutputStream();

		byte[] buf = new byte[1024 * 4];

		int size;

		try {
			in = socket.getInputStream();
			while (-1 != (size = in.read(buf))) {
				baot.write(buf, 0, size);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 文件内容
		byte[] info = baot.toByteArray();

		// 文件名
		String file = "";

		try {
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(info);
			file = new BigInteger(1, hash).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (FileOutputStream fileOut = new FileOutputStream(file)) {

			fileOut.write(info);
			System.out.println("传送成功");

		} catch (Exception e) {
			System.out.println("传送失败");
		}

	}

}
