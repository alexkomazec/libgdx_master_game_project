package com.potatowars.hud.inventory;

public interface InventorySubject {
    public void addObserver(InventoryObserver inventoryObserver);
    public void removeObserver(InventoryObserver inventoryObserver);
    public void removeAllObservers();
    public void notify(final String value, InventoryObserver.InventoryEvent event);
}