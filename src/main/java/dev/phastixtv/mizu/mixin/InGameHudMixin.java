package dev.phastixtv.mizu.mixin;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        DrawOverlayEvent event = new DrawOverlayEvent(context);
        Mizu.BUS.post(event);
        if (event.isCancelled()) ci.cancel();
    }
}
