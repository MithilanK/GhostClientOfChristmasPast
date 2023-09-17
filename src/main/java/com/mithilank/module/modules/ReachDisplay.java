package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class ReachDisplay extends Module {
    public static final String name = "ReachDisplay";
    public static final int bind = 500;
    public static final Category category = Category.GUI;
    float distance = 0;


    public ReachDisplay() {
        super(name, bind, category);

        offsety = mc.fontRendererObj.FONT_HEIGHT *2;
    }

    @Override
    public void onRender() {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow("[" + String.valueOf(distance) + " Reach]", offsetx, offsety, Color.WHITE.getRGB());
    }

    @SubscribeEvent
    public void hurtevent(LivingHurtEvent e) {
        if (e.source.equals(DamageSource.causePlayerDamage(mc.thePlayer))) {
            distance = e.entityLiving.getDistanceToEntity(mc.thePlayer);

        }
    }

}
