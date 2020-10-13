package com.newlecture.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.prj2.canvas.ActionCanvas2;

public class Enemy {

	private double x;
	private double y;	

	//애니메이션을 위한 변수
	private double vx;
	private double vy;	
	private double dx;
	private double dy;	
	private int moveIndex;
	private int speed = 2;
	private int walkTempo = 10;

	private Image img;
	private int w =58;
	private int h =35;
	private int timeoutForMoving=30;//초기화
	Random rand;

	//생성자
	public Enemy() {
		this(100,100);
	}

	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
		rand = new Random();//생성자
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/enemy.gif");
		this.move(300,300);
	}

	public void move(double x, double y) {
		this.dx = x;
		this.dy = y;

		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w+h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;
	}

	public void update() {
		
		timeoutForMoving--;
		if(timeoutForMoving == 0) {

			int x = rand.nextInt(ActionCanvas2.instance.getWidth()-w)+w/2;
			int y = rand.nextInt(ActionCanvas2.instance.getHeight()-h)+h/2;

			this.move(x,y);
			timeoutForMoving = rand.nextInt(60)+60;
		}
		
		if((this.x-1<=this.dx&&this.dx<=this.x+1)&&
				(this.y-1<=this.dy&&this.dy<=this.y+1)) {
			this.vx=0;
			this.vy=0;
			moveIndex = 0;
		}
		this.x += this.vx;
		this.y += this.vy;
	}

	public void paint(Graphics g) {

		int w = this.w;
		int h = this.h;
		int x1 = (int) (x- w/2);
		int y1 = (int) (y-h/2);
		int x2 = x1+w;
		int y2 = y1+h;

		if(walkTempo == 0) {
			moveIndex++;
			moveIndex = moveIndex % 12;
			walkTempo = 3;
		}
		else
			walkTempo --;
		int offsetX = moveIndex*w;
		if(moveIndex<5)
			offsetX-=1;

		g.drawImage(img, x1, y1, x2, y2, 0+offsetX, 0, w+offsetX, h, ActionCanvas2.instance);

	}
}
