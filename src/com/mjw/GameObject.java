package com.mjw;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean live=true;
	public abstract Rectangle getRect();
	public abstract void draw(Graphics g);
}
