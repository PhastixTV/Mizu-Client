package dev.phastixtv.event.impl;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.event.Event;

import java.util.Arrays;

//RICHTIGES EVENTSYSTEM FEHLT!!!

public class ClientCommandListener extends Event {

    private final String command_prefix = "!";

    public void handleChatEvent(String eventMessage) {
        String message = replaceSpaceAtStringStart(eventMessage);

        if(message.startsWith(command_prefix)) {
            Mizu.getInstance().getMinecraftClient().getChatRestriction().allowsChat(false);
            message = message.replace(command_prefix, "");

            String[] args = message.split(" ");
            String command = args[0];
            args = Arrays.stream(args).skip(1).toArray(String[]::new);
            Mizu.getInstance().getCommandManager().performCommand(command, args);
        }
    }

    private String replaceSpaceAtStringStart(String s) {
        while (s.startsWith(" ")) s = s.substring(1);
        return s;
    }
}
