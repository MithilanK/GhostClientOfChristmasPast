package com.mithilank.module.settings;

public class SettingBoolean extends Setting {

    public boolean enabled;

    public SettingBoolean(Boolean on) {
        enabled = on;
    }

    public SettingBoolean(String name) {
        this.name = name;
        enabled = false;
    }

    public void toggle() {
        enabled = !enabled;
    }
}
