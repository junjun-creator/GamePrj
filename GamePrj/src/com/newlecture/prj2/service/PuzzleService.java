package com.newlecture.prj2.service;

import java.util.Random;

import com.newlecture.prj2.entity.Puzzle;

public class PuzzleService {

	private Puzzle[] puzzles;
	private int[] dis;
	private int[] sis;

	public PuzzleService() {
		dis = new int[] {0,1,2,3,4,5};
		sis = new int[] {0,1,2,3,4,5};
		
		puzzles = new Puzzle[6];
		for(int i=0; i<6; i++) {
		puzzles[i] = new Puzzle(dis[i], sis[i]);
		//ÃÊ±âÈ­
		}
	}

	public void shuffle() {

		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			int s = rd.nextInt(6);
			int d = rd.nextInt(6);

			int temp;
			temp = dis[s];
			dis[s] = dis[d];
			dis[d] = temp;
		}
		for(int i=0; i<6; i++) {
			puzzles[i].setDi(dis[i]);
			puzzles[i].setSi(sis[i]);
			
		}
	}

	public Puzzle get(int index) {

		return puzzles[index];
	}
}