package com.mjw;

import java.awt.Graphics;
import java.awt.Rectangle;

public class ShootShell extends GameObject{
	public ShootShell(Plane p) {
		x=p.x+p.width/2;
		y=p.y;
		width=Constant.sizeShoot;
		height=Constant.sizeShoot;
	}
	@Override
	public Rectangle getRect() {
		if(live) {
			return  new Rectangle(x,y,width,height);
		}else {
			return new Rectangle(x,y,0,0);
		}
	}

	@Override
	public void draw(Graphics g) {
		if(live) {
			g.fillOval(x, y, width, height);		
		}
	}
}
