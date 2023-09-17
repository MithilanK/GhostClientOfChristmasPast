package com.mithilank.util;

import com.mithilank.module.Module;
import org.lwjgl.input.Keyboard;

public class ModUtils {
    public static String getBindString(Module m ) {
        String key = "";
        if (m.getBind() != 500) {
            key = Keyboard.getKeyName(m.getBind());
        }
        else {
            key = "none";
        }
        return key;
    }
}
