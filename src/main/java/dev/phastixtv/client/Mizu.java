package dev.phastixtv.client;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.client.commands.CommandManager;
import dev.phastixtv.client.modules.overlay.fps.FpsConfig;
import dev.phastixtv.config.MizuConfig;
import dev.phastixtv.util.player.MessageManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mizu implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    private static Mizu instance;
    private MizuConfig CONFIG;
    private FpsConfig FPS_CONFIG;
    private String MOD_ID;
    private String version;
    private String author;
    private MessageManager messageManager;
    private CommandManager commandManager;
    private Logger logger;
    private Identifier TILESCREEN_BACKGROUND;
    private Identifier TILESCREEN_TITLE;
    private EventBus eventBus;
    private MinecraftClient minecraftClient;

    public static Mizu getInstance() {
        return instance;
    }

    @Override
    public void onInitialize() {
        instance = this;

        MOD_ID = "Mizu Client";
        version = "0.0.1";
        author = "PhastixTV";
        minecraftClient = MinecraftClient.getInstance();

        CONFIG = MizuConfig.createAndLoad();
        FPS_CONFIG = FpsConfig.createAndLoad();
        logger = LoggerFactory.getLogger(MOD_ID);
        TILESCREEN_BACKGROUND = new Identifier("mizuclient:textures/gui/title/background-mizu.png");
        TILESCREEN_TITLE = new Identifier("mizuclient:textures/gui/title/mizu-logo-text-tp.png");
        eventBus = new EventBus();
        messageManager = new MessageManager();
        commandManager = new CommandManager();

    }

    public String getAuthor() {
        return author;
    }

    public MizuConfig getCONFIG() {
        return CONFIG;
    }

    public FpsConfig getFpsConfig() {
        return FPS_CONFIG;
    }

    public String getModId() {
        return MOD_ID;
    }

    public String getVersion() {
        return version;
    }

    public Identifier getTilescreenBackground() {
        return TILESCREEN_BACKGROUND;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Identifier getTilescreenTitle() {
        return TILESCREEN_TITLE;
    }

    public Logger getLogger() {
        return logger;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public MinecraftClient getMinecraftClient() {
        return minecraftClient;
    }
}