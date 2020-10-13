package com.newlecture.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.prj2.canvas.ActionCanvas;


public class Boy {

	
	private double x;
	private double y;	
	private Image img;
	private int w = 64;
	private int h = 96;
	
	public Boy() {
		this(100,100);
	}
	public Boy(double x, double y) {
		this.x = x;
		this.y = y;
		//�̹��� �ҷ��ͼ� �ʱ�ȭ�ϱ�
		Toolkit tk = Toolkit.getDefaultToolkit();//�˾Ƽ� ���� ȯ�濡 �°� ��Ŷ�� �����
		//����ƽ �Լ�. �� �Լ����� return new Toolkit();�� ����
//		imgs = new Image[2];
		img = tk.getImage("res/charater.png");  
		}
	
	public void move(double x, double y) {
		
		this.x = x-w/2;
		this.y = y-h+13;
	}
	
	public void paint(Graphics g) {
		int x = (int)this.x;
		int y = (int)this.y;
		
		g.drawImage(img, x, y, x+w, y+h, 0, 0, w, h, ActionCanvas.instance);
	}
	
	public boolean isSelected(int x, int y) {
		boolean result = false;
		if((this.x<=x && x<=this.x+w)&&(this.y<=y&&y<=this.y+h)) {
			result = true;
		}
		return result;
	}
}
