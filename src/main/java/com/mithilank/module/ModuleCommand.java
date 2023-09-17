package com.mithilank.module;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

public class ModuleCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "ytc";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/ytc";
    }
    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args) throws CommandException {
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("toggle")) {
                for (Module m : ModuleManager.activeModules) {
                    if (args[1].equalsIgnoreCase(m.getName())) {
                        System.out.println("works");
                        m.toggleModule();
                    }
                }
            }
        }
        if (args.length > 0) {
            for (Module m : ModuleManager.activeModules) {
                if (args[0].equalsIgnoreCase(m.getName())) {
                    System.out.println("Working");
                    if (args[1].equalsIgnoreCase("bind")) {
                        if (args.length > 3) {

                            m.setBind(Keyboard.getKeyIndex(args[3]));
                        }
                    }
                    else if (args[1].equalsIgnoreCase("unbind")){
                        m.setBind(500);
                    }
                    else {
                        m.onCommand(args);
                    }


                }
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
