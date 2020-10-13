package com.newlecture.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.prj2.canvas.ActionCanvas;

public class Pic   {

	private int x;
	private int y;
	private Image img;
	private int w = 178;
	private int h = 255;

	public Pic() {
		this(150,150);
	}
	public Pic(int x, int y) {
		this.x = x;
		this.y = y;
	Toolkit tk = Toolkit.getDefaultToolkit();
	img = tk.getImage("res/pic3.jpg");
	}
	
	public void paint(Graphics g) {
		int x = this.x;
		int y = this.y;
		
		g.drawImage(img, x-w/2, y-h/2, x +w/2, y + h/2, 0, 0, w, h, ActionCanvas.instance);
		
	}
}
