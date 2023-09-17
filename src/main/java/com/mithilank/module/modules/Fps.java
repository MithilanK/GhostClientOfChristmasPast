package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

public class Fps extends Module {
    public static final String name = "Fps";
    public static final int bind = 500;
    public static final Category category = Category.GUI;


    public Fps() {
        super(name, bind, category);

        offsety = mc.fontRendererObj.FONT_HEIGHT *2;
    }

    @Override
    public void onRender() {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow("[" + String.valueOf(Minecraft.getDebugFPS()) + " FPS]", offsetx, offsety, Color.WHITE.getRGB());
    }

}
