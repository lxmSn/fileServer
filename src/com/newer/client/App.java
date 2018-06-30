package com.newer.client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author lxmSn
 *
 */
public class App {

	
	
	public static void main(String[] args) {
		
		Client client = new Client();
		
		try {
			client.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
