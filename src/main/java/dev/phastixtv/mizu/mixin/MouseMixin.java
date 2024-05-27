package dev.phastixtv.mizu.mixin;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.ScrollDownEvent;
import dev.phastixtv.mizu.event.impl.ScrollUpEvent;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    public void onScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (Mizu.INSTANCE.getNeedZoom()) {
            ci.cancel();

            if (vertical > 0) {
                new ScrollUpEvent().call();
            } else {
                new ScrollDownEvent().call();
            }
        }
    }
}
