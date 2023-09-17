package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;

public class TntTimer extends Module {
    public static final String name = "TntTimer";
    public static final int bind = 500;
    public static final Category category =  Category.PLAYER;


    public TntTimer() {
        super(name, bind, category);
    }

    @Override
    public void onRender() {
        for (Entity e : Module.selectedentities) {
            if (e instanceof EntityTNTPrimed) {
                EntityTNTPrimed tnt = (EntityTNTPrimed) e;
                tnt.setCustomNameTag(String.valueOf(tnt.ticksExisted / 20));
            }
        }
    }
}
