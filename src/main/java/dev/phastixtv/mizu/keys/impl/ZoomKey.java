package dev.phastixtv.mizu.keys.impl;

import dev.phastixtv.mizu.Mizu;
import dev.phastixtv.mizu.keys.Key;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ZoomKey implements Key {

    boolean pressed = false;
    boolean transitioning = false;
    boolean smooth = true;
    int fovBeforeZoom;
    long startTime;
    long duration = 140;

    @Override
    public int getKey() {
        return GLFW.GLFW_KEY_C;
    }

    @Override
    public String getName() {
        return "Zoom";
    }

    @Override
    public void releaseAction() {
        if (!Mizu.INSTANCE.isNeedZoom() || transitioning) return;

        transitioning = true;
        startTime = System.currentTimeMillis();

        Mizu.SCHEDULER.runTask(new Runnable() {
            @Override
            public void run() {
                reverseSmoothTransition((int) Mizu.INSTANCE.getZoomFactor());
            }
        });
    }

    @Override
    public void pressAction() {
        if (Mizu.INSTANCE.isNeedZoom()) return;

        Mizu.INSTANCE.setZoomFactor(Mizu.INSTANCE.getStandardZoomFactor());

        fovBeforeZoom = Mizu.INSTANCE.getMc().options.getFov().getValue();

        startTime = System.currentTimeMillis();

        if (smooth) {
            Mizu.INSTANCE.getMc().options.smoothCameraEnabled = true;
        }

        Mizu.SCHEDULER.runTask(new Runnable() {
            @Override
            public void run() {
                smoothTransition((int) Mizu.INSTANCE.getZoomFactor());
                Mizu.INSTANCE.setNeedZoom(true);
            }
        });
    }

    private void smoothTransition(int zoomFactor) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime < duration) {
            float progress = (float) elapsedTime / duration;
            int currentFov = (int) (fovBeforeZoom + (zoomFactor - fovBeforeZoom) * progress);

            Mizu.INSTANCE.getMc().options.getFov().setValue(currentFov);
            Mizu.SCHEDULER.runTaskLater(new Runnable() {
                @Override
                public void run() {
                    smoothTransition(zoomFactor);
                }
            }, (long) 0.1);
        } else {
            Mizu.INSTANCE.getMc().options.getFov().setValue(zoomFactor);
            Mizu.INSTANCE.setNeedZoom(true);
        }
    }

    private void reverseSmoothTransition(int targetFov) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime < duration) {
            float progress = (float) elapsedTime / (float) duration;
            int currentFov = (int) (targetFov + (fovBeforeZoom - targetFov) * progress);

            Mizu.INSTANCE.getMc().options.getFov().setValue(currentFov);
            Mizu.SCHEDULER.runTaskLater(new Runnable() {
                @Override
                public void run() {
                    reverseSmoothTransition(targetFov);
                    Mizu.INSTANCE.setNeedZoom(false);
                }
            }, (long) 0.1);
        } else {
            Mizu.INSTANCE.getMc().options.getFov().setValue(fovBeforeZoom);
            Mizu.INSTANCE.setNeedZoom(false);
            Mizu.INSTANCE.getMc().options.smoothCameraEnabled = false;
            transitioning = false;
        }
    }

    @Override
    public void holdAction() {

    }

    @Override
    public void onScrollUp() {
        MinecraftClient mc = Mizu.INSTANCE.getMc();

        Mizu.INSTANCE.setZoomFactor(Mizu.INSTANCE.getZoomFactor() - 1);

        float minZoom = 10;
        if (Mizu.INSTANCE.getZoomFactor() < minZoom) {
            Mizu.INSTANCE.setZoomFactor(minZoom);
        }
        mc.options.getFov().setValue((int) Mizu.INSTANCE.getZoomFactor());
    }

    @Override
    public void onScrollDown() {
        MinecraftClient mc = Mizu.INSTANCE.getMc();
        Mizu.INSTANCE.setZoomFactor(Mizu.INSTANCE.getZoomFactor() + 1);

        if (Mizu.INSTANCE.getZoomFactor() > fovBeforeZoom - 16) {
            Mizu.INSTANCE.setZoomFactor(fovBeforeZoom - 16);
        }
        mc.options.getFov().setValue((int) Mizu.INSTANCE.getZoomFactor());
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
