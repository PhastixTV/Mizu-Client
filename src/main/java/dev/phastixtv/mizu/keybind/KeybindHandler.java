package dev.phastixtv.mizu.keybind;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.gui.modmenu.ModMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {

    public static final KeyBinding R_SHIFT_MENU = createKeybind(InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "Open/Close Menu", "Mizu Client");

    private static KeyBinding createKeybind(InputUtil.Type typeOfKeybinding, int keybind, String keyName, String categoryName) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(keyName, typeOfKeybinding , keybind, categoryName));
    }

    public void registerKeyBinds() {

        Mizu.INSTANCE.getLogger().info("Registering KeyBinds for " + Mizu.INSTANCE.getMOD_ID());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (R_SHIFT_MENU.wasPressed()) {
                client.setScreen(new ModMenuScreen());
            }
        });
    }
}
