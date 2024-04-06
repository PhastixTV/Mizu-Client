package dev.phastixtv.client.module;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum ModuleCategory {

    HUD(new ItemStack(Items.PAINTING), "Hud"),
    SERVER(new ItemStack(Items.COMPASS), "Server"),
    MECHANIC(new ItemStack(Items.REDSTONE), "Mechanic"),
    COSMETICS(new ItemStack(Items.DIAMOND_CHESTPLATE), "Cosmetics");

    @Getter
    private final ItemStack item;
    @Getter
    private final String displayName;

    ModuleCategory(ItemStack item, String displayName) {
        this.item = item;
        this.displayName = displayName;
    }
}
