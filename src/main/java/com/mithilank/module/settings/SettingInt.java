package com.mithilank.module.settings;

public class SettingInt extends Setting{

    public double value, minimum, maximum, increment;

    public SettingInt(double value, double minimum, double maximum, String name) {
        this.name = name;
        if (value > maximum) {
            this.value = maximum;
        }
        else if (value < minimum) {
            this.value = minimum;
        }
        else {
            this.value = value;
        }
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = (maximum - minimum) / 10;
        this.value = increment*(Math.round(this.value/increment));
    }

    public void up() {
        value += increment;
        if (value > maximum) {
            value = maximum;
        }
    }

    public void down() {
        value -= increment;
        if (value < minimum) {
            value = minimum;
        }
    }

    public void increment() {
        value += increment;
        if (value > maximum) {
            value = minimum;
        }
    }




}
