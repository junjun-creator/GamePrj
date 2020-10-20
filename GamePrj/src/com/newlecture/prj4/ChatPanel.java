package com.newlecture.prj4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class ChatPanel extends Panel {
	
	//private TextField text;//한줄만 쓸수 있는것
	private TextArea text;//여러 라인 쓸수 있는것
	private Panel inputPanel;
	
	public ChatPanel() {
		setLayout(new BorderLayout());//panel은 레이아웃이 Border가 아니므로
		//setPreferredSize(new Dimension(0, 200)) 이걸 하기 위해서는 바꿔줘야함
		text = new TextArea();
		inputPanel = new Panel();
		
		inputPanel.setPreferredSize(new Dimension(0, 100));
		
		add(text);
		add(inputPanel,BorderLayout.PAGE_END);
	}
}
