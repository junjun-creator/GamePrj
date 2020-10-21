package com.newlecture.prj3.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import com.newlecture.prj3.entity.Background;
import com.newlecture.prj3.entity.BackgroundMoveListener;
import com.newlecture.prj3.entity.Boy;
import com.newlecture.prj3.entity.Enemy;
import com.newlecture.prj3.entity.EnemyMoveListener;
import com.newlecture.prj3.entity.EnemyMoveListenerImpl;
import com.newlecture.prj3.entity.Item;
import com.newlecture.prj3.entity.Missile;

public class ActionCanvas extends Canvas {

	public static Canvas instance;

	private Enemy enemy; 
	private Boy[] boys;
	private Boy boy1;
	private Boy boy2;
	private Boy boy3;
	private Boy currentBoy;

	private Background background;

	private Item[] items;
	private int itemSize = 0;
	
	private int coolTime = 100;
	private Missile[] missiles;
	int missileSize = 0;
	/*
	private static final int up = 1004; // ���������� ���� static
	private static final int down = 1005;
	private static final int left = 1006;
	private static final int right = 1007;*/

	public ActionCanvas() {
		instance = this;

		enemy = new Enemy();
		enemy.setMoveListener(new EnemyMoveListener() {//익명 클래스
			
			@Override
			public void onMove() {
				//System.out.println("오호라디야~~");
			}
		});

		// Boy�� ����
		boys = new Boy[3];

		boy1 = new Boy(100,200);
		boy2 = new Boy(200,200);
		boy3 = new Boy(300,200);

		boys[0] = boy1;
		boys[1] = boy2;
		boys[2] = boy3;

		background = new Background();

		items = new Item[100];
		items[0] = background;
		items[1] = boy1;
		items[2] = boy2;
		items[3] = boy3;
		items[4] = enemy;
		itemSize = 5;
		missiles = new Missile[100];

		currentBoy = boy1;
		
		currentBoy.setBgMoveListener(new BackgroundMoveListener() {
			@Override
			public void moveImg(double bx, double by) {
				double backX = background.getX();
				double backY = background.getY();
				
				backX += bx;
				backY += by;
				
				background.setX(backX);
				background.setY(backY);
			}
		});
		
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(coolTime <0) {
					System.out.println("마우스 눌림");
					coolTime = 100;
					for(int i=0; i<3; i++)
						if(boys[i].isSelected(e.getX(), e.getY())) {
							currentBoy = boys[i];
						}
					currentBoy.move(e.getX(), e.getY());
				}
				
				//System.out.println(e.getX());
				repaint();
			}
		});//인터페이스
		
		setFocusable(true);//포커스를 잡아줘야 키 리스너가 작동함...... 안그럼 클릭하기전까지 포커스가 없음
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				//if(e.getKeyCode() >=37 && e.getKeyCode() <=40)
				//	currentBoy.upMove(e.getKeyCode());
				//System.out.println(e.getKeyCode());
				
				switch(code) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					currentBoy.move(code);
					break;
				case KeyEvent.VK_SPACE:
					Missile missile = currentBoy.fire();
					items[itemSize++] = missile;
					break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				currentBoy.move(e.getKeyCode());
			}
		});
	}

	public void start() {


		Runnable sub = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {

					//					enemy.update();
					//					for(int i=0; i<boys.length; i++)
					//						boys[i].update();

					for(int i=0; i<itemSize; i++)
						items[i].update();

					coolTime--;

					repaint();
					// -> Canvas.update() : ����� -> Canvas.paint(g) -> �ٽ� �׸���

					//System.out.println("repaint");

					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};

		Thread th = new Thread(sub);
		th.start();

	}
/*
	@Override
	public boolean mouseDown(Event evt, int x, int y) {

		//		boolean ?;
		//		for(3��)
		//			if()
		//				? = ;
		for(int i=0; i<3; i++)
			if(boys[i].isSelected(x, y)) {
				currentBoy = boys[i];
				System.out.printf("boy[%d] selected", i);
				return super.mouseDown(evt, x, y);
			}

		//		if(boy2.isSelected(x, y)) {
		//			currentBoy = boy2;
		//			System.out.println("boy2 selected");
		//			return super.mouseDown(evt, x, y);
		//		}
		//		
		//		if(boy3.isSelected(x, y)) {
		//			currentBoy = boy3;
		//			System.out.println("boy3 selected");
		//			return super.mouseDown(evt, x, y);
		//		}

		currentBoy.move(x, y);

		// ? == true �� �ҳ⿡�� ��� ������ �� ��ΰ� ������ ������ ���ٸ�.
		// ���õ� �ҳ��� �̵� currentBoy.move(x, y);
		//else
		// ������ ����		

		repaint();
		return super.mouseDown(evt, x, y);
	}*/
	/*
	@Override
	public boolean keyDown(Event arg0, int key) {

		System.out.println(1);
		
		currentBoy.move(key);
		
		

	
		return super.keyDown(arg0, key);
	}*/
/*
	@Override
	public boolean keyUp(Event evt, int key) {
		currentBoy.upMove(key);
		return super.keyUp(evt, key);
	}*/

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		//super.update(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		for(int i=0; i<itemSize; i++)
			items[i].paint(bg);

		//		boy1.paint(bg);
		//		boy2.paint(bg);//
		//		boy3.paint(bg);
		//		enemy.paint(bg);

		g.drawImage(buf, 0, 0, this);//
	}
}

// 1. �ҳ��� ������ �� ��ġ�� �ʱ�ȭ�� �� �ְ� �϶�.
// 2. �� ���� �ҳ��� �����
// 3. ����� �Է�ó�� : ���콺�� Ŭ���ϸ� �� ��ġ�� 1��° �ҳ��� �̵���Ű�ÿ�.
// 4. (x,y)�� �ҳ��� ��/����� �̵��� �߽���ǥ�� �ȴ�. 
//     �̰��� �߽�/�ϴ��� �ǵ��� Body.paint �޼ҵ带 �����ؼ� �����Ͻÿ�.
// 5. Boy ���� ��ǥ�� �ָ鼭 �װ� ���õǾ���? isSelected(x, y):true/false -> true�� ��� 
//    isSelected()�� ��ȯ ���� true �ϰ�� -> currentBoy.move(x, y)
// 6. ����, �ڵ���, ����, �κ�....�̵� ���ο� ��ü �ϳ��� �����Ű�ÿ�.

