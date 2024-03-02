package dev.phastixtv.client;

import com.google.common.eventbus.EventBus;
import dev.phastixtv.mixinterface.IMinecraftClient;
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
	public static IMinecraftClient iMinecraftClient;
	public static MinecraftClient minecraftClient;
	public static final Identifier TILESCREEN_BACKGROUND = new Identifier("mizuclient:textures/gui/title/background-mizu.png");
	public static final Identifier TTILESCREEN_TITLE = new Identifier("mizuclient:textures/gui/title/mizu-logo-text-tp.png");


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}