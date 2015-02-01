package com.example.game;

import java.io.IOException;


import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.debug.Debug;

public class OptionsScene extends MenuScene implements IOnMenuItemClickListener{
	private MainActivity activity;
	private final int SOUND_MENU_ITEM = 0;
	private final int LEVEL_MENU_ITEM = 1;
	private Sound mExplosionSound;
	private boolean flag = true;
	public OptionsScene () {
		super(MainActivity.getSharedInstance().mCamera);
		activity = MainActivity.getSharedInstance();
		
		
		
		
		AutoParallaxBackground background = new AutoParallaxBackground(0.09804f, 0.6274f, 0.8784f, 2);
		Sprite sprite = new Sprite(0,0,activity.bgParallaxTextureRegion,MainActivity.getSharedInstance().getVertexBufferObjectManager());
		ParallaxEntity pParallaxEntity = new ParallaxEntity(2.0f, sprite);
		background.attachParallaxEntity(pParallaxEntity);
		setBackground(background);
		IMenuItem soundOption = new SpriteMenuItem(SOUND_MENU_ITEM, activity.soundOptionTextureRegion, activity.getVertexBufferObjectManager());
		soundOption.setPosition(mCamera.getWidth() / 2 - soundOption.getWidth()
				/ 2, mCamera.getHeight() / 2 - soundOption.getHeight() / 2);
		addMenuItem(soundOption);
		
		SoundFactory.setAssetBasePath("mfx/");
		try {
			this.mExplosionSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "explosion.ogg");
		} catch (final IOException e) {
			Debug.e("Error", e);
		}
		
		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch (pMenuItem.getID()) {
		case SOUND_MENU_ITEM:
			mExplosionSound.play();
			flag = !flag;
			TextureRegionFactory.createFromSource(activity.checkedUncheckedAtlas, flag ? activity.checked : activity.unchecked, 0, 0);
			return true;
		default:
			break;
		}
		return false;
	}
}
