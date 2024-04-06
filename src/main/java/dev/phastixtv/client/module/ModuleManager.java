package dev.phastixtv.client.module;

import dev.phastixtv.client.module.mods.hud.fps.FpsHud;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static dev.phastixtv.client.module.ModuleCategory.*;

public class ModuleManager{

    @Getter
    private static ArrayList<Module> moduleList = new ArrayList<>();

    public ModuleManager() {
        init();
    }

    public void init() {
        // Hud


        // Server


        // Mechanic


        // Cosmetics
    }

    public Module loadModules() {
        for (Module module : moduleList) {
            return module;
        }
        return null;
    }

    public List<Module> loadModulesFromCategory(ModuleCategory category) {
        List<Module> filteredModule = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getCategory() == category) {
                filteredModule.add(module);
            }
        }
        return filteredModule;
    }

    public Module getModule(Class<? extends Module> classModule) {
        for (Module module : moduleList) {
            if (module.getClass().equals(classModule)) {
                return module;
            }
        }
        return null;
    }

    public String getModuleByName(String name) {
        for (Module module : moduleList) {
            if (name.equalsIgnoreCase(module.getName())) {
                return module.getName();
            }
        }
        return moduleList.toString();
    }
}
