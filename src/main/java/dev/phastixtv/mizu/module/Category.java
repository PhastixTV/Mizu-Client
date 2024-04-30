package dev.phastixtv.mizu.module;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum Category {

    HUD(new ItemStack(Items.PAINTING), "Hud"),
    SERVER(new ItemStack(Items.COMPASS), "Server"),
    MECHANIC(new ItemStack(Items.REDSTONE), "Mechanic"),
    COSMETICS(new ItemStack(Items.DIAMOND_CHESTPLATE), "Cosmetics");

    @Getter
    private final ItemStack item;
    @Getter
    private final String name;

    Category(ItemStack item, String name) {
        this.item = item;
        this.name = name;
    }
}
