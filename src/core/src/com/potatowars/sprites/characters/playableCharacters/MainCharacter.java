package com.potatowars.sprites.characters.playableCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.potatowars.PotatoWars;
import com.potatowars.box2d.Box2dBodyBuilder;
import com.potatowars.config.GameConfig;
import com.potatowars.hud.status.StatsPacket;
import com.potatowars.hud.status.StatusInterfaces.StatsObserver;
import com.potatowars.hud.status.StatusInterfaces.StatsSubject;
import com.potatowars.sprites.LevelUpSystem;
import com.potatowars.sprites.characters.BasicCharacter;
import com.potatowars.sprites.commonParameters.CommonStates;
import com.potatowars.sprites.commonParameters.Damage;
import com.potatowars.sprites.commonParameters.ParametersPackage;

import static com.potatowars.hud.status.StatusInterfaces.StatsObserver.ComponentType.DEFAULT;
import static com.potatowars.hud.status.StatusInterfaces.StatsObserver.ComponentType.LEVEL_UP_SYSTEM_COMPONENT;
import static com.potatowars.sprites.commonParameters.CommonStates.State.ATTACKING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.BEINGHURT;
import static com.potatowars.sprites.commonParameters.CommonStates.State.DYING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.FALLING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.JUMPING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.RUNNING;
import static com.potatowars.sprites.commonParameters.CommonStates.State.STANDING;

public class MainCharacter extends BasicCharacter implements StatsObserver, StatsSubject {

    private static final String CLASS_NAME = MainCharacter.class.getSimpleName();
    public static String PLAYER_INVENTORY_CONFIGURATION = "scripts/mainCharacter.json";


    protected Array<String> movementTypes;
    private Array<StatsObserver> observers;

    //This can be used for loading any json configuration
    MainCharacterConfig mainCharacterConfig;

    public MainCharacter(PotatoWars game){
        super();
        observers = new Array<StatsObserver>();
        parametersPackage = new ParametersPackage();

        //Set all the character characteristics
        setCharacterStats(parametersPackage);

        //Set all the character movement types
        movementTypes = new Array<String>();
        setAllMovementTypes();

        //Set the sprite bounds
        setBounds(0,0,32/ GameConfig.PPM,32/ GameConfig.PPM);

        //Load config
        setMainCharacterConfig(getMainCharacterConfig(PLAYER_INVENTORY_CONFIGURATION));

    }

    protected void setAllMovementTypes(){

        movementTypes.add("stand");
        movementTypes.add("run");
        movementTypes.add("jump");
        movementTypes.add("hurt");
        movementTypes.add("dead");
        movementTypes.add("fall");
    }


    protected void setCharacterStats(ParametersPackage parametersPackage){

        if(null != parametersPackage) {
            parametersPackage.getDamage().setDamage(GameConfig.HERO_DPS);
            parametersPackage.getEnergyPoints().setHealthPoints(GameConfig.HERO_HP);
            parametersPackage.getEnergyPoints().setManaPoints(GameConfig.HERO_MANA);
            parametersPackage.getEnergyPoints().setArmorPoints(GameConfig.HERO_ARMOR);
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_ATTACK_SPEED);
            parametersPackage.getSpeed().setAtackSpeed(GameConfig.HERO_MOVEMENT_SPEED);
        }
        else{
            System.out.println("parametersPackage is null");
        }
    }

    //Update the position of the character
    public void update(float dt)
    {
        setPosition(
                getB2Body_positionX() - Box2dBodyBuilder.divideByPpm(getWidth()),
                getB2Body_positionY() - Box2dBodyBuilder.divideByPpm(getHeight())
        );
        setRegion(getFrame(dt));
    }

    @Override
    public void setB2Body(Body b2body) {
        super.setB2Body(b2body);
        fixture.setUserData("hero");
    }

    //Get the current frame
    public TextureRegion getFrame(float dt)
    {
        //Set current State
        currentState = getState();

        setCurrentState(currentState);

        TextureRegion region;

        switch (getCurrentState())
        {
            case JUMPING:
                region = (TextureRegion) findSpecifiedPair(JUMPING);
                break;
            case RUNNING:
                region = (TextureRegion) findSpecifiedPair(RUNNING);
                break;
            case FALLING:
                region = (TextureRegion) findSpecifiedPair(FALLING);
                break;
            case STANDING:
                region = (TextureRegion) findSpecifiedPair(STANDING);
                break;
            case DYING:
                region = (TextureRegion) findSpecifiedPair(DYING);
                break;
            case BEINGHURT:
                region = (TextureRegion) findSpecifiedPair(BEINGHURT);
                break;
            case ATTACKING:
                region = (TextureRegion) findSpecifiedPair(ATTACKING);
                break;
            default:
                region = (TextureRegion) findSpecifiedPair(STANDING);
                Gdx.app.debug(CLASS_NAME, "No specified current state");
                break;
        }

        if((b2body.getLinearVelocity().x <0 || !runningRight) &&  !region.isFlipX())
        {
            //Running Left
            region.flip(true,false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x >0|| runningRight) && region.isFlipX())
        {
            //Running Right
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previosState ? stateTimer + dt:0;
        previosState = currentState;
        return region;
    }


    //Calculate the current state
    public CommonStates.State getState()
    {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y <0 && previosState == JUMPING))
        {
            return JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0)
        {
            return FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0)
        {
            return RUNNING;
        }
        else {
            return STANDING;
        }
    }

    //Binding Wrapper methods
    private void setCurrentState(CommonStates.State state){
        parametersPackage.getStates().setCurrentState(state);
    }

    private CommonStates.State getCurrentState(){
        return parametersPackage.getStates().getCurrentState();
    }

    public Array<String> getMovementTypes() {
        return movementTypes;
    }

    @Override
    public void onNotify(StatsPacket statsPacket, ComponentType component) {
            switch(component){
                case LEVEL_UP_SYSTEM_COMPONENT:

                    Gdx.app.error(CLASS_NAME,"Current level:" + parametersPackage.getLevel());
                    Gdx.app.error(CLASS_NAME,"Current Exp:" + parametersPackage.getExp());
                    Gdx.app.error(CLASS_NAME,"Exp Capacity" + statsPacket.getCurrentExpCapacity());

                    notify(statsPacket,LEVEL_UP_SYSTEM_COMPONENT);
                    break;
                case BOX_2D_WORLD_COMPONENT:
                case BATTLE_SYSTEM_COMPONENT:
                case INVENTORY_COMPONENT:
                    break;
                default:
                    Gdx.app.debug(CLASS_NAME, "No specified Component ");
            }
    }

    @Override
    public void onNotify(StatsPacket statusPacket) {
        //Not used there
    }

    @Override
    public void addObserver(StatsObserver statsObserver) {
        observers.add(statsObserver);
    }

    @Override
    public void removeObserver(StatsObserver statsObserver) {
        observers.removeValue(statsObserver, true);
    }

    @Override
    public void removeAllObservers() {
        for(StatsObserver observer: observers){
            observers.removeValue(observer, true);
        }
    }

    @Override
    public void notify(StatsPacket statsPacket, StatsObserver.ComponentType component) {

        parametersPackage.setExp(statsPacket.getExp());

        for(StatsObserver observer: observers){
            Gdx.app.debug(CLASS_NAME,"Going to notify:" + observer);

            int health;
            int mana;
            int armor;
            int damage;

            if(isLeveledup(statsPacket)){

                //Level up!
                parametersPackage.setLevel(statsPacket.getLevel());

                //Get stats bonus
                health     = statsPacket.getEnergyPoints().getHealthPoints();
                mana       = statsPacket.getEnergyPoints().getManaPoints();
                armor     = statsPacket.getEnergyPoints().getArmorPoints();
                damage    = statsPacket.getDamage().getDamage();

                //Increase stats by leveling up
                parametersPackage.getEnergyPoints().increaseHealthCapacity(health);
                parametersPackage.getEnergyPoints().increaseManaCapacity(mana);
                parametersPackage.getEnergyPoints().increaseArmor(armor);
                parametersPackage.getDamage().increaseDamage(damage);

                //Get Stats and store in in the packet that will be send to Status HUD
                health = parametersPackage.getEnergyPoints().getHealthPointsCapacity();
                mana = parametersPackage.getEnergyPoints().getManaPointsCapacity();
                armor = parametersPackage.getEnergyPoints().getArmorPoints();
                damage = parametersPackage.getDamage().getDamage();

                restoreHP(health);
                restoreMP(mana);

                //Store stats capacity, armor and damage
                statsPacket.getEnergyPoints().setHealthPointsCapacity(health);
                statsPacket.getEnergyPoints().setManaPointsCapacity(mana);
                statsPacket.getEnergyPoints().setArmorPoints(armor);
                statsPacket.getDamage().setDamage(damage);

                health  = parametersPackage.getEnergyPoints().getHealthPoints();
                mana    = parametersPackage.getEnergyPoints().getManaPoints();

                //Store HP and MP current points
                statsPacket.getEnergyPoints().setHealthPoints(health);
                statsPacket.getEnergyPoints().setManaPoints(mana);

            }
            else
            {
                health = parametersPackage.getEnergyPoints().getHealthPointsCapacity();
                mana   = parametersPackage.getEnergyPoints().getManaPointsCapacity();

                statsPacket.getEnergyPoints().setHealthPointsCapacity(health);
                statsPacket.getEnergyPoints().setManaPointsCapacity(mana);

                health = parametersPackage.getEnergyPoints().getHealthPoints();
                mana = parametersPackage.getEnergyPoints().getManaPoints();

                statsPacket.getEnergyPoints().setHealthPoints(health);
                statsPacket.getEnergyPoints().setManaPoints(mana);

            }

            //Set level/experience
            this.parametersPackage.setLevel(statsPacket.getLevel());
            this.parametersPackage.setExp(statsPacket.getExp());

            observer.onNotify(statsPacket,component);
        }
    }

    private void restoreHP(int value){
        parametersPackage.getEnergyPoints().setHealthPoints(value);
    }

    private void restoreMP(int value){
        parametersPackage.getEnergyPoints().setManaPoints(value);
    }

    private boolean isLeveledup(StatsPacket statsPacket){

        if(parametersPackage.getLevel() != statsPacket.getLevel() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void modifyHP(int value,boolean isHpIncreased)
    {
        LevelUpSystem levelUpSystem = LevelUpSystem.getInstance();

        int hp          = this.parametersPackage.getEnergyPoints().getHealthPoints();
        int hpCapacity  = this.parametersPackage.getEnergyPoints().getHealthPointsCapacity();

        if(isHpIncreased){
            hp += value;
            if(hp > hpCapacity)
            {
                hp = hpCapacity;
            }
            else
            {
                //Do nothing
            }
        }
        else
        {
            hp -= value;
            if(hp < 0)
            {
                hp = 0;
            }
            else
            {
                //Do nothing
            }
        }

        parametersPackage.getEnergyPoints().setHealthPoints(hp);

        //Create payload packet, fill it and send
        StatsPacket statsPacket = new StatsPacket();
        fillStatsPacket(statsPacket);

        statsPacket.getEnergyPoints().setHealthPoints(hp);

        for(StatsObserver observer: observers) {
            observer.onNotify(statsPacket,DEFAULT);
        }
    }

    public void modifyMP(int value,boolean isManaIncreased)
    {
        LevelUpSystem levelUpSystem = LevelUpSystem.getInstance();

        int mp          = this.parametersPackage.getEnergyPoints().getManaPoints();
        int mpCapacity  = this.parametersPackage.getEnergyPoints().getManaPointsCapacity();

        if(isManaIncreased){
            mp += value;
            if(mp > mpCapacity)
            {
                mp = mpCapacity;
            }
            else
            {
                //Do nothing
            }
        }
        else
        {
            mp -= value;
            if(mp < 0)
            {
                mp = 0;
            }
            else
            {
                //Do nothing
            }
        }

        parametersPackage.getEnergyPoints().setManaPoints(mp);

        //Create payload packet, fill it and send
        StatsPacket statsPacket = new StatsPacket();
        fillStatsPacket(statsPacket);

        statsPacket.getEnergyPoints().setManaPoints(mp);

        for(StatsObserver observer: observers) {
            observer.onNotify(statsPacket,DEFAULT);
        }
    }


    public void modifyGold(int value,boolean isGoldIncreased)
    {
        LevelUpSystem levelUpSystem = LevelUpSystem.getInstance();

        int gold          = this.parametersPackage.getGold();

        if(isGoldIncreased) {
            gold += value;
        }
        else
        {
            if(0 == gold){
               //do nothing
            }else{
                gold -= value;
            }
        }

        parametersPackage.setGold(gold);

        //Create payload packet, fill it and send
        StatsPacket statsPacket = new StatsPacket();
        fillStatsPacket(statsPacket);

        statsPacket.setGold(gold);

        for(StatsObserver observer: observers) {
            observer.onNotify(statsPacket,DEFAULT);
        }
    }
    private void fillStatsPacket(StatsPacket statsPacket)
    {
        if(statsPacket != null)
        {
            int temp_buffer = parametersPackage.getEnergyPoints().getHealthPoints();
            Damage temp_damage = parametersPackage.getDamage();
            LevelUpSystem.LEVEL temp_level = parametersPackage.getLevel();
            LevelUpSystem levelUpSystem = LevelUpSystem.getInstance();

            statsPacket.getEnergyPoints().setHealthPoints(temp_buffer);

            temp_buffer = parametersPackage.getEnergyPoints().getHealthPointsCapacity();
            statsPacket.getEnergyPoints().setHealthPointsCapacity(temp_buffer);

            temp_buffer = parametersPackage.getEnergyPoints().getManaPoints();
            statsPacket.getEnergyPoints().setManaPoints(temp_buffer);

            temp_buffer = parametersPackage.getEnergyPoints().getManaPointsCapacity();
            statsPacket.getEnergyPoints().setManaPointsCapacity(temp_buffer);

            temp_buffer = parametersPackage.getEnergyPoints().getArmorPoints();
            statsPacket.getEnergyPoints().setArmorPoints(temp_buffer);

            statsPacket.setDamage(temp_damage);

            statsPacket.setLevel(temp_level);

            temp_buffer = parametersPackage.getExp();
            statsPacket.setExp(temp_buffer);

            statsPacket.setCurrentExpCapacity(levelUpSystem.getCurrentExpCapacity());

            temp_buffer = parametersPackage.getGold();
            statsPacket.setGold(temp_buffer);

        }else{
            Gdx.app.debug(CLASS_NAME,"statsPacket null pointer reference");
        }
    }

    public void setMainCharacterConfig(MainCharacterConfig mainCharacterConfig){
        this.mainCharacterConfig = mainCharacterConfig;
    }

    public MainCharacterConfig getMainCharacterConfig(String configFilePath){
        Json json = new Json();
        FileHandle fileHandle = Gdx.files.internal(PLAYER_INVENTORY_CONFIGURATION);
        return json.fromJson(MainCharacterConfig.class, fileHandle);
    }

    public MainCharacterConfig getMainCharacterConfig(){
        return this.mainCharacterConfig;
    }

}
