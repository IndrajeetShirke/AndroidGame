package com.example.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;

public class Wall1 {
	protected Rectangle sprite;
	private Camera camera;
	private static Wall1 instance;
	private final int HEIGHT = 400;
	private final int WIDTH = 10;
	public static Wall1 getWall(Camera camera) {
		if(instance == null ) {
			instance = new Wall1(camera);
		}
		return instance;
	}
	
	public Wall1(Camera camera) {
		this.camera = camera;
		sprite = new Rectangle(this.camera.getWidth()/2, this.camera.getHeight()-HEIGHT/2, WIDTH, HEIGHT, MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());		
		sprite.setVisible(true);
	}
	
	public void move(float accelerometerSpeedY) {
		if (accelerometerSpeedY != 0) {

			int lL = (int) (camera.getHeight()-HEIGHT);
			int uL = (int) camera.getHeight();

			float newY;

			// Calculate New X,Y Coordinates within Limits
			if (sprite.getY() >= lL)
				newY = sprite.getY() + accelerometerSpeedY;
			else
				newY = lL;
			if (newY <= uL)
				newY = sprite.getY() + accelerometerSpeedY;
			else
				newY = uL;

			// Double Check That New X,Y Coordinates are within Limits
			if (newY < lL)
				newY = lL;
			else if (newY > uL)
				newY = uL;

			sprite.setPosition(sprite.getX(), newY);
		}
		
	}
}
