package com.mithilank.module.settings;

import java.util.ArrayList;
import java.util.Set;

public class SettingEnum extends Setting{
    ArrayList<String> modes = new ArrayList<String>();
    public String mode = "";
    public SettingEnum(ArrayList<String> modes) {
        this.modes = modes;
        this.mode = modes.get(0);
    }

    public void increment() {
        if (!(this.modes.indexOf(mode) +1 >= this.modes.size())) {
            this.mode = this.modes.get(this.modes.indexOf(mode) +1);
        }
        else {
            mode = this.modes.get(0);
        }
    }
}
