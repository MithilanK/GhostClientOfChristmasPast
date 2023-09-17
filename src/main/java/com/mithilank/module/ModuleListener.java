package com.mithilank.module;

import com.mithilank.main.Main;
import com.mithilank.module.modules.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ModuleListener {
    final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onClick(InputEvent.MouseInputEvent e) {
        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }
        else {
            if (Mouse.isCreated()) {
                for (Module m : ModuleManager.activeModules) {
                    if (m.getState()) {
                        m.onClick(e);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }
        else {
            if (Keyboard.isCreated()) {
                for (Module m : ModuleManager.activeModules) {
                        m.onKey(e);
                        m.movekey();
                        if (m.getBind() != 500) {
                            if (Keyboard.getEventKey() == m.getBind()) {
                                if (Keyboard.getEventKeyState()) {
                                    m.toggleModule();
                                }
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_RSHIFT) {
                            if (Keyboard.getEventKeyState()){
                                mc.displayGuiScreen(Main.gui);
                            }
                        }

                }
            }
        }
    }

    @SubscribeEvent
    public void render(TickEvent.RenderTickEvent e) {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return;
        }
        for (Module m : ModuleManager.activeModules) {
            if (m.getState()) {
                m.onRender();
            }
        }

    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent e) {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return;
        }
        if(ClickGui.show) {
            if (!ClickGui.rendering) {
                mc.displayGuiScreen(Main.gui);
                ClickGui.rendering = true;
            }
        }
        Module.selectedentities = mc.theWorld.loadedEntityList;
        for (Module m : ModuleManager.activeModules) {
            if (m.getState()) {
                m.onEntityRoundup();
            }
        }
        for (Module m : ModuleManager.activeModules) {
            if (m.getState()) {
                m.onTick();
                m.movetick();
            }
        }
    }


    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post e){
        if (mc.theWorld == null || mc.thePlayer == null) {
            return;
        }
        for (Module m : ModuleManager.activeModules) {
            if (m.getState()) {
                if (e.type == RenderGameOverlayEvent.ElementType.TEXT) {
                    m.onTextRender(e);
                }
            }
        }

    }

    @SubscribeEvent
    public void onworldrender(RenderWorldLastEvent ev) {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return;
        }
        for (Module m : ModuleManager.activeModules) {
            if (m.getState()) {
                m.on3drender(ev);
            }
        }
    }


}
