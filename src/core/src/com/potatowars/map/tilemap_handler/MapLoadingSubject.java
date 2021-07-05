package com.potatowars.map.tilemap_handler;

import com.potatowars.map.MapLoadingObserver;

public interface MapLoadingSubject {

    public void addObserver(MapLoadingObserver mapLoadingObserver);
    public void removeObserver(MapLoadingObserver mapLoadingObserver);
    public void removeAllObservers();
    public void notifyAllMembers();
}
