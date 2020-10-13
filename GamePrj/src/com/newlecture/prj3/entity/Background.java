package com.newlecture.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.prj3.canvas.ActionCanvas;

public class Background extends Item{

	private int width = 360;
	private int height = 1200;
	
	private static Image img;

	static {// 스태틱 생성자. 프로그램이 로드 될때 딱 한번만 수행되는 전역 생성자
		try {
			img = ImageIO.read(new File("res/space.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Background() {
		this(0, 0, "res/space.jpg");
	}

	public Background(double x, double y, String imgSrc) {
		super(x, y, imgSrc);
	}
	
	

	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public void paint(Graphics g) {
		Image img = this.getImg();
		double x = this.getX();
		double y = this.getY() + (ActionCanvas.instance.getHeight() - height);

		g.drawImage(img, (int) x, (int) y, ActionCanvas.instance);
		g.drawImage(img, (int) x, (int) y - (height), ActionCanvas.instance);
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
	}

}
