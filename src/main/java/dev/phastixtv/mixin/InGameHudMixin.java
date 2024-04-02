package dev.phastixtv.mixin;

import dev.phastixtv.client.module.mods.hud.coordinate.CoordinateHud;
import dev.phastixtv.client.module.mods.hud.fps.FpsHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow public abstract void tick(boolean paused);

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        new CoordinateHud(context, tickDelta);
        new FpsHud(context, tickDelta);
    }
}
