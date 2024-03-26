package dev.phastixtv.event.impl;

import com.mojang.authlib.exceptions.MinecraftClientHttpException;
import dev.phastixtv.client.Mizu;
import dev.phastixtv.event.Event;
import lombok.Getter;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class ChatEvent extends Event {

    @Getter
    private static String lastMessage = null;

    public String getPlayerMessage() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                Text[] messages = client.inGameHud.getChatHud().getMessageHistory().toArray(new Text[0]);
                if (messages.length > 0) {
                    lastMessage = messages[messages.length - 1].getString();
                }
            }
        });

        return null;
    }
}
