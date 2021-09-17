package com.potatowars.hud.status.StatusInterfaces;

import com.potatowars.hud.status.StatsPacket;

public interface StatsSubject {

    public void addObserver(StatsObserver statsObserver);
    public void removeObserver(StatsObserver statsObserver);
    public void removeAllObservers();
    public void notify(StatsPacket statusPacket, StatsObserver.ComponentType component);

}
