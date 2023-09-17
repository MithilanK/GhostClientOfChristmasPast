package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class NoFall extends Module {
    public static final String name = "NoFall";
    public static final int bind = Keyboard.KEY_H;
    public static final Category category = Category.PLAYER;

    public NoFall() {
        super(name, bind, category);
    }


    public void onEnable() {

    }

    public void onUpdate() {
        if (mc.thePlayer.fallDistance > 2) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }

    public void onDisable() {

    }


}
