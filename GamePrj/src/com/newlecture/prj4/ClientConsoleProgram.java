package com.newlecture.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientConsoleProgram {

	public static void main(String[] args) throws IOException {
		Socket sock = new Socket("192.168.0.70", 10000);
		OutputStream nos = sock.getOutputStream();
		InputStream nis = sock.getInputStream();

		PrintStream out = new PrintStream(nos, true);
		String text;
		Scanner scan = new Scanner(System.in);
		Scanner scanEcho = new Scanner(nis);
		do {

			text = scan.nextLine();

			out.println(text);
			String echo = scanEcho.nextLine();
			System.out.println(echo);
		} while (!text.equals("bye"));

		scan.close();
		out.close();
		nis.close();
		nos.close();
		sock.close();
	}

}
