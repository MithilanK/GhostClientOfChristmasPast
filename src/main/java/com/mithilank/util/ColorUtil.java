package com.mithilank.util;

import java.awt.*;

public class ColorUtil {
    public static int getRainbow() {
        float hue = (System.currentTimeMillis() % 4000) /4000f;
        return Color.HSBtoRGB(hue, 1, 1);
    }

    public static int getRainbow(int index) {
        float hue = ((System.currentTimeMillis() + index) % 4000) /4000f;
        return Color.HSBtoRGB(hue, 1, 1);
    }

    public static Color getRainbowColor() {
        float hue = (System.currentTimeMillis() % 4000) /4000f;
        return Color.getHSBColor(hue, 1, 1);
    }

    public static Color getRainbowColor(int index) {
        float hue = ((System.currentTimeMillis() + index) % 4000) /4000f;
        return Color.getHSBColor(hue, 1, 1);
    }
}
