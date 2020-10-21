package com.newlecture.prj4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintCanvas extends Canvas {
	
	private boolean activated = false;

	public PaintCanvas() {
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		if(activated) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth()-2, getHeight()-2);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-2, getHeight()-2);
	}

	public void setActive() {
		// TODO Auto-generated method stub
		this.activated = true;
		repaint();
	}
}
