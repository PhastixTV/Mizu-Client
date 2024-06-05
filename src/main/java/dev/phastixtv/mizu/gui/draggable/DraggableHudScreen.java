package dev.phastixtv.mizu.gui.draggable;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.module.Module;
import dev.phastixtv.mizu.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class DraggableHudScreen extends Screen {

    private Module selectedModule = null;
    private double offsetX, offsetY;
    private final ModuleManager moduleManager;
    private MinecraftClient mc = Mizu.INSTANCE.getMc();

    public DraggableHudScreen() {
        super(Text.literal("Draggable Hud"));
        this.moduleManager = Mizu.INSTANCE.getModuleManager();
    }

    public static void renderEntity(DrawContext context, int x1, int y1, int x2, int y2, int size, float f, LivingEntity entity) {
        float g = (float)(x1 + x2) / 2.0F;
        float h = (float)(y1 + y2) / 2.0F;
        context.enableScissor(x1, y1, x2, y2);
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(0.007453292F);
        quaternionf.mul(quaternionf2);
        float k = entity.bodyYaw;
        float l = entity.getYaw();
        float m = entity.getPitch();
        float n = entity.prevHeadYaw;
        float o = entity.headYaw;
        entity.bodyYaw = -30F;
        entity.setYaw(-30F);
        entity.setPitch(0.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        Vector3f vector3f = new Vector3f(0.0F, entity.getHeight() / 2.0F + f, 0.0F);
        renderEntity(context, g, h, size, vector3f, quaternionf, quaternionf2, entity);
        entity.bodyYaw = k;
        entity.setYaw(l);
        entity.setPitch(m);
        entity.prevHeadYaw = n;
        entity.headYaw = o;
        context.disableScissor();
    }

    public static void renderEntity(DrawContext context, float x, float y, int size, Vector3f vector3f, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, LivingEntity entity) {
        context.getMatrices().push();
        context.getMatrices().translate((double)x, (double)y, 50.0);
        context.getMatrices().multiplyPositionMatrix((new Matrix4f()).scaling((float)size, (float)size, (float)(-size)));
        context.getMatrices().translate(vector3f.x, vector3f.y, vector3f.z);
        context.getMatrices().multiply(quaternionf);
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            quaternionf2.conjugate();
            entityRenderDispatcher.setRotation(quaternionf2);
        }

        entityRenderDispatcher.setRenderShadows(true);
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880);
        });
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        renderEntity(context, 100, 100, 190, 190, 40, 0.0625F, this.client.player);
        // Draw all modules
        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                context.drawText(mc.textRenderer, module.getName(), module.getPosX(), module.getPosY(), 0xFFFFFF, true);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            for (Module module : moduleManager.getModules()) {
                if (module.isEnabled()) {
                    int modX = module.getPosX();
                    int modY = module.getPosY();
                    int modWidth = mc.textRenderer.getWidth(module.getName());
                    int modHeight = mc.textRenderer.fontHeight;

                    if (mouseX >= modX && mouseX <= modX + modWidth &&
                            mouseY >= modY && mouseY <= modY + modHeight) {
                        selectedModule = module;
                        offsetX = mouseX - modX;
                        offsetY = mouseY - modY;
                        return true;
                    }
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            selectedModule = null;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (selectedModule != null && button == GLFW.GLFW_MOUSE_BUTTON_1) {
            selectedModule.setPosX((int) (mouseX - offsetX));
            selectedModule.setPosY((int) (mouseY - offsetY));
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}