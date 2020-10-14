package com.newlecture.prj3;

import java.awt.Frame;

import com.newlecture.prj3.canvas.ActionCanvas;

public class GameFrame extends Frame {
	
	public GameFrame() {
		//Canvas canvas = new PuzzleCanvas();
		ActionCanvas canvas = new ActionCanvas();
		canvas.start();
		
		
		
		System.out.println(hasFocus());
		setSize(360, 600);
		setVisible(true);	
		add(canvas);
	}
}
