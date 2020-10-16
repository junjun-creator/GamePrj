package com.newlecture.prj2.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

import com.newlecture.prj2.entity.Boy;
import com.newlecture.prj2.entity.Pic;
import com.newlecture.prj2.service.BoyService;

public class ActionCanvas extends Canvas {


	private Boy boy;//ĵ������ �ϴ� ��ü�� �׷����� ������ ��Ų��.
	private BoyService boys;
	private Boy currentBoy;
	public static Canvas instance;
	private Pic pic;

	public ActionCanvas() {
		boy = new Boy();
		instance = this;
		boys = new BoyService();
		pic = new Pic();
	}


	@Override
	public void paint(Graphics g) {
		pic.paint(g);
		
		for(int i=0;i<3;i++) {
			boys.get(i).paint(g);
		}
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

}