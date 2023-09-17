package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.settings.SettingBoolean;
import com.mithilank.module.settings.SettingInt;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ToggleSprint extends Module {
    public static final String name = "Sprint";
    public static final int bind = Keyboard.KEY_LCONTROL;
    public static final Category category = Category.MOVEMENT;

    public ToggleSprint() {
        super(name, bind, category);
        this.settings.put("OmniSprint", new SettingBoolean("OmniSprint"));
        this.settings.put("Indicator", new SettingBoolean("Indicator"));
    }

    @Override
    public void onRender() {
        if (((SettingBoolean)settings.get("Indicator")).enabled) {
            FontRenderer fr = mc.fontRendererObj;
            if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking()) {
                fr.drawStringWithShadow("[Sprinting]", offsetx, offsety, Color.white.getRGB());
            } else {
                fr.drawStringWithShadow("[Not Sprinting]", offsetx, offsety, Color.white.getRGB());
            }
        }
    }

    @Override
    public void onTick() {
        if (getState()) {
            if ((mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking()) || ((SettingBoolean) settings.get("OmniSprint")).enabled){
                mc.thePlayer.setSprinting(true);}
        }
    }



    @Override
    public void onDisable() {
        mc.thePlayer.setSprinting(false);
    }


}
