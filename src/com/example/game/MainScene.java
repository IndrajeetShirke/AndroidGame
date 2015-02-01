package com.example.game;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.hardware.SensorManager;



public class MainScene extends Scene implements IUpdateHandler{
	static MainScene INSTANCE; 
	MainActivity activity;
	public float accelerometerSpeedY;
	SensorManager sensorManager;
	List<Ball> list = new ArrayList<Ball>();
	HUD hud;
	private IUpdateHandler gameLoopUpdateHandler = new IUpdateHandler(){

		@Override
		public void onUpdate(float pSecondsElapsed) {
			if(checkForWinning()){
				activity.mMusic.stop();
				setChildScene(new ResultScene(activity.mCamera));
				clearUpdateHandlers();
			} else {
				move();
			}			
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	public static MainScene getMainScene() {
		if(INSTANCE == null) {
			INSTANCE = new MainScene();
		}
			return INSTANCE;
	}
	public MainScene() {
		
		intialize();	
		//loadResources();

	}
	
	private void setupHUD() {
		hud = new HUD();
		Text red = new Text(0, 0, activity.mFont,
				"RED",
				activity.getVertexBufferObjectManager());
		red.setPosition(activity.mCamera.getWidth(),
				activity.mCamera.getHeight() / 2);
		hud.attachChild(red);
		Ball b = new Ball(activity.mCamera, 20,20, activity.mRoundFaceTextureRegion, Ball.ROUND);
		hud.attachChild(b.sprite);
		activity.mCamera.setHUD(hud);
	}
	
	public void intialize() {
		activity = MainActivity.getSharedInstance();
		Sprite bgSprite = new Sprite(0,0,activity.bgParallaxTextureRegion,activity.getVertexBufferObjectManager());
		Background bg = new SpriteBackground(bgSprite);
		setBackground(bg);
		
		activity.mMusic.play();
		
		int i = 10;
		while(i>0) {
			if(i%2 == 0) {
				list.add(new Ball(activity.mCamera, randomWithRange(0, (int)activity.mCamera.getWidth()),randomWithRange(0, (int)activity.mCamera.getHeight()), activity.mRoundFaceTextureRegion, Ball.ROUND));
			} else {
				list.add(new Ball(activity.mCamera, randomWithRange(0, (int)activity.mCamera.getWidth()),randomWithRange(0, (int)activity.mCamera.getHeight()), activity.mSquareFaceTextureRegion, Ball.SQUARE));
			}
			
			i--;
		}
		
		for(Ball b : list) {
			attachChild(b.sprite);
		}
		
		Wall w = Wall.getWall(activity.mCamera);
		attachChild(w.sprite);
		Wall1 w1 = Wall1.getWall(activity.mCamera);
		attachChild(w1.sprite);

		//MainActivity.getSharedInstance().setCurrentScene(this);
		/*sensorManager = (SensorManager)MainActivity.getSharedInstance().getSystemService(BaseGameActivity.SENSOR_SERVICE);
		SensorListener.getSharedInstance();
		sensorManager.registerListener(SensorListener.getSharedInstance(), sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);*/
		
		this.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				Wall.getWall(activity.mCamera).setPosition(pSceneTouchEvent.getY() - (40 + 400));
				Wall1.getWall(activity.mCamera).setPosition(pSceneTouchEvent.getY() + 40);
				return false;
			}
		});
		
		registerUpdateHandler(gameLoopUpdateHandler);
		setupHUD();
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
		
		//Wall.getWall(activity.mCamera).move(accelerometerSpeedY);
		//Wall1.getWall(activity.mCamera).move(accelerometerSpeedY);
	}
	
	public boolean checkForWinning() {
		Wall w = Wall.getWall(activity.mCamera);
		for(Ball b : list) {
			if(b.type == Ball.ROUND && w.sprite.getX() < b.sprite.getX()) {
				return false;
			} else if(b.type == Ball.SQUARE && w.sprite.getX() > b.sprite.getX()) {
				return false;
			}
		}
		return true;
	}
	
	public void resetValues() {
		clearChildScene();
		detachChildren();
		list.clear();
		intialize();
		//activity.setCurrentScene(new MainMenuScene());
	}
	
	
}
