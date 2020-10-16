package com.newlecture.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.prj3.canvas.ActionCanvas;

public class IntroBackground extends Item{

	private int width;
	private int height;
	
	private static Image img;
	//private BackgroundMoveListener bgMoveListener;
	private int vx = 0;
	private int vy = 0;
	
	
/*
	public void setBgMoveListener(BackgroundMoveListener bgMoveListener) {
		this.bgMoveListener = bgMoveListener;
	}*/

	static {// 스태틱 생성자. 프로그램이 로드 될때 딱 한번만 수행되는 전역 생성자
		try {
			img = ImageIO.read(new File("res/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IntroBackground() {
		this(0, 0, "res/background.jpg");
	}

	public IntroBackground(double x, double y, String imgSrc) {
		super(x, y, imgSrc);
		
		img = getImg();
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	

	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public void paint(Graphics g) {
		System.out.println(1);
		
		Image img = getImg();
		double x = getX();
		double y = getY();

		g.drawImage(img, (int) x, (int) y, ActionCanvas.instance);
	}

	@Override
	public void update() {
		double vy = this.getVy();
		double y = this.getY();

		this.move(0, height);
		y += vy;
		this.setY(y);

		double dy = this.getDy();
		if (dy - 1 <= y && y <= dy + 1)
			this.setY(0);
		
		/*
		if(bgMoveListener != null) {
			bgMoveListener.moveImg();
			bgMoveListener = null;
		}*/
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	
	

}
