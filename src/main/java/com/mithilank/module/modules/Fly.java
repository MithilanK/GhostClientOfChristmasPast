package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public static final String name = "Fly";
    public static final int bind  = Keyboard.KEY_G;
    public static final Category category = Category.MOVEMENT;

    public Fly () {
        super(name, bind, category);
    }

    public void onEnable() {
        Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = true;
        Minecraft.getMinecraft().thePlayer.capabilities.isFlying = true;
    }

    public void onDisable() {

        Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = false;
        Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;

    }
}
