package dev.phastixtv.mizu;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.mizu.event.EventManager;
import dev.phastixtv.mizu.gui.modmenu.ModMenuScreen;
import dev.phastixtv.mizu.module.ModuleManager;
import dev.phastixtv.mizu.module.impl.hud.ModMenu;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mizu implements ModInitializer {

    //Identifier
    @Getter
    private Identifier TILESCREEN_BACKGROUND;
    @Getter
    private Identifier TILESCREEN_TITLE;
    @Setter
    @Getter
    private Identifier skin;
    @Getter
    private Identifier cape;

    //Manager
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private EventManager eventManager;

    public static Mizu INSTANCE;

    //Keybindings
    public static KeyBinding modMenuKey = new KeyBinding("menu.rshift.modmenu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "Mizu Client");

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

        // load images
        TILESCREEN_BACKGROUND = new Identifier("mizu:textures/gui/title/background-mizu.png");
        TILESCREEN_TITLE = new Identifier("mizu:textures/gui/title/mizu-logo-text-tp.png");
        skin = new Identifier("textures/entity/player/wide/steve.png");
        cape = new Identifier("mizu:textures/cosmetics/capes/poison/cape.png");

        // load classes
        BUS = new EventBus();

        // load managers
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        moduleManager.registerModule();

        registerKey();
    }

    public void onClose() {

    }

    private void registerKey() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (modMenuKey.wasPressed()) {
                if (mc.currentScreen instanceof ModMenuScreen) return;
                mc.setScreen(new ModMenuScreen());
            }
        });
    }
}