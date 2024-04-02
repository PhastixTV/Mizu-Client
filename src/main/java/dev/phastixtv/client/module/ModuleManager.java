package dev.phastixtv.client.module;

import dev.phastixtv.client.module.mods.hud.coordinate.CoordinateHud;
import dev.phastixtv.client.module.mods.hud.fps.FpsHud;
import dev.phastixtv.client.module.mods.hud.ping.PingHud;
import dev.phastixtv.event.Event;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;

public class ModuleManager {

    @Getter
    private static ArrayList<Module> moduleList;

    public ModuleManager() {
        moduleList = new ArrayList<>();
    }
/*
    public void init() {
        // HUD
        moduleList.add(new FpsHud());
        moduleList.add(new CoordinateHud());
        moduleList.add(new PingHud());
    }

 */

    public Module loadModules() {
        for (Module module : moduleList) {
            return module;
        }
        return null;
    }

    public String loadModulesName() {
        StringBuilder namesBuilder = new StringBuilder();
        for (Module module : moduleList) {
            namesBuilder.append(module.getName()).append(", ");
        }
        return namesBuilder.append("nix funktioniert").toString();
    }

    public Module getModule(Class<? extends Module> classModule) {
        for (Module module : moduleList) {
            if (module.getClass().equals(classModule)) {
                return module;
            }
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module module : moduleList) {
            if (name.equalsIgnoreCase(module.getName())) {
                return module;
            }
        }
        return null;
    }
}
