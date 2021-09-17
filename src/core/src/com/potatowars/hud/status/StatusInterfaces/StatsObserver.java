package com.potatowars.hud.status.StatusInterfaces;

import com.potatowars.hud.status.StatsPacket;

public interface StatsObserver {

    enum ComponentType {
        DEFAULT,
        LEVEL_UP_SYSTEM_COMPONENT,
        BOX_2D_WORLD_COMPONENT,
        INVENTORY_COMPONENT,
        BATTLE_SYSTEM_COMPONENT,
    }

    void onNotify(StatsPacket statusPacket, ComponentType component);
    void onNotify(StatsPacket statusPacket);

}
