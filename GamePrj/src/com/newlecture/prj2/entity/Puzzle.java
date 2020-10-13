package com.newlecture.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import com.newlecture.prj2.canvas.PuzzleCanvas;

public class Puzzle {
	
	private Image img;
	private int di;
	private int si;
	//private ImageObserver intance;
	
	public Puzzle() {
		this(0,0);
	}

	public Puzzle(int di, int si) {
		this.di = di;
		this.si = si;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/pic1.png");
	}
	

	public int getDi() {
		return di;
	}

	public void setDi(int di) {
		this.di = di;
	}

	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public void paint(Graphics g) {

		int width = 740; //img.getWidth(this);
		int height = 463; //img.getHeight(this);
		int x = (width/3);
		int y = (height/2);

		int dxi =di%3;
		int xoffset = dxi*x;
		int yoffset = (di/3)*y;

		int dx1 = 0+xoffset;
		int dy1 = 0+yoffset;
		int dx2 = x+xoffset;
		int dy2 = y+yoffset;

		int sxi =si%3;
		xoffset = sxi*x;
		yoffset = (si/3)*y;

		int sx1 = 0+xoffset;
		int sy1 = 0+yoffset;
		int sx2 = x+xoffset;
		int sy2 = y+yoffset;

		g.drawImage(img, dx1, dy1, dx2, dy2, 
				sx1, sy1, sx2, sy2, PuzzleCanvas.intance);
	}


}