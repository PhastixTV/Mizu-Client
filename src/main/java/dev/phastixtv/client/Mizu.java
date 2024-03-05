package dev.phastixtv.client;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.client.keybinds.KeyBindHandler;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mizu implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "mizuclient";
	public static final String version = "0.0.1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static EventBus eventBus = new EventBus();
	public static MinecraftClient minecraftClient = MinecraftClient.getInstance();
	public static final Identifier TILESCREEN_BACKGROUND = new Identifier("mizuclient:textures/gui/title/background-mizu.png");
	public static final Identifier TTILESCREEN_TITLE = new Identifier("mizuclient:textures/gui/title/mizu-logo-text-tp.png");


    @Override
	public void onInitialize() {
		KeyBindHandler.registerKeyBinds();
		LOGGER.info("Hello Fabric world!");
	}
}