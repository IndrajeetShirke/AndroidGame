package com.example.game;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
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
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

	static final int CAMERA_WIDTH = 800;
	static final int CAMERA_HEIGHT = 480;

	public Font mFont;
	public Camera mCamera;
	public Music mMusic;
	// A reference to the current scene
	public Scene mCurrentScene;
	public static MainActivity instance;
	public TextureRegion mSquareFaceTextureRegion;
	public TextureRegion mRoundFaceTextureRegion;
	public TextureRegion wallTextureRegion;
	public TextureRegion backgroundTextureRegion;
	public TextureRegion bgParallaxTextureRegion;
	public TextureRegion soundOptionTextureRegion;
	public AssetBitmapTextureAtlasSource sourceBox;
	public AssetBitmapTextureAtlasSource sourceRound;
	
	public AssetBitmapTextureAtlasSource checked;
	public AssetBitmapTextureAtlasSource unchecked;
	
	public BitmapTextureAtlas atlastSquare;
	public BitmapTextureAtlas checkedUncheckedAtlas;
	private Texture mTexture;

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		EngineOptions eo = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		eo.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		return eo;
	}
	


	@Override
	protected void onCreateResources() {
		mFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.createFromAsset(getAssets(), "fonts/Plok.ttf"), 32);
		mFont.load();
		//this.mTexture = new Texture(64, 32, TextureOptions.BILINEAR);
		
		checkedUncheckedAtlas = new BitmapTextureAtlas(getTextureManager(), 64, 64); 
		atlastSquare = new BitmapTextureAtlas(getTextureManager(), 32, 32);
		BitmapTextureAtlas atlastRound = new BitmapTextureAtlas(getTextureManager(), 32, 32);
		BitmapTextureAtlas wallAtlas = new BitmapTextureAtlas(getTextureManager(), 10, 400);
		BitmapTextureAtlas backgroundAtlas = new BitmapTextureAtlas(getTextureManager(), 800, 480);
		BitmapTextureAtlas bgParallaxAtlas = new BitmapTextureAtlas(getTextureManager(), 800,480); 
		
		sourceBox = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/boxface.png");
		sourceRound = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/circleface_tiled.png");
		
		checked = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/rsz_checked.png");
		unchecked = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/rsz_unchecked.png");
		
		AssetBitmapTextureAtlasSource wallSource = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/chain.png");
		AssetBitmapTextureAtlasSource backgroundSource = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/gameBackground.png");
		AssetBitmapTextureAtlasSource bgParallaxSource = AssetBitmapTextureAtlasSource.create(getAssets(), "gfx/clouds.png");
		
		this.mSquareFaceTextureRegion = TextureRegionFactory.createFromSource(atlastSquare, sourceBox, 0,0);
		this.mRoundFaceTextureRegion = TextureRegionFactory.createFromSource(atlastRound, sourceRound, 0,0);
		this.wallTextureRegion = TextureRegionFactory.createFromSource(wallAtlas, wallSource, 0, 0);
		this.backgroundTextureRegion =  TextureRegionFactory.createFromSource(backgroundAtlas, backgroundSource, 0, 0);
		this.bgParallaxTextureRegion = TextureRegionFactory.createFromSource(bgParallaxAtlas, bgParallaxSource, 0, 0); 
		this.soundOptionTextureRegion = TextureRegionFactory.createFromSource(checkedUncheckedAtlas, unchecked, 0, 0);
		//load resource
		atlastSquare.load();
		atlastRound.load();
		wallAtlas.load();
		backgroundAtlas.load();
		bgParallaxAtlas.load();
		checkedUncheckedAtlas.load();
		
		MusicFactory.setAssetBasePath("mfx/");
		try {
			this.mMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "wagner_the_ride_of_the_valkyries.ogg");
			this.mMusic.setLooping(true);
		} catch (final IOException e) {
			Debug.e("Error", e);
		}
			
	}
	

	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mCurrentScene = new SplashScene();
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