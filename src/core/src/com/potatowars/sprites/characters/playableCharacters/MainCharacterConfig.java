package com.potatowars.sprites.characters.playableCharacters;

import com.badlogic.gdx.utils.Array;
import com.potatowars.hud.inventory.inventoryItem.InventoryItem;

public class MainCharacterConfig {

    private Array<InventoryItem.ItemTypeID> inventory;

    public Array<InventoryItem.ItemTypeID> getInventory() {
        return inventory;
    }

    public void setInventory(Array<InventoryItem.ItemTypeID> inventory) {
        this.inventory = inventory;
    }
}
