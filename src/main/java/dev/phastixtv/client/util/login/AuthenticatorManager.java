package dev.phastixtv.client.util.login;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.gui.screens.titlescreen.TitleScreen;
import dev.phastixtv.client.gui.screens.titlescreen.loginscreen.LoginScreen;
import dev.phastixtv.mixin.MinecraftClientAccessor;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import lombok.Getter;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.session.Session;

import java.util.Optional;
import java.util.UUID;


public class AuthenticatorManager {
    private final LoginScreen loginScreen;
    private String email;
    private String password;
    @Getter
    private boolean isAuthentificated;

    public AuthenticatorManager(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }

    public void login(Screen last) {
        email = loginScreen.getEmailField().getText();
        password = loginScreen.getEmailField().getText();
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        MicrosoftAuthResult result = null;
        try {
            result = authenticator.loginWithCredentials(email, password);
            Mizu.minecraftClient.setScreen(last);
            isAuthentificated = true;
        } catch (Exception e) {
            loginScreen.getEmailField().setText("");
            isAuthentificated = false;
            return;
        }
        assert result !=null;

        String uuidString = result.getProfile().getId();
        long msb = Long.parseUnsignedLong(uuidString, 0, 16, 16);
        long lsb = Long.parseUnsignedLong(uuidString, 16, 32, 16);
        UUID uuid = new UUID(msb, lsb);

        Session session = new Session(
                result.getProfile().getName(),
                uuid,
                result.getAccessToken(),
                Optional.empty(),
                Optional.empty(),
                Session.AccountType.LEGACY
        );
        ((MinecraftClientAccessor) Mizu.minecraftClient).setSession(session);

    }

    public boolean checkAuthentification() {
        if (isAuthentificated) {
            return true;
        } else {
            Mizu.LOGGER.info("TESSSSSSSSSSSTTTT");
            return false;
        }
    }


}
