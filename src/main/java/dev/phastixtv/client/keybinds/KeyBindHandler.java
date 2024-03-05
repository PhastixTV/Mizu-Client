package dev.phastixtv.client.keybinds;

import dev.phastixtv.client.Mizu;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyBindHandler {
    public static final KeyBinding R_SHIFT_MENU = createKeybind(InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, Text.translatable("mizu.menu.open.close"), "Mizu Client");

    private static KeyBinding createKeybind(InputUtil.Type typeOfKeybinding, int keybind, MutableText keyName, String categoryName) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(keyName.toString(), typeOfKeybinding , keybind, categoryName));
    }

    private static KeyBinding createKeybind(InputUtil.Type typeOfKeybinding, int keybind, String keyName, String categoryName) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(keyName, typeOfKeybinding , keybind, categoryName));
    }

    public static void registerKeyBinds() {
        Mizu.LOGGER.info("Registering KeyBinds for " + Mizu.MOD_ID);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        while (R_SHIFT_MENU.wasPressed()) {
            Mizu.minecraftClient.player.sendMessage(Text.literal("TEST!"));
        }
        });
    }
}
