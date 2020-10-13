package com.newlecture.prj2.service;

import java.util.Random;
import com.newlecture.prj2.canvas.ActionCanvas2;
import com.newlecture.prj2.entity.Boy2;

public class BoyService2 {
	private Boy2[] boys;

	public BoyService2() {
		boys = new Boy2[3];
		Random random = new Random();

		for(int i=0;i<3;i++) {
			int x = random.nextInt(500);
			int y = random.nextInt(300);
			boys[i] = new Boy2(x,y);
		}
	}

	public Boy2 get(int i) {
		return boys[i];
	}
}
