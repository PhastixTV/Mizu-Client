package dev.phastixtv.mizu.keys;

import dev.phastixtv.mizu.keys.impl.ModMenuKey;
import dev.phastixtv.mizu.keys.impl.ZoomKey;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class KeyInputRegistry {
    public static final String KEY_CATEGORY = "Mizu Client";
    public static List<Key> keys = new ArrayList<>();

    public static void register() {
        ZoomKey zoomKey = new ZoomKey();
        ModMenuKey modMenuKey = new ModMenuKey();
        registerKey(zoomKey, modMenuKey);
    }

    private static void registerKey(Key... keyArray) {
        keys.addAll(List.of(keyArray));
        for (Key key : keyArray) {
            KeyBinding binding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    key.getName(),
                    InputUtil.Type.KEYSYM,
                    key.getKey(),
                    KEY_CATEGORY
            ));

            registerKeyInput(binding, key);
        }
    }

    private static void registerKeyInput(KeyBinding binding, Key key) {
        final boolean[] wasPressed = {false};
        final boolean[] isClicked = {false};

        ClientTickEvents.END_CLIENT_TICK.register(tick -> {
            boolean isPressed = binding.isPressed();

            if (isPressed && !isClicked[0]) {
                key.pressAction();
                isClicked[0] = true;
                key.setPressed(true);
            }

            if (isPressed) {
                key.holdAction();
            } else if (wasPressed[0]) {
                key.releaseAction();
                isClicked[0] = false;
                key.setPressed(false);
            }

            wasPressed[0] = isPressed;
        });
    }
}
