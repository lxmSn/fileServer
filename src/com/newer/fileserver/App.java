package com.newer.fileserver;

import java.io.IOException;

/**
 * 服务端
 * 
 * @author lxmSn
 *
 */
public class App {

	
	public static void main(String[] args) {
		
		Server server = new Server();
		
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
