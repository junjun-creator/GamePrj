package com.newlecture.prj3;

import java.awt.Canvas;
import java.awt.Frame;

import com.newlecture.prj3.canvas.ActionCanvas;
import com.newlecture.prj3.canvas.IntroCanvas;

public class GameFrame extends Frame {
	public static GameFrame instance;
	private ActionCanvas canvas;
	private IntroCanvas introCanvas;
	
	public GameFrame() {
		instance = this;
		
		//Canvas canvas = new PuzzleCanvas();
		//canvas = new ActionCanvas();
		introCanvas = new IntroCanvas();
		
		add(introCanvas);
		//canvas.start();
		
		setSize(360, 600);
		setVisible(true);	
		//add(canvas);
	}

	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		remove(oldCanvas);
		
		add((Canvas)newCanvas.newInstance());
		revalidate();//변화가 생겻으니 다시 확인해서 그려라!
	}
}
