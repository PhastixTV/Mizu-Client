package dev.phastixtv.client.commands;

import dev.phastixtv.client.Mizu;

//RICHTIGES EVENTSYSTEM FEHLT!!!

public abstract class CustomCommand {
    protected abstract void execute(String command, String[] args);

    public final void sendMessage(String message) {
        Mizu.getInstance().getMessageManager().sendMessageToPlayer(message);
    }
}
