package com.newlecture.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.prj2.canvas.ActionCanvas2;


public class Boy2 {


	private double x;
	private double y;	

	//애니메이션을 위한 변수
	private double vx;
	private double vy;	
	private double dx;
	private double dy;	
	private int moveIndex = 0;
	private int speed = 4;
	private int walkTempo = 20;

	private Image img;
	private int w = 64;
	private int h = 96;

	public Boy2() {
		this(100,100);
	}
	public Boy2(double x, double y) {
		this.x = x;
		this.y = y;
		//이미지 불러와서 초기화하기
		Toolkit tk = Toolkit.getDefaultToolkit();//알아서 현재 환경에 맞게 툴킷을 얻어줌
		//스태틱 함수. 이 함수에서 return new Toolkit();을 생성
		img = tk.getImage("res/charater.png");  
	}

	public void move(double x, double y) {
		this.dx = x;
		this.dy = y;

		//동일한 속도로 이동하는 단위벡터
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w+h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;
		//		this.vx = (this.dx - this.x)/15;
		//		this.vy = (this.dy - this.y)/15;
	}

	public void update() {
		if((this.x-speed<=this.dx&&this.dx<=this.x+speed)&&
				(this.y-speed<=this.dy&&this.dy<=this.y+speed)) {
			this.vx=0;
			this.vy=0;
		}
		this.x += this.vx;
		this.y += this.vy;

		//		System.out.printf("%f, %f\n",vx,vy);
	}

	public void paint(Graphics g) {

		int w = this.w;
		int h = this.h;
		int x1 = (int) (x- w/2);
		int y1 = (int) (y-h/2);
		int x2 = x1+w;
		int y2 = y1+h;

		if(vx!=0||vy!=0) {
			if(walkTempo == 0) {
				if(vy>0)
					moveIndex++;
				moveIndex = moveIndex % 4;
				walkTempo = 20;
			}
			else
				walkTempo --;
			int offsetX = moveIndex*w;
			g.drawImage(img, x1, y1, x2, y2, 0+offsetX, 0, w+offsetX, h, ActionCanvas2.instance);
		}
		else
			g.drawImage(img, x1, y1, x2, y2, 0, 0, w, h, ActionCanvas2.instance);
	}

	public boolean isSelected(int x, int y) {
		boolean result = false;
		if((this.x<=x && x<=this.x + w)&&(this.y<=y&&y<=this.y + h)) {
			result = true;
		}
		return result;
	}
}
