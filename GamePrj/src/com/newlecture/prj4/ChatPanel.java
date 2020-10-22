package com.newlecture.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;

public class ChatPanel extends Panel {
	
	//private TextField text;//한줄만 쓸수 있는것
	private TextArea text;//여러 라인 쓸수 있는것
	private Panel inputPanel;
	private Button btnSend;
	private TextArea inputTextBox;
	
	
	
	private btnEventListener listener;
	
	public void setListener(btnEventListener listener) {
		this.listener = listener;
	}

	public ChatPanel() {
		setLayout(new BorderLayout());//panel은 레이아웃이 Border가 아니므로
		//setPreferredSize(new Dimension(0, 200)) 이걸 하기 위해서는 바꿔줘야함
		text = new TextArea();
		text.setEditable(false);
		inputPanel = new Panel();
		btnSend = new Button("send");
		inputTextBox = new TextArea();
		//btnSend.setPreferredSize(new Dimension(0,100));
		
		inputPanel.setPreferredSize(new Dimension(0, 100));
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(inputTextBox,BorderLayout.CENTER);
		inputPanel.add(btnSend,BorderLayout.LINE_END);
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String chatMsg = inputTextBox.getText();
				listener.onSend(chatMsg);
				inputTextBox.setText("");
				inputTextBox.setFocusable(true);
			}
		});
		
		add(text);
		add(inputPanel,BorderLayout.PAGE_END);
		
	}

	public void setOutputText(String message) {
		// TODO Auto-generated method stub
		//text.setText(message);//기존에 있던 문자를 바꿔버림
		text.append(message);//계속해서 중첩
	}

}
