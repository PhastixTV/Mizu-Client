package dev.phastixtv.event.impl;

import com.mojang.authlib.exceptions.MinecraftClientHttpException;
import dev.phastixtv.client.Mizu;
import dev.phastixtv.event.Event;
import lombok.Getter;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class ChatEvent extends Event {

    @Getter
    private static String lastMessage = null;

    public ChatEvent() {
        ClientReceiveMessageEvents.CHAT.register((client, sender, message, messageId, messageSenderIsSelf) -> {
            lastMessage = message.toString();
        });
    }
}
