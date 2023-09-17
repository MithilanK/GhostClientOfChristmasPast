package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cps extends Module {
    public static final String name = "Cps";
    public static final int bind  = 500;
    public static final Category category = Category.GUI;
    public ArrayList<Click> leftclicks = new ArrayList<Click>();
    public ArrayList<Click> rightclicks = new ArrayList<Click>();


    public Cps() {
        super(name, bind, category);
    }

    public void onClick(InputEvent.MouseInputEvent e) {
        if (Mouse.getEventButtonState()){
            if (Mouse.getEventButton() == 0) {
                new Click(0);
            }
            if (Mouse.getEventButton() == 1) {
                new Click(1);
            }
        }
    }

    @Override
    public void onRender() {
        int rcps = rightclicks.size();
        int lcps = leftclicks.size();
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow("["+ String.valueOf(lcps) + " | " + String.valueOf(rcps)  + "]", offsetx, offsety, Color.WHITE.getRGB());
    }


    public void onTick() {
        ArrayList<Click> removeclicks = new ArrayList<Click>();
        for (Click c : leftclicks) {
            long mintime = System.currentTimeMillis() - 1000;
            if (c.time < mintime) {
                removeclicks.add(c);
            }
        }
        leftclicks.removeAll(removeclicks);
        removeclicks = new ArrayList<Click>();
        for (Click c : rightclicks) {
            long mintime = System.currentTimeMillis() - 1000;
            if (c.time < mintime) {
                removeclicks.add(c);
            }
        }
        }





    public class Click {
        public long time;
        public int button;
        public Click(int butcode) {
            button = butcode;
            time = System.currentTimeMillis();
            if (butcode == 0) {
                leftclicks.add(this);
            }
            else if (butcode == 1) {
                rightclicks.add(this);
            }
        }
    }
}
