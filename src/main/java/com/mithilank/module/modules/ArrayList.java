package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.util.ColorUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.input.Keyboard;

public class ArrayList extends Module {
    public static final String name = "ArrayList";
    public static final int bind = 500;
    public static final Category category = Category.RENDER;
    public ArrayList() {
        super(name, bind, category);
    }



    public void onTextRender(RenderGameOverlayEvent.Post e) {
        ScaledResolution sr = new ScaledResolution(mc);;
        int y = 1;
        for (Module m : ModuleManager.activeModules) {
            if (m.getState() && m != this) {
                mc.fontRendererObj.drawStringWithShadow(m.getName(), sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getName()), y, ColorUtil.getRainbow(((y-2) / 9) * 50));
                y+= 9;
            }
        }
    }

}
