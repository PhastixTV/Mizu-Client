package dev.phastixtv.mizu.module.impl.hud;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.event.impl.DrawOverlayEvent;
import dev.phastixtv.mizu.module.Category;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleInfo;
import dev.phastixtv.mizu.util.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;

@ModuleInfo(
        name = "TargetHud",
        description = "It shows information about another player, that you are watching at",
        category = Category.HUD
)
public class TargetHud extends Module {

    @Subscribe
    public void onDrawOverlay(DrawOverlayEvent event) {
        DrawContext context = event.getContext();



        //VRenderUtil.drawText(context, "");
    }
}
