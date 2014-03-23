package com.example.game;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

	static final int CAMERA_WIDTH = 800;
	static final int CAMERA_HEIGHT = 480;

	public Font mFont;
	public Camera mCamera;

	// A reference to the current scene
	public Scene mCurrentScene;
	public static MainActivity instance;
	public TextureRegion mSquareFaceTextureRegion;
	public TextureRegion mRoundFaceTextureRegion;
	private Texture mTexture;

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		mFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
		mFont.load();
		//this.mTexture = new Texture(64, 32, TextureOptions.BILINEAR);
		
		BitmapTextureAtlas atlastSquare = new BitmapTextureAtlas(getTextureManager(), 64, 32);
		BitmapTextureAtlas atlastRound = new BitmapTextureAtlas(getTextureManager(), 64, 32);
		AssetBitmapTextureAtlasSource sourceBox = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/boxface.png");
		AssetBitmapTextureAtlasSource sourceRound = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/circleface_tiled.png");
		this.mSquareFaceTextureRegion = TextureRegionFactory.createFromSource(atlastSquare, sourceBox, 0,0);
		this.mRoundFaceTextureRegion = TextureRegionFactory.createFromSource(atlastRound, sourceRound, 0,0);
		//load resource
		atlastSquare.load();
		atlastRound.load();
			
	}
	

	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mCurrentScene = new MainScene();
		return mCurrentScene;
	}

	// to change the current main scene
	public void setCurrentScene(Scene scene) {
		mCurrentScene = null;
		mCurrentScene = scene;
		getEngine().setScene(mCurrentScene);

	}
	
	static MainActivity getSharedInstance() {
		return instance;
	}

	@Override
	public void onBackPressed() {
		/*Log.v("Jimvaders",
				"MainActivity BackPressed " + mCurrentScene.toString());
		if (mCurrentScene instanceof GameScene)
			((GameScene) mCurrentScene).detach();

		mCurrentScene = null;
		SensorListener.instance = null;*/
		super.onBackPressed();
	}

}