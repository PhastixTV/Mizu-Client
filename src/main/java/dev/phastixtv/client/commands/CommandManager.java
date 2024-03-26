package dev.phastixtv.client.commands;

import dev.phastixtv.client.Mizu;

import java.util.concurrent.ConcurrentHashMap;

//RICHTIGES EVENTSYSTEM FEHLT!!!

public class CommandManager {

    private final ConcurrentHashMap<String, CustomCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
    }

    public void performCommand(String command, String[] args) {
        if(!command.contains(command.toLowerCase().trim())) {
            Mizu.getInstance().getMessageManager().sendMessageToPlayer("§7Dieser Command existiert §cnicht§7.");
            return;
        }

        commands.get(command.toLowerCase().trim()).execute(command, args);
    }

    public boolean register(String name, CustomCommand customCommand) {
        return commands.putIfAbsent(name, customCommand) == null;
    }

    public boolean unregister(String name) {
        return commands.remove(name) != null;
    }
}
