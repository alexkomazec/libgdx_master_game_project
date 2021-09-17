package com.potatowars.sprites.characters.playableCharacters.classes;

import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.commonParameters.ParametersPackage;

public class Hunter extends MainCharacter {

    private static final float HUNTER_HP_COEF = 0.8f;
    private static final float HUNTER_DMG_COEF = 1.3f;
    private static final float HUNTER_MANA_COEF = 0.7f;
    private static final float HUNTER_MOV_SPEED_COEF = 1.0f;
    private static final float HUNTER_ARMOR_COEF = 0.8f;

    public Hunter(PotatoWars game) {
        super(game);
        movementTypes.add("shoot");
    }

    @Override
    protected void setCharacterStats(ParametersPackage parametersPackage) {

        if(null != parametersPackage) {
            parametersPackage.getDamage().setDamage((int)(GameConfig.HERO_DPS*HUNTER_DMG_COEF));

            parametersPackage.getEnergyPoints().setHealthPoints((int)(GameConfig.HERO_HP*HUNTER_HP_COEF));
            parametersPackage.getEnergyPoints().setHealthPointsCapacity((int)(GameConfig.HERO_HP*HUNTER_HP_COEF));

            parametersPackage.getEnergyPoints().setManaPoints((int)(GameConfig.HERO_MANA*HUNTER_MANA_COEF));
            parametersPackage.getEnergyPoints().setManaPointsCapacity((int)(GameConfig.HERO_MANA*HUNTER_MANA_COEF));

            parametersPackage.getEnergyPoints().setArmorPoints((int)(GameConfig.HERO_ARMOR*HUNTER_ARMOR_COEF));
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_ATTACK_SPEED);
            parametersPackage.getSpeed().setAtackSpeed((int)(GameConfig.HERO_MOVEMENT_SPEED*HUNTER_MOV_SPEED_COEF));
        }
        else{
            System.out.println("parametersPackage is null");
        }
    }

}
