package dev.phastixtv.client.module;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum ModuleCategory {

    HUD(new ItemStack(Items.PAINTING)),
    SERVER(new ItemStack(Items.COMPASS)),
    MECHANIC(new ItemStack(Items.REDSTONE)),
    COSMETICS(new ItemStack(Items.DIAMOND_CHESTPLATE));

    @Getter
    private final ItemStack item;

    ModuleCategory(ItemStack item) {
        this.item = item;
    }
}
