package com.example.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;


public class Ball {
	public Rectangle sprite;
	private int SPEEDX = -5;
	private int SPEEDY = -5;
	private Camera camera;
	
	public void update() {
		if(sprite.getX() <= 0 || sprite.getX() + sprite.getWidth() >= camera.getWidth()) {
			SPEEDX = SPEEDX * -1;
		}
		sprite.setX(sprite.getX() + SPEEDX);
		if(sprite.getY() <= 0 || sprite.getY() + sprite.getHeight() >= camera.getHeight()) {
			SPEEDY = SPEEDY * -1;
		}
		sprite.setY(sprite.getY() + SPEEDY);
	}
	
	public Ball(Camera camera, int x, int y) {
		this.camera = camera;
		sprite = new Rectangle(0, 0, 70, 30, MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		sprite.setPosition(x,y);
		sprite.setVisible(true);
	}
}
