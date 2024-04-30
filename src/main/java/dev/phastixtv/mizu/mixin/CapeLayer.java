package dev.phastixtv.mizu.mixin;

import dev.phastixtv.mizu.Mizu;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractClientPlayerEntity.class)
public class CapeLayer {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/PlayerListEntry;getSkinTextures()Lnet/minecraft/client/util/SkinTextures;"), method = "getSkinTextures")
    public SkinTextures getSkinTextures(PlayerListEntry entry) {
        SkinTextures skinTextures = entry.getSkinTextures();

        Identifier cape = Mizu.INSTANCE.getCape();

        return new SkinTextures(skinTextures.texture(), skinTextures.textureUrl(), cape, cape, skinTextures.model(), skinTextures.secure());
    }
}
