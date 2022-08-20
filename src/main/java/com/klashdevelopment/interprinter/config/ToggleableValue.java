package com.klashdevelopment.interprinter.config;

public class ToggleableValue {

    public boolean enabled;
    public String[] values, defaultValues;

    public ToggleableValue(boolean defaultEnabled, String[] defaultValues) {
        this.enabled = defaultEnabled;
        this.values = defaultValues;
    }

    public void toggle() {
        enabled = !enabled;
    }
    public void toggleTo(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getValue() {
        return enabled ? values : defaultValues;
    }
    public void setValue(String[] value) {
        this.values = value;
    }

}
