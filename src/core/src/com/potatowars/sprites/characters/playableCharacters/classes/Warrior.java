package com.potatowars.sprites.characters.playableCharacters.classes;

import com.potatowars.PotatoWars;
import com.potatowars.config.GameConfig;
import com.potatowars.sprites.characters.playableCharacters.MainCharacter;
import com.potatowars.sprites.commonParameters.ParametersPackage;

public class Warrior extends MainCharacter {

    private static final float WARRIOR_HP_COEF = 1.5f;
    private static final float WARRIOR_DMG_COEF = 1.f;
    private static final float WARRIOR_MANA_COEF = 0.8f;
    private static final float WARRIOR_MOV_SPEED_COEF = 0.7f;
    private static final float WARRIOR_ARMOR_COEF = 1.2f;
    
    public Warrior(PotatoWars game) {
        super(game);
        setCharacterStats(parametersPackage);
        movementTypes.add("attack");
    }

    @Override
    protected void setCharacterStats(ParametersPackage parametersPackage) {

        if(null != parametersPackage) {
            parametersPackage.getDamage().setDamage((int)(GameConfig.HERO_DPS*WARRIOR_DMG_COEF));

            parametersPackage.getEnergyPoints().setHealthPoints((int)(GameConfig.HERO_HP*WARRIOR_HP_COEF));
            parametersPackage.getEnergyPoints().setHealthPointsCapacity((int)(GameConfig.HERO_HP*WARRIOR_HP_COEF));

            parametersPackage.getEnergyPoints().setManaPoints((int)(GameConfig.HERO_MANA*WARRIOR_MANA_COEF));
            parametersPackage.getEnergyPoints().setManaPointsCapacity((int)(GameConfig.HERO_MANA*WARRIOR_MANA_COEF));

            parametersPackage.getEnergyPoints().setArmorPoints((int)(GameConfig.HERO_ARMOR*WARRIOR_ARMOR_COEF));
            //parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_ATTACK_SPEED);
            parametersPackage.getSpeed().setAtackSpeed((int)(GameConfig.HERO_MOVEMENT_SPEED*WARRIOR_MOV_SPEED_COEF));
        }
        else{
            System.out.println("parametersPackage is null");
        }
    }
}
