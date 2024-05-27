package dev.phastixtv.mizu;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.mizu.event.EventManager;
import dev.phastixtv.mizu.gui.modmenu.ModMenuScreen;
import dev.phastixtv.mizu.keybind.KeybindHandler;
import dev.phastixtv.mizu.keys.KeyInputRegistry;
import dev.phastixtv.mizu.module.ModuleManager;
import dev.phastixtv.mizu.util.scheduler.ClientScheduler;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mizu implements ModInitializer {

    //Variablen
    @Getter
    @Setter
    private Boolean needZoom;
    @Setter
    @Getter
    private float zoomFactor;
    @Getter
    @Setter
    private float standardZoomFactor;

    //Identifier
    @Getter
    private Identifier TILESCREEN_BACKGROUND;
    @Getter
    private Identifier TILESCREEN_TITLE;
    @Setter
    @Getter
    private Identifier cape;

    //Manager
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private EventManager eventManager;
    @Getter
    private KeybindHandler keybindHandler;

    public static ClientScheduler SCHEDULER;

    public static Mizu INSTANCE;

    @Getter
    private String
            MOD_ID,
            clientName,
            version,
            authors;

    public static EventBus BUS;
    @Getter
    private MinecraftClient mc;
    @Getter
    private Logger logger;

    public void onInitialize() {
        mc = MinecraftClient.getInstance();
        INSTANCE = this;

        // Some information about the client
        MOD_ID = "mizu";
        clientName = "Mizu Client";
        version = "0.1";
        authors = "PhastixTV";

        logger = LoggerFactory.getLogger(MOD_ID);

        // variables
        needZoom = false;
        standardZoomFactor = 45;
        zoomFactor = standardZoomFactor;

        // load images
        TILESCREEN_BACKGROUND = new Identifier("mizu:textures/gui/title/background-mizu.png");
        TILESCREEN_TITLE = new Identifier("mizu:textures/gui/title/mizu-logo-text-tp.png");
        cape = new Identifier("mizu:textures/cosmetics/capes/poison/cape.png");

        // load classes
        BUS = new EventBus();
        BUS.register(this);
        SCHEDULER = new ClientScheduler(3);

        // load managers
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        moduleManager.registerModule();
        keybindHandler = new KeybindHandler();
        keybindHandler.registerKeyBinds();
        KeyInputRegistry.register();


    }

    public void onClose() {
        BUS.unregister(this);
    }
}