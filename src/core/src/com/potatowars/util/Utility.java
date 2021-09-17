package com.potatowars.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Utility {

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "ui/statusui.atlas";
    public final static String STATUSUI_SKIN_PATH = "ui/statusui.json";
    private final static String ITEMS_TEXTURE_ATLAS_PATH = "skins/items.atlas";

    public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);

    public static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    public static TextureAtlas ITEMS_TEXTUREATLAS = new TextureAtlas(ITEMS_TEXTURE_ATLAS_PATH);
}
