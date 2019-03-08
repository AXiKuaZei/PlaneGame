package com.mjw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Plane extends GameObject{
	boolean left=false;
	boolean up=false;
	boolean right=false;
	boolean down=false;
	boolean shoot=false;
	Image planeImage=GameUtils. getPic("Plane.jpg");
	public Plane() {
		super();
		x=Constant.xFrame/2;
		y=(int)(Constant.yFrame*0.75);
		width=50;
		height=50;
	}
	@Override
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);	
	}

	@Override
	public void draw(Graphics g) {
		if(live) {
			g.drawImage(planeImage, x, y, null);		
		}else {
			System.out.println("ƒ„—æÀ¿¡À");
		}
	}

}
