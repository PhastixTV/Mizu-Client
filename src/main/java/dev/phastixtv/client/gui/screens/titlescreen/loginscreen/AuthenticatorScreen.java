package dev.phastixtv.client.gui.screens.titlescreen.loginscreen;

import dev.phastixtv.client.util.login.AuthenticatorManager;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.OwoUIAdapter;
import io.wispforest.owo.ui.core.ParentComponent;
import net.minecraft.client.session.Session;
import org.jetbrains.annotations.NotNull;

public class AuthenticatorScreen extends BaseOwoScreen {

    @Override
    protected @NotNull OwoUIAdapter createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(ParentComponent rootComponent) {

    }
}
