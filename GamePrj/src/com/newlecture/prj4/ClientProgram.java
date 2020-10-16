package com.newlecture.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("192.168.0.70", 10000);
		
		OutputStream nos = sock.getOutputStream();
		InputStream nis = sock.getInputStream();
		
		PrintStream out = new PrintStream(nos,true);//true는 flush를 자동으로 해달라고 설정
		
		Scanner scan = new Scanner(System.in);
		System.out.println("보낼 메세지를 입력하시오. ");
		String k = scan.nextLine();
		out.println(k);
		
		out.close();//클로즈를 열엇던 반대 순서로 닫아줘야함 안그러면 전송중에 닫혀서 문제가 생김
		nos.close();
		nis.close();
		sock.close();
		
		//System.out.println("connect to "+ sock.getRemoteSocketAddress());
	}

}
