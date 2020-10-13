package com.newlecture.prj2.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.prj2.service.PuzzleService;


public class PuzzleCanvas extends Canvas{

	
	public static Canvas intance;
	
	private int imgCount;
	private PuzzleService service;

	public PuzzleCanvas() {
		
		intance = this;
		
		service = new PuzzleService();
		service.shuffle();
	}

	public boolean mouseDown(Event evt, int a, int b) {

		service.shuffle();
		repaint();

		return super.mouseDown(evt, a, b);
	}

	public void paint(Graphics g) {

		for(int i=0;i<6;i++) 
			service.get(i).paint(g);
	}
}
