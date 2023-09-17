package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

public class AntiBot extends Module {
    public static final String name = "AntiBot";
    public static final int bind = 500;
    public static final Category category = Category.WORLD;


    public AntiBot() {
        super(name, bind, category);
    }

    @Override
    public void onEntityRoundup() {
        for (Entity e : Module.selectedentities) {
            if (e.isInvisible() && e != mc.thePlayer) {
                Module.selectedentities.remove(e);
            }
        }
    }
}
