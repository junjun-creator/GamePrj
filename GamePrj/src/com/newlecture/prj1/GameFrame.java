package com.newlecture.prj1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;

public class GameFrame extends Frame {

	public GameFrame() {
		Canvas canvas = new GameCanvas();
		setVisible(true);
		setSize(900, 550);	
		setBackground(Color.pink);
		add(canvas);
	}
}

