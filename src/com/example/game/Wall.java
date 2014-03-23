package com.example.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;

public class Wall {
	protected Rectangle sprite;
	private Camera camera;
	private static Wall instance;
	
	public static Wall getWall(Camera camera) {
		if(instance == null ) {
			instance = new Wall(camera);
		}
		return instance;
	}
	
	public Wall(Camera camera) {
		this.camera = camera;
		sprite = new Rectangle(this.camera.getWidth()/2, this.camera.getHeight()/2-100, 10, 200, MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());		
		sprite.setVisible(true);
	}
	
	public void move(float accelerometerSpeedY) {
		if (accelerometerSpeedY != 0) {

			int lL = 0;
			int uL = (int) (camera.getHeight() - (int) sprite.getHeight());

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
