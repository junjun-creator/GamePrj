package com.newlecture.prj2.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.prj2.entity.Boy2;
import com.newlecture.prj2.entity.Enemy;
import com.newlecture.prj2.entity.Pic;
import com.newlecture.prj2.service.BoyService2;

public class ActionCanvas2 extends Canvas {

	//	private Boy2 boy;//ĵ������ �ϴ� ��ü�� �׷����� ������ ��Ų��.
	public static Canvas instance;
	private BoyService2 boys;
	private Boy2 currentBoy;
	private Color color;
	private Enemy enemy;

	public ActionCanvas2() {
		instance = this;
		boys = new BoyService2();
		currentBoy = new Boy2();
		this.setBackground(color);
		enemy = new Enemy();
	}

	public void update(Graphics g) {
		//super.update(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(this.getWidth(), getHeight()); //buf��� �̹���(��ȭ��)����
		Graphics bg = buf.getGraphics();//buf�� �׸��� �׸� ��������
		//�������� paint(Graphics g) ���� �Ű������� �־��� g, �� , ������ �̹����� �׸��� ������ g�� ����ؼ� �׸��� �׷��Դ�.

		for(int i=0;i<3;i++) {
			boys.get(i).paint(bg);//boys�� bg��� ������ paint(�׸�)�� �׸� 
		}
		enemy.paint(bg);

		g.drawImage(buf, 0, 0, this);
	}

	public boolean mouseDown(Event evt, int x, int y) {
		for(int i=0;i<3;i++) {
			boolean isSelected = boys.get(i).isSelected(x,y);
			if(isSelected == true) {
				currentBoy=boys.get(i);
				break;
			}
		}
		if(currentBoy != null)
			currentBoy.move(x, y);

		repaint();
		return super.mouseDown(evt, x, y);
	}

	public void start() {

		Runnable sub = new Runnable() { //�������̽��� �������ִ� �޼ҵ�

			@Override
			public void run() {

				while(true) {
					//currentBoy.update();
					for(int i = 0; i<3; i++)
						boys.get(i).update();;
					enemy.update();

					repaint();
					//->Canvas.update() : ����� -> Canvas.paint()
					try {
						Thread.sleep(17);//1�ʿ� 60�� ����
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};//�����带 ����
		Thread th = new Thread(sub); //������ ����
		th.start();//�����带 ����
	}

}