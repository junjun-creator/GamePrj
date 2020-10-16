package com.newlecture.prj3.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Button {
	private double x;
	private double y;
	
	private int width;
	private int height;
	private String text;
	
	public Button() {
		this(10,10,200,50,"hello~");
	}
	
	public Button(double x, double y, int width, int height, String text) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	public void paint(Graphics g) {
		
		g.setColor(Color.orange);
		g.fillRoundRect((int)(x), (int)y, width, height, 20, 20);
		
		g.setColor(Color.black);
		g.drawString(text, (int)(x+30), (int)y+30);
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
}
