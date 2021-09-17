package com.potatowars.hud.status;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.potatowars.hud.status.StatusInterfaces.StatsObserver;
import com.potatowars.sprites.LevelUpSystem;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.util.Utility;

public class StatusUI extends Window implements StatsObserver {

    private static final String CLASS_NAME = StatusUI.class.getSimpleName();

    private Image hpBar;
    private Image mpBar;
    private Image xpBar;

    private ImageButton inventoryButton;

    //Attributes
    private int hpVal = 1;
    private int hpValCapacity = 1;

    private int mpVal = 1;
    private int mpValCapacity = 1;

    private LevelUpSystem.LEVEL levelVal = LevelUpSystem.LEVEL.LEVEL1;
    private int xpVal = 0;
    private int xpValCapacity = 0;

    private int goldVal = 1;


    private Label hpValLabel;
    private Label mpValLabel;
    private Label xpValLabel;
    private Label levelValLabel;
    private Label goldValLabel;

    private float barWidth = 0;
    private float barHeight = 0;

    private static StatusUI instance = null;

    public static StatusUI getInstance() {
        if (instance == null) {
            instance = new StatusUI();
        }

        return instance;
    }


    private StatusUI(){
        super("stats", Utility.STATUSUI_SKIN);
        //groups
        WidgetGroup group = new WidgetGroup();
        WidgetGroup group2 = new WidgetGroup();
        WidgetGroup group3 = new WidgetGroup();

        //images
        hpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("HP_Bar"));
        Image bar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
        mpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("MP_Bar"));
        Image bar2 = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
        xpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("XP_Bar"));
        Image bar3 = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("Bar"));

        barWidth = hpBar.getWidth();
        barHeight = hpBar.getHeight();


        //labels
        Label hpLabel = new Label(" hp: ", Utility.STATUSUI_SKIN);
        hpValLabel = new Label(String.valueOf(hpVal), Utility.STATUSUI_SKIN);
        Label mpLabel = new Label(" mp: ", Utility.STATUSUI_SKIN);
        mpValLabel = new Label(String.valueOf(mpVal), Utility.STATUSUI_SKIN);
        Label xpLabel = new Label(" xp: ", Utility.STATUSUI_SKIN);
        xpValLabel = new Label(String.valueOf(xpVal), Utility.STATUSUI_SKIN);
        Label levelLabel = new Label(" lvl: ", Utility.STATUSUI_SKIN);
        levelValLabel = new Label(String.valueOf(levelVal), Utility.STATUSUI_SKIN);
        Label goldLabel = new Label(" gold: ", Utility.STATUSUI_SKIN);
        goldValLabel = new Label(String.valueOf(goldVal), Utility.STATUSUI_SKIN);

        //buttons
        inventoryButton = new ImageButton(Utility.STATUSUI_SKIN, "inventory-button");
        inventoryButton.getImageCell().size(32, 32);

        //Align images
        hpBar.setPosition(3, 6);
        mpBar.setPosition(3, 6);
        xpBar.setPosition(3, 6);

        //add to widget groups
        group.addActor(bar);
        group.addActor(hpBar);
        group2.addActor(bar2);
        group2.addActor(mpBar);
        group3.addActor(bar3);
        group3.addActor(xpBar);

        //Add to layout
        defaults().expand().fill();

        //account for the title padding
        this.pad(this.getPadTop() + 10, 10, 10, 10);

        this.add();
        this.add(inventoryButton).align(Align.right);
        this.row();

        this.add(group).size(bar.getWidth(), bar.getHeight()).padRight(10);
        this.add(hpLabel);
        this.add(hpValLabel).align(Align.left);
        this.row();

        this.add(group2).size(bar2.getWidth(), bar2.getHeight()).padRight(10);
        this.add(mpLabel);
        this.add(mpValLabel).align(Align.left);
        this.row();

        this.add(group3).size(bar3.getWidth(), bar3.getHeight()).padRight(10);
        this.add(xpLabel);
        this.add(xpValLabel).align(Align.left).padRight(20);
        this.row();

        this.add(levelLabel).align(Align.left);
        this.add(levelValLabel).align(Align.left);
        this.row();
        this.add(goldLabel);
        this.add(goldValLabel).align(Align.left);

        //this.debug();
        this.pack();
    }

    public ImageButton getInventoryButton() {
        return inventoryButton;
    }

    public void updateBar(Image bar, int currentVal, int maxVal){
        int val = MathUtils.clamp(currentVal, 0, maxVal);
        float tempPercent = (float) val / (float) maxVal;
        float percentage = MathUtils.clamp(tempPercent, 0, 100);
        bar.setSize(barWidth *percentage, barHeight);
    }

    @Override
    public void onNotify(StatsPacket statusPacket, ComponentType component) {
        //Get all stats from the packet
        this.hpVal = statusPacket.getEnergyPoints().getHealthPoints();
        this.hpValCapacity = statusPacket.getEnergyPoints().getHealthPointsCapacity();

        this.mpVal = statusPacket.getEnergyPoints().getManaPoints();
        this.mpValCapacity = statusPacket.getEnergyPoints().getManaPointsCapacity();

        this.xpVal = statusPacket.getExp();
        this.xpValCapacity = statusPacket.getCurrentExpCapacity();

        this.levelVal = statusPacket.getLevel();

        if(component != ComponentType.LEVEL_UP_SYSTEM_COMPONENT){
            this.goldVal = statusPacket.getGold();
        }

        this.hpValLabel.setText(hpVal);
        this.mpValLabel.setText(mpVal);
        this.xpValLabel.setText(xpVal);
        this.goldValLabel.setText(goldVal);
        this.levelValLabel.setText(levelVal.ordinal()+1);

        updateBar(hpBar, hpVal, hpValCapacity);
        updateBar(mpBar, mpVal, mpValCapacity);
        updateBar(xpBar, xpVal, xpValCapacity);
    }

    @Override
    public void onNotify(StatsPacket statusPacket) {

    }

    public void init(MainCharacter mainCharacter)
    {
        this.hpVal = mainCharacter.getParametersPackage().getEnergyPoints().getHealthPoints();
        this.hpValCapacity = mainCharacter.getParametersPackage().getEnergyPoints().getHealthPointsCapacity();

        this.mpVal = mainCharacter.getParametersPackage().getEnergyPoints().getManaPoints();
        this.mpValCapacity = mainCharacter.getParametersPackage().getEnergyPoints().getManaPointsCapacity();

        this.levelVal = mainCharacter.getParametersPackage().getLevel();

        this.xpVal = mainCharacter.getParametersPackage().getExp();

        LevelUpSystem levelUpSystem = LevelUpSystem.getInstance();
        this.xpValCapacity = levelUpSystem.getCurrentExpCapacity();

        this.goldVal = mainCharacter.getParametersPackage().getGold();

        this.hpValLabel.setText(hpVal);
        this.mpValLabel.setText(mpVal);
        this.xpValLabel.setText(xpVal);
        this.levelValLabel.setText(levelVal.ordinal()+1);
        this.goldValLabel.setText(goldVal);

        updateBar(hpBar, hpVal, hpValCapacity);
        updateBar(mpBar, mpVal, mpValCapacity);
        updateBar(xpBar, xpVal, xpValCapacity);
    }
}