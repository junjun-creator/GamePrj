package com.newlecture.prj1;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;


public class GameCanvas extends Canvas{

	private Image img;
	private int[] dis;
	private int[] sis;
	private int imgCount;


	public GameCanvas() {

		imgCount = 6;
		dis = new int[] {0,1,2,3,4,5};
		sis = new int[] {0,1,2,3,4,5};
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			int s = rd.nextInt(imgCount);
			int d = rd.nextInt(imgCount);

			int temp;
			temp = dis[s];
			dis[s] = dis[d];
			dis[d] = temp;
		}

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/pic1.png");
	}

	public boolean mouseDown(Event evt, int a, int b) {

		System.out.printf("x: %d, y: %d", a,b);

		Random rand = new Random();
		for(int j=0; j<100; j++) {
			int p1 = rand.nextInt(6);
			int p2 = rand.nextInt(6);

			int temp = dis[p1];
			dis[p1] = dis[p2];
			dis[p2] = temp;
		}
		repaint();//

		return super.mouseDown(evt, a, b);
	}


	public void paint(Graphics g) {

		for(int i=0;i<6;i++) {
			int di=dis[i];
			int si=sis[i];

			int width = img.getWidth(this);
			int height = img.getHeight(this);
			int x = (width/3);
			int y = (height/2);

			int dxi =di%3;
			int xoffset = dxi*x;
			int yoffset = (di/3)*y;

			int dx1 = 0+xoffset;
			int dy1 = 0+yoffset;
			int dx2 = x+xoffset;
			int dy2 = y+yoffset;

			int sxi =si%3;
			xoffset = sxi*x;
			yoffset = (si/3)*y;

			int sx1 = 0+xoffset;
			int sy1 = 0+yoffset;
			int sx2 = x+xoffset;
			int sy2 = y+yoffset;

			g.drawImage(img, dx1, dy1, dx2, dy2, 
					sx1, sy1, sx2, sy2, this);
		}
	}
}