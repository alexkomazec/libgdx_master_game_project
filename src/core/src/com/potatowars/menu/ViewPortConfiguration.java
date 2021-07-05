package com.potatowars.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewPortConfiguration {

    private static final String CLASS_NAME = Viewport.class.getSimpleName();

    //Viewport measures => Visible area
    public static  float viewportWidth;
    public static  float viewportHeight;
    
    //Virtul Measures => Measure
    public static float virtualWidth;
    public static float virtualHeight;
    
    //Physical Measures
    public static float physicalWidth;
    public static float physicalHeight;
    
    //Aspect ratio represents division of virtualWidth and virtualHeight
    public static float aspectRatio;

    //Set
    public static void calculateViewport(int worldWidth, int worldHeight){

        //Make the viewport a percentage of the total display area
        virtualWidth = worldWidth;
        virtualHeight = worldHeight;

        //Current viewport dimensions
        viewportWidth = virtualWidth;
        viewportHeight = virtualHeight;

        //pixel dimensions of display
        physicalWidth = Gdx.graphics.getWidth();
        physicalHeight = Gdx.graphics.getHeight();

        //aspect ratio for current viewport
        aspectRatio = (virtualWidth / virtualHeight);

        //update viewport if there could be skewing
        if( physicalWidth / physicalHeight >= aspectRatio){
            //Letterbox left and right
            viewportWidth = viewportHeight * (physicalWidth/physicalHeight);
            viewportHeight = virtualHeight;
            Gdx.app.debug(CLASS_NAME, "WorldRenderer: Physical Mesure ratio is bigger than aspect ratio" );
        }else{
            //letterbox above and below
            viewportWidth = virtualWidth;
            viewportHeight = viewportWidth * (physicalHeight/physicalWidth);
            Gdx.app.debug(CLASS_NAME, "WorldRenderer: Physical Mesure ratio is smaller than aspect ratio" );
        }

        //Display information
        Gdx.app.debug(CLASS_NAME, " Virtual measure: (" + virtualWidth + "," + virtualHeight + ")" );
        Gdx.app.debug(CLASS_NAME, " Viewport measure: (" + viewportWidth + "," + viewportHeight + ")" );
        Gdx.app.debug(CLASS_NAME, " Physical measure: (" + physicalWidth + "," + physicalHeight + ")" );
    }

    private boolean checkBoundariesCollision(float viewPortWidth, float viewPortHeight){

        /*float x_position = debugCameraController.getPosition().x;
        float y_position = debugCameraController.getPosition().y;

        //Check if the camera area is collided to the right map bound
        if( (int)(x_position + viewPortWidth/2) >= (int)map.getProperties().get("width")){
            Gdx.app.debug(TAG, "Yes!" );
        }else{

        }*/
        return true;
    }

    public static void setupPhysicalSize(){

        physicalWidth = Gdx.graphics.getWidth();
        physicalHeight = Gdx.graphics.getHeight();
    }



    public static float getPhysicalWidth(){
        return physicalWidth = Gdx.graphics.getWidth();
    }
    public static float getPhysicalHeight(){
        return physicalHeight = Gdx.graphics.getHeight();
    }
}
