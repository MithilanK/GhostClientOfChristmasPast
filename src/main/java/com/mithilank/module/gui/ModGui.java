package com.mithilank.module.gui;

import com.mithilank.main.Main;
import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.module.settings.Setting;
import com.mithilank.module.settings.SettingBoolean;
import com.mithilank.module.settings.SettingColor;
import com.mithilank.module.settings.SettingInt;
import com.mithilank.util.ColorUtil;
import com.mithilank.util.ModUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class ModGui extends GuiScreen {

    public static boolean show = false;
    public static boolean rendering = false;

    HashMap<Setting, GuiButton> Setbuttons;
    GuiButton backbtn;
    GuiButton enablebtn;
    GuiButton bindbtn;
    boolean bindmode = false;


    Module mod;

    public ModGui(Module m) {
        mod = m;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        Main.gui.drawCenteredString(Main.gui.mc.fontRendererObj, StringUtils.capitalize(mod.getName().toLowerCase()), this.width / 2, this.height / 2 + -95, ColorUtil.getRainbow());

    }
    @Override
    public void initGui() {
        Setbuttons = new HashMap<Setting, GuiButton>();
        this.buttonList.clear();
        int y = -84;
        int id = 0;
        enablebtn = new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, mod.getState() ? "§aEnabled" : "§cDisabled");
        id++;
        y += 22;
        bindbtn = new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, "Bind: " + ModUtils.getBindString(mod));
        y += 22;
        id++;
        for (String s : mod.settings.keySet()) {
            Setting set = mod.settings.get(s);
            if (set instanceof SettingInt) {
                Setbuttons.put(set, new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, StringUtils.capitalize(s) + ": " + ((SettingInt) set).value));
            }
            else if (set instanceof SettingBoolean) {
                Setbuttons.put(set, new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, ((SettingBoolean) set).enabled ? "§a" + set.name : "§c" + set.name));
            }
            else if (set instanceof SettingColor) {
                Setbuttons.put(set, new GuiButton(id, this.width / 2 - 70, this.height / 2 + y, 140, 20, ((SettingColor)set).getName()));
            }
            y+= 22;
            id++;
        }
        backbtn = new GuiButton(500, this.width / 2 - 70, this.height / 2 + y, 140, 20, "Back");
        this.buttonList.addAll(Setbuttons.values());
        this.buttonList.add(backbtn);
        this.buttonList.add(enablebtn);
        this.buttonList.add(bindbtn);
    }

    @Override
    protected void actionPerformed(GuiButton btn) throws IOException {
        if (!bindmode) {
            if (btn.id == backbtn.id) {
                mc.displayGuiScreen(new CategoryGui(mod.getCategory()));
            }
            if (btn.id == enablebtn.id) {
                mod.toggleModule();
                btn.displayString = mod.getState() ? "§aEnabled" : "§cDisabled";

            }
            if (btn.id == bindbtn.id) {
                bindmode = true;
                bindbtn.displayString = "Press Anything";
            }
            for (Setting set : Setbuttons.keySet()) {
                GuiButton button = Setbuttons.get(set);
                if (button.id == btn.id) {
                    if (set instanceof SettingInt) {
                        ((SettingInt) set).increment();
                        button.displayString = StringUtils.capitalize(set.name) + ": " + ((SettingInt) set).value;
                    }if (set instanceof SettingBoolean) {
                        ((SettingBoolean) set).toggle();
                        button.displayString = ((SettingBoolean) set).enabled ? "§a" + set.name : "§c" + set.name;
                    }
                    if (set instanceof SettingColor) {
                        ((SettingColor) set).increment();
                        button.displayString = ((SettingColor) set).getName();
                    }
                }

            }
        }

    }

    @Override
    protected void keyTyped(char let, int key) throws IOException {
        super.keyTyped(let, key);
        if (bindmode) {
            bindmode = false;
            mod.setBind(key);
            bindbtn.displayString = "Bind:  " + let;
        }

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
