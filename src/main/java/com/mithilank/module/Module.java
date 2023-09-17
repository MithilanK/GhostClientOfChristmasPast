package com.mithilank.module;

import com.mithilank.module.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Module {


    public boolean moving = false;
    public boolean movingleft = false;
    public boolean movingright = false;
    public boolean movingup = false;
    public boolean movingdown = false;
    public boolean pause = false;


    private String name;

    public void setBind(int bind) {
        this.bind = bind;
    }

    private int bind;
    private Category category;
    private boolean isEnabled;
    public int offsetx = 0;
    public int offsety = 0;
    public HashMap<String, Setting> settings = new HashMap<String, Setting>();


    public static Minecraft mc = Minecraft.getMinecraft();


    public static List<Entity> selectedentities;


    public Module(String name, int bind, Category category) {
        this.name = name;
        this.bind = bind;
        this.category = category;
        MinecraftForge.EVENT_BUS.register(this);
    }


    public String getName() {
        return name;
    }

    public void onClick(InputEvent.MouseInputEvent e) {

    }

    public int getBind() {
        return bind;
    }

    public Category getCategory() {
        return category;
    }

    public boolean getState() {
        return isEnabled;
    }

    public void setState(boolean state) {
        this.onToggle();
        if (state) {
            this.onEnable();
            this.isEnabled = true;
        } else {
            this.onDisable();
            this.isEnabled = false;
        }
    }

    public void toggleModule() {
        this.setState(!this.getState());
    }

    public void onToggle() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onRender() {
    }


    public final boolean isCategory(Category s) {
        if (s == category)
            return true;
        return false;
    }

    public void onCommand(String[] args) {
        if (args[1].equalsIgnoreCase("move")) {
            if (getState()) {
                moving = !moving;
                if (!moving) {
                    movingleft = false;
                    movingright = false;
                    movingup = false;
                    movingdown = false;
                }
                pause = true;

            }
        }
    }

    public void onTick() {

    }

    public void movetick() {
        if (movingup) {
            offsety -=1;
        }
        if (movingdown) {
            offsety +=1;
        }
        if (movingright) {
            offsetx +=1;
        }
        if (movingleft) {
            offsetx -=1;
        }
    }

    public void movekey() {
        if (moving) {
            if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                movingup = !movingup;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                movingdown = !movingdown;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                movingright = !movingright;

            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                movingleft = !movingleft;

            }
            if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
                if (!pause) {
                    if (moving = !moving) {

                    } else {
                        movingleft = false;
                        movingright = false;
                        movingup = false;
                        movingdown = false;
                    }
                }
                pause = false;

            }
        }
    }

    public void onTextRender(RenderGameOverlayEvent.Post e) {

    }


    public void onEntityRoundup() {

    }

    public void onKey(InputEvent.KeyInputEvent e) {

    }

    public void on3drender(RenderWorldLastEvent e) {
    }
}