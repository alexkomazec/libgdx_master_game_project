package com.potatowars.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.hud.inventory.inventoryItem.InventoryItem;
import com.potatowars.hud.inventory.inventoryItem.InventoryItemLocation;
import com.potatowars.hud.status.StatusUI;
import com.potatowars.hud.inventory.*;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.util.Utility;

public class PlayerHUD implements Screen, InventoryObserver {

    private static final String CLASS_NAME = PlayerHUD.class.getSimpleName();

    private static PlayerHUD instance = null;

    public static PlayerHUD getInstance(Camera camera,MainCharacter mainCharacter) {
        if (instance == null) {
            instance = new PlayerHUD(camera,mainCharacter);
        }

        return instance;
    }

    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    //private Entity _player;

    private StatusUI statusUI;
    private InventoryUI inventoryUI;

    private Dialog messageBoxUI;
    //private MapManager _mapMgr;

    //private ShakeCamera _shakeCam;
    //private ClockActor _clock;

    private static final String INVENTORY_FULL = "Your inventory is full!";

    private PlayerHUD(Camera camera, MainCharacter mainCharacter) {
        this.camera = camera;
        viewport = new ScreenViewport(this.camera);
        stage = new Stage(viewport);
        //_stage.setDebugAll(true);


        messageBoxUI = new Dialog("Message", Utility.STATUSUI_SKIN, "solidbackground"){
            {
                button("OK");
                text(INVENTORY_FULL);
            }
            @Override
            protected void result(final Object object){
                cancel();
                setVisible(false);
            }

        };


        messageBoxUI.setVisible(false);
        messageBoxUI.pack();
        messageBoxUI.setPosition(stage.getWidth() / 2 - messageBoxUI.getWidth() / 2, stage.getHeight() / 2 - messageBoxUI.getHeight() / 2);

        statusUI = StatusUI.getInstance();
        statusUI.init(mainCharacter);
        mainCharacter.addObserver(statusUI);
        statusUI.setVisible(true);
        statusUI.setPosition(0, 0); //Left bottom corner
        statusUI.setKeepWithinStage(false);
        statusUI.setMovable(false);

        inventoryUI = InventoryUI.getInstance(new Skin(Gdx.files.internal(Utility.STATUSUI_SKIN_PATH), Utility.STATUSUI_TEXTUREATLAS));
        inventoryUI.setKeepWithinStage(false);
        inventoryUI.setMovable(true);
        inventoryUI.setVisible(false);
        inventoryUI.setPosition(statusUI.getWidth(), 0);

        //add default items if first time
        Array<InventoryItem.ItemTypeID> items = mainCharacter.getMainCharacterConfig().getInventory();
        Array<InventoryItemLocation> itemLocations = new Array<InventoryItemLocation>();
        for( int i = 0; i < items.size; i++){
            itemLocations.add(new InventoryItemLocation(i, items.get(i).toString(), 1, InventoryUI.PLAYER_INVENTORY));
        }

        stage.addActor(messageBoxUI);
        stage.addActor(statusUI);
        stage.addActor(inventoryUI);

        messageBoxUI.validate();
        statusUI.validate();
        inventoryUI.validate();

        //add tooltips to the stage
        Array<Actor> actors = inventoryUI.getInventoryActors();
        for(Actor actor : actors){
            stage.addActor(actor);
        }

        //Observers
        inventoryUI.addObserver(this);

        //Listeners
        ImageButton inventoryButton = statusUI.getInventoryButton();
        inventoryButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                inventoryUI.setVisible(inventoryUI.isVisible() ? false : true);
            }
        });

        InventoryUI.clearInventoryItems(inventoryUI.getInventorySlotTable());
        InventoryUI.clearInventoryItems(inventoryUI.getEquipSlotTable());
        //s_inventoryUI.resetEquipSlots();

        //Array<InventoryItem.ItemTypeID> items = _player.getEntityConfig().getInventory();
        //sArray<InventoryItemLocation> itemLocations = new Array<InventoryItemLocation>();

        Gdx.input.setInputProcessor(stage);

        InventoryUI.populateInventory(inventoryUI.getInventorySlotTable(), itemLocations, inventoryUI.getDragAndDrop(), InventoryUI.PLAYER_INVENTORY, false);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        camera.position.x = stage.getWidth()/2;
        camera.position.y = stage.getHeight()/2;
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    //InventoryObserver
    //Publisher for this action is inventoryUI
    //When a publisher notifies playerHUD, HP or MP value and actors will be updated
    @Override
    public void onNotify(String value, InventoryEvent event) {
        switch(event){
            case ITEM_CONSUMED:
                String[] strings = value.split("omponent.MESSAGE_TOKEN");
                if( strings.length != 2) return;

                int type = Integer.parseInt(strings[0]);
                int typeValue = Integer.parseInt(strings[1]);

                //if( InventoryItem.doesRestorseHP(type) ){
                    //notify(AudioObserver.AudioCommand.SOUND_PLAY_ONCE, AudioObserver.AudioTypeEvent.SOUND_EATING);
                //    _statusUI.addHPValue(typeValue);
                //}else if( InventoryItem.doesRestoreMP(type) ){
                    //notify(AudioObserver.AudioCommand.SOUND_PLAY_ONCE, AudioObserver.AudioTypeEvent.SOUND_DRINKING);
                //    _statusUI.addMPValue(typeValue);
                //}
                //break;
            default:
                break;
        }
    }

    public StatusUI getStatusUI() {
        return statusUI;
    }

    public void setStatusUI(StatusUI statusUI) {
        this.statusUI = statusUI;
    }

    public InventoryUI getInventoryUI() {
        return inventoryUI;
    }

    public void setInventoryUI(InventoryUI inventoryUI) {
        this.inventoryUI = inventoryUI;
    }
}