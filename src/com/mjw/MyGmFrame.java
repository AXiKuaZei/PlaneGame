package com.mjw;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MyGmFrame extends JFrame{
	Plane plane =new Plane();
	Bullet[] bullet = new Bullet[24];
	List<ShootShell> shells = new ArrayList<>();
	Image nightImage=GameUtils. getPic("night.jpg");
	public static void main(String[] args) {
		MyGmFrame myGmFrame = new MyGmFrame();
		myGmFrame.lauchFrame();
	}
	
	public void paint(Graphics g) {//自动被调用，g相当于一支画笔
		super.paint(g);
		plane.draw(g);
		Color c = g.getColor();
		Bullet b;
		ShootShell ss;
		Rectangle rct;
		for(int i=0;i<bullet.length;i++) {
//			g.drawImage(nightImage, 0, 0, null);	
			g.setColor(Color.RED);
		b=bullet[i];
		rct=b.getRect();
		b.draw(g);
			if(rct.intersects(plane.getRect())) {
				plane.live = false;
			}
			g.setColor(Color.YELLOW);
			for(int j=0;j<shells.size();j++) {
				ss=shells.get(j);
				ss.draw(g);
				if(rct.intersects(ss.getRect())) {
					b.live=false;
					shells.get(j).live=false;
				}
			}
		}

		g.setColor(c);
	}
	
	//initialize the game
	public void lauchFrame() {
		this.setTitle("飞机游戏");
		this.setVisible(true);
		this.setSize(Constant.xFrame, Constant.yFrame);
		this.setLocation(300, 100);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(new KeyMonitor());
		for(int i=0;i<bullet.length;i++) {
			bullet[i]=new Bullet(2*Constant.Pi*i/bullet.length);
		}
		new PaintThread().start();
		new BulletsMove(bullet).start();
		new Thread(()->{
			while(true) {
				if(plane.left&&plane.x>0) plane.x-=Constant.speedPlane;
				if(plane.up&&plane.y>0) plane.y-=Constant.speedPlane;
				if(plane.right&&(plane.x+50)<Constant.xFrame) plane.x+=Constant.speedPlane;
				if(plane.down&&(plane.y+50)<Constant.yFrame) plane.y+=Constant.speedPlane;
				try {
					Thread.sleep(40);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}) .start();
		new Thread(()-> {
			while(true) {
				if(plane.shoot) shells.add(new ShootShell(plane));		
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();
		new ShellMove(shells).start();
	}
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());
			switch(e.getKeyCode()) {
			case 37: plane.left=true;break;
			case 38: plane.up=true;break;
			case 39: plane.right=true;break;
			case 40: plane.down=true;break;
			case 32: plane.shoot=true;break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case 37: plane.left=false;break;
			case 38: plane.up=false;break;
			case 39: plane.right=false;break;
			case 40: plane.down=false;break;
			case 32: plane.shoot=false;break;
			}
		}
	}
		
	//repaint the window
	class PaintThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//双缓冲
	private Image offScreenImage=null;
	public void update(Graphics g) {
		if(offScreenImage==null) {
			offScreenImage=this.createImage(Constant.xFrame, Constant.yFrame);
		}
		Graphics gOff =offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}

