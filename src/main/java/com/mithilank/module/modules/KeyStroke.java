package com.mithilank.module.modules;

import com.mithilank.module.Category;
import com.mithilank.module.Module;
import com.mithilank.module.ModuleManager;
import com.mithilank.module.settings.SettingColor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class KeyStroke extends Module {
    public static final String name = "KeyStroke";
    public static final int bind = 500;
    public static final Category category = Category.GUI;
    public boolean moving = false;
    public boolean movingleft = false;
    public boolean movingright = false;
    public boolean movingup = false;
    public boolean movingdown = false;
    public boolean pause = false;

    /////Keys

    ////Color Keys
    int pressedcolor = new Color(230, 229, 209, 135).getRGB();
    int unpressedcolor = 0x70000000;
    public int W = 0x70000000;
    public int A = 0x70000000;
    public int S = 0x70000000;
    public int D = 0x70000000;
    public int SPACE = 0x70000000;
    public int LMB = unpressedcolor;
    public int RMB = pressedcolor;



    public KeyStroke() {
        super(name, bind, category);
        offsetx = 5 + 2;
        offsety = 130 + 2;

    }

    public void onRender() {
        //W
        Gui.drawRect(offsetx + 24, offsety, offsetx + 24 + 24,  offsety + 24, W);
        //A
        Gui.drawRect(offsetx -2, offsety +24+2, offsetx + 24 - 2,  offsety + 24 + 24 + 2, A);
        //S
        Gui.drawRect(offsetx + 24 , offsety +24+2, offsetx + 24 + 24,  offsety + 24 +2+ 24 , S);
        //D
        Gui.drawRect(offsetx + 24  + 24 + 2 , offsety +24+2, offsetx + 24 + 24  +24  + 2,  offsety + 24 + 24 + 2, D);
        //SPACE
        Gui.drawRect(offsetx -2, offsety +24+2 + 24 + 2, offsetx + 24 - 2 + 2 + 24 + 2 + 24,  offsety + 24 + 24 + 2 + 18 + 2, SPACE);
        ///LMB
        Gui.drawRect(offsetx -2, offsety + 24 + 24 + 2 + 18 + 2 + 2, offsetx + (24 - 2 + 2 + 24 + 2 + 24) / 2 - 2,  offsety + 24 + 24 + 2 + 18 + 2 + 2 + 24, LMB);
        ///RMB
        Gui.drawRect(offsetx + (24 - 2 + 2 + 24 + 2 + 24) / 2, offsety + 24 + 24 + 2 + 18 + 2 + 2, offsetx + (24 - 2 + 2 + 24 + 2 + 24),  offsety + 24 + 24 + 2 + 18 + 2 + 2 + 24, RMB);




        FontRenderer fr = mc.fontRendererObj;
        if (W == pressedcolor) {
            fr.drawStringWithShadow("W", offsetx + 24 + ((24 - fr.getStringWidth("W"))/2f), offsety + 9, Color.BLACK.getRGB());
        }
        else if (W == unpressedcolor) {
            fr.drawStringWithShadow("W", offsetx + 24 + ((24 - fr.getStringWidth("W"))/2f), offsety + 9, Color.WHITE.getRGB());
        }
        if (A == pressedcolor) {
            fr.drawStringWithShadow("A", offsetx + -2 + ((24 - fr.getStringWidth("A"))/2f), offsety + 24 + 2+9, Color.BLACK.getRGB());
        }
        else if (A == unpressedcolor) {
            fr.drawStringWithShadow("A", offsetx + -2 + ((24 - fr.getStringWidth("A"))/2f), offsety + 24 + 2+ 9, Color.WHITE.getRGB());
        }
        if (S == pressedcolor) {
            fr.drawStringWithShadow("S", offsetx + 24 + ((24 - fr.getStringWidth("S"))/2f), offsety + 24 + 2+9, Color.BLACK.getRGB());
        }
        else if (S == unpressedcolor) {
            fr.drawStringWithShadow("S", offsetx + 24 + ((24 - fr.getStringWidth("S"))/2f), offsety + 24 + 2+ 9, Color.WHITE.getRGB());
        }
        if (D == pressedcolor) {
            fr.drawStringWithShadow("D", offsetx + 24 +2+24 +((24 - fr.getStringWidth("D"))/2f), offsety + 24 + 2+9, Color.BLACK.getRGB());
        }
        else if (D == unpressedcolor) {
            fr.drawStringWithShadow("D", offsetx + 24 +2+24+ ((24 - fr.getStringWidth("D"))/2f), offsety + 24 + 2+ 9, Color.WHITE.getRGB());
        }
        if (SPACE == pressedcolor) {
            fr.drawStringWithShadow("-", offsetx -2+ (((3 * 24 + 4) - fr.getStringWidth("-"))/2f), offsety + 24+ 24 + 4+9, Color.BLACK.getRGB());
        }
        else if (SPACE == unpressedcolor) {
            fr.drawStringWithShadow("-", offsetx -2+(((3 * 24 + 4) - fr.getStringWidth("-"))/2f), offsety + 24 +24+ 4+ 9, Color.WHITE.getRGB());
        }
        if (LMB == pressedcolor) {
            fr.drawStringWithShadow("LMB", offsetx -2 + ((((24 - 2 + 2 + 24 + 2 + 24) / 2f) - fr.getStringWidth("LMB"))/2f), offsety + 24+ 24 + 4+9 + 24 - 4, Color.BLACK.getRGB());
        }
        else if (LMB == unpressedcolor) {
            fr.drawStringWithShadow("LMB", offsetx -2 + ((((24 - 2 + 2 + 24 + 2 + 24) / 2f) - fr.getStringWidth("LMB"))/2f), offsety + 24+ 24 + 4+9 + 24 - 4, Color.WHITE.getRGB());
        }
        if (RMB == pressedcolor) {
            fr.drawStringWithShadow("RMB", offsetx + ((24 - 2 + 2 + 24 + 2 + 24) / 2f) + ((((24 - 2 + 2 + 24 + 2 + 24) / 2f) - fr.getStringWidth("RMB"))/2f), offsety + 24+ 24 + 4+9 + 24 - 4, Color.BLACK.getRGB());
        }
        else if (RMB == unpressedcolor) {
            fr.drawStringWithShadow("RMB", offsetx + ((24 - 2 + 2 + 24 + 2 + 24) / 2f) + ((((24 - 2 + 2 + 24 + 2 + 24) / 2f) - fr.getStringWidth("RMB"))/2f), offsety + 24+ 24 + 4+9 + 24 - 4, Color.WHITE.getRGB());
        }


    }

    @Override
    public void onClick(InputEvent.MouseInputEvent e) {
        if (!moving) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    if (LMB == unpressedcolor) {
                        LMB = pressedcolor;
                    }
                }
                if (Mouse.getEventButton() == 1) {
                    if (RMB == unpressedcolor) {
                        RMB = pressedcolor;
                    }
                }
            }
            else {
                if (Mouse.getEventButton() == 0) {
                    if (LMB == pressedcolor) {
                        LMB = unpressedcolor;
                    }
                }
                if (Mouse.getEventButton() == 1) {
                    if (RMB == pressedcolor) {
                        RMB = unpressedcolor;
                    }
                }
            }
        }
    }

    public void onKey(InputEvent.KeyInputEvent e) {
        if (!moving) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                    if (W == unpressedcolor) {
                        W = pressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    if (S == unpressedcolor) {
                        S = pressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    if (A == unpressedcolor) {
                        A = pressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    if (D == unpressedcolor) {
                        D = pressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    if (SPACE == unpressedcolor) {
                        SPACE = pressedcolor;
                    }
                }
            }

            if (!Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                    if (W == pressedcolor) {
                        W = unpressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    if (S == pressedcolor) {
                        S = unpressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    if (A == pressedcolor) {
                        A = unpressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    if (D == pressedcolor) {

                        D = unpressedcolor;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    if (SPACE == pressedcolor) {

                        SPACE = unpressedcolor;
                    }
                }
            }
        }



        }

    public void onTick() {


    }


    void togglepressed(int key) {
        if (key == pressedcolor) {
            key = unpressedcolor;
        }

        if (key == unpressedcolor) {
            key = pressedcolor;
        }
    }
}
