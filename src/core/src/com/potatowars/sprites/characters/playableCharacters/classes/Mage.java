package com.potatowars.sprites.characters.playableCharacters.classes;

import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.commonParameters.ParametersPackage;

public class Mage extends MainCharacter {

    private static final float MAGE_HP_COEF = 0.7f;
    private static final float MAGE_DMG_COEF = 1.3f;
    private static final float MAGE_MANA_COEF = 1.0f;
    private static final float MAGE_MOV_SPEED_COEF = 1.2f;
    private static final float MAGE_ARMOR_COEF = 0.5f;

    public Mage(PotatoWars game) {
        super(game);
        setCharacterStats(parametersPackage);
        movementTypes.add("cast");
    }

    @Override
    protected void setCharacterStats(ParametersPackage parametersPackage) {

        if(null != parametersPackage) {
            parametersPackage.getDamage().setDamage((int)(GameConfig.HERO_DPS*MAGE_DMG_COEF));
            parametersPackage.getEnergyPoints().setHealthPoints((int)(GameConfig.HERO_HP*MAGE_HP_COEF));
            parametersPackage.getEnergyPoints().setManaPoints((int)(GameConfig.HERO_MANA*MAGE_MANA_COEF));
            parametersPackage.getEnergyPoints().setArmorPoints((int)(GameConfig.HERO_ARMOR*MAGE_ARMOR_COEF));
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_ATTACK_SPEED);
            parametersPackage.getSpeed().setAtackSpeed((int)(GameConfig.HERO_MOVEMENT_SPEED*MAGE_MOV_SPEED_COEF));
        }
        else{
            System.out.println("parametersPackage is null");
        }
    }
}
