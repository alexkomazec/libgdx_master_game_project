package com.potatowars.sprites.Animation;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasPack {

    private TextureAtlas textureAtlas;
    private String       textureAtlasName;

    TextureAtlasPack(TextureAtlas textureAtlas, String textureAtlasName){
        this.textureAtlas = textureAtlas;
        this.textureAtlasName = textureAtlasName;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public String getTextureAtlasName() {
        return textureAtlasName;
    }

    public void setTextureAtlasName(String textureAtlasName) {
        this.textureAtlasName = textureAtlasName;
    }
}
