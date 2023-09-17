package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.settings.SettingBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class SpeedGUI extends Module {
    public static final String name = "SpeedGUI";
    public static final int bind = 500;
    public static final Category category = Category.GUI;


    public SpeedGUI() {
        super(name, bind, category);
    }

    @Override
    public void onRender() {
        FontRenderer fr = mc.fontRendererObj;
        EntityPlayerSP entity = mc.thePlayer;
        double vel = entity.getDistance(entity.prevPosX, entity.prevPosY, entity.prevPosZ) * 20;
        String strvel = String.valueOf(vel);
        String[] temp = strvel.split("");
        strvel = temp[0] + temp[1] + temp[2];
        fr.drawStringWithShadow("[" + strvel + " blocks/sec]", offsetx, offsety, Color.WHITE.getRGB());
    }
}
