package com.mithilank.time;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.sql.Time;

public class TimeManager {
    public static TimeManager INSTANCE;

    public TimeManager() {

    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent e) {
    }



    public static void setup() {
        INSTANCE = new TimeManager();

    }
}
