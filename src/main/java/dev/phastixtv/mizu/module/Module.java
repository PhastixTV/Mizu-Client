package dev.phastixtv.mizu.module;

import com.google.common.eventbus.Subscribe;
import dev.phastixtv.mizu.Mizu;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Method;

public abstract class Module {

    public final Mizu client = Mizu.INSTANCE;
    public final MinecraftClient mc = client.getMc();
    public int width;
    public int height;

    @Getter
    @Setter
    private boolean enabled;

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Category category;

    @Getter
    @Setter
    private int posX = 10;
    @Getter
    @Setter
    private int posY = 10;

    @Getter
    @Setter
    private int key;

    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(info, "CONFUSED ANNOTATION EXCEPTION");

        this.name = info.name();
        this.description = info.description();
        this.category = info.category();

        init();
    }

    protected void init() {

    }

    public void enable() {
        enabled = true;
        onEnable();
    }

    public void disable() {
        enabled = false;
        onDisable();
    }

    public void onToggle() {

    }

    public void onEnable() {
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Mizu.BUS.register(this);
                break;
            }
        }
    }

    public void onDisable() {
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Mizu.BUS.unregister(this);
                break;
            }
        }
    }

    public void toggle() {
        onToggle();
        if (enabled) {
            enabled = false;
            onDisable();
        } else {
            enabled = true;
            onEnable();
        }
    }
}
