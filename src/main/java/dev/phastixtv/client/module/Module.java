package dev.phastixtv.client.module;

import dev.phastixtv.client.Mizu;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

@Getter
public class Module {

    private final String name;
    private final ModuleCategory category;
    private final boolean toggled;
    public MinecraftClient client = Mizu.getInstance().getMinecraftClient();

    public Module(String name, ModuleCategory category, boolean toggled) {
        this.name = name;
        this.category = category;
        this.toggled = toggled;
    }
}
