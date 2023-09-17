package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import org.lwjgl.input.Keyboard;

public class FullBright extends Module {
    public static final String name = "FullBright";
    public static final int bind = Keyboard.KEY_J;
    public static final Category category = Category.RENDER;
    public float oldgamma;

    public FullBright() {
        super(name, bind, category);
    }

    public void onEnable() {
        oldgamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 100;
    }

    public void onDisable () {
        mc.gameSettings.gammaSetting = oldgamma;
    }
}
