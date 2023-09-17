package com.mithilank.module.modules;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.settings.SettingInt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class Reach extends Module {
    public static String name = "Reach";
    public static int bind = 500;
    public static Category category = Category.COMBAT;
    Entity pointedEntity;

    public Reach() {
        super(name, bind, category);
        settings.put("Reach", new SettingInt(3.67, 3, 4, "Reach"));
    }

    public void onClick(InputEvent.MouseInputEvent e) {
        if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
            call();
        }
    }


    public boolean call() {

        if ((double) mc.thePlayer.moveForward == 0.0D && (double) mc.thePlayer.moveStrafing == 0.0D) {
            return false;
        } else if (!mc.thePlayer.isSprinting()) {
            return false;
        } else {
            if (mc.objectMouseOver != null) {
                BlockPos p = mc.objectMouseOver.getBlockPos();
                if (p != null && mc.theWorld.getBlockState(p).getBlock() != Blocks.air) {
                    return false;
                }
            }
            double r = mmVal(( (SettingInt)this.settings.get("Reach")).value - .2, ( (SettingInt)this.settings.get("Reach")).value + .2, new Random());
            Object[] o = zz(r, 0.0D);
            if (o == null) {
                return false;
            } else {
                System.out.println("Fuck david");
                Entity en = (Entity) o[0];
                mc.objectMouseOver = new MovingObjectPosition(en, (Vec3) o[1]);
                mc.pointedEntity = en;
                return true;
            }
        }
    }

    private static Object[] zz(double zzD, double zzE) {

        Entity zz2 = mc.getRenderViewEntity();
        Entity entity = null;
        if (zz2 == null) {
            return null;
        } else {
            mc.mcProfiler.startSection("pick");
            Vec3 zz3 = zz2.getPositionEyes(1.0F);
            Vec3 zz4 = zz2.getLook(1.0F);
            Vec3 zz5 = zz3.addVector(zz4.xCoord * zzD, zz4.yCoord * zzD, zz4.zCoord * zzD);
            Vec3 zz6 = null;
            List zz8 = mc.theWorld.getEntitiesWithinAABBExcludingEntity(zz2, zz2.getEntityBoundingBox().addCoord(zz4.xCoord * zzD, zz4.yCoord * zzD, zz4.zCoord * zzD).expand(1.0D, 1.0D, 1.0D));
            double zz9 = zzD;

            for(int zz10 = 0; zz10 < zz8.size(); ++zz10) {
                Entity zz11 = (Entity)zz8.get(zz10);
                if (zz11.canBeCollidedWith()) {
                    float ex = (float)((double)zz11.getCollisionBorderSize());
                    AxisAlignedBB zz13 = zz11.getEntityBoundingBox().expand((double)ex, (double)ex, (double)ex);
                    zz13 = zz13.expand(zzE, zzE, zzE);
                    MovingObjectPosition zz14 = zz13.calculateIntercept(zz3, zz5);
                    if (zz13.isVecInside(zz3)) {
                        if (0.0D < zz9 || zz9 == 0.0D) {
                            entity = zz11;
                            zz6 = zz14 == null ? zz3 : zz14.hitVec;
                            zz9 = 0.0D;
                        }
                    } else if (zz14 != null) {
                        double zz15 = zz3.distanceTo(zz14.hitVec);
                        if (zz15 < zz9 || zz9 == 0.0D) {
                            if (zz11 == zz2.ridingEntity) {
                                if (zz9 == 0.0D) {
                                    entity = zz11;
                                    zz6 = zz14.hitVec;
                                }
                            } else {
                                entity = zz11;
                                zz6 = zz14.hitVec;
                                zz9 = zz15;
                            }
                        }
                    }
                }
            }

            if (zz9 < zzD && !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItemFrame)) {
                entity = null;
            }

            mc.mcProfiler.endSection();
            if (entity != null && zz6 != null) {
                return new Object[]{entity, zz6};
            } else {
                return null;
            }
        }
    }

    public static double mmVal(double a, double b, Random r) {
        return a == b ? a : a + r.nextDouble() * (b - a);
    }
}