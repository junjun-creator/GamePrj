package com.newlecture.prj3.canvas;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.prj3.entity.Item;

public class Missile extends Item {
	
	private static Image img;
	
	static {//스태틱 생성자. 프로그램이 로드 될때 딱 한번만 수행되는 전역 생성자
		try {
			img = ImageIO.read(new File("res/missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Missile() {
		this(0,0);
	}
	
	public Missile(double x, double y) {
		super(x, y, img.getWidth(null)/3, img.getHeight(null), 
				"res/missile.png");
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {

		int w = (int)this.getWidth();
		int h = (int)this.getHeight();
		int x1 = (int)this.getX() - w/2;
		int y1 = (int)this.getY()- h/2;
		int x2 = x1+w;
		int y2 = y1+h;

		Image img = getImg();
		double vx = getVx();
		double vy = getVy();
		
		g.drawImage(img, x1, y1, x2, y2, 
				0, 0, 0+w, h, ActionCanvas.instance);
	}

}
