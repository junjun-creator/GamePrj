package com.newlecture.prj4;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerProgram {

	public static void main(String[] args) throws IOException {
		ServerSocket svrSock = new ServerSocket(10000);
		System.out.println("Server Started...");
		svrSock.accept();
		System.out.println("손님이 왔습니다.");
		
		//System.out.println("connected from " + svrSock.getLocalSocketAddress());
	}

}
