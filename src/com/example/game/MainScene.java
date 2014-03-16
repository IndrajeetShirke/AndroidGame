package com.example.game;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;



public class MainScene extends Scene {
	MainActivity activity;
	
	List<Ball> list = new ArrayList<Ball>();
	public MainScene() {
		setBackground(new Background(0.09804f, 0.6274f, 0));
		activity = MainActivity.getSharedInstance();
		int i = 10;
		while(i>0) {
			list.add(new Ball(activity.mCamera, randomWithRange(0, (int)activity.mCamera.getWidth()),randomWithRange(0, (int)activity.mCamera.getHeight())));
			i--;
		}
		
		for(Ball b : list) {
			attachChild(b.sprite);
		}
		

		
		
		
		registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				move();
			}
		});
		//loadResources();

	}
	
	
	
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	public void move() {
		for(Ball b : list) {
			b.update();
		}
	}
}
