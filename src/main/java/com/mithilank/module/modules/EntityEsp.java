package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.module.settings.SettingEnum;
import com.mithilank.util.ColorUtil;
import com.mithilank.util.RenderUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import java.util.ArrayList;

public class EntityEsp extends Module {
    public static final String name = "EntityEsp";
    public static final int bind = 500;
    public static final Category category = Category.RENDER;

    public EntityEsp() {
        super(name, bind, category);
        ArrayList<String> poop = new ArrayList<String>();
        poop.add("Entities");
        poop.add("Player");
        poop.add("Mob");
        settings.put("SE" , new SettingEnum(poop));
    }

    public void onEnable() {

    }

    @Override
    public void on3drender(RenderWorldLastEvent e) {
        mc.gameSettings.viewBobbing = false;
        for (Entity entity : Module.selectedentities) {
            if (entity.getDistanceToEntity(mc.thePlayer) < 250) {
                if (entity instanceof EntityLivingBase) {
                    if (((SettingEnum) settings.get("SE")).mode.equals("Entities")) {
                        RenderUtil.drawline(entity, ColorUtil.getRainbow(), 1, e);
                    }
                    if (((SettingEnum) settings.get("SE")).mode.equals("Player")) {
                        if (entity instanceof EntityPlayer) {
                            RenderUtil.drawline(entity, ColorUtil.getRainbow(), 1, e);
                        }
                    }
                    if (((SettingEnum) settings.get("SE")).mode.equals("Mob")) {
                        if (!(entity instanceof EntityPlayer) ){
                            RenderUtil.drawline(entity, ColorUtil.getRainbow(), 1, e);
                        }
                    }
                }
            }

        }
    }
}
