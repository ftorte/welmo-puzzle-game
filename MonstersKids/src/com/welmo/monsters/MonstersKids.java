package com.welmo.monsters;

import android.view.KeyEvent;
import com.welmo.andengine.scenes.ManageableScene;
import com.welmo.andengine.ui.SimpleWelmoActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MonstersKids extends SimpleWelmoActivity {
	//list of resources file to load while showing the startup file
	private final String[] resourceFiles = {
			"resources/Texture.xml"
	};
	//list of scene file to load
	private final String[] sceneFiles = {
			"scenes/MenuPuzzles.xml",
			"scenes/PuzzleScenes.xml"
	};

	public MonstersKids() {
		super();
		//setup the resource for the starting scenes
		setStartResourceDscFiles("resources/StartUp.xml");
		setStartSceneDscFile("scenes/StartUp.xml");
		//setup the first scene name
		setFirstSceneName("OpenScene",3000);	
		//setup the first scene name
		//setMainSceneName("MainMenu");
		setMainSceneName("MenuPuzzles01");
		
		//setup the scene dimensions
		setCameraWidth(1280);
		setCameraHeight(800);
	}
	@Override
	protected void onLoadResourcesDescriptionsInBackGround() {
		
		this.readResourceDescriptions(resourceFiles);
	}
	@Override
	protected void onLoadScenesDescriptionsInBackGround() {
		this.readScenesDescriptions(sceneFiles); 
	}
	
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if(pKeyCode == KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			ManageableScene theCurrentScene = (ManageableScene) mEngine.getScene();
			String newScene = theCurrentScene.getFatherScene();
			
			if(newScene != ""){
				this.onChangeScene(newScene);
				return true;
			}
			else
				return super.onKeyDown(pKeyCode, pEvent);
		} else {
			return super.onKeyDown(pKeyCode, pEvent);
		}
	}
}
