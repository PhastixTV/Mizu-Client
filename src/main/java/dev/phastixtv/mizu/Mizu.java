package dev.phastixtv.mizu;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.mizu.event.EventManager;
import dev.phastixtv.mizu.gui.tictactoe.TTT;
import dev.phastixtv.mizu.keys.KeyInputRegistry;
import dev.phastixtv.mizu.module.ModuleManager;
import dev.phastixtv.mizu.util.scheduler.ClientScheduler;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mizu implements ModInitializer {

    //Variablen
    @Getter
    @Setter
    private boolean needZoom;
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
    @Getter
    private Identifier X;
    @Getter
    private Identifier O;


    //Manager
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private EventManager eventManager;

    //Some Classes
    public static EventBus BUS;
    @Getter
    private MinecraftClient mc;
    @Getter
    private Logger logger;
    @Getter
    private TTT ttt;

    public static ClientScheduler SCHEDULER;

    public static Mizu INSTANCE;



    @Getter
    private String
            MOD_ID,
            clientName,
            version,
            authors;


    public void onInitialize() {
        mc = MinecraftClient.getInstance();
        INSTANCE = this;

        // Some information about the client
        MOD_ID = "mizu";
        clientName = "Mizu Client";
        version = "0.1";
        authors = "PhastixTV";

        logger = LoggerFactory.getLogger(MOD_ID);

        // load images
        TILESCREEN_BACKGROUND = new Identifier("mizu:textures/gui/title/background-mizu.png");
        TILESCREEN_TITLE = new Identifier("mizu:textures/gui/title/mizu-logo-text-tp.png");
        cape = new Identifier("mizu:textures/cosmetics/capes/poison/cape.png");
        X = new Identifier("mizu:textures/gui/tictactoe/x.png");
        O = new Identifier("mizu:textures/gui/tictactoe/o.png");

        // load classes
        BUS = new EventBus();
        BUS.register(this);
        SCHEDULER = new ClientScheduler(3);
        ttt = new TTT();

        // load managers
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        moduleManager.registerModule();
        KeyInputRegistry.register();

        // variables
        needZoom = false;
        standardZoomFactor = 45;
        zoomFactor = standardZoomFactor;
    }

    public void onClose() {
        BUS.unregister(this);
    }
}