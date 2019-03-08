package com.mjw;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	double degree;
	public Bullet(double degree ) {
		super();
		x=Constant.xBullets;
		y=Constant.yBullets;
		width=Constant.sizeBullet;
		height=Constant.sizeBullet;
		this.degree = degree;
	}
	public Rectangle getRect() {
		if(live) {
			return new Rectangle(x,y,width,height);
		}else {
			return new Rectangle(x,y,0,0);
		}
	}
	public void draw(Graphics g) {
		if(live) {
			g.fillOval(x, y, width, height);
		}
	}
}
