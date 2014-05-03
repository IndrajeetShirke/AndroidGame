package com.example.game;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;

//placeHolder scene class for the main menu, currently only includes a start menu item 
public class MainMenuScene extends MenuScene implements
		IOnMenuItemClickListener {
	static MainMenuScene INSTANCE;
	MainActivity activity;
	final int MENU_START = 0;
	final int MENU_OPTIONS = 1;

	public static MainMenuScene getMainMenuScene() {
		if(INSTANCE == null){
			INSTANCE = new MainMenuScene();
		}
		return INSTANCE;
	}
	public MainMenuScene() {
		super(MainActivity.getSharedInstance().mCamera);
		activity = MainActivity.getSharedInstance();

		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		IMenuItem startButton = new TextMenuItem(MENU_START, activity.mFont,
				activity.getString(R.string.start),
				activity.getVertexBufferObjectManager());
		startButton.setPosition(mCamera.getWidth() / 2 - startButton.getWidth()
				/ 2, mCamera.getHeight() / 2 - startButton.getHeight() / 2);

		addMenuItem(startButton);
		
		IMenuItem optionsButton = new TextMenuItem(MENU_OPTIONS, activity.mFont,
				activity.getString(R.string.options),
				activity.getVertexBufferObjectManager());
		optionsButton.setPosition(startButton.getX(), startButton.getY() + 100);

		addMenuItem(optionsButton);
		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		switch (arg1.getID()) {
		case MENU_START:
			activity.setCurrentScene(new MainScene());
			detachChildren();
			return true;
		case MENU_OPTIONS :
			break;
		default:
			break;
		}
		return false;
	}

}
