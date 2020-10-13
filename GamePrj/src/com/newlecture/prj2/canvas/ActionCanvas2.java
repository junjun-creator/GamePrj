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

	//	private Boy2 boy;//캔버스에 일단 개체를 그려놓고 행위를 시킨다.
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

		Image buf = this.createImage(this.getWidth(), getHeight()); //buf라는 이미지(도화지)생성
		Graphics bg = buf.getGraphics();//buf에 그림을 그릴 도구생성
		//기존에는 paint(Graphics g) 에서 매개변수로 주어진 g, 즉 , 윈도우 이미지에 그리는 도구인 g를 사용해서 그림을 그려왔다.

		for(int i=0;i<3;i++) {
			boys.get(i).paint(bg);//boys를 bg라는 도구로 paint(그림)을 그림 
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

		Runnable sub = new Runnable() { //인터페이스가 구현되있는 메소드

			@Override
			public void run() {

				while(true) {
					//currentBoy.update();
					for(int i = 0; i<3; i++)
						boys.get(i).update();;
					enemy.update();

					repaint();
					//->Canvas.update() : 지우기 -> Canvas.paint()
					try {
						Thread.sleep(17);//1초에 60번 정도
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};//스레드를 구현
		Thread th = new Thread(sub); //스레드 생성
		th.start();//스레드를 실행
	}

}