package dev.phastixtv.mizu.module;

import dev.phastixtv.mizu.module.impl.hud.*;
import lombok.Getter;

import java.util.*;

@Getter
public class ModuleManager {

    private final List<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
    }

    public void registerModule() {
        //HUD
        modules.add(new FpsHud());
        modules.add(new ModMenu());
        modules.add(new Coordinate());
        modules.add(new Ping());
        modules.add(new TargetHud());

        //Cosmetics


        //Mechanic


        //Server

    }

    public void unregisterModule(Module... module) {
        for (Module mod: module) {
            modules.remove(mod.getClass());
        }
    }

    public ArrayList<Module> getEnabledMods() {
        ArrayList<Module> enabledMods = new ArrayList<Module>();

        for(Module m : modules) {
            if(m.isEnabled()) enabledMods.add(m);
        }

        /*
        if(mc.currentScreen instanceof ClickGuiScreen) {
            enabledMods.add(getModule(ClickGui.class));
        }
         */

        return enabledMods;
    }

    public Module getModule(Class<? extends Module> mod) {
        for (Module m : modules) {
            if (m.getClass().equals(mod)) {
                return m;
            }
        }

        return null;
    }

    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    public Module[] getModules(Category category) {
        return getModules().stream().filter(module -> module.getCategory() == category).toArray(Module[]::new);
    }
}
