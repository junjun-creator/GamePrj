package com.newlecture.prj3.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.newlecture.prj3.GameFrame;
import com.newlecture.prj3.entity.Button;
import com.newlecture.prj3.entity.IntroBackground;

public class IntroCanvas extends Canvas {
	
	private Button button;
	private IntroBackground intB;
	private Button button2;
	private Button[] buttons;
	
	public IntroCanvas() {
		button = new Button(10,10,200,50,"hello~");
		intB = new IntroBackground();
		button2 = new Button(10,100,200,50,"Okay~");
		buttons = new Button[2];
		
		buttons[0] = button;
		buttons[1] = button2;
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//down과 up을 합치는것
				
				int x = e.getX();
				int y = e.getY();
				// TODO Auto-generated method stub
				//for(int i=0;i<2;i++) {
					//if(buttons[i].contains(x,y)) {
						//JOptionPane.showMessageDialog(IntroCanvas.this, "clicked : "+i);//그냥 this 하게되면 MouseAdapter를 가리킴
				try {
					GameFrame.instance.switchCanvas(IntroCanvas.this, ActionCanvas.class);//class 정보를 넘겨준다
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					System.out.println(1);
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					System.out.println(2);
					e1.printStackTrace();
				}
					//}
				//}
			}
			
			
		});
	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();
		intB.paint(bg);
		button.paint(bg);
		button2.paint(bg);
		
		
		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	
}
