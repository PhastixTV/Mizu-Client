package dev.phastixtv.util.player;

import com.mojang.brigadier.LiteralMessage;
import dev.phastixtv.client.Mizu;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MessageManager {

    public void sendMessageToPlayer(String message) {
        ClientPlayerEntity player = Mizu.getInstance().getMinecraftClient().player;
        Text confirmationText = Text.of(new LiteralMessage(message));
        player.sendMessage(confirmationText, false);
    }
}
