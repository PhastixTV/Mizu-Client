package dev.phastixtv.mizu.mixin;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.KeyPressEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKeyEvent(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (key != -1) {
            KeyPressEvent event = new KeyPressEvent(key,scancode,action);
            Mizu.BUS.post(event);
            if (event.isCancelled()) ci.cancel();
        }
    }
}
