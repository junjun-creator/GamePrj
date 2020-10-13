package com.newlecture.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.prj3.canvas.ActionCanvas;

public class Boy extends Item {

	//	private double x;
	//	private double y;
	//	
	//	// 애니메이션을 위한 변수
	//	private double vx;
	//	private double vy;
	//	private double dx;
	//	private double dy;
	//	
	//	private double width;
	//	private double height;
	//	private Image img;
	//	
	//	private int movIndex = 0;
	//	private int speed = 3;
	//	private int walkTempo = 6;
	
	private static Image img;
	
	static {//스태틱 생성자. 프로그램이 로드 될때 딱 한번만 수행되는 전역 생성자
		try {
			img = ImageIO.read(new File("res/boy.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boy() {
		this(100,100);
	}

	public Boy(double x, double y) {
		super(x, y, 64, 96, "res/boy.png");
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		img = tk.getImage("res/boy.png");
		//		
		//		this.x=x;
		//		this.y=y;
		//		this.width = 64;
		//		this.height = 96;
	}

	//	public void move(double x, double y) {
	////		this.x = x;
	////		this.y = y;
	//		this.dx = x;
	//		this.dy = y;
	//		
	//		// 동일한 속도로 이동하는 단위벡터
	//		double w = this.dx - this.x;
	//		double h = this.dy - this.y;
	//		double d = Math.sqrt(w*w + h*h);
	//		this.vx = w/d*speed;
	//		this.vy = h/d*speed;
	//		
	//		// 동일한 시간내에 이동하는 단위벡터
	//		//this.vx = (this.dx - this.x) / 15;
	//		//this.vy = (this.dy - this.y) / 15;
	//		
	////		this.x = x;
	////		this.y = y;
	//	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	public void update() {
		double x = getX();
		double y = getY();
		double dx = getDx();
		double dy = getDy();
		double vx = getVx();
		double vy = getVy();
		int movIndex = getMovIndex();

		// 목적지에 박스를 만들어 놓고 비교
		if((dx - 1 <= x && x <= dx + 1) && 
				(dy - 1 <= y && y <= dy + 1)) {			
			//		if((this.x - 1 <= this.dx && this.dx <= this.x + 1) && 
			//				(this.y - 1 <= this.dy && this.dy <= this.y + 1)) {
			vx = 0;
			vy = 0;
			movIndex = 0;
		}

		x += vx;
		y += vy;

		this.setX(x);
		this.setY(y);
		this.setVx(vx);
		this.setVy(vy);
		this.setMovIndex(movIndex);

	}

	

	public void paint(Graphics g) {//bg

		int w = (int)this.getWidth();
		int h = (int)this.getHeight();
		int x1 = (int)this.getX() - w/2;
		int y1 = (int)this.getY()- h+13;
		int x2 = x1+w;
		int y2 = y1+h;
		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();
		Image img = getImg();
		double vx = getVx();
		double vy = getVy();

		if(vx != 0 || vy != 0) {
			if(walkTempo == 0) {
				movIndex++;
				movIndex = movIndex % 4;

				walkTempo = 6;
			}
			else
				walkTempo--;
		}

		int offsetX = movIndex*w;

//		System.out.printf("index:%d, offset:%d\n", movIndex, offsetX);
		g.drawImage(img, x1, y1, x2, y2, 
				0+offsetX, 0, 0+w+offsetX, h, ActionCanvas.instance);
		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

	public boolean isSelected(int x, int y) {
		int w = (int)this.getWidth();
		int h = (int)this.getHeight();
		int x1 = (int)this.getX() - w/2;
		int y1 = (int)this.getY()- h+13;
		int x2 = x1+w;
		int y2 = y1+h;

		if((x1  < x && x < x2)  
				&& (y1 < y && y < y2))
			return true;
		return false;
	}
}
