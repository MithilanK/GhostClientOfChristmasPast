package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.util.ColorUtil;
import com.mithilank.util.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import java.awt.*;

public class Hitbox extends Module {
    public static String name = "Hitbox";
    public static int bind = 500;
    public static Category category = Category.RENDER;

    public Hitbox() {
        super(name, bind, category);
    }
    public void onEnable() {

    }

    @Override
    public void on3drender(RenderWorldLastEvent e) {
        for (Entity entity : Module.selectedentities) {
            if (entity.getDistanceToEntity(mc.thePlayer) < 250) {
                if (entity instanceof EntityLivingBase) {
                    RenderUtil.drawHitbox(entity, Color.WHITE, e);
                }
            }

        }
    }
}
