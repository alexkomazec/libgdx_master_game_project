package com.potatowars.hud.inventory.inventorySlot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Scaling;
import com.potatowars.hud.inventory.inventoryItem.InventoryItem;
import com.potatowars.hud.inventory.inventoryItem.InventoryItemJSON;
import com.potatowars.util.Utility;

import java.util.ArrayList;
import java.util.Hashtable;

public class InventoryItemFactory {
    private Json json = new Json();
    private final String INVENTORY_ITEM = "scripts/inventory_items.json";
    private static InventoryItemFactory instance = null;
    private Hashtable<InventoryItem.ItemTypeID,InventoryItem> inventoryItemList;

    public static InventoryItemFactory getInstance() {
        if (instance == null) {
            instance = new InventoryItemFactory();
        }
        return instance;
    }

    private InventoryItemFactory(){
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(INVENTORY_ITEM));
        inventoryItemList = new Hashtable<InventoryItem.ItemTypeID, InventoryItem>();

        for (JsonValue jsonVal : list) {
            InventoryItemJSON inventoryItemJSON = json.readValue(InventoryItemJSON.class, jsonVal);
            InventoryItem inventoryItem = new InventoryItem(inventoryItemJSON);
            inventoryItemList.put(inventoryItem.getItemTypeID(), inventoryItem);
        }
    }

    public InventoryItem getInventoryItem(InventoryItem.ItemTypeID inventoryItemType){
        InventoryItem item = new InventoryItem(inventoryItemList.get(inventoryItemType));
        item.setDrawable(new TextureRegionDrawable(Utility.ITEMS_TEXTUREATLAS.findRegion(item.getItemTypeID().toString())));
        item.setScaling(Scaling.none);
        return item;
    }

    /*
    public void testAllItemLoad(){
        for(ItemTypeID itemTypeID : ItemTypeID.values()) {
            InventoryItem item = new InventoryItem(_inventoryItemList.get(itemTypeID));
            item.setDrawable(new TextureRegionDrawable(PlayerHUD.itemsTextureAtlas.findRegion(item.getItemTypeID().toString())));
            item.setScaling(Scaling.none);
        }
    }*/
}
