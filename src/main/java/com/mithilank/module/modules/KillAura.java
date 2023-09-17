package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.settings.SettingInt;
import com.mithilank.util.ColorUtil;
import com.mithilank.util.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.input.Keyboard;

public class KillAura extends Module {
    public static final String name = "KillAura";
    public static final int bind = Keyboard.KEY_X;
    public static final Category category = Category.COMBAT;


    private EntityLivingBase target;
    private long current, last;
    private float yaw, pitch;
    private Boolean others;


    public KillAura() {
        super(name, bind, category);
        settings.put("reach", new SettingInt(3.78, 0, 10, "reach"));
    }

    @Override
    public void onTick() {
        for (Entity e: Module.selectedentities) {
            if (e instanceof EntityLivingBase) {
                if (allowedToAttack(e)) {
                    target = (EntityLivingBase) e;
                    final float[] rotation = ExecuteRotation(target);
                    mc.thePlayer.rotationYaw = rotation[0];
                    mc.thePlayer.rotationPitch = rotation[1];
                    doattack();
                }
            }

        }

    }


    public float[] ExecuteRotation(Entity e) {
        double d0 = e.posX - mc.thePlayer.posX;
        double d1 = (e.posY + 1) - ((mc.thePlayer.posY) + (double) mc.thePlayer.eyeHeight);
        double d2 = e.posZ - mc.thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1);
        float f = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * 180.0D / Math.PI));
        return new float[]{f, f1};

    }

    private void doattack() {
        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, target);
    }

    public boolean allowedToAttack(Entity e) {
        SettingInt reachset = (SettingInt) settings.get("reach");
        double reach = reachset.value;
        return e instanceof EntityLivingBase && e != mc.thePlayer && mc.thePlayer.getDistanceToEntity(e) <= reach;
    }
}
