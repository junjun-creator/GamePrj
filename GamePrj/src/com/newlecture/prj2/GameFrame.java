package com.newlecture.prj2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;

import com.newlecture.prj2.canvas.ActionCanvas;
import com.newlecture.prj2.canvas.ActionCanvas2;

public class GameFrame extends Frame {

	public GameFrame() {
		ActionCanvas2 canvas = new ActionCanvas2();
		setVisible(true);
		setSize(900, 550);	
		canvas.start();//���� ������� ������� ���...���� ������� �ִϸ��̼� ���
		
		add(canvas);
	}
}

