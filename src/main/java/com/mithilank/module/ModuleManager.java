package com.mithilank.module;

import com.mithilank.module.modules.*;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class ModuleManager {
    public static ArrayList<Module> activeModules = new ArrayList<Module>();
    public ModuleManager() {


    }x
    public static ArrayList<Module> getModules() {
        return activeModules;
    }

    public static Module getModule(Class<? extends Module> clazz) {
        for (Module mod : getModules()) {
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }

    public static ArrayList<Module> getModuleListPerCategory(Category cat) {
        ArrayList<Module> catmodules = new ArrayList<Module>();
        for (Module m : activeModules) {
            if (m.getCategory() == cat) {
                catmodules.add(m);
            }
        }
        return catmodules;
    }

    public static void Setup() {
        MinecraftForge.EVENT_BUS.register(new ModuleListener());
        ModuleManager.activeModules.add(new com.mithilank.module.modules.ArrayList());
        ModuleManager.activeModules.add(new ToggleSprint());
        ModuleManager.activeModules.add(new FullBright());
        ModuleManager.activeModules.add(new TabGui());
        ModuleManager.activeModules.add(new KillAura());
        ModuleManager.activeModules.add(new AntiBot());
        ModuleManager.activeModules.add(new KeyStroke());
        ModuleManager.activeModules.add(new Fps());
        ModuleManager.activeModules.add(new Fly());
        ModuleManager.activeModules.add(new Cps());
        ModuleManager.activeModules.add(new SpeedGUI());
        ModuleManager.activeModules.add(new NoFall());
 //       ModuleManager.activeModules.add(new PlayerEsp());
        ModuleManager.activeModules.add(new EntityEsp());
 //       ModuleManager.activeModules.add(new MobEsp());
        ModuleManager.activeModules.add(new Hitbox());
        ModuleManager.activeModules.add(new Reach());
    }
}
