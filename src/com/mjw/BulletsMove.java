package com.mjw;

import java.awt.Rectangle;

public class BulletsMove extends Thread {
	Bullet[] bullet=null;
	public BulletsMove(Bullet[] bullet) {
		super();
		this.bullet = bullet;
	}
	public void run(){
		while(true) {
			for(int i=0;i<bullet.length;i++) {
				if(bullet[i].y>Constant.yFrame | bullet[i].y<0){
					bullet[i].degree=2*Constant.Pi-bullet[i].degree;
				}else if (bullet[i].x>Constant.xFrame| bullet[i].x<0) {
					bullet[i].degree=Constant.Pi-bullet[i].degree;
				}
				bullet[i].x +=(int)(Constant.speedBullet*Math.cos(bullet[i].degree));
				bullet[i].y +=(int)(Constant.speedBullet*Math.sin(bullet[i].degree));
			}
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
