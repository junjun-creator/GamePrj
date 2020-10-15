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
	
	private static final int UP = 38; // 전역변수로 만듬 static
	private static final int DOWN = 40;
	private static final int LEFT = 37;
	private static final int RIGHT = 39;
	
	private int N = 0;
	private int S = 0;
	private int W = 0;
	private int E = 0;
	
	private static Image img;
	
	private BackgroundMoveListener bgMoveListener;
	
	public void setBgMoveListener(BackgroundMoveListener bgMoveListener) {
		this.bgMoveListener = bgMoveListener;
	}
	
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
		int canvasWidth = ActionCanvas.instance.getWidth();
		int canvasHeight = ActionCanvas.instance.getHeight();
		
		if(N == 1 )
			this.setY(this.getY()-1);
		if(S == 1)
			this.setY(this.getY()+1);
		if(E == 1  && this.getX() > 0 + this.getWidth()/2 -1)
			this.setX(this.getX()-1);
		if(W == 1 && this.getX() < canvasWidth - this.getWidth()/2 + 1)
			this.setX(this.getX()+1);
		//System.out.println(getX());
		
		double x = getX();
		double y = getY();
		double dx = getDx();
		double dy = getDy();
		double vx = getVx();
		double vy = getVy();
		int movIndex = getMovIndex();

		// 목적지에 박스를 만들어 놓고 비교
		if(((dx - 1 <= x && x <= dx + 1) && 
				(dy - 1 <= y && y <= dy + 1))) {			
			//		if((this.x - 1 <= this.dx && this.dx <= this.x + 1) && 
			//				(this.y - 1 <= this.dy && this.dy <= this.y + 1)) {
			//System.out.println("111111111111111");
			vx = 0;
			vy = 0;
			movIndex = 0;
		}
		
		if((S == 0 && W == 0 && N == 0 && E == 0))
			movIndex =0;
		/*
		int canvasWidth = ActionCanvas.instance.getWidth();
		
		if(x >= canvasWidth - this.getWidth() -1 && x <= canvasWidth-this.getWidth() + 1) {
			System.out.println("x = 0");
			this.setVx(0);
			vx = getVx();
		}
		
		if(y == ActionCanvas.instance.getHeight()-this.getHeight()) {
			this.setVy(0);
			vy = getVy();
		}*/

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
		
		if(vx != 0 || vy != 0 || N == 1 || S == 1 || W == 1 || E == 1) {
			//System.out.println("test");
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

	public void move(int key) {
		double x = this.getX();
		double y = this.getY();
		
		int canvasWidth = ActionCanvas.instance.getWidth();
		int canvasHeight = ActionCanvas.instance.getHeight();
		
		//System.out.println(key);
		System.out.println(S);
		System.out.println(W);
		switch(key) {
		case UP ://case 값은 상수값(변수 x)만 와야함. final 변수가 와야함
			if(this.getY() >= 0 +this.getHeight() -1 && this.getY() <= 0+this.getHeight()  + 1) {
				double bx = 0;
				double by = 3;
				bgMoveListener.moveImg(bx,by);
			}
			N = 1;
			break;
		case DOWN :
			if(this.getY() >= canvasHeight -1 && this.getY() <= canvasHeight + 1) {
				double bx = 0;
				double by = -3;
				bgMoveListener.moveImg(bx,by);
			}
			S = 1;
			break;
		case LEFT :
			if(x <= 0 + this.getWidth()/2 + 1) {
				//this.move(this.getX(),y);
				double bx = 3;
				double by = 0;
				bgMoveListener.moveImg(bx,by);//바운더리에 도착했다라는것을 알림
			}
			E = 1;
			break;
		case RIGHT :
			//System.out.println(this.getX());
			if(x >= 0 + canvasWidth - this.getWidth()/2 -1) {
				System.out.println(1);
				double bx = -3;
				double by = 0;
				bgMoveListener.moveImg(bx,by);
			}
			W = 1;
			break;
		}
	}

	public void upMove(int key) {
		switch(key) {
		case UP :
			N = 0;
			break;
		case DOWN :
			S = 0;
			break;
		case LEFT :
			E = 0;
			break;
		case RIGHT :
			System.out.println(W);
			W = 0;
			break;
		}
	}
}
