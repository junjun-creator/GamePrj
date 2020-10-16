package com.newlecture.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerProgram {

	public static void main(String[] args) throws IOException {
		ServerSocket svrSock = new ServerSocket(10000);
		System.out.println("Server Started...");
		Socket sock = svrSock.accept();
		System.out.println("손님이 왔습니다.");
		
		OutputStream nos = sock.getOutputStream();
		InputStream nis = sock.getInputStream();
		PrintStream nout = new PrintStream(nos,true);
		
		Scanner nscan = new Scanner(nis);
		
		String msg;
		
		do {
			
			msg = nscan.nextLine();
			System.out.println(msg);
			
			nout.println("echo : "+ msg);
			
		}while(!msg.equals("bye"));
	
		
		
		
		//System.out.println("connected from " + svrSock.getLocalSocketAddress());
	}

}
