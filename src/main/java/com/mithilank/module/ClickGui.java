package com.mithilank.module;

import com.mithilank.main.Main;
import com.mithilank.module.gui.CategoryGui;
import com.mithilank.util.ColorUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.HashMap;

public class ClickGui extends GuiScreen {
    public static boolean show = false;
    public static boolean rendering = false;

    HashMap<Category, GuiButton> Categorybuttons;

    HashMap<Integer, CategoryGui> CategoryMenus;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        Main.gui.drawCenteredString(Main.gui.mc.fontRendererObj, "The Ghost Client of Chirstmas Yet to Come", this.width / 2, this.height / 2 + -95 - Main.gui.mc.fontRendererObj.FONT_HEIGHT - 2, ColorUtil.getRainbow());
        Main.gui.drawCenteredString(Main.gui.mc.fontRendererObj, "Select a Category", this.width / 2, this.height / 2 + -95, ColorUtil.getRainbow());

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
    protected void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
    }

    @Override
    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException{
        if (button.id < 6) {
            mc.displayGuiScreen(CategoryMenus.get(button.id));
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        allowUserInput = true;
        Categorybuttons = new HashMap<Category, GuiButton>();
        CategoryMenus = new HashMap<Integer, CategoryGui>();

        int y = -84;
        int id = 0;

        for (Category c: Category.values()) {
            Categorybuttons.put(c, new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, StringUtils.capitalize(c.name().toLowerCase())));
            CategoryMenus.put(id, new CategoryGui(c));
            y += 22;
            id++;
        }

        this.buttonList.addAll(Categorybuttons.values());
    }

    @Override
    public void onGuiClosed() {

        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
