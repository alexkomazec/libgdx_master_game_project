package com.potatowars.menu.PlayScreens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.commonParameters.EnvironmentalDifficulty;

public class Hud implements Disposable {


    //stage  - an empty box that represents a tray that receives a *table
    //table  - a mechanism that is created into the *stage and users can organize layot of the hud
    public Stage stage;

    //Hud should stay untouched while screens are changing.
    private Viewport viewport;

    private int hp;
    private int stamina;
    private int mana;

    private Label hpLabel;
    private Label manaLabel;
    private Label staminaLabel;

    private Label hpLabel_num;
    private Label manaLabel_num;
    private Label staminaLabel_num;

    private EnvironmentalDifficulty environmentalDifficulty;

    public Hud(SpriteBatch sb, MainCharacter mainCharacter, EnvironmentalDifficulty environmentalDifficulty)
    {
        viewport = new FitViewport(GameConfig.PHYSICAL_WIDTH, GameConfig.PHYSICAL_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);
        this.environmentalDifficulty = environmentalDifficulty;

        hp = mainCharacter.getParametersPackage().getEnergyPoints().getHealthPoints();
        mana = mainCharacter.getParametersPackage().getEnergyPoints().getManaPoints();
        stamina = this.environmentalDifficulty.getEnvironmentalDifficultyValue();

        hpLabel = new Label("HP", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manaLabel = new Label("MANA", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        staminaLabel = new Label(environmentalDifficulty.getTypeOfEnviromental(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        hpLabel_num = new Label(String.format("%02d",hp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manaLabel_num = new Label(String.format("%02d",mana), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        staminaLabel_num = new Label(String.format("%02d",stamina), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table = new Table();
        //table will be set at the top of our stage
        table.left().top();
        table.defaults().pad(1).padRight(1);
        //table.debug();

        //table is sized to the size of *stage
        table.setFillParent(true);

        //Creating table

        //marioLabel expaneds the x, row using 10 pixels
        //These three labels are on the top, all in one row
        table.add(hpLabel).padLeft(1).padRight(1).width(100);
        table.add(manaLabel).padLeft(1).padRight(1).width(100);
        table.add(staminaLabel).padLeft(1).padRight(1).width(100);

        //Creating a new row
        table.row();
        //Putting these labels into a new row
        table.add(hpLabel_num).padLeft(1).padRight(1).width(100);
        table.add(manaLabel_num).padLeft(1).padRight(1).width(100);
        table.add(staminaLabel_num).padLeft(1).padRight(1).width(100);

        stage.addActor(table);
    }

    public void update(int newStamina)
    {
        stamina = newStamina;
        staminaLabel_num.setText(stamina);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
