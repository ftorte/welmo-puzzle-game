package com.welmo.monsters;

import java.util.HashMap;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;

import com.welmo.andengine.managers.SharedPreferenceManager;
import com.welmo.andengine.scenes.ManageableScene;
import com.welmo.andengine.scenes.descriptors.components.ButtonSceneLauncherDescriptor;
import com.welmo.andengine.scenes.operations.Operation;
import com.welmo.andengine.ui.SimpleWelmoActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MonstersKids extends SimpleWelmoActivity {
	private final static String 		TAG ="MonstersKids";
	//list of resources file to load while showing the startup file
	static final HashMap<String , String> NEXT_SCENE_LAUNCHER = new HashMap<String , String>() {
		private static final long serialVersionUID = 1L;

	{
	    put("PuzzleMonster01",    "PuzzleMonster02");
	    put("PuzzleMonster02",    "PuzzleMonster03");
	    put("PuzzleMonster03",    "PuzzleMonster04");
	    put("PuzzleMonster04",    "PuzzleMonster05");
	    put("PuzzleMonster05",    "PuzzleMonster06");
	    put("PuzzleMonster06",    "PuzzleMonster07");
	    put("PuzzleMonster07",    "PuzzleMonster08");
	    put("PuzzleMonster08",    "PuzzleMonster09");
	    put("PuzzleMonster09",    "PuzzleMonster10");
	    put("PuzzleMonster10",    "PuzzleMonster11");
	    put("PuzzleMonster11",    "PuzzleMonster12");
	    put("PuzzleMonster12",    "PuzzleMonster13");
	    put("PuzzleMonster13",    "PuzzleMonster14");
	    put("PuzzleMonster14",    "PuzzleMonster15");
	    put("PuzzleMonster15",    "PuzzleMonster16");
	    put("PuzzleMonster16",    "PuzzleMonster17");
	    put("PuzzleMonster17",    "PuzzleMonster18");
	    put("PuzzleMonster18",    "PuzzleMonster19");
	    put("PuzzleMonster19",    "PuzzleMonster20");
	    put("PuzzleMonster20",    "PuzzleMonster21");
	    put("PuzzleMonster21",    "PuzzleMonster22");
	    put("PuzzleMonster22",    "PuzzleMonster23");
	    put("PuzzleMonster23",    "PuzzleMonster24");
	    put("PuzzleMonster24",    "PuzzleMonster25");
	    put("PuzzleMonster25",    "PuzzleMonster26");
	    put("PuzzleMonster26",    "PuzzleMonster27");
	    put("PuzzleMonster27",    "PuzzleMonster28");
	    put("PuzzleMonster28",    "");
	    
	}};
	
	private SharedPreferenceManager 	pSPM=null;
	
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
		
		pSPM = SharedPreferenceManager.getInstance(this);
		
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
	@Override
	public void doOperation(Operation msg) {
		super.doOperation(msg);
		switch(msg.type){
			case ACTIVATE_BTN_NEXT_SCENE_LAUNCER:
				Log.i(TAG,"SET_LAUNCH_BUTTON_STATUS");
				String nextScene = NEXT_SCENE_LAUNCHER.get(msg.getParameterString(0));
				if(nextScene!= null && !(nextScene.contentEquals(""))){
					SharedPreferences sp = pSPM.getSharedPreferences(nextScene);
					Editor edt = sp.edit();
					edt.putString("LaunchStatus", ButtonSceneLauncherDescriptor.Status.level0.name());
					edt.commit();
				}
				break;
			default:
				break;
		}
	}
}
