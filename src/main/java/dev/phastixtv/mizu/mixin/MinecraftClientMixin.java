package dev.phastixtv.mizu.mixin;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.OpenScreenEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow public abstract Window getWindow();

    @Inject(at = @At("HEAD"), method = "setScreen", cancellable = true)
    public void openScreen(Screen screen, CallbackInfo info) {
        OpenScreenEvent event = new OpenScreenEvent(screen);
        Mizu.BUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "close", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/session/telemetry/TelemetryManager;close()V"))
    public void closeScreen(CallbackInfo info) {
        Mizu.INSTANCE.onClose();
    }
}
