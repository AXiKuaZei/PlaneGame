package com.mjw;

import java.util.Iterator;
import java.util.List;

public class ShellMove extends Thread{
	List<ShootShell> shells;
	Iterator itr ;
	ShootShell ss;
	public ShellMove(List<ShootShell> shells) {
		this.shells = shells;
		itr=shells.iterator();
		ShootShell ss;
	}
	public void run() {
			while(true) {
				synchronized(shells) {
					for(int i=0;i<shells.size();i++)  {
						if(shells.get(i).y<0) {
							shells.remove(i);
						}else {
							shells.get(i).y-=Constant.speedShoot;
						}
					}
				}
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
