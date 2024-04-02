package dev.phastixtv.client;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.client.commands.CommandManager;
import dev.phastixtv.client.modules.overlay.coordinates.CoordinateConfig;
import dev.phastixtv.client.modules.overlay.fps.FpsConfig;
import dev.phastixtv.client.modules.overlay.fps.FpsOverlay;
import dev.phastixtv.config.MizuConfig;
import dev.phastixtv.util.player.MessageManager;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Mizu implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    private static Mizu instance;
    private MizuConfig CONFIG;
    private FpsConfig FPS_CONFIG;
    private CoordinateConfig COORDINATE_CONFIG;
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
    @Setter
    private Identifier skin;

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
        COORDINATE_CONFIG = CoordinateConfig.createAndLoad();
        logger = LoggerFactory.getLogger(MOD_ID);
        TILESCREEN_BACKGROUND = new Identifier("mizuclient:textures/gui/title/background-mizu.png");
        TILESCREEN_TITLE = new Identifier("mizuclient:textures/gui/title/mizu-logo-text-tp.png");
        eventBus = new EventBus();
        messageManager = new MessageManager();
        commandManager = new CommandManager();
        skin = new Identifier("textures/entity/player/wide/steve.png");
    }
}