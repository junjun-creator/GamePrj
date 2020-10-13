package com.newlecture.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.newlecture.prj3.canvas.ActionCanvas;

public class Enemy extends Item {

	//	private double x;
	//	private double y;
	//	
	//	// �ִϸ��̼��� ���� ����
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
	private int timeoutForMoving = 120;
	private Random rand;
	
	private static Image img;
	
	static {//����ƽ ������. ���α׷��� �ε� �ɶ� �� �ѹ��� ����Ǵ� ���� ������
		try {
			img = ImageIO.read(new File("res/enemy.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Enemy() {
		this(100,100);
	}

	public Enemy(double x, double y) {
		super(x, y, 58, 35, "res/enemy.gif");
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		img = tk.getImage("res/alien.gif");
		//		
		//		this.x=x;
		//		this.y=y;
		//		this.width = 58;
		//		this.height = 35;
		rand = new Random();
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}


	//	public void move(double x, double y) {
	////		this.x = x;
	////		this.y = y;
	//		this.dx = x;
	//		this.dy = y;
	//		
	//		// ������ �ӵ��� �̵��ϴ� ��������
	//		double w = this.dx - this.x;
	//		double h = this.dy - this.y;
	//		double d = Math.sqrt(w*w + h*h);
	//		this.vx = w/d*speed;
	//		this.vy = h/d*speed;
	//		
	//		// ������ �ð����� �̵��ϴ� ��������
	//		//this.vx = (this.dx - this.x) / 15;
	//		//this.vy = (this.dy - this.y) / 15;
	//		
	////		this.x = x;
	////		this.y = y;
	//	}
	
	

	public void update() {
		

		timeoutForMoving--;
		if(timeoutForMoving == 0){

			double width = getWidth();
			double height = getHeight();
			int w = ActionCanvas.instance.getWidth()-(int)width;
			int h = ActionCanvas.instance.getHeight()-(int)height;
			int x = rand.nextInt(w)+(int)this.getWidth()/2;
			int y = rand.nextInt(h)+(int)this.getHeight()/2;

			this.move(x,y);

			
			timeoutForMoving = rand.nextInt(60)+60;//0~59+60 // 60~119
		
		}
		
		double x = getX();
		double y = getY();
		double dx = getDx();
		double dy = getDy();
		double vx = getVx();
		double vy = getVy();
		int movIndex = getMovIndex();
		// �������� �ڽ��� ����� ���� ��
		if((dx - 1 <= x && x <= dx + 1) && 
				(dy - 1 <= y && y <= dy + 1)) {
			vx = 0;
			vy = 0;
			movIndex = 0;
			timeoutForMoving = 1;
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


		if(walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 12;

			walkTempo = 3;
		}
		else
			walkTempo--;

		int offsetX = movIndex*w;

		//		if ( movIndex < 5 ) 
		//			offsetX -= 1;
//		System.out.printf("index:%d, offset:%d\n", movIndex, offsetX);

		g.drawImage(img, x1, y1, x2, y2, 
				0+offsetX, 0, 0+w+offsetX, h, ActionCanvas.instance);

		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

//	public boolean isSelected(int x, int y) {
//		int w = (int)this.getWidth();
//		int h = (int)this.getHeight();
//		int x1 = (int)this.getX() - w/2;
//		int y1 = (int)this.getY()- h+13;
//		int x2 = x1+w;
//		int y2 = y1+h;
//
//		if((x1  < x && x < x2)  
//				&& (y1 < y && y < y2))
//			return true;
//		return false;
//	}

}
