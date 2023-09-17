package com.mithilank.module.settings;

import com.mithilank.util.ColorUtil;

import java.awt.*;

public class SettingColor extends Setting {
    public int value = Color.WHITE.getRGB();
    public boolean chroma = false;

    public SettingColor(String name) {
        this.name = name;
    }

    public int getColor() {
        if (chroma) {
            return ColorUtil.getRainbow();
        }
        else {
            return value;
        }
    }
    public String getName() {
        String name = "";
        if (value == Color.white.getRGB()) {
            name = "White";
        }
        else if (value == Color.red.getRGB()) {
            name = "Red";
        }
        else if (value == Color.orange.getRGB()) {
           name = "Orange";
        }
        else if (value == Color.yellow.getRGB()) {
            name = "Yellow";
        }
        else if (value == Color.green.getRGB()) {
            name = "Green";
        }
        else if (value == Color.blue.getRGB()) {
            name = "Blue";
        }
        else if (value == Color.pink.getRGB()) {
            name = "Pink";
        }
        else if (value == Color.CYAN.getRGB()) {
            name = "Cyan";
        }
        else if (chroma) {
           name = "Chroma";
        }
        return name;
    }

    public void increment() {
        if (value == Color.white.getRGB()) {
            value = Color.red.getRGB();
        }
        else if (value == Color.red.getRGB()) {
            value = Color.orange.getRGB();
        }
        else if (value == Color.orange.getRGB()) {
            value = Color.yellow.getRGB();
        }
        else if (value == Color.yellow.getRGB()) {
            value = Color.green.getRGB();
        }
        else if (value == Color.green.getRGB()) {
            value = Color.blue.getRGB();
        }
        else if (value == Color.blue.getRGB()) {
            value = Color.pink.getRGB();
        }
        else if (value == Color.pink.getRGB()) {
            value = Color.CYAN.getRGB();
        }
        else if (value == Color.CYAN.getRGB()) {
            chroma = true;
        }
        else if (chroma) {
            value = Color.white.getRGB();
            chroma = false;
        }
    }


}
