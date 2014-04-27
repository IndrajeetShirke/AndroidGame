package com.example.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;


public class Ball {
	public Sprite sprite;
	private int SPEEDX = -2;
	private int SPEEDY = -2;
	private Camera camera;
	public static final int ROUND = 1;
	public static final int SQUARE = 2;
	public int type;
	public void update() {
		if(sprite.getX() <= 0 || sprite.getX() + sprite.getWidth() >= camera.getWidth()) {
			SPEEDX = SPEEDX * -1;
		}
		sprite.setX(sprite.getX() + SPEEDX);
		if(sprite.getY() <= 0 || sprite.getY() + sprite.getHeight() >= camera.getHeight()) {
			SPEEDY = SPEEDY * -1;
		}
		
		if(sprite.collidesWith(Wall.getWall(camera).sprite)) {
			SPEEDX = SPEEDX * -1;
			if(sprite.getY() < Wall.getWall(camera).sprite.getY() || sprite.getY() > Wall.getWall(camera).sprite.getHeight() + Wall.getWall(camera).sprite.getY()) {
				SPEEDY = SPEEDY * -1;
			}
		} else if(sprite.collidesWith(Wall1.getWall(camera).sprite)) {
			SPEEDX = SPEEDX * -1;
			if(sprite.getY() < Wall1.getWall(camera).sprite.getY() || sprite.getY() > Wall1.getWall(camera).sprite.getHeight() + Wall1.getWall(camera).sprite.getY()) {
				SPEEDY = SPEEDY * -1;
			}
		}
		
		
		sprite.setY(sprite.getY() + SPEEDY);
	}
	
	public Ball(Camera camera, int x, int y, ITextureRegion region, int type) {
		this.camera = camera;
		this.type = type;
		/*sprite = new Dia(0, 0, 70, 30, MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());*/
		sprite = new Sprite(x,y,region,MainActivity.getSharedInstance().getVertexBufferObjectManager());
		sprite.setPosition(x,y);
		sprite.setVisible(true);
	}
}
