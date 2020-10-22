package com.newlecture.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClientFrame extends JFrame {
	
	private PaintCanvas canvas;
	private ChatPanel panel;
	private Button btnSend;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mExit;
	
	private JMenu mnServer;
	private JMenuItem mConnect;
	private JMenuItem mClose;
	
	private Socket socket;
	
	private Scanner nscan;
	private PrintStream nout;
	
	public ClientFrame() {
		
		btnSend = new Button("send");
		canvas = new PaintCanvas();
		add(canvas);
		
		panel = new ChatPanel();
		panel.setPreferredSize(new Dimension(300,0));
		panel.setListener(new btnEventListener() {
			
			@Override
			public void onSend(String chatMsg) {
				// TODO Auto-generated method stub
				panel.setOutputText("\n"+chatMsg);
				
			}
		});
		add(panel,BorderLayout.LINE_END);
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Menu");
		menuBar.add(mnFile);
		
		mExit = new JMenuItem("Exit");
		mnFile.add(mExit);
		mExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mnServer = new JMenu("Server");
		menuBar.add(mnServer);
		
		mConnect = new JMenuItem("Connect");
		mConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket("192.168.0.70",10000);
					
					if(socket.isConnected()) {
						InputStream nis = socket.getInputStream();
						OutputStream nos = socket.getOutputStream();
						
						nscan = new Scanner(nis);
						nout = new PrintStream(nos);//연결되자마자 버퍼를 만들어서
						
						//메세지를 받기위해 대기해야함
						
						new Thread(new Runnable() {
							@Override
							public void run() {//다른 스레드에서 계속 메시지를 받아옴
								while(nscan.hasNextLine()) {
									String msg = nscan.nextLine();
									panel.setOutputText("\n"+msg);
								}
							}
						}).start();
						
						canvas.setActive();
						
						panel.setOutputText("서버에 연결되었습니다.");
					}
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mClose = new JMenuItem("Close");
		mnServer.add(mConnect);
		mnServer.add(mClose);
		
		//add(btnSend,BorderLayout.LINE_END);//default layout은 borderLayout
		setSize(800,500);
		
		addWindowListener(new WindowAdapter() {
			
		
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		
		});
	}
}
