package com.newlecture.prj3.entity;

public interface EnemyMoveListener {
	//여기에 약속을 정의 한다.
	
	void onMove();//로직은 구현하지 않는다. 이 약속을 이용할 애들이 직접 구현하는거다. public 하지 않음.
}
