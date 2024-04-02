package dev.phastixtv.client.module;

import dev.phastixtv.event.Event;
import lombok.Getter;

import java.util.ArrayList;

public class ModuleManager {

    @Getter
    private static ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
    }

    public void addModule(Module module) {
        modules.add(module);
    }
}
