package com.welmo.monsters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.andengine.entity.scene.Scene;
import org.json.JSONException;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.welmo.andengine.managers.SceneDescriptorsManager;
import com.welmo.andengine.managers.SceneManager;
import com.welmo.andengine.managers.SharedPreferenceManager;
import com.welmo.andengine.scenes.IConfigurableScene;
import com.welmo.andengine.scenes.ManageableScene;
import com.welmo.andengine.scenes.descriptors.ConfiguredSceneDescriptor;
import com.welmo.andengine.scenes.descriptors.SceneDescriptor;
import com.welmo.andengine.scenes.descriptors.components.ButtonSceneLauncherDescriptor;
import com.welmo.andengine.scenes.operations.Operation;
import com.welmo.andengine.ui.SimpleWelmoActivity;
import com.welmo.andengine.utility.inappbilling.Inventory;
import com.welmo.andengine.utility.inappbilling.Purchase;
import com.welmo.andengine.utility.inappbilling.PurchasingManager;



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MonstersKids extends SimpleWelmoActivity {
	private final static String 		TAG ="MonstersKids";
	//list of resources file to load while showing the startup file
	static final HashMap<String ,String> NEXT_SCENE_LAUNCHER = new HashMap<String , String>() {
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
	    put("PuzzleMonster28",    "PuzzleMonster29");
	    put("PuzzleMonster29",    "PuzzleMonster30");
	    put("PuzzleMonster30",    "PuzzleMonster31");
	    put("PuzzleMonster31",    "PuzzleMonster32");
	    put("PuzzleMonster32",    "MenuPuzzles04");
	}};
	//list of resources file to load while showing the startup file
	static final HashMap<String , String> MENU_SCENE_LAUNCHER = new HashMap<String , String>() {
		private static final long serialVersionUID = 1L;

		{
			put("PuzzleMonster01",    "MenuPuzzles01");
			put("PuzzleMonster02",    "MenuPuzzles01");
			put("PuzzleMonster03",    "MenuPuzzles01");
			put("PuzzleMonster04",    "MenuPuzzles01");
			put("PuzzleMonster05",    "MenuPuzzles01");
			put("PuzzleMonster06",    "MenuPuzzles01");
			put("PuzzleMonster07",    "MenuPuzzles01");
			put("PuzzleMonster08",    "MenuPuzzles01");
			put("PuzzleMonster09",    "MenuPuzzles02");
			put("PuzzleMonster10",    "MenuPuzzles02");
			put("PuzzleMonster11",    "MenuPuzzles02");
			put("PuzzleMonster12",    "MenuPuzzles02");
			put("PuzzleMonster13",    "MenuPuzzles02");
			put("PuzzleMonster14",    "MenuPuzzles02");
			put("PuzzleMonster15",    "MenuPuzzles02");
			put("PuzzleMonster16",    "MenuPuzzles02");
			put("PuzzleMonster17",    "MenuPuzzles03");
			put("PuzzleMonster18",    "MenuPuzzles03");
			put("PuzzleMonster19",    "MenuPuzzles03");
			put("PuzzleMonster20",    "MenuPuzzles03");
			put("PuzzleMonster21",    "MenuPuzzles03");
			put("PuzzleMonster22",    "MenuPuzzles03");
			put("PuzzleMonster23",    "MenuPuzzles03");
			put("PuzzleMonster24",    "MenuPuzzles03");
			put("PuzzleMonster25",    "MenuPuzzles04");
			put("PuzzleMonster26",    "MenuPuzzles04");
			put("PuzzleMonster27",    "MenuPuzzles04");
			put("PuzzleMonster28",    "MenuPuzzles04");
			put("PuzzleMonster29",    "MenuPuzzles04");
			put("PuzzleMonster30",    "MenuPuzzles04");
			put("PuzzleMonster31",    "MenuPuzzles04");
			put("PuzzleMonster32",    "MenuPuzzles04");
		}};

		private SharedPreferenceManager 	pSPM=null;

	static final ArrayList<String> INAPP_CONSUMABE_PRODUCTS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
				add("monsters_4to24");
				add("default");
		}};
	static final ArrayList<String> INAPP_NOTCONSUMABE_PRODUCTS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;{}};
		
	static final ArrayList<String> INAPP_SUBSCRIPTIONS_PRODUCTS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;{}};

	
	private final String[] resourceFiles = {
			"resources/Texture.xml",
			"resources/musics_and_sounds.xml",
			"resources/fonts.xml",
			"resources/colors.xml"
	};
	
	//list of scene file to load
	private final String[] sceneFiles = {
			"scenes/MenuPuzzles.xml",
			"scenes/PuzzleScenes.xml",
			"scenes/ModalChildScenes.xml",
			"scenes/ConfigureChildScenes.xml"
	};
	private final String[] particuleSuystemFiles = {
			"resources/ParticuleSystems.xml"
	};
	
	@Override
	public void addDefaultProductOwned(Inventory inv){

		String DefaultProduct = new String(
				"{\"orderId\":\"12999763169054705758.1371079406387615\"," +
						"\"packageName\":\"com.example.app\"," +
						"\"productId\":\"default\","+
						"\"purchaseTime\":1345678900000,"+
						"\"purchaseState\":0,"+
						"\"developerPayload\":\"bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ\","+
				"\"purchaseToken\":\"opaque-token-up-to-1000-characters\"}");
		// Record ownership and token
		

		Purchase purchase;
		
		try {
			purchase = new Purchase("inapp", DefaultProduct, "");
			inv.addPurchase(purchase);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MonstersKids() {
		super();
		//setup the resource for the starting scenes
		setStartResourceDscFiles("resources/StartUp.xml");
		setStartSceneDscFile("scenes/StartUp.xml");
		
		//setup the starting scene name
		setFirstSceneName("OpenScene",2000);	
		
		//setup the first scene name
		//setMainSceneName("MenuPuzzles01");
		setMainSceneName("MainMenu");
		
		//setup the scene dimensions
		setCameraWidth(1280);
		setCameraHeight(800);
		
		pSPM = SharedPreferenceManager.getInstance(this);
		
		//setup key for inAppPurchas
		this.mTheBase64EncodedPublicKey = new String("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0F5I7kVSNalYxpvvRpAU5/vL4" +
				"CsiWvnsLcOzq5pDKSJ4PGHh/8iaHuCUFIGpFzZlIgosXleo7uKi5CHBLVkPO3rgDtgJEZYo7O/1W5/chjz" +
				"5G5dGMVByNQj5Wp9TSp0MzGU8O94j7WFAAFfG80J/xO/hm83yG8B2nAUrqPZflVSTJQtwu5YkAhMW9AJATO1Qu" +
				"qM24fIRWWmswDNcO+0pcGe+0XhdffrcZz5ijZs549xRcNcwN2d0EhfpoZcXuKI9qlCUv4nMTjuqjtmEXizj+a+Sq0X" +
				"UuYppPqv72aFjP7OM4mTZaPRmvBIETClJWPEf9euKfAdmoYKp7sjLos4OuwIDAQAB");
	}
	@Override
	public Scene onCreateScene() {
		Scene newScene = super.onCreateScene();
		
		//Initialize catalog of consumable products
		Iterator<String> it;
		it = INAPP_CONSUMABE_PRODUCTS.iterator();
		while(it.hasNext()) this.mPurchMgr.addProductCatalogue(PurchasingManager.SKUS_TYPES.NOT_CONSUMABLE, it.next());
		
		it = INAPP_NOTCONSUMABE_PRODUCTS.iterator();
		while(it.hasNext()) this.mPurchMgr.addProductCatalogue(PurchasingManager.SKUS_TYPES.NOT_CONSUMABLE, it.next());
		
		it = INAPP_SUBSCRIPTIONS_PRODUCTS.iterator();
		while(it.hasNext()) this.mPurchMgr.addProductCatalogue(PurchasingManager.SKUS_TYPES.SUBSCRIPTION, it.next());
		
		return newScene;
	}
	@Override
	protected void onLoadResourcesDescriptionsInBackGround() {
		
		this.readResourceDescriptions(resourceFiles);
		this.readParticuleSystemDescriptions(particuleSuystemFiles);
		
		
	}
	@Override
	protected void onLoadScenesDescriptionsInBackGround() {
		this.readScenesDescriptions(sceneFiles); 
	}
	@Override
	public void doOperation(Operation msg) {
		switch(msg.type){
			case ACTIVATE_BTN_NEXT_SCENE_LAUNCER:
				Log.i(TAG,"SET_LAUNCH_BUTTON_STATUS");
				String nextScene = NEXT_SCENE_LAUNCHER.get(msg.getParameterString(0));
				if(nextScene!= null && !(nextScene.contentEquals(""))){
					SharedPreferences sp = pSPM.getSharedPreferences(nextScene);
					//get current status of next scene button and if is Locked unlook it
					ButtonSceneLauncherDescriptor.Status currentStatus = ButtonSceneLauncherDescriptor.Status.valueOf(sp.getString("LaunchStatus", ButtonSceneLauncherDescriptor.Status.Locked.name()));
					if(currentStatus == ButtonSceneLauncherDescriptor.Status.Locked){
						Editor edt = sp.edit();
						edt.putString("LaunchStatus", ButtonSceneLauncherDescriptor.Status.Unlocked.name());
						edt.commit();
					}
				}
				break;
			default:
				break;
		}
		super.doOperation(msg);
	}
	@Override
	public void onGoToMenu() {	
		ManageableScene currentScene = (ManageableScene)this.mEngine.getScene();
		String newScene = null;
		if(currentScene instanceof IConfigurableScene)
			newScene = MENU_SCENE_LAUNCHER.get(((IConfigurableScene)currentScene).getNameOfInstantiatedScene());
		else	
			newScene = MENU_SCENE_LAUNCHER.get(currentScene.getpSCDescriptor().sceneName);
		
		if(newScene != null)
			onChangeScene(newScene);
	}
	@Override
	public void onGoToNextLevel() {
		ManageableScene currentScene = (ManageableScene)this.mEngine.getScene();
		String newScene = null;
		
		if(currentScene instanceof IConfigurableScene)
			newScene = NEXT_SCENE_LAUNCHER.get(((IConfigurableScene)currentScene).getNameOfInstantiatedScene());
		else	
			newScene = NEXT_SCENE_LAUNCHER.get(currentScene.getpSCDescriptor().sceneName);
		
		//check if the use has purchased the license for the scene
		if(newScene != null){
			//get scene descriptor
			SceneDescriptor pSDesc;
			ConfiguredSceneDescriptor pCfgSDesc;
			String theLicence = null;
			
			if((pSDesc = SceneDescriptorsManager.getInstance().getScene(newScene)) != null)
				theLicence = pSDesc.getSceneLicenceID();
			else
				if((pCfgSDesc = SceneDescriptorsManager.getInstance().getCFGScene(newScene))!= null)
					theLicence = pCfgSDesc.getSceneLicenceID();
				else
					theLicence = "default";
			if(!theLicence.equalsIgnoreCase("default")){
				if(!mInventory.hasPurchase(theLicence)){		//check if has the licence to show the requested scene
					newScene = "IAP_monsters_4to24"; 			//if not launch scene to by the lincence	
					onChangeChildScene(newScene);
					return;
				}
			}
		}
		onChangeScene(newScene);
		return;
	}
}
