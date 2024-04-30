package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.event.impl.OpenScreenEvent;
import dev.phastixtv.mizu.gui.modmenu.ModMenuScreen;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@ModuleInfo(
        name = "ClickGui",
        description = "I dont know",
        category = Category.HUD
)
public class ModMenu extends Module {

    @Subscribe
    public void onKey(OpenScreenEvent event) {
        event.setScreen(new ModMenuScreen());
    }

    @Override
    public void onToggle() {
        Mizu.INSTANCE.getLogger().info("IT worked");
        super.onToggle();
    }
}
