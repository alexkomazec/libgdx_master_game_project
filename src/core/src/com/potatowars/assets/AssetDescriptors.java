package com.potatowars.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {


    /**************************Texture atlasses**************************/
    public static final AssetDescriptor<TextureAtlas> BACK_GROUND =
            new AssetDescriptor<TextureAtlas>(AssetPaths.BACK_GROUND, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> HERO_FRAMES =
            new AssetDescriptor<TextureAtlas>(AssetPaths.HERO_FRAME_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> WARRIOR_FRAMES =
            new AssetDescriptor<TextureAtlas>(AssetPaths.WARRIOR_FRAME_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> HUNTER_FRAMES =
            new AssetDescriptor<TextureAtlas>(AssetPaths.HUNTER_FRAME_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> MAGE_FRAMES =
            new AssetDescriptor<TextureAtlas>(AssetPaths.MAGE_FRAME_ATLAS, TextureAtlas.class);

    /**************************Skins**************************/
    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetPaths.UI_SKIN, Skin.class);

    /**************************Fonts**************************/
    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT, BitmapFont.class);


    /**************************Music**************************/
    public static final AssetDescriptor<Music> BACKGROUND_MUSIC =
            new AssetDescriptor<Music>(AssetPaths.BACKGROUND_MUSIC, Music.class);


    /**************************Sound**************************/
    public static final AssetDescriptor<Sound> CLICK_SOUND=
            new AssetDescriptor<Sound>(AssetPaths.CLICK_BUTTON, Sound.class);


    private AssetDescriptors(){

    }
}
