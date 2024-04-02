package dev.phastixtv.client.module;

import dev.phastixtv.client.Mizu;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;

@Getter
public class Module {
    private final String name;
    private final ModuleCategory category;
    private final boolean isEnabled;
    public MinecraftClient client = Mizu.getInstance().getMinecraftClient();

    public Module(String name, ModuleCategory category, boolean isEnabled) {
        this.name = name;
        this.category = category;
        this.isEnabled = isEnabled;
    }
}
