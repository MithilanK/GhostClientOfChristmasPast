package com.mithilank.module.gui;

import com.mithilank.main.Main;
import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.util.ColorUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryGui extends GuiScreen {

    public static boolean show = false;
    public static boolean rendering = false;

    HashMap<Module, GuiButton> Modbuttons;
    HashMap<Integer, ModGui> ModGui;
    GuiButton backbtn;

    Category cat;

    public CategoryGui(Category c) {
        cat = c;
    }

    @Override
    public void initGui() {
        Modbuttons = new HashMap<Module, GuiButton>();
        ModGui = new HashMap<Integer, ModGui>();
        this.buttonList.clear();
        int y = -84;
        int id = 0;
        ModuleManager.getModuleListPerCategory(cat);
        for (Module m : ModuleManager.getModuleListPerCategory(cat)) {
            Modbuttons.put(m, new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, m.getName()));
            ModGui.put(id, new ModGui(m));

            id++;
            y += 22;
        }
        backbtn = new GuiButton(500, this.width / 2 - 70, this.height / 2 + y, 140, 20, "Back");
        this.buttonList.addAll(Modbuttons.values());
        this.buttonList.add(backbtn);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        Main.gui.drawCenteredString(Main.gui.mc.fontRendererObj, StringUtils.capitalize(cat.name().toLowerCase()), this.width / 2, this.height / 2 + -95, ColorUtil.getRainbow());

    }

    @Override
    protected void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        if (p_keyTyped_2_ == Keyboard.KEY_RSHIFT || p_keyTyped_2_ == Keyboard.KEY_ESCAPE) {
            show = false;
            rendering = false;
            mc.thePlayer.closeScreen();

        }
    }

    @Override
    protected void actionPerformed(GuiButton btn) throws IOException {
        if (btn.id == 500) {
            mc.displayGuiScreen(Main.gui);
        }
        if (btn.id < Modbuttons.size()) {
            mc.displayGuiScreen(ModGui.get(btn.id));
        }
    }
}
