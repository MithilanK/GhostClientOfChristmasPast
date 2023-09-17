package com.mithilank.module.modules;

import com.mithilank.main.Main;
import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.util.ColorUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TabGui extends Module {
    public static final String name = "TabGUI";
    public static final int bind = Keyboard.KEY_TAB;
    public static final Category category = Category.GUI;
    public int cat = 0;
    public int cat2 = 0;
    public boolean expanded = false;
    public Category selectedcat;
    public boolean moving = false;
    public boolean movingleft = false;
    public boolean movingright = false;
    public boolean movingup = false;
    public boolean movingdown = false;
    public boolean pause = false;



    public TabGui() {
        super(name, bind, category);
        offsetx = 5;
        offsety = 30;
    }

    public void onRender() {
        if (this.getState()) {
            int largestlen = 0;
            float hue = (System.currentTimeMillis() % 4000) /4000f;
            int color = Color.HSBtoRGB(hue, 1, 1);
            FontRenderer fr = mc.fontRendererObj;

            Gui.drawRect(offsetx, offsety, offsetx + 75, offsety + Category.values().length * 16 + 2, 0x90000000);
            Gui.drawRect( offsetx + 1, offsety + 3 + cat * 16 + 1, offsetx + 75, offsety + 3 + cat * 16 + 12, color);

            int count = 0;

            for (Category c: Category.values()) {
                if (c == Category.values()[cat]) {
                    fr.drawStringWithShadow("§f" + c.name(), offsetx + 5, offsety + 6+ (count * 16), -1);
                }
                else {
                    fr.drawStringWithShadow("§7" + c.name(), offsetx + 5, offsety + 6 + (count * 16), -1);
                }
                count++;
            }

            if (expanded) {
                count = 0;
                largestlen = fr.getStringWidth(ModuleManager.getModuleListPerCategory(selectedcat).get(0).getName());
                for (Module m : ModuleManager.getModuleListPerCategory(selectedcat)) {
                    if (largestlen < fr.getStringWidth(m.getName())) {
                        largestlen = fr.getStringWidth(m.getName());
                    }
                }
                for (Module m : ModuleManager.getModuleListPerCategory(selectedcat)) {
                    Gui.drawRect(offsetx + 75, offsety + (count * 16), offsetx + 75 + 8 + largestlen + 5 + 5, offsety + 16 + (count * 16), 0x90000000);
                    if (count == cat2) {
                        Gui.drawRect(offsetx + 75 + 1, offsety + 3 + cat2 * 16 + 1, offsetx + 75 +8  + largestlen + 5 + 5, offsety + 3 + cat2 * 16 + 12, color);
                    }
                    count += 1;
                }

                count = 0;

                for (Module c : ModuleManager.getModuleListPerCategory(selectedcat)) {
                    if (c.getState()) {
                        fr.drawStringWithShadow("§f" + c.getName(),  75 + offsetx + 5, offsety + 6 + (count * 16), -1);
                    }
                    else {
                        fr.drawStringWithShadow("§7" + c.getName(), 75 + offsetx + 5, offsety + 6 + (count * 16), -1);

                    }
                    count++;
                }
            }


        }

    }


    public void onKey(InputEvent.KeyInputEvent e) {
        if (Keyboard.getEventKeyState()) {
            if (getState()) {
                if (!moving) {
                    if (!expanded) {
                        if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                            cat -= 1;
                            if (cat < 0) {
                                cat = 5;
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                            cat += 1;
                            if (cat > 5) {
                                cat = 0;
                            }

                        }
                    }

                    if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                        cat2 -= 1;
                        if (cat2 < 0) {
                            cat2 = ModuleManager.getModuleListPerCategory(selectedcat).size() - 1;
                        }
                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                        cat2 += 1;
                        if (cat2 > ModuleManager.getModuleListPerCategory(selectedcat).size() - 1) {
                            cat2 = 0;
                        }

                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                        expanded = true;
                        selectedcat = Category.values()[cat];
                        cat2 = 0;
                        if (ModuleManager.getModuleListPerCategory(selectedcat).size() <= 0) {
                            expanded = false;
                        }

                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                        expanded = false;
                        cat2 = cat;


                    }
                    if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
                        if (expanded) {
                            ModuleManager.getModuleListPerCategory(selectedcat).get(cat2).toggleModule();
                        }

                        if (!expanded) {
                            expanded = true;
                            selectedcat = Category.values()[cat];
                            cat2 = 0;
                            if (ModuleManager.getModuleListPerCategory(selectedcat).size() <= 0) {
                                expanded = false;
                            }
                        }
                    }
                }

            }
        }
    }
    public void onTextRender(RenderGameOverlayEvent.Post e) {
        GL11.glPushMatrix();
        GL11.glScalef(1.25f, 1.25f, 1.25f);
        mc.fontRendererObj.drawStringWithShadow(Main.Name, 5, 6, ColorUtil.getRainbow(-50));
        GL11.glScalef(.8f, .8f, .8f);
        GL11.glPopMatrix();
    }
    public void onTick() {
    }

}
