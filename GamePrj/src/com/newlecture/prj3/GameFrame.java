package com.newlecture.prj3;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.newlecture.prj3.canvas.ActionCanvas;
import com.newlecture.prj3.canvas.IntroCanvas;

public class GameFrame extends Frame {
	public static GameFrame instance;
	private ActionCanvas canvas;
	private IntroCanvas introCanvas;
	
	public GameFrame() {
		instance = this;
		
		//Canvas canvas = new PuzzleCanvas();
		//canvas = new ActionCanvas();
		introCanvas = new IntroCanvas();
		
		add(introCanvas);
		//canvas.start();
		
		setSize(360, 600);
		setVisible(true);	
		//add(canvas);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// 닫히기 전
				/*
				System.out.println("bye~~~");
				int input = JOptionPane.showConfirmDialog(GameFrame.this, 
						"정말갈꺼야? ",					//여기에 그냥 this를 하게되면 windowAdapter를 가리켜서 오류
						"종료?",
						JOptionPane.OK_CANCEL_OPTION);
						
				if(input == JOptionPane.OK_OPTION) {
					System.exit(0);
					//**프로세스 종료시 반환값이 있다. 0은 정상종료.
					//더 종료할 것이 있다면 먼저 해줘야함. 소켓, 파일입출력 등등
				}*/
				
				Object[] options = { "예", "아니오" };
				int input = JOptionPane.showOptionDialog(null, "Click OK to exit", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(input == JOptionPane.OK_OPTION) {
					System.exit(0);
					//**프로세스 종료시 반환값이 있다. 0은 정상종료.
					//더 종료할 것이 있다면 먼저 해줘야함. 소켓, 파일입출력 등등
				}
			}
		});
	}

	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {
		//remove(oldCanvas);
		
		//add((Canvas)newCanvas.newInstance());
		
		//스타트를 시켜줄까? 아니면 알아서 스타트 하게끔 할까?
		Canvas canvas = (Canvas)newCanvas.newInstance();
		add(canvas);
		canvas.setFocusable(true);//포커스를 가질수 있게 함.
		canvas.requestFocus();//잡을 포커스가 하나밖에 없을 경우에는 request하지 않아도 알아서 포커스를 잡는다.
		// 하지만 확실한 포커싱을 위해 setFocusable, requestFocus를 수행해주자!
		if(canvas instanceof ActionCanvas) {
			((ActionCanvas)canvas).start();//여기서 스타트를 시켜줌. 형변환보다 메소드 호출하는 . 이 우선순위가 더 높으므로 괄호를 해줘야함
			//((ActionCanvas)canvas).requestFocus();
		}
		revalidate();//변화가 생겻으니 다시 확인해서 그려라!
		
		oldCanvas.setVisible(false);//화면 전환 중간에 흰 백지가 출력이 되는 현상을 막기 위해
									//새로운 캔버스를 화면에 먼저 올리고, revalidate 한 다음에 지우거나 가림
		
	}
}
