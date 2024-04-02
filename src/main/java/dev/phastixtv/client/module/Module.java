package dev.phastixtv.client.module;

public class Module {

    private final String name;
    private final ModuleCategory category;
    private final boolean isEnabled;

    public Module(String name, ModuleCategory category, boolean isEnabled) {
        this.name = name;
        this.category = category;
        this.isEnabled = isEnabled;
    }
}
