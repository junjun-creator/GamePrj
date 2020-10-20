package com.newlecture.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class ClientFrame extends JFrame {
	
	private PaintCanvas canvas;
	private ChatPanel panel;
	private Button btnSend;
	
	public ClientFrame() {
		
		btnSend = new Button("send");
		canvas = new PaintCanvas();
		add(canvas);
		
		panel = new ChatPanel();
		panel.setPreferredSize(new Dimension(300,0));
		add(panel,BorderLayout.LINE_END);
		
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
